package characters;

import javax.swing.ImageIcon;
import image.Drawable;

public abstract class Charact extends Drawable {
	//int vida;
	//int direction;
	ImageIcon image;
	
	public Charact(ImageIcon image, int x, int y, int h, int w) {
		super(image.getImage(), x, y, h, w);
	}
	
	public void gravity() {
		setY(getY() + 1);
	}
	public abstract void move(int i);
}
