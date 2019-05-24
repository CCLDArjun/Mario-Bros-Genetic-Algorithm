
import java.util.concurrent.ExecutionException;

public class Trainer {
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		Trainer t = new Trainer();
		t.start();
	}
	
	public void start() throws InterruptedException, ExecutionException {
		GeneticAlgorithm ga = new GeneticAlgorithm(0.9, 10, 169);
		ga.start((int)Double.MAX_VALUE);
	}
	
	public void indiv() {
		Individual ind = new Individual(169);
		ind.call();
	}
}
