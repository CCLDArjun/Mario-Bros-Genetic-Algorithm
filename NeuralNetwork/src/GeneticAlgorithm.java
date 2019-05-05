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
	public NueralNetwork net; 


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
		int temp = 0;
		for(int i = 0; i < popSize; i++) {
			Individual ind = new Individual(24, null);
			futures.add(service.submit(ind));
		}


		for (int i=0; i<futures.size(); i++) {
			Individual ind = futures.get(i).get();
			// add in a sorted manner into individuals ArrayList.

			
		}

		for(int i = 0; i < individuals.size() -1; i++) {


			if (ind < individuals.get(i)) continue;

			if (ind  == individuals.get(i)) 

				individuals.add(i, ind);

		}

		select(individuals);
	}


	private void select(ArrayList<Individual> inds) {

		for(int i = inds.size() -1; i > 90; i--) {
			inds.remove(i);
		}

		for(int i = 0; i < inds.size() -1; i++) {
			net.reproduce(inds.get(i).network, inds.get(i + 1).network, mutationRate);
		}

		ArrayList<Individual> theBest = new ArrayList<Individual>();
		for(int i = 0: i < 3; i++) {
			theBest.add(individuals.get(i));
		}
		for(int i = 0; i < 69; i++) {
		NeuralNetwork m = GeneticAlgorithm.reproduce(individuals.get(i).n, individuals.get(i + 1).n, GeneticAlgorithm.mutationRate);
		theBest.add(new Individual(m, game));
		}
		for(int i = 0; i < 30; i++) {
			theBest.add(new Individual(network, game));
		}
		
	}
}







