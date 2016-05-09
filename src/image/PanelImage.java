package image;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;

public class PanelImage extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Drawable> draws;
	public void paintComponent(Graphics g) {
		if(draws != null) {
			for(Drawable d : draws) {
				g.drawImage(d.getImage(), d.getX(), d.getY(), d.getW(), d.getH(), this);
			}
		}	
	}
	public void drawComponent(ArrayList<Drawable> draws) {
		this.draws = draws;
		repaint();
	}
}
