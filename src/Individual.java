import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Individual implements Callable<Individual> {
	private NeuralNetwork network; 
	private Game game;

	public Individual(int numInputs, Game game) {
		network = new NeuralNetwork(numInputs);
		network.addLayer(40, Activation.ReLu);
		network.addLayer(4, Activation.Sigmoid);
		this.game = game;
	}
	
	/**
	 * @author Sri Kondapalli
	 * @param NeeralNetwork passed in as a requirement for pairs of individuals to reproduce
	 */
	public Individual(NeuralNetwork n, Game game) {
		network = n; 
		this.game = game; 
		
	}
	public double getFitness() {
		return network.getFitness();
		
	}
	
	public void play() {
		game.start();
		boolean isDone = false;
		while (true) {
			double[][] state = game.getState();
			ArrayList<Double> newState = new ArrayList<Double>();

			for(int r = 0; r < state.length; r++) {
				for(int c = 0; c < state[r].length; c++) {
					newState.add(state[r][c]); 
				}
			}
			
//			System.out.println(newState.size());

			ArrayList<Integer> actions = network.predict(newState, 0.1);
			
			for (Integer i : actions) {
				System.out.print(i.intValue());
			}
			System.out.print("\n sso");
			
//			game.moveRight();
			if (actions.get(0) == -1) {
				continue;
			}
			
			boolean isMovingRight = false;
			if (actions.get(0) >= 1) {
				game.moveRight();
				isMovingRight = true;
			}
			if(actions.get(1) >= 1) {
				System.out.println("JUMPING");
				game.jump();
			}
			if(actions.get(2) >= 1) {
				if (!isMovingRight) {
					System.out.println("MOVING LEFT");
					game.moveLeft();
				}
			}
			isDone = game.isDone;
			if (isDone) {
				network.setFitness(game.getFitness());
				System.out.println("done boi");
				break;
			}
		}
	}
	public NeuralNetwork getNN() {
		return network;
		
	}

	

	@Override
	public Individual call() throws Exception {
		play();
		return this;
	}
}
