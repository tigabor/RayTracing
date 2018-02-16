/**
 * Installáció:
 *   - Felépít egy 3D-s tárgyakból álló listát 
 *   - Felépít egy lámpákból álló listát
 *   - Felépít egy kamerákból álló listát 
 *   - Megmondja, hogy megadott irányban milyen színt látunk
 */
package hu.datadesign.raytracing;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author tigabor@gmail.com
 *
 */
public abstract class Installation {
	
	private static final int MAXLEVEL = 5;
	protected static final float SPREADLIGHT = 0.1f;
	
	protected static ArrayList<Shape> shapeList;
	protected static ArrayList<Light> lightList;
	protected static ArrayList<Camera> cameraList;
	
	//felépítjük az installációt
	static void build() {
		shapeList = new ArrayList<Shape>();
		lightList = new ArrayList<Light>();
		cameraList = new ArrayList<Camera>();
		
		SimpleSphere mySphere;
		Light myLight;
		Camera myCamera;
		
		mySphere = new SimpleSphere("Piros gömb");
		mySphere.setOwnColor(Color.RED);
		mySphere.getCenter().setCoords(5.0, 0.0, 0.0);
		mySphere.setRadius(3.0);
		shapeList.add(mySphere);
		
		mySphere = new SimpleSphere("Szürke gömb");
		mySphere.setOwnColor(Color.GRAY);
		mySphere.getCenter().setCoords(-0.0, 0.0, 5.0);
		mySphere.setRadius(3.0);
		mySphere.setReflectRatio(0.6f);
		shapeList.add(mySphere);
		
		mySphere = new SimpleSphere("Zöld gömb");
		mySphere.setOwnColor(Color.GREEN);
		mySphere.getCenter().setCoords(-5.0, 0.0, 0.0);
		mySphere.setRadius(3.0);
		shapeList.add(mySphere);
		
		mySphere = new SimpleSphere("Sárga gömb");
		mySphere.setOwnColor(Color.YELLOW);
		mySphere.getCenter().setCoords(-0.0, 0.0, -5.0);
		mySphere.setRadius(3.0);
		shapeList.add(mySphere);
		
		//myLight = new Light("Lámpa");
		//myLight.getCenter().setCoords(-10.0, 5.0, -20.0);
		//shapeList.add(myLight);
		//lightList.add(myLight);
		
		myLight = new Light("Lámpa 2");
		myLight.getCenter().setCoords(0.0, 4.0, 0.0);
		myLight.setRadius(0.5);
		shapeList.add(myLight);
		lightList.add(myLight);
		
		myCamera = new Camera("Kamera_1");
		cameraList.add(myCamera);
		
		int i;
		for ( i = 0; i < shapeList.size(); i++ ) {
			System.out.println(shapeList.get(i).toString());
		}
		
		for ( i = 0; i < cameraList.size(); i++ ) {
			System.out.println(cameraList.get(i).toString());
		}
		
	}
	
	
	public static Color getPointColor( Vector ray, int level ) {
		final Color backgroundColor = Color.BLACK;
		Color ownColor = backgroundColor;
		Vector reflectRay;
		Color reflectColor = backgroundColor;
		float reflectRatio;
		Shape shape;
		int myShapeIndex = -1;
		double distance = 0.0;
		double minDistance = Double.POSITIVE_INFINITY;
		
		for ( int k = 0; k < shapeList.size(); k++ ) {
			shape = shapeList.get(k);
			if (shape.hasIntersection(ray)) {
				distance = shape.getDistance(ray);
				if ( distance > 0.001 && distance < minDistance ) {
					minDistance = distance;
					myShapeIndex = k;
				}				
			}
		}
		
		int redPart = ownColor.getRed();
		int greenPart = ownColor.getGreen();
		int bluePart = ownColor.getBlue();
		
		if ( myShapeIndex > -1 ) {
			ownColor = shapeList.get(myShapeIndex).getOwnColor(ray);
			
			redPart = ownColor.getRed();
			greenPart = ownColor.getGreen();
			bluePart = ownColor.getBlue();
			
			if ( level < MAXLEVEL ) {
				
				reflectRay = shapeList.get(myShapeIndex).getReflectRay(ray);
				reflectRatio = shapeList.get(myShapeIndex).getReflectRatio();
				reflectColor = getPointColor( reflectRay, ++level );
				
				/*if (myShapeIndex==1) {
					System.out.println( myShapeIndex + ": " + ray.getEndPoint().minus(ray.getStartPoint()).toString() + " / " 
					+ reflectRay.getEndPoint().minus(reflectRay.getStartPoint()).toString() );
				} */
				
				redPart = (int) (redPart * ( 1 - reflectRatio ) + reflectColor.getRed() * reflectRatio);
				greenPart = (int) (greenPart * ( 1 - reflectRatio ) + reflectColor.getGreen() * reflectRatio);
				bluePart = (int) (bluePart * ( 1 - reflectRatio ) + reflectColor.getBlue() * reflectRatio); 
				
				/*redPart = (int) Math.max(redPart, reflectColor.getRed() * reflectRatio );
				greenPart = (int) Math.max(greenPart, reflectColor.getGreen() * reflectRatio );
				bluePart = (int) Math.max(bluePart, reflectColor.getBlue() * reflectRatio ); */
				
			}
			
		}
		
		return new Color(redPart, greenPart, bluePart);
	};
	
	public static ArrayList<Camera> getCameraList() {
		return cameraList;
	}
}
