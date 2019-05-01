import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NeuralNetworkTester implements Callable<Integer> {
	public static void main(String args[]) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Callable<Integer> callable = new NeuralNetworkTester();
		Callable<Integer> callable2 = new NeuralNetworkTester();
		
		int t = 0;
		t+= executor.submit(callable).get().intValue();
		t+= executor.submit(callable).get().intValue();
		System.out.println("Took "+t+" ms");
	}
	
	
	@Override
	public Integer call() throws Exception {
		Instant start = Instant.now();
		testPredict();
		Instant finish = Instant.now();
		int timeElapsed = (int) Duration.between(start, finish).toMillis();
		return timeElapsed;
	}
	
	public void testPredict() {
		
		for (int i=0; i<50; i++) {
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
			Neuron.print(nn.predict(in));
		}
		
		
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
