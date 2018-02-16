/**
 * L�mpa:
 *   - egy feh�r G�mb, ami a k�z�ppontj�b�l f�nyt is kibocs�jt (a megvil�g�t�st biztos�tja)
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

