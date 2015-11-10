package cs335;
import javax.media.opengl.GL2;

public class Racetrack{
	
	public float size = 0.0f;  // size of track
	public final float p = 4096.0f;   // pixel size of track image
	
	
	public float tw = 163.0f;  // track width
	public float ww = 17.0f;   // wall width
	
	
	public float c1x = 1080.0f;  // center points of each curve
	public float c1y = 1439.0f;
	
	public float c2x = 1088.0f;
	public float c2y = 2630.0f;
	
	public float c3x = 1589.5f;
	public float c3y = 2029.0f;
	
	public float c4x = 2018.0f;
	public float c4y = 2503.0f;
	
	public float c5x = 2405.0f;
	public float c5y = 1792.0f;
	
	public float c6x = 2823.0f;
	public float c6y = 2058.0f;
	
	public float c7x = 3079.0f;
	public float c7y = 1446.0f;
	
	
	public float left_r1 = 217.0f;          // radius of each wall (track-side)
	public float right_r1 = left_r1 + tw;
	
	public float left_r2 = 224.0f;
	public float right_r2 = left_r2 + tw;
	
	public float left_r3 = 277.5f;
	public float right_r3 = left_r3 - tw;
	
	public float left_r4 = 109.0f;
	public float right_r4 = left_r4 + tw;
	
	public float left_r5 = 276.0f;
	public float right_r5 = left_r5 - tw;
	
	public float left_r6 = 141.0f;
	public float right_r6 = left_r6 + tw;
	
	public float left_r7 = 223.0f;
	public float right_r7 = left_r7 + tw;
	
	
	final float deg = ((float)Math.PI / 180.0f);  // degrees to radians
    final float pi = (float)Math.PI;
    
