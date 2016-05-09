package image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private final int width; //Ancho
	private final int height;
	public final int[] pixels;
	
	public SpriteSheet(final String url, final int width, final int height) {
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
		
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(url));
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getWidth() {
		return width;
	}
}
