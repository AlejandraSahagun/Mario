package image;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;

public class PanelImage extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Drawable> draws;
	int corrimiento = 50;
	public void paintComponent(Graphics g) {
		if(draws != null) {
			for (int i = 0; i < draws.size(); i++) {
				g.drawImage(draws.get(i).getImage(), draws.get(i).getX() - corrimiento, draws.get(i).getY(), draws.get(i).getW(), draws.get(i).getH(), this);
			}
		}	
	}
	public void drawComponent(ArrayList<Drawable> draws) {
		this.draws = draws;
		repaint();
	}
	
	public void drawComponent(Drawable draw) {
		this.draws = new ArrayList<Drawable>();
		this.draws.add(draw);
		repaint();
		
	}
}
