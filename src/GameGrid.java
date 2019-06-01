import java.awt.Color;
import java.awt.Graphics;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameGrid implements Serializable{
	public int[][] grid;
	public int rows = 13;
	public int cols;
	
	public GameGrid (int cols) {
		this.cols = cols;
		grid = new int[rows][cols];
		for (int r=rows-1; r<rows; r++) {
			for (int c=0; c<grid[0].length; c++) {
				grid[r][c] = 1;
			}
		}
	}
	
	public void addRandom(int num) {
		for (int i=0; i<num; i++) {
			int col = (int) (Math.random()*grid[0].length);
			int row = (int) (Math.random()*grid.length);
			grid[row][col] = 1;
		}
	}

	public void updateClick(int x, int y) {
		int row = (y/size);
		int col = (x/size);
		
		if (grid[row][col] == 1)
			grid[row][col] = 0;
		else 
			grid[row][col] = 1;
		System.out.println(""+row+", "+col);
	}
	
	static int size = 25;
	public void draw(Graphics g) {
		for (int r=0; r<grid.length; r++) {
			for (int c=0; c<grid[0].length; c++) {
				if (grid[r][c] == 1) {
					g.setColor(Color.green);
					g.fillRect((c)*size, (r)*size, size, size);
				}
				else {
					g.setColor(Color.red);
					g.drawRect((c)*size, (r)*size, size, size);
				}
			}
		}
	}
	
	public void save(String path) {
		try {
			FileOutputStream f = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(this);
			out.close();
			f.close();
		} 
		catch (Exception e) {
		}
	}

	public static GameGrid getFromFile(String path) throws EOFException {
		try {
			FileInputStream fi = new FileInputStream(path);
			ObjectInputStream in;
			in = new ObjectInputStream(fi);
			GameGrid gg = (GameGrid) in.readObject();
			in.close();
			fi.close();
			return gg;
		} 
		catch (Exception e) {
		}
		return null;
	}
	
	public int[] recommendedDims() {
		int[] dims = {size*cols+3, size*rows+3};
		return dims;
	}
	
	private void rotate(int[][] newG) {
		for (int r=0; r<grid.length; r++) {
			for (int c=0; c<grid[0].length; c++) {
				newG[c][r] = grid[r][c];
			}
		}
	}
	
	private void flip(int[][] newG) {
		int[][] gg = new int[newG.length][newG[0].length];
		int index2 = newG.length-1;
		for (int r=0; r<gg.length; r++) {
			gg[index2] = newG[r];
			index2--;
		}
		newG = gg;
	}
	
	public int[][] getGrid() {
		int[][] gg = new int[grid[0].length][grid.length];
		flip(gg);
		rotate(gg);
		return gg;
	}
}
