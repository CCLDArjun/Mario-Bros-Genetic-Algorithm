import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GeneticAlgorithm {
	private ArrayList <Individual> individuals =  new ArrayList <Individual>();
	private double mutationRate;
	private int popSize;
	
	
	public GeneticAlgorithm(double mutationRate, int popSize, int numInputs) {
		this.mutationRate = mutationRate; 
		this.popSize = popSize;
	}
	
	public void start(int times) throws InterruptedException, ExecutionException {
		for (int i=0; i<times; i++) {
			main();
		}
	}
	
	
	private void main() throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(10);
		ArrayList<Future<Individual>> futures = new ArrayList<Future<Individual>>();
		
		for(int i = 0; i < popSize; i++) {
			Individual ind = new Individual(24, null);
			futures.add(service.submit(ind));
		}
		
		
		for (int i=0; i<futures.size(); i++) {
			Individual ind = futures.get(i).get();
			// add in a sorted manner into individuals ArrayList.
		}
		select(individuals);
	}


	private void select(ArrayList<Individual> inds) {
		// individuals should be sorted, kill ones who are doing bad, 
		// repoduce good ones, dont reproduce best, just keep them in the
		// new generation
	}
}







