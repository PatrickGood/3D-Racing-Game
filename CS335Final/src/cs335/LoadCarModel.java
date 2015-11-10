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

public class LoadCarModel {

	//Models
		private Build my_model;
		private Build my_model_wheel_frontL;
		private Build my_model_wheel_frontR;
		private Build my_model_wheel_backL;
		private Build my_model_wheel_backR;
		
		float rotFW = 0;
		//Body
		int materialTexID[]  = new int[100]; 
		String[] materialNameList = new String[100];
		int list_size=0;
		//FrontWheelsL
		int materialTexID1[]  = new int[100]; 
		String[] materialNameList1 = new String[100];
		int list_size1=0;
		//FrontWheelsR
				int materialTexID2[]  = new int[100]; 
				String[] materialNameList2 = new String[100];
				int list_size2=0;
		
		//BackWheelsL
		int materialTexID3[]  = new int[100]; 
		String[] materialNameList3 = new String[100];
		int list_size3=0;
		//BackWheelsR
		int materialTexID4[]  = new int[100]; 
		String[] materialNameList4 = new String[100];
		int list_size4=0;
		
	    float materialAmbient2[] = {1.0f, 0.0f, 0.0f};
		float materialDiffuse2[] ={1.0f,0.0f,0.0f};
		float  materialSpecular2[] = {1.0f,0.0f,0.0f};
		float shininess = 90;
		public LoadCarModel()
		{
			my_model = new Build();
			try {
			    new Parse( my_model, "models/PorCar/Body/body.obj" );
			} catch ( Exception e ) {
			    e.printStackTrace();
			}
			
			my_model_wheel_frontL = new Build();
			try {
			    new Parse( my_model_wheel_frontL, "models/PorCar/FrontWL/untitled.obj" );
			} catch ( Exception e ) {
			    e.printStackTrace();
			}
			my_model_wheel_frontR = new Build();
			try {
			    new Parse( my_model_wheel_frontR, "models/PorCar/FrontWR/FrontRightWheel.obj" );
			} catch ( Exception e ) {
			    e.printStackTrace();
			}			
			my_model_wheel_backL = new Build();
			try {
			    new Parse( my_model_wheel_backL, "models/PorCar/BackWL/BackWheel.obj" );
			} catch ( Exception e ) {
			    e.printStackTrace();
			}
			my_model_wheel_backR = new Build();
			try {
			    new Parse( my_model_wheel_backR, "models/PorCar/BackWR/BackRightWheel.obj" );
			} catch ( Exception e ) {
			    e.printStackTrace();
			}
		
	
		}
		void drawCarModel(GL2 gl, float WRot, float CarH, float CarS)
		{
			gl.glEnable(GL.GL_BLEND);
			gl.glEnable(GL2.GL_COLOR_MATERIAL);
			rotFW = WRot ; 
			gl.glPushMatrix();
			gl.glEnable(GL.GL_TEXTURE_2D);
			gl.glTranslatef(0.0f, 0.0f, CarH);
			gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
			gl.glScalef(CarS,CarS,CarS );
			//Add Body
			gl.glPushMatrix();
			drawCarBodyModel(gl);
			gl.glPopMatrix();
			
			

			//Add Front Left Wheel
			gl.glPushMatrix();
			gl.glTranslatef(0.77598f, -0.24387f ,1.33906f);
			gl.glRotatef(rotFW, 1.0f, 0.0f, 0.0f );
			gl.glTranslatef(-0.77598f,0.24387f ,-1.33906f);
			drawMyModelWheelFL(gl );
			gl.glPopMatrix();
			
			
			
			//Add Front Right Wheel
			gl.glPushMatrix();
			gl.glTranslatef(-0.77598f,-0.24507f ,1.3388f);
			gl.glRotatef(rotFW, 1.0f, 0.0f, 0.0f );
			gl.glTranslatef(0.77598f,0.24507f ,-1.3388f);
			drawMyModelWheelFR(gl );
			gl.glPopMatrix();
			
			
			
			//Add Back Left Wheel
			gl.glPushMatrix();
			gl.glTranslatef(-0.77346f,-0.20595f ,-1.37456f);
			gl.glRotatef(rotFW, 1.0f, 0.0f, 0.0f );		
			gl.glTranslatef(0.77346f,0.20595f ,1.37456f);
			drawMyModelWheelBL(gl );
			gl.glPopMatrix();
			
			
			
			//Add Back Right Wheel
			gl.glPushMatrix();
			gl.glTranslatef(0.77346f,-0.20595f ,-1.37456f);
			gl.glRotatef(rotFW, 1.0f, 0.0f, 0.0f );
			gl.glTranslatef(-0.77346f,0.20595f ,1.37456f);
			drawMyModelWheelBR(gl );
			gl.glPopMatrix();
			
			
			

			

			gl.glDisable(GL.GL_TEXTURE_2D);
			gl.glPopMatrix();
			gl.glDisable(GL.GL_BLEND);
			gl.glDisable(GL2.GL_COLOR_MATERIAL);
			
			
		}
		
		
		
