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
	private JFrame frame = new JFrame("Ultra Mario Bros!");
	private JPanel panel;
	private Mario m = new Mario(0, 624);
	private Keyboard keys = new Keyboard(m);
	private int[][] tilelayout = new int[13][13];
	private String[] tileID = {"AIR", "ground", "block"};
	private int offset = 0;
//	private int[] tileData = {1, 2};
	private Timer repaint = new Timer(1, new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			frame.repaint();
//				frame.dispose();
//				JOptionPane.showMessageDialog(null, "You died!\nPoints: " + ((Integer) movey).toString(), "You lost!", JOptionPane.WARNINGMESSAGE);
//				repaint.stop();
//			}
		}
	});	
	
	public static void main(String[] args) {
		new Game().start();
	}

	private void start() {
		makeFrame();
		repaint.start();
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
		
		for (int x = 0; x < tilelayout.length; x++) {
			tilelayout[12][x] = 1;
		}
		
		panel.repaint();
		panel.setPreferredSize(new Dimension(624, 624));
		panel.addKeyListener(keys);
		panel.setFocusable(true);
		panel.setLayout(null);
		frame.pack();
		frame.setVisible(true);

	}
	private void loadNext() {
		for (int y = 0; y < tilelayout.length; y++) {
			for (int x = 1; x < tilelayout.length; x++) {
				tilelayout[y][x - 1] = tilelayout[y][x];
			}
		}
		int[] colay = getNewLine();
		for (int y = 0; y < tilelayout.length; y++) {
			tilelayout[y][tilelayout[y].length - 1] = colay[y];
		}
	}
	
	private int[] getNewLine() {
		return new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
	}

	private void draw(Graphics g) {
		offset += m.draw(g, tilelayout);
		while (offset >= 48) {
			loadNext();
			offset -= 48;
		}
		
		for (int y = 0; y < tilelayout.length; y++) {
			for (int x = 0; x < tilelayout.length; x++) {
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
