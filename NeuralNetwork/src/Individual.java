import java.util.ArrayList;

public class Individual implements Callable {
	private NeuralNetwork network; 
	private Game game;

	public Individual(int numInputs) {
		network = new NeuralNetwork(numInputs);
		network.addLayer(4,	Activation.ReLu);
		network.addLayer(3, Activation.Sigmoid);
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

			ArrayList<Integer> actions = network.predict(newState);

			if (actions.get(0) == -1) {
				continue;
			}
			if (actions.get(0) == 1) {
				game.jump();
			}
			if(actions.get(1) >= 1) {
				game.moveRight();
			}
			if(actions.get(2) >= 1) {
				game.moveLeft();
			}
			isDone = game.isDone;
			if (isDone) {
				network.setFitness(game.getFitness());
				break;
			}
		}


	}
}
