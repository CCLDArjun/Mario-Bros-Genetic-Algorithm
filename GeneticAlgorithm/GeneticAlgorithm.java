import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;



public class GeneticAlgorithm implements Callable, Runnable {
	private ArrayList <Individual> individuals =  new ArrayList <Individual>();
	private double mutationRate;
	private int popSize;
	public NeuralNetwork n;
	
	public GeneticAlgorithm(double mutationRate, int popSize, int numInputs) {
		this.mutationRate = mutationRate; 
		this.popSize = popSize;
		for(int i = 0; i < popSize; i++) {
			Individual ind = new Individual(numInputs);
			individuals.add(new Individual(numInputs));
			ind.play();
			int index = findNextIndex();		
		}
	}
	public static void MultiThreading() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		
		for(int i = 0; i < 10; i++) {
			Runnable worker = new GeneticAlgorithm(i); 
			executor.execute(worker);
			n.reproduce();
		}
		
		executor.shutdown();
		
		
	}

	
	public int findNextIndex() {
		return 1;
	}
	
	
	
	@Override
	public Object call() throws Exception {

		return null;
	}


	@Override
	public void run() {
		
	}

}
