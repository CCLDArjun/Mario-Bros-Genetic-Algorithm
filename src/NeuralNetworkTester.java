import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class NeuralNetworkTester implements Callable<ArrayList<Integer>> {
	public static void main(String args[]) throws InterruptedException, ExecutionException {
//		ExecutorService executor = Executors.newFixedThreadPool(10);
//		int times = 0;
//		while (true) {
//			times++;
//			System.out.println(times+" times");
//			Callable<ArrayList<Double>> callable = new NeuralNetworkTester();
//			executor.submit(callable).get();
//			executor.submit(callable).get();
//		}
		
		for (int i=0; i<100; i++) {
			System.out.println(1 / (1 + Math.pow(Math.E, (-1 * i))));
		}
	}
	
	@Override
	public ArrayList<Integer> call() throws Exception {
		return testPredict();
	}
	
	public ArrayList<Integer> testPredict() {	
		NeuralNetwork nn = new NeuralNetwork(5);
		nn.addLayer(4, Activation.ReLu);
		nn.addLayer(5, Activation.Sigmoid);
		nn.addLayer(5, Activation.Sigmoid);
		nn.addLayer(5, Activation.Sigmoid);
		nn.addLayer(5, Activation.ReLu);
		nn.addLayer(5, Activation.Sigmoid);
		nn.addLayer(5, Activation.Tanh);
		nn.addLayer(5, Activation.Sigmoid);
		ArrayList<Double> in = new ArrayList<Double>();
		in.add(1.0);
		in.add(2.0);
		in.add(1.0);
		in.add(2.0);
		in.add(5.0);
		return nn.predict(in, 0.8);
	}
	
	public void testReproduction() {
		NeuralNetwork nn2 = new NeuralNetwork(5);
		nn2.addLayer(4, Activation.ReLu);
		nn2.addLayer(5, Activation.Sigmoid);
		
		NeuralNetwork nn1 = new NeuralNetwork(5);
		nn1.addLayer(4, Activation.ReLu);
		nn1.addLayer(5, Activation.Sigmoid);
		
		NeuralNetwork nn3 = NeuralNetwork.reproduce(nn1, nn2, 0.1);
		
		boolean didFail = false;
		if (nn3.getLayers().size() != nn1.getLayers().size()) {
			didFail = true;
			System.out.println("FAIL");
		}
		
		for (int r=0; r<nn1.getLayers().size(); r++) {
			if (nn2.getLayers().get(r).size() != nn1.getLayers().get(r).size()) {
				didFail = true;
				System.out.println("FAIL 2");
			}
		}
		
		double su = 0;
		for (int r=0; r<nn1.getLayers().size(); r++) {
			for (int c=0; c<nn1.getLayers().get(r).size(); c++) {
				su += nn1.getLayers().get(r).get(c).compareTo(nn3.getLayers().get(r).get(c));
			}
		}
		
		if (su == 0) {
			System.out.println("FAIL 3 "+su);
			didFail = true;
		}
		
		if (!didFail) {
			System.out.println("SUCCESS!");
		}
		
	}
}
