import java.util.ArrayList;

public class NeuralNetwork {
	int numInputs = 0;

	public static void main(String args[]) {
		NeuralNetwork nn = new NeuralNetwork(5);
	}

	ArrayList<ArrayList<Neuron>> layers = new ArrayList<ArrayList<Neuron>>();

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

}
