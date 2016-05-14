package characters;

import javax.swing.ImageIcon;

public class Bowser extends Charact {

	private static final long serialVersionUID = 1L;
	private boolean isJumping = false;
	private int moveCounter = 0;
	private int jumps = 1;
	private boolean moveDirection = true;


	public Bowser(int x, int y) {
		super(new ImageIcon("resources/bowser_right1.png"), x, y, 50, 50);	
	}

	public void move(int direction) {		
		if (direction == 0) {
			setX(getX() + 2);
		} else if (direction == 1) {
			setX(getX() - 2);
		}
	}

	public void resetJump() {
		jumps = 1;
	}
	public boolean getJumping() {
		return isJumping;
	}
	public void setJump() {
		jumps--;
		moveCounter = 0;
		isJumping = true;
	}
	public void jump(boolean colisionUp, boolean colisionDown) {
		if (jumps > 0) {
			if(colisionUp) {
				isJumping = false;
			} else if(!isJumping) {
				moveCounter = 0;
				isJumping = true;
			} else if(isJumping) {
				if(moveCounter == 70 || colisionUp) {
					moveDirection = false;
					isJumping = false;
				} else {
					moveCounter++;
					setY(getY() - 2);
				}
			}
		}
	}

}
