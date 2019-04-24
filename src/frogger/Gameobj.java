package frogger;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public abstract class Gameobj extends Rectangle{
	Image image;
	private Image spriteSheet;
	static int size = 25;
	int x;
	int y;
	int width;
	int height;
	
	public Gameobj(int x, int y, int width, int height, int x_i, int y_i, int width_i, int height_i) {
		super(x, y - 10, width, height - 10);
		this.x = x;
		this.y = y;
		this.width = width_i;
		this.height = height_i;
		openSpriteSheet();
		image = openImageFromSpriteSheet(x_i, y_i, width, height);
	}
	
	public void openSpriteSheet() {
		if (spriteSheet == null) {
			try {
				spriteSheet = ImageIO.read(new File("sprite_sheet.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
	public Image openImageFromSpriteSheet(int x, int y, int w, int h) {
		return ((BufferedImage)spriteSheet).getSubimage(x,y,w,h).getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
	}
	
	public boolean draw(Graphics g) {
		g.drawImage(image, x, y, null);
		return ((x < -width) || (y < -height) || (x >= 700) || (y >= 700));
	}
	
	public void resetRect() {
		setLocation(x, y);
	}
}
