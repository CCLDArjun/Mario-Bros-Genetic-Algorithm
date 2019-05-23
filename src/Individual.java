import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Individual implements Callable<Individual> {
	private NeuralNetwork network; 
	private Game game;
	public Individual(int numInputs) {
		network = new NeuralNetwork(numInputs);
		network.addLayer(40, Activation.Sigmoid);
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
	public static double predictionThreshold = 0.8;
	int times = 0;
	public void play() {
		times++;
		
//		System.out.println("PLAYING");
		double[][] state = game.getState();
		ArrayList<Double> newState = new ArrayList<Double>();

		for(int r = 0; r < state.length; r++) {
			for(int c = 0; c < state[r].length; c++) {
				newState.add(state[r][c]); 
			}
		}

		ArrayList<Double> actions = network.predict(newState, predictionThreshold);
		System.out.println(actions);
//		if (actions.get(0) == -1) {
//			return;
//		}
		
		if (actions.get(0) >= -1) {
			game.moveRight();
			right++;
			System.out.print("MOVING RIGHT"+times);
		}
//		else

//		if(actions.get(1) >= predictionThreshold) {
//			game.moveLeft();
//			left++;
//			System.out.print("MOVING LEFT");
//		}
//		if(actions.get(2) >= predictionThreshold) {
//			game.moveRight();
//			jump++;
//			System.out.print("JUMPING");
//		}
		System.out.print("\n");
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
			
			catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("DOINE");
		network.setFitness(game.getFitness());
		GeneticAlgorithm.numDone++;
		return this;
	}
}
