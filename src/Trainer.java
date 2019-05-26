
import java.io.EOFException;
import java.util.concurrent.ExecutionException;

public class Trainer {
	public static void main(String args[]) throws InterruptedException, ExecutionException, EOFException {
		Trainer t = new Trainer();
		t.start();
	}
	
	public void start() throws InterruptedException, ExecutionException {
		GeneticAlgorithm ga = new GeneticAlgorithm(0.9, 10, 169);
		ga.start((int)Double.MAX_VALUE);
	}
	static String path  = "/home/computerscience/eclipse-workspace/Mario-Bros-Genetic-Algorithm/best.nn";
	public void testSave() throws EOFException {
		
		NeuralNetwork network = new NeuralNetwork(100);
		network.addLayer(40, Activation.Sigmoid);
		network.addLayer(3, Activation.Sigmoid);
		network.save(path);
		System.out.println("done");
		NeuralNetwork nn2 = NeuralNetwork.getFromFile(path);
		System.out.println("done2");
	}
	
	
	public void testElse() {
		
	}
	
}
