package image;


import javax.swing.JPanel;

import characters.Bowser;
import driver.UseMario;

import java.awt.Graphics;
import java.util.ArrayList;

public class PanelImage extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Drawable> draws;
	Bowser bowser = new Bowser(getX(), getY());

	public void paintComponent(Graphics g) {
		if(draws != null) {
			for (int i = 0; i < draws.size(); i++) {
				if(draws.get(i) instanceof Background) {
					g.drawImage(draws.get(i).getImage(), draws.get(i).getX(), draws.get(i).getY(), draws.get(i).getW(), draws.get(i).getH(), this);
				} else if (draws.get(i) instanceof GameOver) {
					g.drawImage(draws.get(i).getImage(), draws.get(i).getX(), draws.get(i).getY(), draws.get(i).getW(), draws.get(i).getH(), this);
				} else if (draws.get(i) instanceof YouWin) {
					g.drawImage(draws.get(i).getImage(), draws.get(i).getX(), draws.get(i).getY(), draws.get(i).getW(), draws.get(i).getH(), this);
				} else {
					g.drawImage(draws.get(i).getImage(), draws.get(i).getX() - UseMario.corrimiento, draws.get(i).getY(), draws.get(i).getW(), draws.get(i).getH(), this);
				}
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
