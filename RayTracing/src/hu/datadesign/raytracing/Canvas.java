/**
 * K�palkot� v�szon:
 *   - defini�l a t�rben egy t�glalapot �s - az orig�b�l n�zve - mindig pontosan k�z�pen m�g�tte 
 *   egy n�z�pontot (itt van a kamera)
 *   - a v�szon (a kamer�val) t�rben elforgathat� a b�rmely tengely k�r�l megadott fokkal
 *   - be�p�tett zoom is van
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
	
	//v�szon elforgat�sa egy ortogon�lis m�rtixszal
    private void rotateCanvas( double[][] ortoMtx ) {
    	center.transform(ortoMtx); 
        viewPoint.transform(ortoMtx);
        unitX1.transform(ortoMtx);
        unitX2.transform(ortoMtx);
        unitX3.transform(ortoMtx);
    }
    
    //v�szon elforgat�sa X tengely ment�n
    public void rotateX( double degrees ) {
        double radians = Math.toRadians(degrees);
        
        double[][] trans = {
            { 1.0, 0.0, 0.0 },
            { 0.0, Math.cos(radians), -Math.sin(radians) },
            { 0.0, Math.sin(radians),  Math.cos(radians) }
        };
        
        this.rotateCanvas(trans); 
    }
    
    //v�szon elforgat�sa Y tengely ment�n
    public void rotateY( double degrees ) {
        double radians = Math.toRadians(degrees);
        
        double[][] trans = {
            { Math.cos(radians), 0.0, -Math.sin(radians) },
            { 0.0, 1.0, 0.0 },
            { Math.sin(radians), 0.0,  Math.cos(radians) }
        };
        
        this.rotateCanvas(trans);
    }
    
    //v�szon elforgat�sa Z tengely ment�n
    public void rotateZ(double degrees) {
        double radians = Math.toRadians(degrees);
        
        double[][] trans = {
            { Math.cos(radians), -Math.sin(radians), 0.0 },
            { Math.sin(radians),  Math.cos(radians), 0.0 },
            { 0.0, 0.0, 1.0 }
        };
        
        this.rotateCanvas(trans);
    }

	//getterek �s setterek
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
