package image;

import java.awt.Image;

public class Drawable {
	private int x, y, h, w;
	private Image image;
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	
	public int getH() {
		return h;
	}
	public int getW() {
		return w;
	}
	public Image getImage() {
		return image;
	}
	public Drawable(Image image, int x, int y, int h, int w) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.image = image;
	}
}
