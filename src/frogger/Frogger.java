package frogger;

import java.awt.Graphics;

@SuppressWarnings("serial")
public class Frogger extends Gameobj{
	int move_y = 0;
	int back = 0;
	int frame = -1;
	boolean alive = true;
	long waits = System.currentTimeMillis();
	public Frogger(int x, int y) {
		super(x, y, 22, 30, 0, 0, size, size);
	}

	public void key(int[] useKeys) {
		x = x + (useKeys[0] * size);
//		y = y + (useKeys[1] * size);
		frame = 0;

		if (useKeys[1] != 0) {
			if ((useKeys[1] == 1) && (back < 10)) {
				y = y + size;
				back++;
			}
			else if ((useKeys[1] == -1) && (back > 0)){
				y = y - size;
				back--;
			}
			else if (back < 10){
				move_y++;
			}
		}
//		System.out.println(back);
		resetRect();
	}
	
	@Override
	public boolean draw(Graphics g) {
		if (frame != -1) {
			image = openImageFromSpriteSheet(22 * frame, 0, 22, 30);
			if (System.currentTimeMillis() - waits > 5) {
				if (frame < 6) {
					frame++;
				}
				else {
					frame = -1;
				}
				waits = System.currentTimeMillis();
			}
		}
		else {
			image = openImageFromSpriteSheet(0, 0, 22, 30);
		}
		return super.draw(g);
	}
}
