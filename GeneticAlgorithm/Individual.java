import java.util.ArrayList;

public class Individual {
	private int fitness;
	private NeuralNetwork network; 
	//Mario possible moves--> 0(Jump), 1(LeftMove), 2(RightMove)
	
	public int play(int[][] state) {
		return network.predict(state);
	}
	
	public int getFitness() {
		return network.getFitness();
	}


}
