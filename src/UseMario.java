import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Objects.Cube;
import Objects.Floor;
import Objects.Pipe;
import Objects.Solid;
import Objects.SurpriseCube;
import characters.Bowser;
import characters.Charact;
import image.FrameImage;

public class UseMario {
	ArrayList<Solid> solids;
	ArrayList<Charact> characters;
	public UseMario(){
		boolean alive = true;
		solids  = new ArrayList<Solid>();
		characters = new ArrayList<Charact>();
		Bowser bowser = new Bowser(50, 30);
		characters.add(bowser);
		loadComponents();
		FrameImage f = new FrameImage();
		do {
			//IO
			ArrayList<Integer> actions = f.getEvents();
			//Logic
			for (int i = 0; i < actions.size(); i++) {
				boolean canMove = true;
				if(actions.get(i) == KeyEvent.VK_RIGHT) {
					for (int j = 0; j < solids.size(); j++) {
						if(solids.get(j).getX() <= bowser.getX() + bowser.getW() + 1 && solids.get(j).getX() + solids.get(j).getW() >= bowser.getX() + 1 &&
								solids.get(j).getY() < bowser.getY() + bowser.getH() && solids.get(j).getY() + solids.get(j).getH() > bowser.getY()) {
							canMove = false;
						}
					} 
					if (canMove) {
						bowser.move(0);
					}
				} else if(actions.get(i) == KeyEvent.VK_LEFT) {
					for (int j = 0; j < solids.size(); j++) {
						if(solids.get(j).getX() + solids.get(j).getW() >= bowser.getX() - 1 && solids.get(j).getX() <= bowser.getX() - 1 &&
								solids.get(j).getY() < bowser.getY() + bowser.getH() && solids.get(j).getY() + solids.get(j).getH() > bowser.getY()) {
							canMove = false;
						}
					}
					if (canMove) {
						bowser.move(1);
					}

				} else if (actions.get(i) == KeyEvent.VK_UP) {
					bowser.jump(false, false);
					for (int j = 0; j < solids.size(); j++) {	
						if(solids.get(j).getY() + solids.get(j).getH() < bowser.getY() + 1 && //solids.get(j).getH() > bowser.getY() + 1 && 
								(solids.get(j).getX() <= bowser.getX() && solids.get(j).getX() + solids.get(j).getW() >= bowser.getX()) ||
								(solids.get(j).getX() <= bowser.getX() + bowser.getW() && solids.get(j).getX()+ solids.get(j).getW() >= bowser.getX() 
								+ bowser.getW() && solids.get(j).getY() - 1 < bowser.getY() + bowser.getH() && solids.get(j).getY() > bowser.getY() + 1)) {
							bowser.gravity();
						}
					}
//					for (int j = 0; j < solids.size(); j++) {
//						if(solids.get(j).getY() + solids.get(j).getH() < bowser.getY() + 1 &&
//								solids.get(j).getY() > bowser.getY() + 1) {
//							bowser.gravity();
//
//						}
//					}						
				}
			}
			//Gravity
			boolean bowserMove = true;
			for (int i = 0; i < solids.size(); i++) {	
				if(solids.get(i).getY() < bowser.getY() + bowser.getH() + 1 && solids.get(i).getY() > bowser.getY() - 1 && 
						(solids.get(i).getX() <= bowser.getX() && solids.get(i).getX() + solids.get(i).getW() >= bowser.getX()) ||
						(solids.get(i).getX() <= bowser.getX() + bowser.getW() && solids.get(i).getX()+ solids.get(i).getW() >= bowser.getX() 
						+ bowser.getW() && solids.get(i).getY() - 1 < bowser.getY() + bowser.getH() && solids.get(i).getY() > bowser.getY() + 1)) {
					bowserMove = false;
				}
			}
			if (bowserMove) {
				bowser.gravity();
			}
			//			for (int i = 0; i < characters.size(); i++) {
			//				
			//					charcter.gravity();
			//				}
			//			}
			if (bowser.getJumping()) {
				bowser.jump(false, false);
			}
			//Draw
			f.Draw(characters, solids);
			try {
				Thread.sleep(1000/24);
			} catch (InterruptedException e) {}
		} while(alive);

	}
	public static void main(String[] args) {
		new UseMario();

	}
	//Cargar el mapa
	public void loadComponents() {
		try {
			BufferedReader fr = new BufferedReader(new FileReader("resources/nivel1.obj"));
			String line = fr.readLine();
			int count = 0; //Contador de lineas
			while(line != null) {
				String[] tmp = line.split(","); //Separa String en arreglos
				for(int i = 0; i < tmp.length; i++) {
					int value = Integer.parseInt(tmp[i]); 
					//Piso
					if(value == 1) { 
						solids.add(new Floor(count, i));
					} else if(value == 2) { 
						solids.add(new Pipe(count, i));
					} else if(value == 3) { 
						solids.add(new Cube(count, i));
					} else if(value == 4) { 
						solids.add(new SurpriseCube(count, i));
					}	
				}
				count++;
				line = fr.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
