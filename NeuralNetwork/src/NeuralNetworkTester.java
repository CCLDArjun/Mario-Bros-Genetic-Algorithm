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
