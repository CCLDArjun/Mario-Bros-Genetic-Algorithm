import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GeneticAlgorithm {
	private ArrayList <Individual> individuals =  new ArrayList <Individual>();
	private double mutationRate;
	private int popSize;
	private int numInputs;
	public static int numDone = 0;

/**
 * @author - Sri Kondapalli 
 * @param mutationRate - 
 * @param popSize
 * @param numInputs
 */

	public GeneticAlgorithm(double mutationRate, int popSize, int numInputs) {
		this.mutationRate = mutationRate; 
		this.popSize = popSize;
		this.numInputs = numInputs;
	}

	public void start(int times) throws InterruptedException, ExecutionException {
		for (int i = 0; i < times; i++) {
			main();
		}
	}


	private void main() throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(50);
		ArrayList<Future<Individual>> futures = new ArrayList<Future<Individual>>();
		for(int i = 0; i < popSize; i++) {
			Individual ind = new Individual(numInputs);
			futures.add(service.submit(ind));
		}
		System.out.println("Population size is" + popSize);
		/**
		 * @author Sri Kondapalli 
		 * places an Individual ind into a sorted ArrayList
		 */
		while (true) {
			System.out.println("counts "+GeneticAlgorithm.numDone+", "+popSize);
			if (GeneticAlgorithm.numDone == popSize-1) {
				for (int i = 0; i < futures.size(); i++) {
					Individual ind = futures.get(i).get(); 
					// add in a sorted manner into individuals ArrayList.
					if (individuals.size() == 0) individuals.add(ind);
					System.out.println("GOT HERE " + individuals.size());
					
					boolean didAdd =  false;
					int size = individuals.size();
					for(int j = 0; j < size; j++) {
						if (ind.getFitness()  >= individuals.get(j).getFitness()) {
							individuals.add(j, ind);
							didAdd = true;
						}
					}
					if (!didAdd) {
						individuals.add(0, ind);
					}
				}
				break;
			}
		}
		GeneticAlgorithm.numDone = 0;
		select();
	}


	private void select() {

		/**
		 * @author Sri Kondapalli
		 * 
		 * Adds the best individuals from the individuals arrayList, and reproduces pairs of best 70 individuals
		 * adds 30 new individuals to maintain population size(100). 
		 */
		int initSize = individuals.size();
		ArrayList<Individual> theBest = new ArrayList<Individual>();
		for(int i = 0; i < individuals.size()*0.03; i++) {
			theBest.add(individuals.get(i));

		}
		for(int i = 0; i < individuals.size()*0.69; i++) {
			NeuralNetwork m1 = NeuralNetwork.reproduce(individuals.get(i).getNN(), individuals.get(i + 1).getNN(), mutationRate);
			NeuralNetwork m2 = NeuralNetwork.reproduce(individuals.get(i).getNN(), individuals.get(i + 1).getNN(), mutationRate);

			theBest.add(new Individual(m1));
			theBest.add(new Individual(m2));
		}
		
		while (theBest.size() != initSize)
			theBest.add(new Individual(numInputs));
		
		individuals = theBest;
		if (mutationRate >= 0.01) {
			mutationRate = mutationRate * 0.5;
		}
	}
}







