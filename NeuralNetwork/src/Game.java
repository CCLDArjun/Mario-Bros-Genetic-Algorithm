
public interface Game {
	public double[][] getState();
	public boolean isDone = false;
	public void jump();
	public void moveRight();
	public void moveLeft();
	public double getFitness();
	public void start();
}
