import java.util.ArrayList;

public class NeuralNetworkTester {
	public static void main(String args[]) {
		NeuralNetworkTester tst = new NeuralNetworkTester();
		System.out.println("IS RUNNING");
		tst.testReproduction();
	}
	
	
	public void testPredict() {
		for (int i=0; i<50; i++) {
			NeuralNetwork nn = new NeuralNetwork(5);
			nn.addLayer(4, Activation.ReLu);
			nn.addLayer(5, Activation.Sigmoid);
			ArrayList<Double> in = new ArrayList<Double>();
			in.add(1.0);
			in.add(2.0);
			in.add(1.0);
			in.add(2.0);
			in.add(5.0);
			System.out.println(nn.predict(in));
		}
	}
	
	public void testReproduction() {
		NeuralNetwork nn = new NeuralNetwork(5);
		nn.addLayer(4, Activation.ReLu);
		nn.addLayer(5, Activation.Sigmoid);
		
		NeuralNetwork nn2 = new NeuralNetwork(5);
		nn2.addLayer(4, Activation.ReLu);
		nn2.addLayer(5, Activation.Sigmoid);
		
		NeuralNetwork nn3 = NeuralNetwork.reproduce(nn, nn2, 0.1);
		
		
		boolean didFail = false;
		if (nn3.getLayers().size() != nn.getLayers().size()) {
			didFail = true;
			System.out.println("FAIL");
		}
		for (int r=0; r<nn.getLayers().size(); r++) {
			if (nn3.getLayers().get(r).size() != nn.getLayers().get(r).size()) {
				didFail = true;
				System.out.println("FAIL 2");
			}
		}
		
		if (!didFail) {
			System.out.println("SUCCESS!");
		}
		
	}
}
