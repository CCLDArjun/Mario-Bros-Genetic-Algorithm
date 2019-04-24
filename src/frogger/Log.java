package frogger;

@SuppressWarnings("serial")
public class Log extends Moveable{
	// small, medium, large
	// {x, y, width, height}
	static int[][] logs = {{0, 100, 150, 25}, {150, 100, 70, 25}, {0, 130, 100, 25}};
//	int speedt = (int) Math.random() * 20 - 10;
//	boolean goingRightt = (Math.random() > 0.5);
	
	public Log(int x, int y, int num) {
		super(x, y, logs[num][2], logs[num][3], logs[num][0], logs[num][1], logs[num][2], logs[num][3], (x < 350), (int) (Math.random() * 2 + 1));
	}

	public void collided(Frogger frog) {
		move(frog);
	}
}
