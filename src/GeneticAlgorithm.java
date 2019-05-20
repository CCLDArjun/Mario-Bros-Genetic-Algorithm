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
	public int oldBest = 0;

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
			System.out.println("Starting generation "+(i+1));
			main();
		}
	}

	boolean firstTime = true;
	/**
	 * @author Sri Kondapalli 
	 * places an Individual ind into a sorted ArrayList
	 */
	private void main() throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(popSize);
		ArrayList<Future<Individual>> futures = new ArrayList<Future<Individual>>();
		if (firstTime) {
			for(int i = 0; i < popSize; i++) {
				Individual ind = new Individual(numInputs);
				futures.add(service.submit(ind));
			}
			firstTime = false;
		}
		else {
			for (Individual indi : individuals) {
				futures.add(service.submit(indi));
			}
		}
		System.out.println("Population size is " + popSize);
		double mean = 0;
		double best = 0;
		while (true) {
			Thread.sleep(1);
			if (GeneticAlgorithm.numDone >= popSize-1) {
				System.out.println("Saving Results...");
				individuals = new ArrayList<Individual>();
 				for (int i = 0; i < futures.size(); i++) {
					Individual ind = futures.get(i).get();
					mean += ind.getFitness();
					if (ind.getFitness()>best)
						best = ind.getFitness();
					int x = 0;
					while (x < individuals.size() && individuals.get(x).getFitness() >= ind.getFitness()) {
						x++;
					}
					individuals.add(x, ind);
				}
				break;
			}
		}
		System.out.println("Generation Score: " + (mean / individuals.size()));
		System.out.println("Best Fitness: "+ (best));
		Game.maxFrames += (int) ((best - oldBest) / 200.0) + 1;
		mutationRate = mutationRate * (oldBest / best + 0.5);
		if (mutationRate > 1) {
			mutationRate = 0.9999;
		}
		
		oldBest = (int) best;
		System.out.println(Game.maxFrames);
		System.out.println(mutationRate);
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
		System.out.println("Selecting...");
		int initSize = individuals.size();
		ArrayList<Individual> theBest = new ArrayList<Individual>();
		for(int i = 0; i < individuals.size() * 0.3; i++) {
			//System.out.println("WAITING HERE12");
			theBest.add(individuals.get(i));

		}
		for(int i = 0; i < individuals.size() * 0.7; i++) {
			//System.out.println("WAITING HERE1" + " " + i + " " + individuals.size() * 0.69);
			NeuralNetwork m1 = NeuralNetwork.reproduce(individuals.get((int) (Math.random() * individuals.size() * 0.3)).getNN(), individuals.get(i).getNN(), mutationRate);
//			NeuralNetwork m2 = NeuralNetwork.reproduce(individuals.get(i).getNN(), individuals.get(i + 1).getNN(), mutationRate);

			theBest.add(new Individual(m1));
//			theBest.add(new Individual(m2));
		}
		
		while (theBest.size() < initSize) {
			//System.out.println("WAITING HERE");
			theBest.add(new Individual(numInputs));
		}
		
		individuals = theBest;
		System.out.println("Finished generation starting next one");
		System.out.println("*********************************");
	}
}







