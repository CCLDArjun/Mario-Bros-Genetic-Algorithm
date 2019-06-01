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
	public int oldBest = 1;
	public int time = 0;

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
		System.out.println(mutationRate);
		System.out.println(popSize);
		System.out.println(numInputs);
	}

	public void start(int times) throws InterruptedException, ExecutionException {
		for (int i = 0; i < times; i++) {
			System.out.println("Starting generation "+(i+1));
			time = i + 1;
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
				/*/
				try {
					ind.getNN().setLayers(NeuralNetwork.getFromFile(System.getProperty("user.dir") + "/" + "best.nn").getLayers());
				} catch (Exception e) {
					e.printStackTrace();
				}
//				/*/
				System.out.println(ind.getNN().getLayers().get(0).get(0).getBias());
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
			//System.out.println("num done: "+numDone);
			if (GeneticAlgorithm.numDone >= popSize-1) {
				System.out.println("Saving Results...");
				individuals = new ArrayList<Individual>();
 				for (int i = 0; i < futures.size(); i++) {
					Individual ind = futures.get(i).get();
					mean += ind.getFitness();
					if (ind.getFitness() > best)
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
		
		individuals.get(0).getNN().save(System.getProperty("user.dir") + "/" + "best.nn");
		System.out.println("Generation Score: " + (mean / individuals.size()));
		System.out.println("Best Fitness: "+ (best));
		System.out.println("Old best: " + (oldBest));
		System.out.println("Order: " + (individuals));
//		Game.maxFrames += 10;
		Game.maxFrames += (int) ((best - oldBest) / 10.0) + 1;
		mutationRate = mutationRate * (oldBest / (best + time) * (1.0 + (1.0 / time)));
		if (mutationRate > 1) {
			mutationRate = 0.9999;
		}
		//mutationRate = 0.99;

		
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
//		for(int i = 0; i < individuals.size() * 0.3; i++) {
//			//System.out.println("WAITING HERE12");
//			theBest.add(individuals.get(i));
//
//		}
//		for(int i = 0; i < individuals.size() * 0.7; i++) {
//			//System.out.println("WAITING HERE1" + " " + i + " " + individuals.size() * 0.69);
//			NeuralNetwork m1 = NeuralNetwork.reproduce(individuals.get((int) (Math.random() * individuals.size() * 0.3)).getNN(), individuals.get(i).getNN(), mutationRate);
//			NeuralNetwork m2 = NeuralNetwork.reproduce(individuals.get(i).getNN(), individuals.get(i + 1).getNN(), mutationRate);
//
//			theBest.add(new Individual(m1));
//			theBest.add(new Individual(m2));
//		}
		for (int i = 0; i < Math.round(initSize / 3.0); i++)
			theBest.add(individuals.get(i));
		
		for (int i = 0; i < Math.round(initSize * 2 / 3.0); i++) {
			NeuralNetwork m1 = NeuralNetwork.reproduce(individuals.get(i).getNN(), individuals.get(i+1).getNN(), mutationRate);
			theBest.add(new Individual(m1));
		}
		
		while (theBest.size() < initSize) {
			System.out.println("WAITING HERE");
			theBest.add(new Individual(numInputs));
		}
		System.out.println(theBest);
//		mutationRate -= mutationRate*0.06;
		
		Individual.predictionThreshold += Individual.predictionThreshold*0.03; 
		individuals = theBest;
		System.out.println("Finished generation starting next one");
	//	System.out.println(Individual.jump+", "+Individual.left+", "+Individual.right);
		System.out.println("*********************************");
	}
}
