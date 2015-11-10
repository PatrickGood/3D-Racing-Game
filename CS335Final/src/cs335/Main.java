package cs335;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import java.awt.Frame;
import com.jogamp.opengl.util.Animator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;


public class Main extends JFrame {
	static private Animator animator = null;
	
	public Main() {
		  super("CS335 FINAL");

	        setLayout(new BorderLayout());
	        
	        
	        
	     
	        addWindowListener(new WindowAdapter()
	        {
	            public void windowClosing(WindowEvent e)
	            {
	                System.exit(0);
	            }
	        });
	        
	        setSize(1280, 960);
	        setLocation(40, 40);

	        setVisible(true);

	        setupJOGL();
	}
	
	public static void main( String[] args ) {
		Main m = new Main();
		m.setVisible( true );
	}
	
	private void setupJOGL() {
		GLCapabilities caps = new GLCapabilities( null );
		caps.setDoubleBuffered( true );
		caps.setHardwareAccelerated( true );
		
		GLCanvas canvas = new GLCanvas( caps ); 
		add( canvas );
		
		JoglEventListener jgl = new JoglEventListener();
		canvas.addGLEventListener( jgl ); 
		canvas.addKeyListener( jgl ); 
		canvas.addMouseListener( jgl );
		canvas.addMouseMotionListener( jgl );
		
		animator = new Animator( canvas );
		animator.start();
	}
}
class MyWin extends WindowAdapter {
	 public void windowClosing(WindowEvent e)
  {
      System.exit(0);
  }
}
