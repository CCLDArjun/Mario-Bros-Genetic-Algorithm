import java.util.ArrayList;

public class NeuralNetwork {
	private int numInputs = 0;
	private double fitness = 0;
	
	
	public static void main(String args[]) {
		NeuralNetwork nn = new NeuralNetwork(5);
	}

	ArrayList<ArrayList<Neuron>> layers = new ArrayList<ArrayList<Neuron>>();
	
	
	public void setFitness(double f) {
		fitness = f;
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public NeuralNetwork(int numInputs) {
		this.numInputs = numInputs;
	}

	/**
	 * Adds a new layer to the network. No need to add the Input Layer
	 *
	 * @param numNeurons - Number of Neurons
	 * @param activation - can choose between ReLu, Sigmoid, Tanh
	 * @return void
	 */
	public void addLayer(int numNeurons, Activation activation) {
		ArrayList<Neuron> newLayer = new ArrayList<Neuron>();
		for (int i = 0; i < numNeurons; i++) {
			if (layers.size() == 1)
				newLayer.add(new Neuron(activation, numInputs));
			else
				newLayer.add(new Neuron(activation, layers.get(layers.size() - 1).size()));
		}
		layers.add(newLayer);
	}
	
	public int predict(ArrayList<Double> input) {
		ArrayList<Double> oldRes = input;
		ArrayList<Double> newRes = new ArrayList<Double>();
		
		for (int r=0; r<layers.size(); r++) {
			for (int c=0; c<layers.get(r).size(); c++) {
				newRes.add(layers.get(r).get(c).propagate(oldRes));
			}
			oldRes = newRes;
			newRes = new ArrayList<Double>();
		}
		return getMaxIndex(oldRes);
	}
	
	public int getMaxIndex(ArrayList<Double> l) {
		int maxIndex = 0;
		for (int i=1; i<l.size(); i++) {
			if (l.get(i) < l.get(maxIndex)) {
				maxIndex = i;
			}
		}
		return maxIndex;	
	}
}
