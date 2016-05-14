package Objects;

import javax.swing.ImageIcon;

public class Coin extends Solid{
	public Coin(int x, int y) {
		super(new ImageIcon("resources/coins.png"), x, y, 50, 50);	
	}
}
