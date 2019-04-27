import java.util.ArrayList;
import java.util.Random;

public class Neuron {
	private ArrayList<Double> weights;
	private double bias;
	Activation activation;

	public ArrayList<Double> getWeights() {
		return new ArrayList<Double>(weights);
	}
	
	public void setWeights(ArrayList<Double> w) {
		weights = new ArrayList<Double>(w);
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

		return sum;
	}
	
	public static Neuron reproduce(Neuron n1, Neuron n2) {
		if (n1.getWeights().size() != n2.getWeights().size())
			throw new RuntimeException("Neuron input sizes are not same while trying to reproduce");
		Neuron n = new Neuron(n1.activation, n1.getWeights().size());
		ArrayList<Double> newWeights = n1.getWeights();
		int lastRandom = 0;
		while (true) {
			int rand = randomNum(lastRandom, n1.getWeights().size()-1);
			for (int i=lastRandom; i<rand; i++) {
				if (Math.random() < 0.5) {
					newWeights.set(i, n2.getWeights().get(i));
				}
			}
			if (rand >= n1.getWeights().size()) {
				break;
			}
		}
		n.setWeights(newWeights);
		return n;
	}
	
	private static int randomNum(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
