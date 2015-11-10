package cs335;
import com.owens.oobjloader.builder.*;
import com.owens.oobjloader.parser.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import java.nio.DoubleBuffer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.awt.image.DataBufferByte; 










import java.nio.ByteOrder;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.media.opengl.*;

import com.jogamp.opengl.util.awt.TextRenderer;

public class LoadBuildingModel {
	//Models
	private Build my_building;
	
	
	
	private int materialTexID[]  = new int[100]; 
	private String[] materialNameList = new String[100];
	private int list_size=0;
	
	

	public LoadBuildingModel()
	{
		my_building = new Build();
		try {
		    new Parse( my_building, "models/building/building.obj" );
		} catch ( Exception e ) {
		    e.printStackTrace();
		}
		

	

	}
	void drawbuildingModel(GL2 gl, float WRot, float buildingH, float buildingS)
	{
 
		gl.glPushMatrix();
		gl.glEnable(GL.GL_TEXTURE_2D);
		gl.glTranslatef(0.0f, 0.0f, buildingH);
		gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
		gl.glScalef(buildingS,buildingS,buildingS );
		//Add Body
		gl.glPushMatrix();
		drawbuildingBodyModel(gl);
		gl.glPopMatrix();
		gl.glDisable(GL.GL_TEXTURE_2D);
		gl.glPopMatrix();

		
		
	}
	
	
	
	void drawbuildingBodyModel( GL2 gl ) {
	    //gl.glBindTexture( GL2.GL_TEXTURE_2D, 0 );
    
	    for ( int i = 0; i < my_building.faces.size(); ++i ) {
	        ArrayList<FaceVertex> vertices = my_building.faces.get( i ).vertices;
	        // TODO: check and bind texture for this face here
	        String currentmaterialName = my_building.faces.get( i ).material.name;
	        int foundmaterialIndex=list_size;
	        for(int iname=0; iname < list_size; iname++) {
	        	if(currentmaterialName == materialNameList[iname]){
	        		foundmaterialIndex=iname;
	        		break;
	        	}
	        }
	        if(foundmaterialIndex==list_size) {
	        	materialNameList[foundmaterialIndex]=currentmaterialName;
	        	list_size++;
	        	
	        	try {
	        		String filename=my_building.faces.get( i ).material.mapKdFilename;
	        		if(filename == null)
	        		{
	        			filename = "Map__8_Raytrace.bmp";
	        		}
	        		//System.out.printf("%d, %s\n", list_size, filename);
	        	BufferedImage materialImage = ImageIO.read(new File("models/building/"+filename));
				//URL url = new URL("http:\\")
				ByteBuffer buf = convertImageData(materialImage);
				int[] tmpTex = new int[1];
				gl.glGenTextures(1, tmpTex, 0);
				materialTexID[foundmaterialIndex] = tmpTex[0];
				gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID[foundmaterialIndex]);
				
				gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
		        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

				gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, materialImage.getWidth(), 
						materialImage.getHeight(), 0, GL2.GL_BGR, GL.GL_UNSIGNED_BYTE, buf);
				
				//gl.glEnable(GL.GL_TEXTURE_2D);
				
				gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
				gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
	        	} catch (IOException e) {
	    			
	    			e.printStackTrace();
	    		}
	        	
	        }
	        else {
	        	gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID[foundmaterialIndex]);
	        }
	        gl.glDisable(gl.GL_BLEND);
	        gl.glDisable(GL2.GL_COLOR_MATERIAL);
	        
	        float materialColor[] = {1.0f, 0.3f, 0.0f, 1.0f};
	         //The specular (shiny) component of the material
	         float  materialSpecular[] = {1,0.2f,0,0};
	         //The color emitted by the material
	         float materialEmission[] = {0.05f,0.25f,0, 0.0f};

	         float shininess = 28;

	         //gl.gldddEnable(GL2.GL_COLOR_MATERIAL);
	         gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, materialColor, 0);
	         gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materialSpecular, 0);
	         gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, materialEmission, 0);
	         gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
	        gl.glBegin( GL2.GL_TRIANGLES );
	        for ( int j = 0; j < vertices.size(); ++j ) {
	            FaceVertex vertex = vertices.get( j );
	            
	            gl.glTexCoord2f(vertex.t.u, 1-vertex.t.v);
	            gl.glVertex3f( vertex.v.x, vertex.v.y, vertex.v.z );
	        }
	        gl.glEnd();
	        
	    }
	    
	    //gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
	}


	
	
	
	
	
	
	
	 private ByteBuffer convertImageData(BufferedImage bufferedImage) {
 	        ByteBuffer imageBuffer;
 	        DataBuffer buf = bufferedImage.getRaster().getDataBuffer(); 
 	        final byte[] data = ((DataBufferByte) buf).getData();	        
 	        return (ByteBuffer.wrap(data)); 
 	    }







}
