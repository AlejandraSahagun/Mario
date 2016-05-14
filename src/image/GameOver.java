package image;

import javax.swing.ImageIcon;

public class GameOver extends Drawable {
	public GameOver(){
		super(new ImageIcon("resources/game_over.jpg").getImage(), 0, 0, 678, 800);
	}
}