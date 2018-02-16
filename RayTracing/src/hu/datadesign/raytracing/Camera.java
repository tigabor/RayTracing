/**
 * Kamera:
 *   - tartalmaz egy fényképet és létrehoz egy vásznat
 *   - kérésre (külön szálon) leképzi a vásznon látgató képet a fényképre (RayTracing technikával)
 */
package hu.datadesign.raytracing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author tigabor@gmail.com
 *
 */
public class Camera implements Runnable {
	
	public static final int blockSize = 64;
	public static final int imageWidth = 16 * blockSize;
	public static final int imageHeight = 9 * blockSize;
	
	private String name;
	private BufferedImage image;
	
	private Canvas canvas;
		
	Vector ray;
		
	Camera( String name ) {
		
		setName(name);
		
		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_3BYTE_BGR);   
		
		canvas = new Canvas();
		canvas.rotateX(20.0);
        canvas.rotateY(10.0);
	
	}
	
	@Override
	public void run() {
		
		Point stepX1 = canvas.getUnitX1().multiply(canvas.getCanvasWidth() / imageWidth);
		Point stepX2 = canvas.getUnitX2().multiply(-canvas.getCanvasHeight() / imageHeight);
		Point coord00 = canvas.getCenter().add(canvas.getUnitX1().multiply(-canvas.getCanvasWidth() / 2))
										.add(canvas.getUnitX2().multiply(canvas.getCanvasHeight() / 2));
		Point coordXY = new Point(coord00);
		
		Color myColor;
		
		ray = new Vector( canvas.getViewPoint(), coordXY );
		
		//várunk, amíg a felhasználói felület elkészül
		try{Thread.sleep(600);}catch(InterruptedException e){System.out.println(e);}
		
		for ( int i = 0; i < imageWidth; i++ ) {
			for ( int j = 0; j < imageHeight; j++ ) {
				
				coordXY.setCoords(coord00);
				coordXY.push(stepX1.multiply(i)).push(stepX2.multiply(j));
				
				myColor = Installation.getPointColor(ray,0);
				
				image.setRGB(i, j, myColor.getRGB());
				
			}

		}
		
		//a kép mentése
        try {
            File outputfile = new File("../Image_" + name + ".png");
            ImageIO.write(image, "png", outputfile);
        }
        catch (IOException e) {
        }	
		
	}
	
	public String toString( ) {
		return "\"" + name + "\" = " + canvas;
	}

	public BufferedImage getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
