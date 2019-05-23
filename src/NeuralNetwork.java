import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class NeuralNetwork implements Serializable {
	private int numInputs = 0;
	private double fitness = 0;
	private ArrayList<ArrayList<Neuron>> layers = new ArrayList<ArrayList<Neuron>>();
	
	/**
	 * Adds a new layer to the network. No need to add the Input Layer
	 * 
	 * @author Arjun
	 * @param numNeurons - Number of Neurons
	 * @param activation - can choose between ReLu, Sigmoid, Tanh
	 * @return void
	 */
	public void addLayer(int numNeurons, Activation activation) {
		ArrayList<Neuron> newLayer = new ArrayList<Neuron>();
		for (int i = 0; i < numNeurons; i++) {
			if (layers.size() == 0) 
				newLayer.add(new Neuron(activation, numInputs));
			else
				newLayer.add(new Neuron(activation, layers.get(layers.size() - 1).size()));
		}
		layers.add(newLayer);
	}
	
	/**
	 * Predicts the result based on current weights and biases. First it takes the input and gives
	 * it to the first layer, the layer then uses it's neurons to create a new list(neurons use the
	 * propagate method). This list is the new input and is passed into the next layer. This process 
	 * keeps on happening until the program has reached the last layer, where it then returns the 
	 * index of the highest value neuron(a.k.a the prediction).
	 * 
	 * @author Arjun
	 * @param input
	 * @return prediction
	 */
	public ArrayList<Double> predict(ArrayList<Double> input, double thresh) {
		ArrayList<Double> oldRes = input;
		ArrayList<Double> newRes = new ArrayList<Double>();
		
		for (int r=0; r<layers.size(); r++) {
			for (int c=0; c<layers.get(r).size(); c++) {
				newRes.add(layers.get(r).get(c).propagate(oldRes));
			}
			oldRes = newRes;
			newRes = new ArrayList<Double>();
		}
		return oldRes;//getMaxIndexs(oldRes, thresh);
	}
	
//	public ArrayList<Integer> predict(ArrayList<Double> input) {
//		return predict(input, 0.9);
//	}
	
	/**
	 *  Reproduce two Neural Networks. Analogous to recombination in meiosis.
	 * @author Arjun
	 *  
	 * @param nn1 - First Neural Network
	 * @param nn2 - Second Neural Network
	 * @param mutationRate - Higher number will result in more mutations, number should be between 0 and 1
	 * @return returns the offspring of the two neural networks passed in
	 */
	public static NeuralNetwork reproduce(NeuralNetwork nn1, NeuralNetwork nn2, double mutationRate) {
		NeuralNetwork newNN = new NeuralNetwork(nn1.numInputs);
		
		if (mutationRate >= 1 || mutationRate < 0)
			throw new RuntimeException("Mutation Rate given is not between 0 and 1 ");
		
		ArrayList<ArrayList<Neuron>> newLayers = new ArrayList<ArrayList<Neuron>>();
		for (int r=0; r<nn1.getLayers().size(); r++) {
			newLayers.add(new ArrayList<Neuron>());
		}
		
		for (int r=0; r<nn1.getLayers().size(); r++) {
			for (int c=0; c<nn1.getLayers().get(r).size(); c++) {
				Neuron n1 = nn1.getLayers().get(r).get(c);
				Neuron n2 = nn2.getLayers().get(r).get(c);
				newLayers.get(r).add(c, Neuron.reproduce(n1, n2, mutationRate));
			}
		}
		newNN.setLayers(newLayers);
		return newNN;
	}
	
	private ArrayList<Integer> getMaxIndexs(ArrayList<Double> l, double thresh) {
		ArrayList<Integer> maxIndexs = new ArrayList<Integer>();
		boolean didFind = false;
		for (int i=1; i<l.size(); i++) {
			if (l.get(i) > thresh) {
				maxIndexs.add(i);
				didFind = true;
			}
			else {
				maxIndexs.add(0);
			}
		}
		if (!didFind) {
		        maxIndexs = new ArrayList<Integer>();
			maxIndexs.add(-1);
		}
		return maxIndexs;	
	}
	
	public void save(String path) {
		try {
			FileOutputStream f = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(this);
			out.close();
			f.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static NeuralNetwork getFromFile(String path) throws EOFException {
		try {
			FileInputStream fi = new FileInputStream(path);
			ObjectInputStream in;
			in = new ObjectInputStream(fi);
			NeuralNetwork net = (NeuralNetwork) in.readObject();
			in.close();
			fi.close();
			return net;
		} 
		catch (Exception e) {
		}
		return null;
	}
	
	
	public void setFitness(double f) {
		fitness = f;
	}
	
	public double getFitness() {
		return fitness;
	}
	
	public NeuralNetwork(int numInputs) {
		this.numInputs = numInputs;
	}
	
	public ArrayList<ArrayList<Neuron>> getLayers() {
		return new ArrayList<ArrayList<Neuron>>(layers);
	}
	
	public void setLayers(ArrayList<ArrayList<Neuron>> l) {
		layers = new ArrayList<ArrayList<Neuron>>(l);
	}
}
