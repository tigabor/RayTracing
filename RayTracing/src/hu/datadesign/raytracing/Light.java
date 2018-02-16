/**
 * Lámpa:
 *   - egy fehér Gömb, ami a középpontjából fényt is kibocsájt (a megvilágítást biztosítja)
 */
package hu.datadesign.raytracing;

import java.awt.Color;

public class Light extends SimpleSphere {
	
	Light( String name ) {
		super.setName(name);
		super.setOwnColor(Color.WHITE);
		super.setReflectRatio(0.0f);
	}

}

