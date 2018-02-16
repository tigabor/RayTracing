/**
 * Egy térbeli pont vagy helyvektor:
 *   - eltolható (összeadás, kivonás), nyújtható (skalárral szorzás), egységnyi hosszúra alakítható (normálás)
 *   - vektoriális szorzatot is számol
 */
package hu.datadesign.raytracing;

/**
 * @author tigabor@gmail.com
 *
 */
public class Point {
	private double x1, x2, x3;
	
	//konstruktorok
	Point(double x1, double x2, double x3) {
		setCoords(x1, x2, x3);
	}
	
	Point() {
		this(0.0, 0.0, 0.0);
	}
	
	Point( Point p ) {
		setCoords(p);
	}
	
	//koordináták beállítáa
	void setCoords( double x1, double x2, double x3 )  {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
	}
	
	//koordináták beállítáa
		void setCoords( Point p )  {
			this.x1 = p.getX1();
			this.x2 = p.getX2();
			this.x3 = p.getX3();
		}
	
    //pont hozzáadása (új pont jön létre)
    public Point add( Point p ) {
        return new Point( getX1() + p.getX1(), getX2() + p.getX2(), getX3() + p.getX3() );
    }
    
   //pont eltolása (az eredeti változik)
    public Point push( Point p ) {
    	this.x1 += p.getX1();
    	this.x2 += p.getX2();
    	this.x3 += p.getX3();    	
        return this;
    }
	
    //helyvektor kivonása (új vektor jön létre)
    public Point minus( Point p ) {
        return new Point( getX1() - p.getX1(), getX2() - p.getX2(), getX3() - p.getX3() );
    }
    
    //helyvektor szorzása skalárral (új pont jön létre)
    public Point multiply( double l ) {
        return new Point( getX1() * l, getX2() * l, getX3() * l );
    }
    
    //helyvektor normálása (az eredmény hossza: 1)
    public Point norm() {
        double length = Math.sqrt(x1 * x1 + x2 * x2 + x3 * x3);
        return this.multiply( 1 / length );
    }
    
    //helyvektor nyújtása (az eredeti változik)
    public Point extend( double l ) {
    	this.x1 *= l;
    	this.x2 *= l;
    	this.x3 *= l; 
        return this;
    }
    
    //két helyvektor skaláris szorzata
    public static double dotProduct( Point v, Point w ) {
        return v.getX1() * w.getX1() + v.getX2() * w.getX2() + v.getX3() * w.getX3();
    }
    
    //vektoriális szorzás (új vektor jön létre)
    public Point product( Point p ) {
        double s1 = getX2() * p.getX3() - getX3() * p.getX2();
        double s2 = getX3() * p.getX1() - getX1() * p.getX3();
        double s3 = getX1() * p.getX2() - getX2() * p.getX1();

        return new Point( s1, s2, s3 );
    }
    
    //ortogonális transzformáció (az eredeti vektoron)
    //paraméter: mtx - egy térbeli transzformációt végzõ ortogolális mátrix
    public Point transform( double[][] mtx ) {
        double[] me = { x1, x2, x3 }; 
        double[] res = new double[3];
        
        for (int i = 0; i < 3; i++) {
            res[i] = 0.0;
            for (int j = 0; j < 3; j++) {
                res[i] += mtx[i][j] * me[j];
            }
        }
        
        x1 = res[0];
        x2 = res[1];
        x3 = res[2];
        
        return this;
    }
    
	public String toString() {
		return "coords: " + getX1() + ", " + getX2() + ", " + getX3();
	}
	
	//getterek
	public double getX1() {
		return x1;
	}
	
	public double getX2() {
		return x2;
	}
	
	public double getX3() {
		return x3;
	}
	
	public double abs() { return Math.sqrt( x1*x1 + x2*x2 + x3*x3 ); }
}
