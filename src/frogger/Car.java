package frogger;

@SuppressWarnings("serial")
public class Car extends Moveable{
	// {x, y, width, height}
	static int[][] cars = {{0, 155, 70, 23}, {75, 155, 120, 23}, {5, 190, 50, 23}, {60, 190, 50, 23}, {115, 190, 50, 23}};
	
	public Car(int x, int y, int num) {
		super(x, y, cars[num][2], cars[num][3], cars[num][0], cars[num][1], cars[num][2], cars[num][3], (x < 350), (int) (Math.random() * 10 + 1));
	}

	public void collided(Frogger frog) {
		frog.alive = false;
	}

}
