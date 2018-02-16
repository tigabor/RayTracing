/**
 * Egyszerû gömb:
 *   - egy egyszínû gömb valahol a térben, ami RayTracinggel letapogatható
 */
package hu.datadesign.raytracing;

import java.awt.Color;

/**
 * @author tigabor@gmail.com
 *
 */
public class SimpleSphere extends Shape {
	
	private String name;
	private Point center;
	private double radius;
	private Color ownColor;
	private float reflectRatio;
	
	SimpleSphere() {
		setName("dummy");
		setCenter(new Point());
		setRadius(1.0);
		setOwnColor(Color.GRAY);
		setReflectRatio(0.3f);
	}
	
	SimpleSphere( String name) {
		this();
		setName(name);
	}

	@Override
	boolean hasIntersection(Vector ray) {
		Point p1 = ray.getStartPoint();
		Point p2 = ray.getEndPoint();
		return p1.minus(p2).product(p1.minus(center)).abs() / p2.minus(p1).abs() <= radius ;
	}

	@Override
	double getDistance(Vector ray) {
		
		Point v = ray.getStartPoint().minus(center);
		Point d = ray.getEndPoint().minus(ray.getStartPoint()).norm();
		
		double b = Math.sqrt( Math.pow( Point.dotProduct(v, d), 2) - ( Point.dotProduct(v, v) - Math.pow( radius, 2 ) ) );
		
		double t1 = - Point.dotProduct( v, d ) + b;
		double t2 = - Point.dotProduct( v, d ) - b;
		double t;
		
		if ( t1 > 0.0 && t2 > 0.0 ) {
            t = Math.min(t1, t2);
	    } else {
	        if ( t1 > 0.0 ) {
	            t = t1;
	        } else {
	            t = t2;
	        }
	    }
		
		return t;
	}

	@Override
	Color getOwnColor(Vector ray) {
		
		Point myFocusPoint; 
		float brightness = 0.0f;
		boolean hasIntersection;
		Vector lightVector;		
        
		//Point unitVector = ray.getEndPoint().minus(ray.getStartPoint()).norm();
		//Point myFocusPoint = ray.getStartPoint().add( unitVector.multiply( this.getDistance(ray) ));
		
		myFocusPoint = getFocusPoint(ray);
		
		lightVector = new Vector (myFocusPoint, myFocusPoint);
		Point myFocusUnitVector = myFocusPoint.minus(this.center).norm();
		
		for ( int i = 0; i < Installation.lightList.size(); i++ ) {
			Point myLightUnitVector = Installation.lightList.get(i).getCenter().minus(myFocusPoint).norm();
			
			lightVector.setEndPoint(Installation.lightList.get(i).getCenter());
			
			hasIntersection = false;			
			for ( int j = 0; j < Installation.shapeList.size(); j++ ) {
				if ( this == Installation.shapeList.get(j) || 
						Installation.shapeList.get(j) instanceof Light ) { continue; }
				if ( Installation.shapeList.get(j).hasIntersection(lightVector) ) {
					if ( Installation.shapeList.get(j).getDistance(lightVector) > 0.0) {
						hasIntersection = true;
						//System.out.print(Installation.shapeList.get(j).getClass().toString());
					}
				}
			}
		    
		    float dot = (float)Point.dotProduct(myFocusUnitVector, myLightUnitVector);
		    float myBrightness;		    
		    
		    if ( dot > Installation.SPREADLIGHT ) {
		        myBrightness = dot;
		    } else {
		        myBrightness = Installation.SPREADLIGHT;
		    }
		    
		    if ( hasIntersection ) {
		    	myBrightness = Math.min(myBrightness, Installation.SPREADLIGHT);
		    }
		    
		    brightness = Math.max(myBrightness, brightness);
		}
		
		if (this instanceof Light) {
			brightness = 1.0f;
		}
		
		float[] HSBColor;
		Color myColor;
		
		HSBColor = Color.RGBtoHSB( ownColor.getRed(), ownColor.getGreen(), ownColor.getBlue(), null );
		myColor = Color.getHSBColor( HSBColor[0], HSBColor[1], HSBColor[2] * brightness );
		
		return myColor;
	}
	
	@Override
	Vector getReflectRay(Vector ray) {
		
		Point myFocusPoint = getFocusPoint(ray);
		Point x0, n, x1, x2;
		
		x0 = ray.getStartPoint().minus(ray.getEndPoint()).norm();
		n = myFocusPoint.minus(this.center).norm();
		x1 = n.multiply(Point.dotProduct(x0, n));
		x2 = x0.add(x1.minus(x0)).add(x1.minus(x0));
		
		Vector result = new Vector ( myFocusPoint, myFocusPoint.add(x2) );
		
		return result;
	}

	public String toString() {
		return "\"" + name + "\" = " + center + "; radius: " + radius + "; color: " + ownColor ;
	}
	
	private Point getFocusPoint( Vector ray ) {
		Point unitVector = ray.getEndPoint().minus(ray.getStartPoint()).norm();
		return ray.getStartPoint().add( unitVector.multiply( this.getDistance(ray) ));
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Color getOwnColor() {
		return ownColor;
	}

	public void setOwnColor(Color ownColor) {
		this.ownColor = ownColor;
	}
	
	@Override
	public float getReflectRatio() {
		return reflectRatio;
	}

	public void setReflectRatio(float reflectRatio) {
		this.reflectRatio = reflectRatio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
