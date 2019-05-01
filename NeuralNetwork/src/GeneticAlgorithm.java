import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GeneticAlgorithm implements Callable {
	private ArrayList <Individual> individuals =  new ArrayList <Individual>();
	private double mutationRate;
	private int popSize;
	
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
	
	
	public int findNextIndex() {
		return 1;
	}
	
	
	
	@Override
	public Object call() throws Exception {

		return null;
	}

}
