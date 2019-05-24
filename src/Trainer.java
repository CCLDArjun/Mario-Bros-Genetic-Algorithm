
import java.io.EOFException;
import java.util.concurrent.ExecutionException;

public class Trainer {
	public static void main(String args[]) throws InterruptedException, ExecutionException, EOFException {
		Trainer t = new Trainer();
		t.testSave();
	}
	
	public void start() throws InterruptedException, ExecutionException {
		GeneticAlgorithm ga = new GeneticAlgorithm(0.9, 10, 169);
		ga.start((int)Double.MAX_VALUE);
	}
	static String path  = "/home/computerscience/eclipse-workspace/Mario-Bros-Genetic-Algorithm/best.nn";
	public void testSave() throws EOFException {
		NeuralNetwork nn = new NeuralNetwork(60);
		nn.save(path);
		System.out.println("done");
		NeuralNetwork nn2 = NeuralNetwork.getFromFile(path);
		System.out.println("done2");
	}
}
