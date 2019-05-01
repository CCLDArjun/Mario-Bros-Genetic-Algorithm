import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mario{
	int x = 0;
	int y = 0;
	double x_vel = 0;
	double y_vel = 0;
	boolean inAir = true;
	
	public Mario(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean collided(int x1, int y1, int x2, int y2) {
		return true;
	}
	
	public void key(int[] useKeys) {
//		System.out.print(useKeys[0] + " ");
//		System.out.print(useKeys[1] + " ");
//		System.out.println(useKeys[2]);
		if ((useKeys[1] == -1) && (inAir == false)) {
			y_vel = 48;
		}
		if (useKeys[0] == 1) {
			x_vel += 1;
		}
		if (useKeys[0] == -1) {
			x_vel -= 1;
		}
		
		if ((useKeys[2] == 1) && (useKeys[0] != 0)) {
			x_vel = x_vel * 1.2;
		}

	}
	
	public int draw(Graphics g, int[][] t, int offset) {
		int answer = 0;

		int tilex = (int) Math.ceil(x / 48.0);
		int tiley = (int) Math.ceil((624 - y) / 48.0);
		g.setColor(Color.RED);
		g.drawLine((tilex * 48) - offset, (tiley * 48), (tilex * 48) + 48 - offset, (tiley * 48));
		g.drawLine((tilex * 48) - offset, (tiley * 48), (tilex * 48) - offset, (tiley * 48) + 48);
		g.drawLine((tilex * 48) - offset, (tiley * 48) + 48, (tilex * 48) + 48 - offset, (tiley * 48) + 48);
		g.drawLine((tilex * 48) + 48 - offset, (tiley * 48) + 48, (tilex * 48) + 48 - offset, (tiley * 48));
		if (tiley < 0) {
			tiley = 0;
		}
		if (tiley > 13) {
			tiley = 13;
		}
//		System.out.println(tilex);
//		System.out.println(tiley);
		boolean ti;
		if (tiley == 0) {
			ti = true;
		}
		else {
			ti = t[tiley - 1][tilex] == 1;
		}

		if (ti && y_vel > 0) {
			y_vel = 0;
		}
		
		if (t[tiley + 1][tilex] == 1) {
			inAir = false;
			y = y / 48 * 48;
			if (y_vel < 0) {
				y_vel = 0;
			}
		}
		else {
			inAir = true;
		}

		if (t[tiley][tilex + 1] == 1 && x_vel > 0) {
			x_vel = 0;
		}
		
		if (tilex == 0) {
			ti = true;
		}
		else {
			ti = t[tiley][tilex - 1] == 1;
		}

		if (ti && x_vel < 0) {
			x_vel = 0;
		}

		x = (int) Math.round(x + x_vel);
		y = (int) Math.round(y + y_vel);
		x_vel = x_vel * 0.9;
		if (inAir) {
			y_vel = y_vel - 5;
		}
		if (x > 312) {
			answer = x - 312;
			x = 312;
		}
		if (x < 0) {
			x = 0;
			if (x_vel < 0) {
				x_vel = 0;
			}
		}
		
		Image img;
		try {
			img = ImageIO.read(new File("mario.png"));
			g.drawImage(img, x, 624 - y, null);
		} catch (IOException e) {
			// 1 2 Oatmeal
		}
		return answer;
	}
}
