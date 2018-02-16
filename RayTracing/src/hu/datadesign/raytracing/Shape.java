/**
 * Absztrakt 3D-s tárgy:
 *   - a Ray Tracing technikával letapogatható 3D-s tárgyak viselkedését definiálja
 */
package hu.datadesign.raytracing;

import java.awt.Color;

/**
 * @author tigabor@gmail.com
 *
 */
public abstract class Shape {
	abstract boolean hasIntersection( Vector ray );
	abstract double getDistance( Vector ray );
	abstract Color getOwnColor( Vector ray );
	abstract Vector getReflectRay( Vector ray );
	abstract float getReflectRatio();
	abstract String getName();
	
	static Vector reflectRay( Vector ray, Vector norm ) {
		
		Point x0, n, x1, x2;
		
		x0 = ray.getStartPoint().minus(ray.getEndPoint()).norm();
		n = norm.getEndPoint().minus(norm.getStartPoint()).norm();
		x1 = n.multiply(Point.dotProduct(x0, n));
		x2 = x1.minus(x0).multiply(2.0);
		
		return new Vector ( norm.getStartPoint(), x2.minus(norm.getStartPoint()) );
	}
	
}
