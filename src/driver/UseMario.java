package driver;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Objects.Castle;
import Objects.Coin;
import Objects.Cube;
import Objects.Floor;
import Objects.Peach;
import Objects.Pipe;
import Objects.Solid;
import Objects.SurpriseCube;
import Objects.UsedBlock;
import characters.Bowser;
import characters.Charact;
import characters.Yoshi;
import image.FrameImage;
import image.GameOver;
import image.YouWin;

public class UseMario {
	ArrayList<Solid> solids;
	ArrayList<Charact> characters;
	public static int corrimiento = 0;
	public UseMario(){
		boolean alive = true;
		solids  = new ArrayList<Solid>();
		characters = new ArrayList<Charact>();
		Bowser bowser = new Bowser(50, 500);
		characters.add(bowser);
		Yoshi yoshi1 = new Yoshi(600);
		characters.add(yoshi1);
		
		
		loadComponents();
		music();
		FrameImage f = new FrameImage();

		do {
			//IO
			ArrayList<Integer> actions = f.getEvents();

			//********************************* Monedas ***************************************			
			for (int i = 0; i < solids.size(); i++) {
				if(solids.get(i) instanceof Coin) {
					if(solids.get(i).getY() < bowser.getY() + bowser.getH() + 2 && solids.get(i).getY() + solids.get(i).getH() > bowser.getY() + bowser.getH() + 2 && 
							(solids.get(i).getX() <= bowser.getX() && solids.get(i).getX() + solids.get(i).getW() >= bowser.getX() ||
							solids.get(i).getX() < bowser.getX() + bowser.getW() && solids.get(i).getX()+ solids.get(i).getW() > bowser.getX() 
							+ bowser.getW())) {
						solids.remove(i);
						break;
					}
					else if(solids.get(i).getX() + solids.get(i).getW() >= bowser.getX() - 2 && solids.get(i).getX() <= bowser.getX() - 2 &&
							(solids.get(i).getY() < bowser.getY() + bowser.getH() && solids.get(i).getY() + solids.get(i).getH() > bowser.getY())) {
						solids.remove(i);
						break;
					}

					else if(solids.get(i).getY() + solids.get(i).getH() >= bowser.getY() - 2 && solids.get(i).getY() < bowser.getY() - 2 && 
							(solids.get(i).getX() <= bowser.getX() && solids.get(i).getX() + solids.get(i).getW() >= bowser.getX() ||
							solids.get(i).getX() < bowser.getX() + bowser.getW() && solids.get(i).getX()+ solids.get(i).getW() > bowser.getX() 
							+ bowser.getW())) {
						solids.remove(i);
						break;
					}
					else if(solids.get(i).getX() < bowser.getW() + bowser.getX() + 2 && solids.get(i).getX() + solids.get(i).getW() > bowser.getX() + bowser.getW() + 2 &&
							(solids.get(i).getY() < bowser.getY() + bowser.getH() && solids.get(i).getY() + solids.get(i).getH() > bowser.getY() + bowser.getH() ||
									solids.get(i).getY() + solids.get(i).getH() > bowser.getY() && solids.get(i).getY() < bowser.getY())) {
						solids.remove(i);
						break;
					}
				}
			}

			//********************************* Yoshi *************************************
			
			
			for (int i = 0; i < solids.size(); i++) {
				//Colision izquierda
				if(solids.get(i).getX() + solids.get(i).getW() >= yoshi1.getX() - 2 && solids.get(i).getX() <= yoshi1.getX() - 2 &&
						(solids.get(i).getY() < yoshi1.getY() + yoshi1.getH() && solids.get(i).getY() + solids.get(i).getH() > yoshi1.getY())) {
					yoshi1.changeDirection();
					break;
					
				}
				//Colision derecha
				else if(solids.get(i).getX() < yoshi1.getW() + yoshi1.getX() + 2 && solids.get(i).getX() + solids.get(i).getW() > yoshi1.getX() + yoshi1.getW() + 2 &&
						(solids.get(i).getY() < yoshi1.getY() + yoshi1.getH() && solids.get(i).getY() + solids.get(i).getH() > yoshi1.getY() + yoshi1.getH() ||
								solids.get(i).getY() + solids.get(i).getH() > yoshi1.getY() && solids.get(i).getY() < yoshi1.getY())) {
					yoshi1.changeDirection();
					break;
				}
			}	
			for(int i = 0; i < characters.size(); i++) {
				if (!(characters.get(i) instanceof Bowser)) {
					characters.get(i).move(0);
				}
			}





			//Logic

			for (int i = 0; i < actions.size(); i++) {
				boolean canMove = true;
				if(actions.get(i) == KeyEvent.VK_RIGHT) {
					for (int j = 0; j < solids.size(); j++) {
						if(solids.get(j).getX() <= bowser.getX() + bowser.getW() + 2 && solids.get(j).getX() + solids.get(j).getW() >= bowser.getX() + 2 &&
								solids.get(j).getY() < bowser.getY() + bowser.getH() && solids.get(j).getY() + solids.get(j).getH() > bowser.getY()) {
							canMove = false;
						}
					} 
					if (canMove) {
						bowser.move(0);
						if (bowser.getX() - corrimiento > 400) {
							corrimiento += 2;
						}
					}
				} else if(actions.get(i) == KeyEvent.VK_LEFT) {
					for (int j = 0; j < solids.size(); j++) {
						if(solids.get(j).getX() + solids.get(j).getW() >= bowser.getX() - 2 && solids.get(j).getX() <= bowser.getX() - 2 &&
								solids.get(j).getY() < bowser.getY() + bowser.getH() && solids.get(j).getY() + solids.get(j).getH() > bowser.getY()) {
							canMove = false;
						}
					}
					if (canMove) {
						bowser.move(1);
					}

				} else if (actions.get(i) == KeyEvent.VK_UP) {
					bowser.setJump();
					bowser.sound(0);
				}
			}
			//Gravity
			boolean bowserMove = true;
			for (int i = 0; i < solids.size(); i++) {	
				if(solids.get(i).getY() < bowser.getY() + bowser.getH() + 2 && solids.get(i).getY() + solids.get(i).getH() > bowser.getY() + bowser.getH() + 2 && 
						(solids.get(i).getX() <= bowser.getX() && solids.get(i).getX() + solids.get(i).getW() >= bowser.getX() ||
						solids.get(i).getX() < bowser.getX() + bowser.getW() && solids.get(i).getX()+ solids.get(i).getW() > bowser.getX() 
						+ bowser.getW())) {
					bowserMove = false;
				}
			}
			if (bowser.getJumping()) {
				boolean canJump = true;
				for (int j = 0; j < solids.size(); j++) {	
					if(solids.get(j).getY() + solids.get(j).getH() >= bowser.getY() - 2 && solids.get(j).getY() <= bowser.getY() - 2 && 
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
				bowser.sound(1);
				alive = false;
			} 
			//********************************* Cubo Sorpresa ****************************************
			for (int i = 0; i < solids.size(); i++) {
				if(solids.get(i) instanceof SurpriseCube) {
					if((solids.get(i).getY() + solids.get(i).getH() <= bowser.getY() - 2 && solids.get(i).getY() + solids.get(i).getH() >= bowser.getY() - 2 &&
							(solids.get(i).getX() <= bowser.getX() && solids.get(i).getX() + solids.get(i).getW() >= bowser.getX() ||
							solids.get(i).getX() < bowser.getX() + bowser.getW() && solids.get(i).getX()+ solids.get(i).getW() > bowser.getX() 
							+ bowser.getW()))) {
						solids.add(new UsedBlock(solids.get(i).getX() / 50, solids.get(i).getY() / 50));
						solids.remove(i);
						break;
					}
				}
			}

			if(bowser.getX() + bowser.getH() >= 3150) {	
				break;
			}

			f.draw(characters, solids);
			try {
				Thread.sleep(1000/24);
			} catch (InterruptedException e) { }
		} while(alive);

		if(!alive) {
			f.draw(new GameOver());
		} else {
			f.draw(new YouWin());
			bowser.sound(2);
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
					} else if(value == 5) { 
						solids.add(new Castle(count, i));
					} else if(value == 6) {
						solids.add(new Coin(count, i));
					} else if(value == 7) { 
						solids.add(new Peach(count, i));
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
	public void music() {
		Clip sound = null;
		try {
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(new File("resources/back.wav")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		sound.start();
		sound.drain();
	}
}
