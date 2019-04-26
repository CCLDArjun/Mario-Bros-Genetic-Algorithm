import java.util.ArrayList;

public class Neuron {
	private ArrayList<Double> weights;
	private double bias;
	Activation activation;

	public ArrayList<Double> getWeights() {
		return weights;
	}

	public double getBias() {
		return bias;
	}

	public void setBias(double newB) {
		bias = newB;
	}

	public Neuron(Activation activation, int numInputs) {
		this.activation = activation;
		weights = new ArrayList<Double>();
		for (int i = 0; i < numInputs; i++) {
			weights.add((Math.random() * 2) - 1);
		}
	}

	public double propagate(ArrayList<Double> inputs) {
		if (inputs.size() != weights.size())
			throw new Error("Input size given is not assigned");

		double sum = 0;
		for (int i = 0; i < inputs.size(); i++) {
			sum += inputs.get(i) * weights.get(i);
		}

		if (activation == activation.Sigmoid)
			sum = (1 / (1 + Math.pow(Math.E, (-1 * sum))));

		else if (activation == activation.ReLu)
			sum = Math.max(0.01 * sum, sum);

		else if (activation == activation.Tanh)
			sum = 2 / (1 + Math.pow(Math.E, (-2 * sum)));

		return 1;
	}
}
