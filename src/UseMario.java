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
import image.GameOver;

public class UseMario {
	ArrayList<Solid> solids;
	ArrayList<Charact> characters;
	public UseMario(){
		boolean alive = true;
		solids  = new ArrayList<Solid>();
		characters = new ArrayList<Charact>();
		Bowser bowser = new Bowser(400, 300);
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
					bowser.setJump();						
				}
			}
			//Gravity
			boolean bowserMove = true;
			for (int i = 0; i < solids.size(); i++) {	
				if(solids.get(i).getY() < bowser.getY() + bowser.getH() + 1 && solids.get(i).getY() + solids.get(i).getH() > bowser.getY() + bowser.getH() + 1 && 
						(solids.get(i).getX() <= bowser.getX() && solids.get(i).getX() + solids.get(i).getW() >= bowser.getX() ||
						solids.get(i).getX() < bowser.getX() + bowser.getW() && solids.get(i).getX()+ solids.get(i).getW() > bowser.getX() 
						+ bowser.getW())) {
					bowserMove = false;
				}
			}
			if (bowser.getJumping()) {
				boolean canJump = true;
				for (int j = 0; j < solids.size(); j++) {	
					if(solids.get(j).getY() + solids.get(j).getH() >= bowser.getY() - 1 && solids.get(j).getY() <= bowser.getY() - 1 && 
						(solids.get(j).getX() <= bowser.getX() && solids.get(j).getX() + solids.get(j).getW() >= bowser.getX() ||
									solids.get(j).getX() <= bowser.getX() + bowser.getW() && solids.get(j).getX() + solids.get(j).getW() >= bowser.getX() 
									+ bowser.getW())) {
						canJump = false;
					}
				}
				bowser.jump(!canJump, false);
			}
			if (bowserMove) {
				bowser.gravity();
			} else {
				bowser.resetJump();
			}
			if(bowser.getY() + bowser.getH() == 650) {
				alive = false;
			}
			//Draw
			f.draw(characters, solids);
			try {
				Thread.sleep(1000/24);
			} catch (InterruptedException e) {}
		} while(alive);
		
		if(!alive) {
			f.draw(new GameOver());
		}
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
