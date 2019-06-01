
import java.io.EOFException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Trainer extends Thread {
	public static void main(String args[]) throws InterruptedException, ExecutionException, EOFException {
		Trainer t = new Trainer(0.9, 10, 169, 10);
		try{
			t.start();
		}		
		catch(Exception e) {
			e.printStackTrace();
		}
//		
//		t.testSave();
//		t.testGet();
	}
	
	private GeneticAlgorithm ga;
	private int times;
	public Trainer(double mutationRate, int popSize, int numInputs, int numTimes) {
		if (numTimes < 10)
			times = 10;
		else
			times = numTimes;
		ga = new GeneticAlgorithm(mutationRate, popSize, numInputs);
//		double mutationRate, int popSize, int numInputs
	}
	
	public void run() {
		try {
			ga.start(times);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
//	public void start() throws InterruptedException, ExecutionException {
//		GeneticAlgorithm ga = new GeneticAlgorithm(0.9, 10, 169);
//		ga.start((int)Double.MAX_VALUE);
//	}
	
	static String path  = "best.nn";
	public void testSave() throws EOFException {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(123);
		arr.add(9);
		NeuralNetwork network = new NeuralNetwork(2);
		network.addLayer(40, Activation.Sigmoid);
		network.addLayer(3, Activation.Sigmoid);
		network.save(path);
		System.out.println(network.getLayers().get(0).get(0).getBias());
	}
	
	public void testGet() {
		NeuralNetwork network =  null;
		try {
			network = NeuralNetwork.getFromFile(path);
		} catch (EOFException e) {
			
		}
		
		System.out.println(network.getLayers().get(0).get(0).getBias());
	}
	
	
	public void testElse() {
		
	}
	
}
