package frogger;

@SuppressWarnings("serial")
public abstract class Moveable extends Gameobj implements Interactable{
	boolean goingRight;
	int speed;
	
	public Moveable(int x, int y, int width, int height, int x_i, int y_i, int width_i, int height_i, boolean goingRight, int speed) {
		super(x, y, width, height, x_i, y_i, width_i, height_i);
		this.goingRight = goingRight;
		this.speed = speed;
	}
	
	public boolean is_collide(Frogger frog) {
//		System.out.println("I tri");
		if (frog.intersects(this.getBounds())){
			collided(frog);
		}
		return frog.intersects(this.getBounds());
	}

	public void move(Gameobj g) {
		if (goingRight) {
			g.x = g.x + speed;
		}
		else {
			g.x = g.x - speed;	
		}
		g.resetRect();
	}
}
