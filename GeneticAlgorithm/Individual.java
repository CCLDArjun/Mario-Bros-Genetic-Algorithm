import java.util.ArrayList;

public class Individual {
	private int fitness;
	private static ArrayList<Byte> genes = new ArrayList<Byte>(22); 
	private static byte uniqueID;
	//Mario possible moves--> 0(Jump), 1(LeftMove), 2(RightMove)
	public static void generateUniqueID() {
		;
		for(int i = 0; i < genes.size(); i++) {
			genes.set(i, (byte)(2 * Math.random()));
			
		}

		System.out.println(genes);
		
	}

	
	public int calcFitness() {
		return fitness;

	}


}
