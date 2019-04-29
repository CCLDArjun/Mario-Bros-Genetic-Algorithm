import java.util.ArrayList;
import java.util.Random;




public class GeneticAlgorithm {
	
	public static void main(String []args) {
		
		Individual.generateUniqueID();
	}
	private ArrayList<Individual> population = new ArrayList<Individual>(25);
	public GeneticAlgorithm(ArrayList<Individual> Individuals){

		this.population = Individuals;

	}



	public void populate() {

	

	}






}