    private float materialAmbientDiffuse2[] = {0.0f, 0.0f, 0.0f};
    private float  materialSpecular2[] = {0.0f,0.0f,0.0f};
    private float shininess = 45;
	
    
	public Racetrack(float s)
	{
		size = s;
	}
    
    
	void draw_walls( GL2 gl, float size, float h ) {
		 
	    // add lighting/texture stuff here
	    // best to not texture with an image if we can find some material that looks decent	
		
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE, materialAmbientDiffuse2, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_EMISSION, materialSpecular2, 0);
		gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, materialSpecular2, 0);
		gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
	    
		final float d = size / 2.0f;
		
	    // draw outer walls
	     
	    // inside of outer wall
	    gl.glBegin( GL2.GL_QUAD_STRIP );
	    gl.glNormal3f(0, -1, 0);  // starting point, end of curve 7
	    gl.glVertex3f(d*2*(right_r7/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*(right_r7/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), 0f);
	    gl.glVertex3f(d*2*(right_r7/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*(right_r7/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), h);
	    for( int phi = 90; phi <= 180; phi++ )  // curve 1
	    {
	        float x = right_r1/p * (float)Math.cos(phi * deg) + (c1x/p - 0.5f);
	        float y = right_r1/p * -(float)Math.sin(phi * deg) + (c1y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi <= 360; phi++ )  // curve 2
	    {
	        float x = right_r2/p * (float)Math.cos(phi * deg) + (c2x/p - 0.5f);
	        float y = right_r2/p * -(float)Math.sin(phi * deg) + (c2y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi >= 5; phi-- )  // curve 3
	    {
	        float x = right_r3/p * (float)Math.cos(phi * deg) + (c3x/p - 0.5f);
	        float y = right_r3/p * -(float)Math.sin(phi * deg) + (c3y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 185; phi <= 360; phi++ )  // curve 4
	    {
	        float x = right_r4/p * (float)Math.cos(phi * deg) + (c4x/p - 0.5f);
	        float y = right_r4/p * -(float)Math.sin(phi * deg) + (c4y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi >= 0; phi-- )  // curve 5
	    {
	        float x = right_r5/p * (float)Math.cos(phi * deg) + (c5x/p - 0.5f);
	        float y = right_r5/p * -(float)Math.sin(phi * deg) + (c5y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi <= 330; phi++ )  // curve 6
	    {
	        float x = right_r6/p * (float)Math.cos(phi * deg) + (c6x/p - 0.5f);
	        float y = right_r6/p * -(float)Math.sin(phi * deg) + (c6y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = -30; phi <= 90; phi++ )  // curve 7
	    {
	        float x = right_r7/p * (float)Math.cos(phi * deg) + (c7x/p - 0.5f);
	        float y = right_r7/p * -(float)Math.sin(phi * deg) + (c7y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    gl.glEnd();
	    // outside of outer wall
	    gl.glBegin( GL2.GL_QUAD_STRIP );
	    gl.glNormal3f(0, 1, 0);  // starting point, end of curve 7
	    gl.glVertex3f(d*2*((right_r7 + ww)/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*((right_r7 + ww)/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), 0f);
	    gl.glVertex3f(d*2*((right_r7 + ww)/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*((right_r7 + ww)/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), h);
	    for( int phi = 90; phi <= 180; phi++ )  // curve 1
	    {
	        float x = (right_r1 + ww)/p * (float)Math.cos(phi * deg) + (c1x/p - 0.5f);
	        float y = (right_r1 + ww)/p * -(float)Math.sin(phi * deg) + (c1y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }  
	    for( int phi = 180; phi <= 360; phi++ )  // curve 2
	    {
	        float x = (right_r2 + ww)/p * (float)Math.cos(phi * deg) + (c2x/p - 0.5f);
	        float y = (right_r2 + ww)/p * -(float)Math.sin(phi * deg) + (c2y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi >= 5; phi-- )  // curve 3
	    {
	        float x = (right_r3 - ww)/p * (float)Math.cos(phi * deg) + (c3x/p - 0.5f);
	        float y = (right_r3 - ww)/p * -(float)Math.sin(phi * deg) + (c3y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 185; phi <= 360; phi++ )  // curve 4
	    {
	        float x = (right_r4 + ww)/p * (float)Math.cos(phi * deg) + (c4x/p - 0.5f);
	        float y = (right_r4 + ww)/p * -(float)Math.sin(phi * deg) + (c4y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi >= 0; phi-- )  // curve 5
	    {
	        float x = (right_r5 - ww)/p * (float)Math.cos(phi * deg) + (c5x/p - 0.5f);
	        float y = (right_r5 - ww)/p * -(float)Math.sin(phi * deg) + (c5y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi <= 330; phi++ )  // curve 6
	    {
	        float x = (right_r6 + ww)/p * (float)Math.cos(phi * deg) + (c6x/p - 0.5f);
	        float y = (right_r6 + ww)/p * -(float)Math.sin(phi * deg) + (c6y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = -30; phi <= 90; phi++ )  // curve 7
	    {
	        float x = (right_r7 + ww)/p * (float)Math.cos(phi * deg) + (c7x/p - 0.5f);
	        float y = (right_r7 + ww)/p * -(float)Math.sin(phi * deg) + (c7y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    gl.glEnd();
	    // top of outer wall
	    gl.glBegin( GL2.GL_QUAD_STRIP );
	    gl.glNormal3f(0, 0, 1);  // starting point, end of curve 7
	    gl.glVertex3f(d*2*(right_r7/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*(right_r7/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), h);
	    gl.glVertex3f(d*2*((right_r7 + ww)/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*((right_r7 + ww)/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), h);
	    for( int phi = 90; phi <= 180; phi++ )  // curve 1
	    {
	        float xi = right_r1/p * (float)Math.cos(phi * deg) + (c1x/p - 0.5f);
	        float yi = right_r1/p * -(float)Math.sin(phi * deg) + (c1y/p - 0.5f);
	        float xo = (right_r1 + ww)/p * (float)Math.cos(phi * deg) + (c1x/p - 0.5f);
	        float yo = (right_r1 + ww)/p * -(float)Math.sin(phi * deg) + (c1y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);  
	    }  
	    for( int phi = 180; phi <= 360; phi++ )  // curve 2
	    {
	        float xi = right_r2/p * (float)Math.cos(phi * deg) + (c2x/p - 0.5f);
	        float yi = right_r2/p * -(float)Math.sin(phi * deg) + (c2y/p - 0.5f);
	        float xo = (right_r2 + ww)/p * (float)Math.cos(phi * deg) + (c2x/p - 0.5f);
	        float yo = (right_r2 + ww)/p * -(float)Math.sin(phi * deg) + (c2y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    for( int phi = 180; phi >= 5; phi-- )  // curve 3
	    {
	        float xi = right_r3/p * (float)Math.cos(phi * deg) + (c3x/p - 0.5f);
	        float yi = right_r3/p * -(float)Math.sin(phi * deg) + (c3y/p - 0.5f);
	        float xo = (right_r3 - ww)/p * (float)Math.cos(phi * deg) + (c3x/p - 0.5f);
	        float yo = (right_r3 - ww)/p * -(float)Math.sin(phi * deg) + (c3y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);  
	    }
	    for( int phi = 185; phi <= 360; phi++ )  // curve 4
	    {
	        float xi = right_r4/p * (float)Math.cos(phi * deg) + (c4x/p - 0.5f);
	        float yi = right_r4/p * -(float)Math.sin(phi * deg) + (c4y/p - 0.5f);
	        float xo = (right_r4 + ww)/p * (float)Math.cos(phi * deg) + (c4x/p - 0.5f);
	        float yo = (right_r4 + ww)/p * -(float)Math.sin(phi * deg) + (c4y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    for( int phi = 180; phi >= 0; phi-- )  // curve 5
	    {
	        float xi = right_r5/p * (float)Math.cos(phi * deg) + (c5x/p - 0.5f);
	        float yi = right_r5/p * -(float)Math.sin(phi * deg) + (c5y/p - 0.5f);
	        float xo = (right_r5 - ww)/p * (float)Math.cos(phi * deg) + (c5x/p - 0.5f);
	        float yo = (right_r5 - ww)/p * -(float)Math.sin(phi * deg) + (c5y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    for( int phi = 180; phi <= 330; phi++ )  // curve 6
	    {
	        float xi = right_r6/p * (float)Math.cos(phi * deg) + (c6x/p - 0.5f);
	        float yi = right_r6/p * -(float)Math.sin(phi * deg) + (c6y/p - 0.5f);
	        float xo = (right_r6 + ww)/p * (float)Math.cos(phi * deg) + (c6x/p - 0.5f);
	        float yo = (right_r6 + ww)/p * -(float)Math.sin(phi * deg) + (c6y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    for( int phi = -30; phi <= 90; phi++ )  // curve 7
	    {
	        float xi = right_r7/p * (float)Math.cos(phi * deg) + (c7x/p - 0.5f);
	        float yi = right_r7/p * -(float)Math.sin(phi * deg) + (c7y/p - 0.5f);
	        float xo = (right_r7 + ww)/p * (float)Math.cos(phi * deg) + (c7x/p - 0.5f);
	        float yo = (right_r7 + ww)/p * -(float)Math.sin(phi * deg) + (c7y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    gl.glEnd();
	     
	    // draw inner walls
	     
	    // inside of inner wall
	    gl.glBegin( GL2.GL_QUAD_STRIP );
	    gl.glNormal3f(0, -1, 0);  // starting point, end of curve 7
	    gl.glVertex3f(d*2*((left_r7 - ww)/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*((left_r7 - ww)/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), 0f);
	    gl.glVertex3f(d*2*((left_r7 - ww)/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*((left_r7 - ww)/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), h);
	    for( int phi = 90; phi <= 180; phi++ )  // curve 1
	    {
	        float x = (left_r1 - ww)/p * (float)Math.cos(phi * deg) + (c1x/p - 0.5f);
	        float y = (left_r1 - ww)/p * -(float)Math.sin(phi * deg) + (c1y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi <= 360; phi++ )  // curve 2
	    {
	        float x = (left_r2 - ww)/p * (float)Math.cos(phi * deg) + (c2x/p - 0.5f);
	        float y = (left_r2 - ww)/p * -(float)Math.sin(phi * deg) + (c2y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi >= 5; phi-- )  // curve 3
	    {
	        float x = (left_r3 + ww)/p * (float)Math.cos(phi * deg) + (c3x/p - 0.5f);
	        float y = (left_r3 + ww)/p * -(float)Math.sin(phi * deg) + (c3y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 185; phi <= 360; phi++ )  // curve 4
	    {
	        float x = (left_r4 - ww)/p * (float)Math.cos(phi * deg) + (c4x/p - 0.5f);
	        float y = (left_r4 - ww)/p * -(float)Math.sin(phi * deg) + (c4y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi >= 0; phi-- )  // curve 5
	    {
	        float x = (left_r5 + ww)/p * (float)Math.cos(phi * deg) + (c5x/p - 0.5f);
	        float y = (left_r5 + ww)/p * -(float)Math.sin(phi * deg) + (c5y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi <= 330; phi++ )  // curve 6
	    {
	        float x = (left_r6 - ww)/p * (float)Math.cos(phi * deg) + (c6x/p - 0.5f);
	        float y = (left_r6 - ww)/p * -(float)Math.sin(phi * deg) + (c6y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = -30; phi <= 90; phi++ )  // curve 7
	    {
	        float x = (left_r7 - ww)/p * (float)Math.cos(phi * deg) + (c7x/p - 0.5f);
	        float y = (left_r7 - ww)/p * -(float)Math.sin(phi * deg) + (c7y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(pi + phi * deg), (float)Math.sin(pi + phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    gl.glEnd();
	    // outside of inner wall
	    gl.glBegin( GL2.GL_QUAD_STRIP );
	    gl.glNormal3f(0, 1, 0);  // starting point, end of curve 7
	    gl.glVertex3f(d*2*(left_r7/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*(left_r7/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), 0f);
	    gl.glVertex3f(d*2*(left_r7/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*(left_r7/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), h);
	    for( int phi = 90; phi <= 180; phi++ )  // curve 1
	    {
	        float x = left_r1/p * (float)Math.cos(phi * deg) + (c1x/p - 0.5f);
	        float y = left_r1/p * -(float)Math.sin(phi * deg) + (c1y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }  
	    for( int phi = 180; phi <= 360; phi++ )  // curve 2
	    {
	        float x = left_r2/p * (float)Math.cos(phi * deg) + (c2x/p - 0.5f);
	        float y = left_r2/p * -(float)Math.sin(phi * deg) + (c2y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi >= 5; phi-- )  // curve 3
	    {
	        float x = left_r3/p * (float)Math.cos(phi * deg) + (c3x/p - 0.5f);
	        float y = left_r3/p * -(float)Math.sin(phi * deg) + (c3y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 185; phi <= 360; phi++ )  // curve 4
	    {
	        float x = left_r4/p * (float)Math.cos(phi * deg) + (c4x/p - 0.5f);
	        float y = left_r4/p * -(float)Math.sin(phi * deg) + (c4y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi >= 0; phi-- )  // curve 5
	    {
	        float x = left_r5/p * (float)Math.cos(phi * deg) + (c5x/p - 0.5f);
	        float y = left_r5/p * -(float)Math.sin(phi * deg) + (c5y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = 180; phi <= 330; phi++ )  // curve 6
	    {
	        float x = left_r6/p * (float)Math.cos(phi * deg) + (c6x/p - 0.5f);
	        float y = left_r6/p * -(float)Math.sin(phi * deg) + (c6y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    for( int phi = -30; phi <= 90; phi++ )  // curve 7
	    {
	        float x = left_r7/p * (float)Math.cos(phi * deg) + (c7x/p - 0.5f);
	        float y = left_r7/p * -(float)Math.sin(phi * deg) + (c7y/p - 0.5f);
	        gl.glNormal3f((float)Math.cos(phi * deg), (float)Math.sin(phi * deg), 0f);
	        gl.glVertex3f(d*2*x, d*2*y, 0f);
	        gl.glVertex3f(d*2*x, d*2*y, h);
	    }
	    gl.glEnd();
	    // top of inner wall
	    gl.glBegin( GL2.GL_QUAD_STRIP );
	    gl.glNormal3f(0, 0, 1);  // starting point, end of curve 7
	    gl.glVertex3f(d*2*((left_r7 - ww)/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*((left_r7 - ww)/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), h);
	    gl.glVertex3f(d*2*(left_r7/p * (float)Math.cos(90 * deg) + (c7x/p - 0.5f)), d*2*(left_r7/p * -(float)Math.sin(90 * deg) + (c7y/p - 0.5f)), h);
	    for( int phi = 90; phi <= 180; phi++ )  // curve 1
	    {
	        float xi = (left_r1 - ww)/p * (float)Math.cos(phi * deg) + (c1x/p - 0.5f);
	        float yi = (left_r1 - ww)/p * -(float)Math.sin(phi * deg) + (c1y/p - 0.5f);
	        float xo = left_r1/p * (float)Math.cos(phi * deg) + (c1x/p - 0.5f);
	        float yo = left_r1/p * -(float)Math.sin(phi * deg) + (c1y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);  
	    }  
	    for( int phi = 180; phi <= 360; phi++ )  // curve 2
	    {
	        float xi = (left_r2 - ww)/p * (float)Math.cos(phi * deg) + (c2x/p - 0.5f);
	        float yi = (left_r2 - ww)/p * -(float)Math.sin(phi * deg) + (c2y/p - 0.5f);
	        float xo = left_r2/p * (float)Math.cos(phi * deg) + (c2x/p - 0.5f);
	        float yo = left_r2/p * -(float)Math.sin(phi * deg) + (c2y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    for( int phi = 180; phi >= 5; phi-- )  // curve 3
	    {
	        float xi = (left_r3 + ww)/p * (float)Math.cos(phi * deg) + (c3x/p - 0.5f);
	        float yi = (left_r3 + ww)/p * -(float)Math.sin(phi * deg) + (c3y/p - 0.5f);
	        float xo = left_r3/p * (float)Math.cos(phi * deg) + (c3x/p - 0.5f);
	        float yo = left_r3/p * -(float)Math.sin(phi * deg) + (c3y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);  
	    }
	    for( int phi = 185; phi <= 360; phi++ )  // curve 4
	    {
	        float xi = (left_r4 - ww)/p * (float)Math.cos(phi * deg) + (c4x/p - 0.5f);
	        float yi = (left_r4 - ww)/p * -(float)Math.sin(phi * deg) + (c4y/p - 0.5f);
	        float xo = left_r4/p * (float)Math.cos(phi * deg) + (c4x/p - 0.5f);
	        float yo = left_r4/p * -(float)Math.sin(phi * deg) + (c4y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    for( int phi = 180; phi >= 0; phi-- )  // curve 5
	    {
	        float xi = (left_r5 + ww)/p * (float)Math.cos(phi * deg) + (c5x/p - 0.5f);
	        float yi = (left_r5 + ww)/p * -(float)Math.sin(phi * deg) + (c5y/p - 0.5f);
	        float xo = left_r5/p * (float)Math.cos(phi * deg) + (c5x/p - 0.5f);
	        float yo = left_r5/p * -(float)Math.sin(phi * deg) + (c5y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    for( int phi = 180; phi <= 330; phi++ )  // curve 6
	    {
	        float xi = (left_r6 - ww)/p * (float)Math.cos(phi * deg) + (c6x/p - 0.5f);
	        float yi = (left_r6 - ww)/p * -(float)Math.sin(phi * deg) + (c6y/p - 0.5f);
	        float xo = left_r6/p * (float)Math.cos(phi * deg) + (c6x/p - 0.5f);
	        float yo = left_r6/p * -(float)Math.sin(phi * deg) + (c6y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    for( int phi = -30; phi <= 90; phi++ )  // curve 7
	    {
	        float xi = (left_r7 - ww)/p * (float)Math.cos(phi * deg) + (c7x/p - 0.5f);
	        float yi = (left_r7 - ww)/p * -(float)Math.sin(phi * deg) + (c7y/p - 0.5f);
	        float xo = left_r7/p * (float)Math.cos(phi * deg) + (c7x/p - 0.5f);
	        float yo = left_r7/p * -(float)Math.sin(phi * deg) + (c7y/p - 0.5f);
	        gl.glVertex3f(d*2*xi, d*2*yi, h);
	        gl.glVertex3f(d*2*xo, d*2*yo, h);
	    }
	    gl.glEnd();
	}
	
	public float curve_radius( int curve, int path )
	{
		float r = 0.0f;
		float offset = path * 51.5f - 21.5f;
		if( curve == 1 )
		{
			r = left_r1 + offset;
		}
		else if( curve == 2 )
		{
			r = left_r2 + offset;
		}
		else if( curve == 3 )
		{
			r = left_r3 - offset;
		}
		else if( curve == 4 )
		{
			r = left_r4 + offset;
		}
		else if( curve == 5 )
		{
			r = left_r5 - offset;
		}
		else if( curve == 6 )
		{
			r = left_r6 + offset;
		}
		else if( curve == 7 )
		{
			r = left_r7 + offset;
		}
		return r;
	}
	
	public float curve_angle( int curve )
	{
		float a = 0.0f;
		if( curve == 1 )
		{
			a = 90.0f;
		}
		else if( curve == 2 )
		{
			a = 180.0f;
		}
		else if( curve == 3 )
		{
			a = 175.0f;
		}
		else if( curve == 4 )
		{
			a = 175.0f;
		}
		else if( curve == 5 )
		{
			a = 180.0f;
		}
		else if( curve == 6 )
		{
			a = 150.0f;
		}
		else if( curve == 7 )
		{
			a = 120.0f;
		}
		return a;
	}
	
	public float car_start_angle( int curve )
	{
		float a = 0.0f;
		if( curve == 1 )
		{
			a = 0.0f;
		}
		else if( curve == 2 )
		{
			a = 90.0f;
		}
		else if( curve == 3 )
		{
			a = 270.0f;
		}
		else if( curve == 4 )
		{
			a = 95.0f;
		}
		else if( curve == 5 )
		{
			a = 270.0f;
		}
		else if( curve == 6 )
		{
			a = 90.0f;
		}
		else if( curve == 7 )
		{
			a = 240.0f;
		}
		return a;
	}
	
	public float car_end_angle( int curve )
	{
		float a = 0.0f;
		if( curve == 1 )
		{
			a = 90.0f;
		}
		else if( curve == 2 )
		{
			a = 270.0f;
		}
		else if( curve == 3 )
		{
			a = 95.0f;
		}
		else if( curve == 4 )
		{
			a = 270.0f;
		}
		else if( curve == 5 )
		{
			a = 90.0f;
		}
		else if( curve == 6 )
		{
			a = 240.0f;
		}
		else if( curve == 7 )
		{
			a = 360.0f;
		}
		return a;
	}
	
	public float curve_distance( int curve, int path )
	{
		float dist = 0;
		float offset = path * 51.5f - 21.5f;
		if( curve == 1 )
		{
			dist = 90.0f * deg * (left_r1 + offset);
		}
		else if( curve == 2 )
		{
			dist = 90.0f * deg * (left_r2 + offset);
		}
		else if( curve == 3 )
		{
			dist = 90.0f * deg * (left_r3 + offset);
		}
		else if( curve == 4 )
		{
			dist = 90.0f * deg * (left_r4 + offset);
		}
		else if( curve == 5 )
		{
			dist = 90.0f * deg * (left_r5 + offset);
		}
		else if( curve == 6 )
		{
			dist = 90.0f * deg * (left_r6 + offset);
		}
		else if( curve == 7 )
		{
			dist = 90.0f * deg * (left_r7 + offset);
		}
		return dist;
	}
	
	public float straight_distance( int curve, int path )
	{
		float dist = 0;
		float offset = path * 51.5f - 21.5f;
		if( curve == 1 )
		{
			float startx = size * ((left_r7 + offset)/p * (float)Math.cos(180 * deg) + (c7x/p - 0.5f));
	        float starty = size * ((left_r7 + offset)/p * -(float)Math.sin(180 * deg) + (c7y/p - 0.5f));
	        float endx = size * ((left_r1 + offset)/p * (float)Math.cos(180 * deg) + (c1x/p - 0.5f));
	        float endy = size * ((left_r1 + offset)/p * -(float)Math.sin(180 * deg) + (c1y/p - 0.5f));
	        dist = (float)Math.sqrt(Math.pow((endx-startx),2) + Math.pow((endy-starty),2));
		}
		else if( curve == 2 )
		{
			float startx = size * ((left_r1 + offset)/p * (float)Math.cos(180 * deg) + (c1x/p - 0.5f));
	        float starty = size * ((left_r1 + offset)/p * -(float)Math.sin(180 * deg) + (c1y/p - 0.5f));
	        float endx = size * ((left_r2 + offset)/p * (float)Math.cos(180 * deg) + (c2x/p - 0.5f));
	        float endy = size * ((left_r2 + offset)/p * -(float)Math.sin(180 * deg) + (c2y/p - 0.5f));
	        dist = (float)Math.sqrt(Math.pow((endx-startx),2) + Math.pow((endy-starty),2));
		}
		else if( curve == 3 )
		{
			float startx = size * ((left_r2 - offset)/p * (float)Math.cos(180 * deg) + (c2x/p - 0.5f));
	        float starty = size * ((left_r2 - offset)/p * -(float)Math.sin(180 * deg) + (c2y/p - 0.5f));
	        float endx = size * ((left_r3 - offset)/p * (float)Math.cos(180 * deg) + (c3x/p - 0.5f));
	        float endy = size * ((left_r3 - offset)/p * -(float)Math.sin(180 * deg) + (c3y/p - 0.5f));
	        dist = (float)Math.sqrt(Math.pow((endx-startx),2) + Math.pow((endy-starty),2));
		}
		else if( curve == 4 )
		{
			float startx = size * ((left_r3 + offset)/p * (float)Math.cos(180 * deg) + (c3x/p - 0.5f));
	        float starty = size * ((left_r3 + offset)/p * -(float)Math.sin(180 * deg) + (c3y/p - 0.5f));
	        float endx = size * ((left_r4 + offset)/p * (float)Math.cos(180 * deg) + (c4x/p - 0.5f));
	        float endy = size * ((left_r4 + offset)/p * -(float)Math.sin(180 * deg) + (c4y/p - 0.5f));
	        dist = (float)Math.sqrt(Math.pow((endx-startx),2) + Math.pow((endy-starty),2));
		}
		else if( curve == 5 )
		{
			float startx = size * ((left_r4 - offset)/p * (float)Math.cos(180 * deg) + (c4x/p - 0.5f));
	        float starty = size * ((left_r4 - offset)/p * -(float)Math.sin(180 * deg) + (c4y/p - 0.5f));
	        float endx = size * ((left_r5 - offset)/p * (float)Math.cos(180 * deg) + (c5x/p - 0.5f));
	        float endy = size * ((left_r5 - offset)/p * -(float)Math.sin(180 * deg) + (c5y/p - 0.5f));
	        dist = (float)Math.sqrt(Math.pow((endx-startx),2) + Math.pow((endy-starty),2));
		}
		else if( curve == 6 )
		{
			float startx = size * ((left_r5 + offset)/p * (float)Math.cos(180 * deg) + (c5x/p - 0.5f));
	        float starty = size * ((left_r5 + offset)/p * -(float)Math.sin(180 * deg) + (c5y/p - 0.5f));
	        float endx = size * ((left_r6 + offset)/p * (float)Math.cos(180 * deg) + (c6x/p - 0.5f));
	        float endy = size * ((left_r6 + offset)/p * -(float)Math.sin(180 * deg) + (c6y/p - 0.5f));
	        dist = (float)Math.sqrt(Math.pow((endx-startx),2) + Math.pow((endy-starty),2));
		}
		else if( curve == 7 )
		{
			float startx = size * ((left_r6 + offset)/p * (float)Math.cos(180 * deg) + (c6x/p - 0.5f));
	        float starty = size * ((left_r6 + offset)/p * -(float)Math.sin(180 * deg) + (c6y/p - 0.5f));
	        float endx = size * ((left_r7 + offset)/p * (float)Math.cos(180 * deg) + (c7x/p - 0.5f));
	        float endy = size * ((left_r7 + offset)/p * -(float)Math.sin(180 * deg) + (c7y/p - 0.5f));
	        dist = (float)Math.sqrt(Math.pow((endx-startx),2) + Math.pow((endy-starty),2));
		}
		return dist;
	}
	
	
	
	
	
	
	
}