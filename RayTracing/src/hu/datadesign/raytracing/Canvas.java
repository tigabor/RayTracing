/**
 * Képalkotó vászon:
 *   - definiál a térben egy téglalapot és - az origóból nézve - mindig pontosan középen mögötte 
 *   egy nézõpontot (itt van a kamera)
 *   - a vászon (a kamerával) térben elforgatható a bármely tengely körül megadott fokkal
 *   - beépített zoom is van
 */
package hu.datadesign.raytracing;

/**
 * @author tigabor@gmail.com
 *
 */
public class Canvas {
	private Point unitX1, unitX2, unitX3;
	private Point center;
	private Point viewPoint;
	private double canvasWidth = 16;
	private double canvasHeight = 9;
	private float zoom;

	Canvas() {
		unitX1 = new Point(1.0, 0.0, 0.0);
		unitX2 = new Point(0.0, 1.0, 0.0);
		unitX3 = new Point(0.0, 0.0, 1.0);
		
		center = new Point(0.0, 0.0, -15.0);
		
		zoom = 25.0f;
		viewPoint = center.add(unitX3.multiply(-zoom));
		
	}
	
	public String toString( ) {
		return "center: " + center;
	}
	
	//vászon elforgatása egy ortogonális mártixszal
    private void rotateCanvas( double[][] ortoMtx ) {
    	center.transform(ortoMtx); 
        viewPoint.transform(ortoMtx);
        unitX1.transform(ortoMtx);
        unitX2.transform(ortoMtx);
        unitX3.transform(ortoMtx);
    }
    
    //vászon elforgatása X tengely mentén
    public void rotateX( double degrees ) {
        double radians = Math.toRadians(degrees);
        
        double[][] trans = {
            { 1.0, 0.0, 0.0 },
            { 0.0, Math.cos(radians), -Math.sin(radians) },
            { 0.0, Math.sin(radians),  Math.cos(radians) }
        };
        
        this.rotateCanvas(trans); 
    }
    
    //vászon elforgatása Y tengely mentén
    public void rotateY( double degrees ) {
        double radians = Math.toRadians(degrees);
        
        double[][] trans = {
            { Math.cos(radians), 0.0, -Math.sin(radians) },
            { 0.0, 1.0, 0.0 },
            { Math.sin(radians), 0.0,  Math.cos(radians) }
        };
        
        this.rotateCanvas(trans);
    }
    
    //vászon elforgatása Z tengely mentén
    public void rotateZ(double degrees) {
        double radians = Math.toRadians(degrees);
        
        double[][] trans = {
            { Math.cos(radians), -Math.sin(radians), 0.0 },
            { Math.sin(radians),  Math.cos(radians), 0.0 },
            { 0.0, 0.0, 1.0 }
        };
        
        this.rotateCanvas(trans);
    }

	//getterek és setterek
	public Point getUnitX1() {
		return unitX1;
	}

	public void setUnitX1(Point unitX1) {
		this.unitX1 = unitX1;
	}

	public Point getUnitX2() {
		return unitX2;
	}

	public void setUnitX2(Point unitX2) {
		this.unitX2 = unitX2;
	}
	
	public Point getUnitX3() {
		return unitX3;
	}

	public void setUnitX3(Point unitX2) {
		this.unitX2 = unitX3;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}
	
	public Point getViewPoint() {
		return viewPoint;
	}

	public void setViewPoint(Point viewPoint) {
		this.viewPoint = viewPoint;
	}

	public double getCanvasWidth() {
		return canvasWidth;
	}

	public void setCanvasWidth(double canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	public double getCanvasHeight() {
		return canvasHeight;
	}

	public void setCanvasHeight(double canvasHeight) {
		this.canvasHeight = canvasHeight;
	}
	
	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}
}
