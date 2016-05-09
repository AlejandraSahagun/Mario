package image;

public final class Sprite {
	private final int measure;
	private int x;
	private int y;
	
	public int [] pixels;
	private final SpriteSheet sheet;

	public Sprite(final int measure, final int column, final int row, final SpriteSheet sheet) {
		this.measure = measure;
		
		pixels = new int[measure * measure];
		
		
		this.x = column * measure;
		this.y = row * measure;
		this.sheet = sheet;
		
		//Dibujar la pantalla de izquierda a derecha
		for(int i = 0; i < measure; i ++) { 
			//Dibujar la pantalla de arriba a abajo
			for(int j = 0; i <  measure; j++) {
				//Accede a las coordenadas y se multiplica por el lado
				pixels[j + i * measure] = sheet.pixels[(i + this.x) + (j + this.y) * sheet.getWidth()];
			}
			
		}
	}

}
