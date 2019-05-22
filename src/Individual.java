import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Individual implements Callable<Individual> {
	private NeuralNetwork network; 
	private Game game;
	public Individual(int numInputs) {
		network = new NeuralNetwork(numInputs);
//		network.addLayer(40, Activation.ReLu);
		network.addLayer(4, Activation.Sigmoid);
	}
	
	/**
	 * @author Sri Kondapalli
	 * @param NeeralNetwork passed in as a requirement for pairs of individuals to reproduce
	 */
	public Individual(NeuralNetwork n) {
		network = n; 
	}
	
	public double getFitness() {
		return network.getFitness();
		
	}
	public static double predictionThreshold = 0.9;
	public void play() {
//		System.out.println("PLAYING");
		double[][] state = game.getState();
		ArrayList<Double> newState = new ArrayList<Double>();

		for(int r = 0; r < state.length; r++) {
			for(int c = 0; c < state[r].length; c++) {
				newState.add(state[r][c]); 
			}
		}

		ArrayList<Integer> actions = network.predict(newState, predictionThreshold);
			
		if (actions.get(0) == -1) {
			return;
		}
		
		if (actions.size() >= 1 && actions.get(0) >= 1) {
			game.moveRight();
			right++;
		}
		else if(actions.size() >= 2 && actions.get(1) >= 1) {
			game.moveLeft();
			left++;
		}
		else if(actions.size() >= 3 && actions.get(2) >= 1) {
			game.jump();
			jump++;
		}
	}
	public static int right = 0;
	public static int left = 0;
	public static int jump = 0;
	
	public boolean isDone = false;
	public void setDone(boolean f) {
		this.isDone = f;
		//GeneticAlgorithm.numDone++;
		//network.setFitness(game.getFitness());
		//System.out.println("INDIVDONE "+GeneticAlgorithm.numDone);
	}
	
	public NeuralNetwork getNN() {
		return network;
		
	}

	@Override
	public String toString() {
		return "Individual<fitness: "+ network.getFitness()+">";
	}

	@Override
	public Individual call() {
		game = new Game();
		game.indiv = this;
		game.start();
		while (true) {
			try {
				Thread.sleep(1);
				if (game.isDone || isDone)
					break;
				else
					play();
			}
			
			catch (Exception e) {}
		}
		network.setFitness(game.getFitness());
		GeneticAlgorithm.numDone++;
		return this;
	}
}
