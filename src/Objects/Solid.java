package Objects;

import javax.swing.ImageIcon;

import image.Drawable;

public class Solid extends Drawable {
	
	public Solid(ImageIcon image, int x, int y, int h, int w) {
		super(image.getImage(), x * 50, y * 50, h, w);
	}

	public String toString() {
		return "Solid [getX()=" + getX() + ", getY()=" + getY() + ", getH()=" + getH() + ", getW()=" + getW() + "]";
	}
	
}
