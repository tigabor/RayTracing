/**
 * 
 */
package hu.datadesign.raytracing;

/**
 * @author tigabor@gmail.com
 *
 */
public class TestReflection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Shape shape = new SimpleSphere("Teszt gömb");
		Vector ray = new Vector( new Point(0.0, 0.0, -2.0), new Point(-0.02, 0.0, -1.0));
		Vector reflectRay = shape.getReflectRay(ray);
		
		System.out.println(ray + " => " + reflectRay );

	}

}
