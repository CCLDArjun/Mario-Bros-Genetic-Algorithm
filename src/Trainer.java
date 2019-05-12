import java.util.concurrent.ExecutionException;

public class Trainer {
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		Trainer t = new Trainer();
		t.start();
	}
	
	public void start() throws InterruptedException, ExecutionException {
		GeneticAlgorithm ga = new GeneticAlgorithm(0.5, 2, 182);
		ga.start(10);
	}
}
