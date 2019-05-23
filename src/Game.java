import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game {
	private JFrame frame = new JFrame("Super Mario Bros!");
	private JPanel panel;
	private Mario m = new Mario(0, 624);
	private Keyboard keys = new Keyboard(m);
	private int[][] tilelayout = new int[13][14];
	private String[] tileID = {"AIR", "ground"};
	private int offset = 0;
	private double fitness = 0;
	boolean isDone = false;
	private int frames = 0;
	public Individual indiv;
	public static int me = 0;
	public static int maxFrames = 10000;
	public boolean play = false;
	public BufferedReader in;
	private Timer repaint = new Timer(0, new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			frame.repaint();
			frames += 1;
			if (m.y < 0 || (frames >= maxFrames && !play)) {
				//System.out.println("done"+GeneticAlgorithm.numDone);
				if (m.y < 0) {
					fitness -= 200;
				}
				
				Game.me++;
				//System.out.println("ME"+Game.me);
				if (indiv != null)
					indiv.setDone(true);
				isDone = true;
				frame.dispose();
				if (isDone) {
					repaint.stop();
					try {
						in.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					System.out.println("My fit: " + fitness);
				}
			}
		}
	});	
	
	public static void main(String[] args) {
		new Game().start();
	}

	void start() {
		//System.out.println("THREAD: "+Thread.currentThread().getId()+ " "+getFitness());
		makeFrame();
		repaint.start();
	}

	public double[][] getState() {
		double[][] doubles = new double[tilelayout.length][tilelayout[0].length - 1];
		/*/
		for (int i = 0; i < tilelayout.length; i++) {
			for (int j = 0; j < tilelayout.length - 1; j++) {
				doubles[i][j] = tilelayout[i][j] * 5.0;
			}
		}
		/*/
		for (int i = m.tiley - 6; i < m.tiley + 6; i++) {
			if (i < 0) continue;
			if (i > 13) continue;
			for (int j = m.tilex - 6; j < m.tilex + 6; j++) {
				if (j < 0) continue;
				if (j > 13) continue;
				doubles[i][j] = tilelayout[i][j] * 5.0;
			}
		}				

		
		return doubles;
	}
	
	public void jump() {
		m.jump();
	}
	
	public void moveRight() {
		m.moveRight();
	}

	public void moveLeft() {
		m.moveLeft();
	}
	
	public double getFitness() {
		return fitness;
	}
	
	@SuppressWarnings("serial")
	private void makeFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				m.key(keys.r);
				draw(g);
			}
		};
		panel.setBackground(new Color(111,196,249));
		frame.add(panel);

//		for (int x = 0; x < tilelayout[0].length; x++) {
//			tilelayout[tilelayout.length - 1][x] = 1;
//		}
//		tilelayout[tilelayout.length - 2][13] = 1;
		
		try {
			in = new BufferedReader(new FileReader("data"));
			String str;
			for (int x = 0; x < tilelayout[0].length; x++) {
				str = in.readLine();
				process(str, x);
			}
		} catch (IOException e) {
			System.out.println("BRUHHHHH");
		}

//		printArray(tilelayout);
		panel.repaint();
		panel.setPreferredSize(new Dimension(624, 624));
		panel.addKeyListener(keys);
		panel.setFocusable(true);
		panel.setLayout(null);
		frame.pack();
		frame.setVisible(true);

	}

	private void process(String str, int x) {
		int[] replace = new int[tilelayout.length];
		for (int y = 0; y < tilelayout.length; y++) {
			char s = str.charAt(3 * y);
			int i = Character.getNumericValue(s);
			replace[y] = i;
		}
		for (int y = 0; y < tilelayout.length; y++) {
			tilelayout[y][x] = replace[y];
		}
	}

	/*/	private void printArray(int[][] t) {
		for (int [] y : t) {
			for (int x : y) {
				System.out.print(x + ", ");
			}
			System.out.println();
		}
	}
	/*/
	
	private void loadNext() {
		for (int y = 0; y < tilelayout.length; y++) {
			for (int x = 1; x < tilelayout[0].length; x++) {
				tilelayout[y][x - 1] = tilelayout[y][x];
			}
		}
		try {
			String str;
			str = in.readLine();
			process(str, tilelayout[0].length - 1);
			
		} catch (Exception e) {
//			e.printStackTrace();
			int[] colay = getNewLine();
			for (int y = 0; y < tilelayout.length; y++) {
				tilelayout[y][tilelayout[y].length - 1] = colay[y];
			}
		}
	}
	
	private int[] getNewLine() {
		int[] ans = new int[14];
		int[] lastcol = new int[14];
		int HEIGHT = 1;

		for (int y = 0; y < tilelayout.length; y++) {
			lastcol[y] = tilelayout[y][tilelayout[y].length - 2];
		}

		for (int y = 0; y < lastcol.length; y++) {
			if (lastcol[y] == 1) {
				try {
					ans[(int) (Math.random() * (lastcol.length - y + HEIGHT)) + y - HEIGHT] = 1;
				} catch (IndexOutOfBoundsException e) {
					//System.out.println("ERRRRRROOOOOOOOOOOAAAAAAAARRRRRR");
					//System.out.println(y);
					//System.out.println(HEIGHT);
					//System.out.println(lastcol.length);
				}
			}
		}

		ans[(int) (Math.random() * (lastcol.length))] = 1;
		ans[(int) (Math.random() * (lastcol.length))] = 0;
		
		return ans;
//		return new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
	}
	
	private void draw(Graphics g) {
//		System.out.println(offset);
		int moved = m.draw(g, tilelayout, offset);
		offset += moved;
		fitness += moved;
		while (offset >= 48) {
			loadNext();
			offset -= 48;
		}
		
		for (int y = 0; y < tilelayout.length; y++) {
			for (int x = 0; x < tilelayout[0].length; x++) {
				int tile = tilelayout[y][x];
				if (tile != 0) {
					Image img;
					try {
						img = ImageIO.read(new File(tileID[tile] + ".png"));
						g.drawImage(img, x * 48 - offset, y * 48, null);
					} catch (IOException e) {
						// Auto-generated catch block
					}
				}
			}
		}
	}
}
