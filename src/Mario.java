import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mario{
	int x = 0;
	int y = 0;
	int tilex = 0;
	int tiley = 0;
	int prev_x = 0;
	int prev_y = 0;
	final int MAX_SPEED = 48;
	double x_vel = 0;
	double y_vel = 0;
	boolean inAir = true;
	boolean moved = false;
	boolean isLeft = false;
	boolean upButton = false;
	
	public Mario(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean collided(int x2, int y2) {
//		System.out.println(Math.abs(x - x2));
//		System.out.println(Math.abs((618 - y) - y2));
		return ((Math.abs(x - x2) < 48) && (Math.abs((618 - y) - y2) < 48));
	}
	
	public void jump() {
		if (!upButton) {
			if (inAir == false) {
				y_vel = 48;
	//			System.out.println("y");
			}
			else {
				y_vel += 7;
			}
			upButton = true;
		}
	}
	
	public void moveRight() {
		if (!moved) {
			x_vel += 1;
			moved = true;
			isLeft = false;
		}
//		System.out.println("x+");
	}

	public void moveLeft() {
		if (!moved) {
			x_vel -= 1;
			moved = true;
			isLeft = true;
		}
//		System.out.println("x-");
	}
	
	public void key(int[] useKeys) {
//		System.out.print(useKeys[0] + " ");
//		System.out.print(useKeys[1] + " ");
//		System.out.println(useKeys[2]);
		if (useKeys[1] == -1) {
			jump();
		}
		if (useKeys[0] == 1) {
			moveRight();
		}
		if (useKeys[0] == -1) {
			moveLeft();
		}
		
		if ((useKeys[2] == 1) && (useKeys[0] != 0)) {
			x_vel = x_vel * 1.2;
		}

	}
	
	public int draw(Graphics g, int[][] t, int offset) {
		int answer = 0;

//		System.out.println("x: " + x);
//		System.out.println("y: " + y);
//		System.out.println("prev_x: " + prev_x);
//		System.out.println("prev_y: " + prev_y);

		tilex = (int) Math.ceil(x / 48.0);
		tiley = (int) Math.ceil((624 - y) / 48.0);
//		g.setColor(Color.RED);
//		g.drawLine((tilex * 48) - offset, (tiley * 48), (tilex * 48) + 48 - offset, (tiley * 48));
//		g.drawLine((tilex * 48) - offset, (tiley * 48), (tilex * 48) - offset, (tiley * 48) + 48);
//		g.drawLine((tilex * 48) - offset, (tiley * 48) + 48, (tilex * 48) + 48 - offset, (tiley * 48) + 48);
//		g.drawLine((tilex * 48) + 48 - offset, (tiley * 48) + 48, (tilex * 48) + 48 - offset, (tiley * 48));
		if (tiley < 0) {
			tiley = 0;
		}
		if (tiley > 11) {
			tiley = 11;
		}
//		System.out.println(tilex);
//		System.out.println(tiley);
		if (t[tiley][tilex] == 1 && collided(tilex * 48 - offset, tiley * 48)) {
//			System.out.println("SUGONDESE");
//			System.out.println("CO");
			x = prev_x;
			y = prev_y;
			/*/
			if (x > tilex * 48 - offset) {
//				System.out.println("plus");
				x++;
			}
			else if (x < tilex * 48 - offset){
//				System.out.println("minus");
				x--;
			}

			if (y > tiley * 48) {
				y++;
			}
			else if (y < tiley * 48){
				y--;
			}
			/*/
		}
		
		boolean ti;
		if (tiley == 0) {
			ti = true;
		}
		else {
			ti = t[tiley - 1][tilex] == 1 && collided(tilex * 48 - offset, (tiley - 1) * 48);
		}

		if (ti && y_vel > 0) {
			y_vel = 0;
			y = y / 48 * 48;
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

		if (t[tiley][tilex + 1] == 1 && x_vel > 0 && collided((tilex + 1) * 48 - offset, tiley * 48)) {
			x_vel = 0;
			x = tilex * 48 - offset;
		}
		
		if (tilex == 0) {
			ti = true;
		}
		else {
			ti = t[tiley][tilex - 1] == 1 && collided((tilex - 1) * 48 - offset, tiley * 48);
		}

		if (ti && x_vel < 0) {
			x_vel = 0;
			x = tilex * 48 - offset;
		}
		
		Image img;
		String str = "frame_0_delay-0.1s.gif";
		try {
			if (!inAir) {
				if (Math.abs(x_vel) > 1) {
					str = "frame_" + ((x + offset) / 10 % 4) + "_delay-0.1s.gif";
				}
				else {
					str = "frame_1_delay-0.1s.gif";
				}
			}
			else {
				str = "frame_0_delay-0.1s.gif";
			}
			if (isLeft) {
				str = "L" + str;
			}
			img = ImageIO.read(new File(System.getProperty("user.dir") + "/" + str));
			g.drawImage(img, x, 624 - y, null);
		} catch (IOException e) {
			// 1 2 Oatmeal
		}
		
		if (x_vel > MAX_SPEED) {
			x_vel = MAX_SPEED;
		}
		if (x_vel < -MAX_SPEED) {
			x_vel = -MAX_SPEED;
		}
		if (y_vel < -MAX_SPEED) {
			y_vel = -MAX_SPEED;
		}

		
		prev_x = x;
		prev_y = y;

		x = (int) Math.round(x + x_vel);
		y = (int) Math.round(y + y_vel);
		if (inAir) {
			y_vel = y_vel - 10;
			x_vel = x_vel * 0.95;
		}
		else {
			x_vel = x_vel * 0.9;
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
		
		if (y > 624) {
			y = 624;
		}
//		System.out.println(x_vel);
//		System.out.println(y_vel);
		moved = false;
		upButton = false;
		//		System.out.println(answer);
		return answer;
	}
}