		void drawCarBodyModel( GL2 gl ) {
		    gl.glBindTexture( GL2.GL_TEXTURE_2D, 0 );
		    gl.glColor3f( 1.0f, 1.0f, 1.0f );
		    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, materialDiffuse2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materialSpecular2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, materialAmbient2, 0);
			gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
		    
		    for ( int i = 0; i < my_model.faces.size(); ++i ) {
		        ArrayList<FaceVertex> vertices = my_model.faces.get( i ).vertices;
		        
		        String currentmaterialName = my_model.faces.get( i ).material.name;
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
		        		String filename=my_model.faces.get( i ).material.mapKdFilename;
		        		if(filename == null)
		        		{
		        			filename = "Map__8_Raytrace.bmp";
		        		}
		        		System.out.printf("%d, %s\n", list_size, filename);
		        	BufferedImage materialImage = ImageIO.read(new File("models/PorCar/"+filename));
					
					ByteBuffer buf = convertImageData(materialImage);
					int[] tmpTex = new int[1];
					gl.glGenTextures(1, tmpTex, 0);
					materialTexID[foundmaterialIndex] = tmpTex[0];
					gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID[foundmaterialIndex]);
					
					gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

					gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, materialImage.getWidth(), 
							materialImage.getHeight(), 0, GL2.GL_BGR, GL.GL_UNSIGNED_BYTE, buf);
					
					gl.glEnable(GL.GL_TEXTURE_2D);
					
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
		        	} catch (IOException e) {
		    			
		    			e.printStackTrace();
		    		}
		        	
		        }
		        else {
		        	gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID[foundmaterialIndex]);
		        }
		        gl.glBegin( GL2.GL_TRIANGLES );
		        for ( int j = 0; j < vertices.size(); ++j ) {
		            FaceVertex vertex = vertices.get( j );
		            
		            gl.glTexCoord2f(vertex.t.u, 1-vertex.t.v);
		            gl.glVertex3f( vertex.v.x, vertex.v.y, vertex.v.z );
		        }
		        gl.glEnd();
		    }
		  
		}
	
		void drawMyModelWheelFL( GL2 gl ) {
		    gl.glBindTexture( GL2.GL_TEXTURE_2D, 0 );
		    gl.glColor3f( 1.0f, 1.0f, 1.0f );
		    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, materialDiffuse2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materialSpecular2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, materialAmbient2, 0);
			gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
		    gl.glEnable(GL.GL_BLEND);
		    for ( int i = 0; i < my_model_wheel_frontL.faces.size(); ++i ) {
		        ArrayList<FaceVertex> vertices = my_model_wheel_frontL.faces.get( i ).vertices;
		        
		        String currentmaterialName = my_model_wheel_frontL.faces.get( i ).material.name;
		        int foundmaterialIndex=list_size1;
		        for(int iname=0; iname < list_size1; iname++) {
		        	if(currentmaterialName == materialNameList1[iname]){
		        		foundmaterialIndex=iname;
		        		break;
		        	}
		        }
		        if(foundmaterialIndex==list_size1) {
		        	materialNameList1[foundmaterialIndex]=currentmaterialName;
		        	list_size1++;
		        	
		        	try {
		        		String filename=my_model_wheel_frontL.faces.get( i ).material.mapKdFilename;
		        		if(filename == null)
		        		{
		        			filename = "Map__8_Raytrace.bmp";
		        		}
		        		System.out.printf("%d, %s\n", list_size, filename);
		        	BufferedImage materialImage = ImageIO.read(new File("models/PorCar/"+filename));
					//URL url = new URL("http:\\")
					ByteBuffer buf = convertImageData(materialImage);
					int[] tmpTex = new int[1];
					gl.glGenTextures(1, tmpTex, 0);
					materialTexID1[foundmaterialIndex] = tmpTex[0];
					gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID1[foundmaterialIndex]);
					
					gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

					gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, materialImage.getWidth(), 
							materialImage.getHeight(), 0, GL2.GL_BGR, GL.GL_UNSIGNED_BYTE, buf);
					
					gl.glEnable(GL.GL_TEXTURE_2D);
					
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
		        	} catch (IOException e) {
		    			
		    			e.printStackTrace();
		    		}
		        	
		        }
		        else {
		        	gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID1[foundmaterialIndex]);
		        }
		        gl.glBegin( GL2.GL_TRIANGLES );
		        for ( int j = 0; j < vertices.size(); ++j ) {
		            FaceVertex vertex = vertices.get( j );
		            
		            gl.glTexCoord2f(vertex.t.u, 1-vertex.t.v);
		            gl.glVertex3f( vertex.v.x, vertex.v.y, vertex.v.z );
		        }
		        gl.glEnd();
		    }
		    gl.glEnable(GL.GL_BLEND);
		}
		void drawMyModelWheelFR( GL2 gl ) {
		    gl.glBindTexture( GL2.GL_TEXTURE_2D, 0 );
		    gl.glColor3f( 1.0f, 1.0f, 1.0f );
		    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, materialDiffuse2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materialSpecular2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, materialAmbient2, 0);
			gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
		    gl.glEnable(GL.GL_BLEND);
		    for ( int i = 0; i < my_model_wheel_frontR.faces.size(); ++i ) {
		        ArrayList<FaceVertex> vertices = my_model_wheel_frontR.faces.get( i ).vertices;
		        
		        String currentmaterialName = my_model_wheel_frontR.faces.get( i ).material.name;
		        int foundmaterialIndex=list_size2;
		        for(int iname=0; iname < list_size2; iname++) {
		        	if(currentmaterialName == materialNameList2[iname]){
		        		foundmaterialIndex=iname;
		        		break;
		        	}
		        }
		        if(foundmaterialIndex==list_size2) {
		        	materialNameList2[foundmaterialIndex]=currentmaterialName;
		        	list_size2++;
		        	
		        	try {
		        		String filename=my_model_wheel_frontR.faces.get( i ).material.mapKdFilename;
		        		if(filename == null)
		        		{
		        			filename = "Map__8_Raytrace.bmp";
		        		}
		        		System.out.printf("%d, %s\n", list_size, filename);
		        	BufferedImage materialImage = ImageIO.read(new File("models/PorCar/"+filename));
					//URL url = new URL("http:\\")
					ByteBuffer buf = convertImageData(materialImage);
					int[] tmpTex = new int[1];
					gl.glGenTextures(1, tmpTex, 0);
					materialTexID2[foundmaterialIndex] = tmpTex[0];
					gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID2[foundmaterialIndex]);
					
					gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

					gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, materialImage.getWidth(), 
							materialImage.getHeight(), 0, GL2.GL_BGR, GL.GL_UNSIGNED_BYTE, buf);
					
					gl.glEnable(GL.GL_TEXTURE_2D);
					
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
		        	} catch (IOException e) {
		    			
		    			e.printStackTrace();
		    		}
		        	
		        }
		        else {
		        	gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID2[foundmaterialIndex]);
		        }
		        gl.glBegin( GL2.GL_TRIANGLES );
		        for ( int j = 0; j < vertices.size(); ++j ) {
		            FaceVertex vertex = vertices.get( j );
		            
		            gl.glTexCoord2f(vertex.t.u, 1-vertex.t.v);
		            gl.glVertex3f( vertex.v.x, vertex.v.y, vertex.v.z );
		        }
		        gl.glEnd();
		    }
		    gl.glEnable(GL.GL_BLEND);
		}
		void drawMyModelWheelBL( GL2 gl ) {
		    gl.glBindTexture( GL2.GL_TEXTURE_2D, 0 );
		    gl.glColor3f( 1.0f, 1.0f, 1.0f );
		    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, materialDiffuse2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materialSpecular2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, materialAmbient2, 0);
			gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
		    gl.glEnable(GL.GL_BLEND);
		    for ( int i = 0; i < my_model_wheel_backL.faces.size(); ++i ) {
		        ArrayList<FaceVertex> vertices = my_model_wheel_backL.faces.get( i ).vertices;
		        
		        String currentmaterialName = my_model_wheel_backL.faces.get( i ).material.name;
		        int foundmaterialIndex=list_size3;
		        for(int iname=0; iname < list_size3; iname++) {
		        	if(currentmaterialName == materialNameList3[iname]){
		        		foundmaterialIndex=iname;
		        		break;
		        	}
		        }
		        if(foundmaterialIndex==list_size3) {
		        	materialNameList3[foundmaterialIndex]=currentmaterialName;
		        	list_size3++;
		        	
		        	try {
		        		String filename=my_model_wheel_backL.faces.get( i ).material.mapKdFilename;
		        		if(filename == null)
		        		{
		        			filename = "Map__8_Raytrace.bmp";
		        		}
		        		System.out.printf("%d, %s\n", list_size, filename);
		        	BufferedImage materialImage = ImageIO.read(new File("models/PorCar/"+filename));
					//URL url = new URL("http:\\")
					ByteBuffer buf = convertImageData(materialImage);
					int[] tmpTex = new int[1];
					gl.glGenTextures(1, tmpTex, 0);
					materialTexID3[foundmaterialIndex] = tmpTex[0];
					gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID3[foundmaterialIndex]);
					
					gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

					gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, materialImage.getWidth(), 
							materialImage.getHeight(), 0, GL2.GL_BGR, GL.GL_UNSIGNED_BYTE, buf);
					
					gl.glEnable(GL.GL_TEXTURE_2D);
					
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
		        	} catch (IOException e) {
		    			
		    			e.printStackTrace();
		    		}
		        	
		        }
		        else {
		        	gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID3[foundmaterialIndex]);
		        }
		        gl.glBegin( GL2.GL_TRIANGLES );
		        for ( int j = 0; j < vertices.size(); ++j ) {
		            FaceVertex vertex = vertices.get( j );
		          
		            gl.glTexCoord2f(vertex.t.u, 1-vertex.t.v);
		            gl.glVertex3f( vertex.v.x, vertex.v.y, vertex.v.z );
		        }
		        gl.glEnd();
		    }
		    gl.glEnable(GL.GL_BLEND);
		}
		void drawMyModelWheelBR( GL2 gl ) {
		    gl.glBindTexture( GL2.GL_TEXTURE_2D, 0 );
		    gl.glColor3f( 1.0f, 1.0f, 1.0f );
		    gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, materialDiffuse2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materialSpecular2, 0);
			gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, materialAmbient2, 0);
			gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
		    gl.glEnable(GL.GL_BLEND);
		    for ( int i = 0; i < my_model_wheel_backR.faces.size(); ++i ) {
		        ArrayList<FaceVertex> vertices = my_model_wheel_backR.faces.get( i ).vertices;
		        
		        String currentmaterialName = my_model_wheel_backR.faces.get( i ).material.name;
		        int foundmaterialIndex=list_size4;
		        for(int iname=0; iname < list_size4; iname++) {
		        	if(currentmaterialName == materialNameList4[iname]){
		        		foundmaterialIndex=iname;
		        		break;
		        	}
		        }
		        if(foundmaterialIndex==list_size4) {
		        	materialNameList4[foundmaterialIndex]=currentmaterialName;
		        	list_size4++;
		        	
		        	try {
		        		String filename=my_model_wheel_backR.faces.get( i ).material.mapKdFilename;
		        		if(filename == null)
		        		{
		        			filename = "Map__8_Raytrace.bmp";
		        		}
		        		System.out.printf("%d, %s\n", list_size, filename);
		        	BufferedImage materialImage = ImageIO.read(new File("models/PorCar/"+filename));
					//URL url = new URL("http:\\")
					ByteBuffer buf = convertImageData(materialImage);
					int[] tmpTex = new int[1];
					gl.glGenTextures(1, tmpTex, 0);
					materialTexID4[foundmaterialIndex] = tmpTex[0];
					gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID4[foundmaterialIndex]);
					
					gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			        gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

					gl.glTexImage2D(GL.GL_TEXTURE_2D, 0, GL.GL_RGB, materialImage.getWidth(), 
							materialImage.getHeight(), 0, GL2.GL_BGR, GL.GL_UNSIGNED_BYTE, buf);
					
					gl.glEnable(GL.GL_TEXTURE_2D);
					
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
					gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);
		        	} catch (IOException e) {
		    			
		    			e.printStackTrace();
		    		}
		        	
		        }
		        else {
		        	gl.glBindTexture(GL.GL_TEXTURE_2D, materialTexID4[foundmaterialIndex]);
		        }
		        gl.glBegin( GL2.GL_TRIANGLES );
		        for ( int j = 0; j < vertices.size(); ++j ) {
		            FaceVertex vertex = vertices.get( j );
		            
		            gl.glTexCoord2f(vertex.t.u, 1-vertex.t.v);
		            gl.glVertex3f( vertex.v.x, vertex.v.y, vertex.v.z );
		        }
		        gl.glEnd();
		    }
		    gl.glEnable(GL.GL_BLEND);
		}
		
		
		
		
		
		
		
		
		 private ByteBuffer convertImageData(BufferedImage bufferedImage) {
	 	        ByteBuffer imageBuffer;
	 	        DataBuffer buf = bufferedImage.getRaster().getDataBuffer(); 
	 	        final byte[] data = ((DataBufferByte) buf).getData();	        
	 	        return (ByteBuffer.wrap(data)); 
	 	    }

	
	
	
	
	
	
}

