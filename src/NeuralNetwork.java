import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class NeuralNetwork{
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
	public ArrayList<Integer> predict(ArrayList<Double> input, double thresh) {
		ArrayList<Double> oldRes = input;
		ArrayList<Double> newRes = new ArrayList<Double>();
		
		for (int r=0; r<layers.size(); r++) {
			for (int c=0; c<layers.get(r).size(); c++) {
				newRes.add(layers.get(r).get(c).propagate(oldRes));
			}
			oldRes = newRes;
			newRes = new ArrayList<Double>();
		}
		return getMaxIndexs(oldRes, 0.5);
	}
	
	public ArrayList<Integer> predict(ArrayList<Double> input) {
		return predict(input, 0.9);
	}
	
	public ArrayList<Double> rawPredict(ArrayList<Double> input) {
		ArrayList<Double> oldRes = input;
		ArrayList<Double> newRes = new ArrayList<Double>();
		
		for (int r=0; r<layers.size(); r++) {
			for (int c=0; c<layers.get(r).size(); c++) {
				newRes.add(layers.get(r).get(c).propagate(oldRes));
			}
			oldRes = newRes;
			newRes = new ArrayList<Double>();
		}
		return oldRes;
	} 
	
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
		PrintWriter writer;
		try {
			writer = new PrintWriter("best.nn", "UTF-8");
			for (int x = 0; x < layers.size(); x++) {
				for (int y = 0; y < layers.get(x).size(); y++) {
					writer.println(layers.get(x).get(y));
				}
				writer.println();
			}
			writer.close();	
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double toDouble(String s) {
		return Double.parseDouble(s);
	}
	
	public NeuralNetwork getFromFile(String path){
		try {
			BufferedReader in = new BufferedReader(new FileReader("best.nn"));
			String str = in.readLine();
			String shore;
			ArrayList<Double> ad = new ArrayList<Double>();
			int x = 0;
			int y = 0;
			while (str != null) {
//				System.out.println(x + " " + y);
				if (str.length() > 0) {
//					System.out.println(str.indexOf(' '));
					shore = str.substring(0, str.indexOf(' '));
//					System.out.println(shore);
					while (str.length() > 0) {
//						System.out.println(shore);
//						System.out.println(shore.charAt(0));
						if (shore.charAt(0) == ',') {
							shore = shore.substring(1);
//							System.out.println(x + " " + y);
							layers.get(x).get(y).setBias(toDouble(shore));
							break;
						}
						else {
							ad.add(toDouble(shore));
							str = str.substring(shore.length() + 1);
//							System.out.println(str);
							shore = str.substring(0, str.indexOf(' '));
//							System.out.println(shore);
						}
					}
					layers.get(x).get(y).setWeights(ad);
					ad.clear();
					y++;
				}
				else {
//					System.out.println("YEYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
					ad.clear();
					x++;
					y = 0;
				}
				str = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
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
