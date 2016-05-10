package image;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Objects.Solid;
import characters.Charact;

public class FrameImage extends JFrame implements KeyListener{

	private static final long serialVersionUID = 1L;
	private static final int HEIGTH = 678;
	private static final int WIDHT = 800;
	private PanelImage pi;
	private ArrayList<Integer> events;
	public FrameImage () {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		int width = (int) d.getWidth();
		events = new ArrayList<Integer>();
		int height = (int) d.getHeight();
		
		pi = new PanelImage();
		add(pi);
		
		setSize(WIDHT, HEIGTH);
		setResizable(false);		
		setTitle("MARIO BROSS");

		int centerX = (int) ((width - getSize().getWidth()) / 2);
		int centerY = (int) ((height - getSize().getHeight()) / 2);
		setLocation(centerX, centerY);
		setVisible(true);
		addKeyListener(this); //Ayuda a detectar cuando una tecla se presiona
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void draw(ArrayList<Charact> characters, ArrayList<Solid> solids) {
		//Se crea un arrayList de todo lo que se va a dibujar en el tablero
		ArrayList<Drawable> draws = new ArrayList<Drawable>();
		draws.add(new Background());
		for(int i = 0; i < solids.size(); i++) {
			draws.add(solids.get(i)); //Imagen, PosiciónX * 50, PosicionY * 50, tamaño del cubo (50, 50)
		}
		for(int i = 0; i < characters.size(); i ++) {
			draws.add(characters.get(i));
		}
		pi.drawComponent(draws);
	}
	public void draw(Drawable draw) {
		pi.drawComponent(draw);
	}
	public ArrayList<Integer> getEvents(){
		ArrayList <Integer> tmp = (ArrayList<Integer>) events.clone();
		events = new ArrayList<Integer>();
		return tmp;
	}
	public void keyPressed(KeyEvent e) { 
		events.add(e.getKeyCode());
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
