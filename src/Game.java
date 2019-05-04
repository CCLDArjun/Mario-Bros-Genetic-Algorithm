import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
	private int fitness = 0;
	private boolean isDone = false;
	private Timer repaint = new Timer(60, new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			frame.repaint();
			if (m.y < 0) {
				frame.dispose();
				isDone = true;
				repaint.stop();
				System.out.println(fitness / 48);
			}
		}
	});	
	
	public static void main(String[] args) {
		new Game().start();
	}

	private void start() {
		makeFrame();
		repaint.start();
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
		frame.add(panel);
		for (int x = 0; x < tilelayout[0].length; x++) {
			tilelayout[tilelayout.length - 1][x] = 1;
		}
		tilelayout[tilelayout.length - 2][13] = 1;
//		printArray(tilelayout);
		panel.repaint();
		panel.setPreferredSize(new Dimension(624, 624));
		panel.addKeyListener(keys);
		panel.setFocusable(true);
		panel.setLayout(null);
		frame.pack();
		frame.setVisible(true);

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
		int[] colay = getNewLine();
		for (int y = 0; y < tilelayout.length; y++) {
			tilelayout[y][tilelayout[y].length - 1] = colay[y];
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
					System.out.println("ERRRRRROOOOOOOOOOOAAAAAAAARRRRRR");
					System.out.println(y);
					System.out.println(HEIGHT);
					System.out.println(lastcol.length);
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
		offset += m.draw(g, tilelayout, offset);
		fitness += offset;
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
