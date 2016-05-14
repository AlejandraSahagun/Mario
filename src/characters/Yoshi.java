package characters;

import javax.swing.ImageIcon;

import image.Drawable;

public class Yoshi extends Charact {
	private boolean moveDirection = true;
	private int direction = 0;

	public Yoshi(int x) {
		super(new ImageIcon("resources/yoshi.png"), x, 500, 50, 50);	
	}

	public void move(int i) {		
		if (direction == 0) {
			setX(getX() + 2);
		} else if (direction == 1) {
			setX(getX() - 2);
		}
	}
	public void changeDirection() {
		if (direction == 0) {
			direction = 1;
		} else {
			direction = 0;
		}
	}
	public Drawable get(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
