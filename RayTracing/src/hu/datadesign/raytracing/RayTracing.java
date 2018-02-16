/**
 * F�program:
 *   - bel�p�si pont
 *   - k�l�n sz�lon elind�tja a k�pk�sz�t�set
 *   - megjelen�ti a felhaszn�l�i fel�letet
 */
package hu.datadesign.raytracing;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author tigabor@gmail.com
 *
 */
public class RayTracing {
	
	private Thread myThread;
	private Camera myCamera;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new RayTracing().go();
	}
	
	public void go() {
		Installation.build();
		
		myCamera = Installation.getCameraList().get(0);
		myThread = new Thread(myCamera);
        myThread.start();
		
        gui();
	}
	
	public void gui() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        MyImagePanel imagePanel = new MyImagePanel();
        
        frame.getContentPane().add(imagePanel);
        frame.setSize(Camera.imageWidth + 16, Camera.imageHeight + 40);
        frame.setVisible(true);
        
        while (myThread.isAlive()) {
            try{Thread.sleep(5);}catch(InterruptedException e){System.out.println(e);}
            imagePanel.repaint();
        }
        
        imagePanel.repaint();
        
        System.out.print( "K�sz. Kil�p�shez kapcsold ki a grafikus ablakot." );
    }
        
    //bels� oszt�ly, ami kiteszi a fel�letre a k�p aktu�lis �llapot�t
    class MyImagePanel extends JPanel {
        
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
            
            g.drawImage(myCamera.getImage(), 0, 0, null);
            
        }
    }

}
