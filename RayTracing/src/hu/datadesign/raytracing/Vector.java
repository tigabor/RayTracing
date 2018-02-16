/**
 * Térbeli vektor
 * 
 */
package hu.datadesign.raytracing;

/**
 * @author tigabor@gmail.com
 *
 */
public class Vector {
	private Point startPoint;
	private Point endPoint;
	
	Vector (Point p1, Point p2) {
		setStartPoint(p1);
		setEndPoint(p2);
	}
	
	public String toString() {
		return startPoint.toString() + " / " + endPoint.toString();
	}
	
	//setterek és getterek
	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
}
