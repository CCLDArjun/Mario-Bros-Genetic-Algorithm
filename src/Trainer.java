
import java.io.EOFException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Trainer {
	public static void main(String args[]) throws InterruptedException, ExecutionException, EOFException {
		Trainer t = new Trainer();
//		try{
//			t.start();
//		}
//		
//		catch(Exception e) {
//			e.printStackTrace();
//		}
		while (true) {
			t.testSave();
			t.testGet();
		}
	}
	
	public void start() throws InterruptedException, ExecutionException {
		GeneticAlgorithm ga = new GeneticAlgorithm(0.9, 10, 9);
		ga.start((int)Double.MAX_VALUE);
	}
	static String path  = "/Users/arjunbemarkar/eclipse-workspace/Mario-Bros-Genetic-Algorithm/best.nn";
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
		} catch (EOFException e) {}
		
		System.out.println(network.getLayers().get(0).get(0).getBias());
	}
	
	
	public void testElse() {
		
	}
	
}
