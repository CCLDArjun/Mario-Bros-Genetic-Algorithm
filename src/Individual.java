import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class Individual implements Callable<Individual> {
	private NeuralNetwork network; 
	private Game game;
	public Individual(int numInputs) {
		network = new NeuralNetwork(numInputs);
		network.addLayer(4, Activation.Sigmoid);
		network.addLayer(5, Activation.ReLu);
		network.addLayer(5, Activation.Sigmoid);
		network.addLayer(3, Activation.Sigmoid);
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
	public static double predictionThreshold = 0.5;
	public void play() {
//		System.out.println("PLAYING");
		ArrayList<Double> newState = game.getState();
		ArrayList<Double> actions = network.rawPredict(newState);
		if (actions.get(0) == -1) {
			return;
		}
			
		if (actions.get(0) >= predictionThreshold) {
			game.moveRight();
		}
		if(actions.get(1) >= predictionThreshold) {
			game.jump();
		}
		if(actions.get(2) >= predictionThreshold) {
			game.moveLeft();
		}
	}
	
	public boolean ifDone = false;
	public void setDone(boolean f) {
		//System.out.println("WHAT?"+GeneticAlgorithm.numDone);
		ifDone = f;
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
		try {
			game = new Game();
			game.indiv = this;
			game.start();
			while (!game.isDone) {
				play();
			}
			network.setFitness(game.getFitness());
			GeneticAlgorithm.numDone++;
			return this;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
}
