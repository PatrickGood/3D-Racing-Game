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

public class LoadCatModel2{
		//Models
			private Build my_cat;
			
			
			
			private int mtlTexID[]  = new int[100]; 
			private String[] mtlNameList = new String[100];
			private int list_size=0;
			

			public LoadCatModel2()
			{
				my_cat = new Build();
				try {
				    new Parse( my_cat, "models/cat/cat.obj" );
				} catch ( Exception e ) {
				    e.printStackTrace();
				}
				
		
			
		
			}
			void drawCatModel(GL2 gl, float WRot, float CatH, float CatS)
			{
				//gl.glEnable(GL.GL_BLEND);
				//gl.glEnable(GL2.GL_COLOR_MATERIAL); 
				gl.glPushMatrix();
				gl.glEnable(GL.GL_TEXTURE_2D);
				gl.glTranslatef(0.0f, 0.0f, CatH);
				gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
				gl.glScalef(CatS,CatS,CatS );
				//Add Body
				gl.glPushMatrix();
				drawCatBodyModel(gl);
				gl.glPopMatrix();
				
	

				gl.glDisable(GL.GL_TEXTURE_2D);
				gl.glPopMatrix();
				//gl.glDisable(GL.GL_BLEND);
				//gl.glDisable(GL2.GL_COLOR_MATERIAL);
				
				
			}
			
			
			
			void drawCatBodyModel( GL2 gl ) {
			    gl.glBindTexture( GL2.GL_TEXTURE_2D, 0 );
			    for ( int i = 0; i < my_cat.faces.size(); ++i ) {
			        ArrayList<FaceVertex> vertices = my_cat.faces.get( i ).vertices;
			        // TODO: check and bind texture for this face here
			        String currentMtlName = my_cat.faces.get( i ).material.name;
			        int foundMtlIndex=list_size;
			        for(int iname=0; iname < list_size; iname++) {
			        	if(currentMtlName == mtlNameList[iname]){
			        		foundMtlIndex=iname;
			        		break;
			        	}
			        }
			        if(foundMtlIndex==list_size) {
			        	mtlNameList[foundMtlIndex]=currentMtlName;
			        	list_size++;
			        	
			        	try {
			        		String filename=my_cat.faces.get( i ).material.mapKdFilename;
			        		if(filename == null)
			        		{
			        			filename = "Map__8_Raytrace.bmp";
			        		}
			        		System.out.printf("%d, %s\n", list_size, filename);
			        	BufferedImage mtlImage = ImageIO.read(new File("models/cat/"+filename));
						//URL url = new URL("http:\\")
						ByteBuffer buf = convertImageData(mtlImage);
						int[] tmpTex = new int[1];
						gl.glGenTextures(1, tmpTex, 0);
						mtlTexID[foundMtlIndex] = tmpTex[0];
						gl.glBindTexture(GL.GL_TEXTURE_2D, mtlTexID[foundMtlIndex]);
						
						gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
				        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

						gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, mtlImage.getWidth(), 
								mtlImage.getHeight(), 0, GL2.GL_RGB, GL.GL_UNSIGNED_BYTE, buf);
						
						gl.glEnable(GL.GL_TEXTURE_2D);
						
						gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
						gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
			        	} catch (IOException e) {
			    			// TODO Auto-generated catch block
			    			e.printStackTrace();
			    		}
			        	
			        }
			        else {
			        	gl.glBindTexture(GL.GL_TEXTURE_2D, mtlTexID[foundMtlIndex]);
			        }
			        gl.glDisable(gl.GL_BLEND);
			        //gl.glEnable(GL2.GL_COLOR_MATERIAL);
			        float materialColor[] = {1.0f, 0.3f, 0.0f, 1.0f};
			         //The specular (shiny) component of the material
			         float  materialSpecular[] = {1,0.2f,0,0};
			         //The color emitted by the material
			         float materialEmission[] = {0.05f,0.01f,0, 0.0f};

			         float shininess = 28;
			         gl.glDisable(GL2.GL_LIGHTING);
			         gl.glDisable(GL2.GL_COLOR_MATERIAL);
			         gl.glDisable(GL2.GL_BLEND);
			         gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f );
			         gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, materialColor, 0);
			         gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materialSpecular, 0);
			         gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, materialEmission, 0);
			         gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
			        gl.glBegin( GL2.GL_TRIANGLES );
			        gl.glBegin( GL2.GL_TRIANGLES );
			        for ( int j = 0; j < vertices.size(); ++j ) {
			            FaceVertex vertex = vertices.get( j );
			            // TODO: add texture coordinates here
			            gl.glTexCoord2f(vertex.t.u, 1-vertex.t.v);
			            gl.glVertex3f( vertex.v.x, vertex.v.y, vertex.v.z );
			        }
			        gl.glEnd();
			    }
			  
			}
		

			
			
			
			
			
			
			
			 private ByteBuffer convertImageData(BufferedImage bufferedImage) {
		 	        ByteBuffer imageBuffer;
		 	        DataBuffer buf = bufferedImage.getRaster().getDataBuffer(); 
		 	        final byte[] data = ((DataBufferByte) buf).getData();	        
		 	        return (ByteBuffer.wrap(data)); 
		 	    }

		
		
		
		
		
		
	}



