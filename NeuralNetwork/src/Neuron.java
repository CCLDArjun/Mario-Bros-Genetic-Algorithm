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
		this.bias = (Math.random() * 2) - 1; 
		weights = new ArrayList<Double>();
		for (int i = 0; i < numInputs; i++) {
			weights.add((Math.random() * 2) - 1);
		}
	}
	
	public Neuron() {
		
	}
	
	/**
	 *  Returns the output of the neuron given the appropriate inputs. First it multiplies each
	 *  input to the corresponding weight, then it sums that value and runs it through an activation
	 *  function so that the output is low and non-linear.
	 *  
	 * @param inputs is an ArrayList
	 * @return output of the neuron
	 */
	public double propagate(ArrayList<Double> inputs) {
		if (inputs.size() != weights.size())
			throw new Error("Input size given is not assigned");

		double sum = 0;
		for (int i = 0; i < inputs.size(); i++) {
			sum += inputs.get(i) * weights.get(i);
		}
		sum += bias;
		
		if (activation == Activation.Sigmoid)
			sum = (1 / (1 + Math.pow(Math.E, (-1 * sum))));

		else if (activation == activation.ReLu)
			sum = Math.max(0.01 * sum, sum);

		else if (activation == activation.Tanh)
			sum = 2 / (1 + Math.pow(Math.E, (-2 * sum)));

		return sum;
	}
	
	public static void print(ArrayList<Integer> arrayList) {
		for (Integer a : arrayList) {
			System.out.print(a.intValue()+", ");
		}
		System.out.print("\n");
	}
	
	
	/**
	 * Creates offspring of two parent neurons analogous to meiosis. First it selects one of the two
	 * parents randomly. Then it copies a random section of the parent's weights and adds it to the 
	 * offspring's weights.
	 * 
	 * @param n1 First Parent Neuron
	 * @param n2 Second Parent Neuron
	 * @return offspring of the two neurons
	 */
	public static Neuron reproduce(Neuron n1, Neuron n2, double mutationRate) {
		if (n1.getWeights().size() != n2.getWeights().size())
			throw new RuntimeException("Neuron input sizes are not same while trying to reproduce");
		Neuron n = new Neuron();
		ArrayList<Double> newWeights = n1.getWeights();
		int lastRandom = 0;
		while (true) {
			int rand = randomNum(lastRandom, n1.getWeights().size()-1);
			for (int i=lastRandom; i<rand; i++) {
				double num = Math.random();
				if (num < mutationRate) {
					newWeights.set(i, Math.random());
					System.out.println("mutation");
				}
				else if (num < 0.5) {
					newWeights.set(i, n2.getWeights().get(i)); // need to clone
					System.out.println("not");
				}
				else {
					newWeights.set(i, n1.getWeights().get(i));
					System.out.println("not");
				}
			}
			
			if (rand >= n1.getWeights().size()-1) {
				break;
			}
		}
		
		double num = Math.random();
		if (num < mutationRate)
			n.setBias(Math.random());
		else if (num < 0.5)
			n.setBias(n1.getBias());
		else
			n.setBias(n2.getBias());
		
		n.setWeights(newWeights);
		return n;
	}
	
	private static int randomNum(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	@SuppressWarnings("null")
	public double compareTo(Neuron n) {
		double sum = 0.0;
		
		if (n.getWeights().size() != weights.size())
			return (Double) null;
		
		for (int i=0; i<n.getWeights().size(); i++) {
			sum += n.getWeights().get(i) - weights.get(i);
		}
		
		sum += bias - n.getBias();
		
		return sum;
	}
}
