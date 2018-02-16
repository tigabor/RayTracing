/**
 * Egy t�rbeli pont vagy helyvektor:
 *   - eltolhat� (�sszead�s, kivon�s), ny�jthat� (skal�rral szorz�s), egys�gnyi hossz�ra alak�that� (norm�l�s)
 *   - vektori�lis szorzatot is sz�mol
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
	
	//koordin�t�k be�ll�t�a
	void setCoords( double x1, double x2, double x3 )  {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
	}
	
	//koordin�t�k be�ll�t�a
		void setCoords( Point p )  {
			this.x1 = p.getX1();
			this.x2 = p.getX2();
			this.x3 = p.getX3();
		}
	
    //pont hozz�ad�sa (�j pont j�n l�tre)
    public Point add( Point p ) {
        return new Point( getX1() + p.getX1(), getX2() + p.getX2(), getX3() + p.getX3() );
    }
    
   //pont eltol�sa (az eredeti v�ltozik)
    public Point push( Point p ) {
    	this.x1 += p.getX1();
    	this.x2 += p.getX2();
    	this.x3 += p.getX3();    	
        return this;
    }
	
    //helyvektor kivon�sa (�j vektor j�n l�tre)
    public Point minus( Point p ) {
        return new Point( getX1() - p.getX1(), getX2() - p.getX2(), getX3() - p.getX3() );
    }
    
    //helyvektor szorz�sa skal�rral (�j pont j�n l�tre)
    public Point multiply( double l ) {
        return new Point( getX1() * l, getX2() * l, getX3() * l );
    }
    
    //helyvektor norm�l�sa (az eredm�ny hossza: 1)
    public Point norm() {
        double length = Math.sqrt(x1 * x1 + x2 * x2 + x3 * x3);
        return this.multiply( 1 / length );
    }
    
    //helyvektor ny�jt�sa (az eredeti v�ltozik)
    public Point extend( double l ) {
    	this.x1 *= l;
    	this.x2 *= l;
    	this.x3 *= l; 
        return this;
    }
    
    //k�t helyvektor skal�ris szorzata
    public static double dotProduct( Point v, Point w ) {
        return v.getX1() * w.getX1() + v.getX2() * w.getX2() + v.getX3() * w.getX3();
    }
    
    //vektori�lis szorz�s (�j vektor j�n l�tre)
    public Point product( Point p ) {
        double s1 = getX2() * p.getX3() - getX3() * p.getX2();
        double s2 = getX3() * p.getX1() - getX1() * p.getX3();
        double s3 = getX1() * p.getX2() - getX2() * p.getX1();

        return new Point( s1, s2, s3 );
    }
    
    //ortogon�lis transzform�ci� (az eredeti vektoron)
    //param�ter: mtx - egy t�rbeli transzform�ci�t v�gz� ortogol�lis m�trix
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
