package frogger;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Runner {
	private JFrame frame = new JFrame("Froggy froggy, Hop Hop!");
	private JPanel panel;
	private JLabel label;
	private Frogger frog = new Frogger(300, 300);
	private KeyListener keys = new Keyboard(frog);
	private ArrayList<Moveable> objs = new ArrayList<Moveable>();
	private int[] terrain = new int[28];
	private final static int WINDOW = 700;
	private int delay = 10;
	private int move_y = 0;
	private int change = 0;
	private double chance = 0.95;
	private int frames = 0;
	
	private Timer repaint = new Timer(delay, new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			frame.repaint();
			if (frog.alive == false) {
				frame.dispose();
				JOptionPane.showMessageDialog(null, "You died!\nPoints: " + ((Integer) move_y).toString(), "You lost!", JOptionPane.WARNING_MESSAGE);
				repaint.stop();
			}
		}
	});	
	
	public static void main(String[] args) {
		new Runner().start();
	}

	private void start() {
		makeFrame();
		repaint.start();
	}

	private double avighnaRandom() {
		return (Math.sin((System.nanoTime())) + 1) / 2.0;
	}
	
	@SuppressWarnings("serial")
	private void makeFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				draw(g);
				update_terrain();
				label.setText("Points: " + ((Integer) move_y).toString());
			}
		};
		frame.add(panel);
		
		label = new JLabel(((Integer) move_y).toString());
		label.setSize(190, 25);
		label.setLocation(0, WINDOW);
		panel.add(label);

		panel.repaint();
		panel.setPreferredSize(new Dimension(WINDOW, WINDOW + 100));
		panel.addKeyListener(keys);
		panel.setFocusable(true);
		panel.setLayout(null);
		frame.pack();
		frame.setVisible(true);
		
		for (int x = 0; x < 8; x++) {
			terrain[x] = (int) (avighnaRandom() * 2);
		}
	}
	
	private void update_terrain() {
		for (int x = terrain.length - change - 1; x >= 0; x--) {
			terrain[x + change] = terrain[x];
		}
		for (int x = 0; x < change; x++) {
			terrain[x] = (int) (avighnaRandom() * 2);
		}
		
		for (int y = 0; y < objs.size(); y++) {
			Gameobj x = objs.get(y);
			x.y += Gameobj.size * change;
		}
	}

	
	private void draw(Graphics g) {
		for (int y = 0; y < terrain.length; y++) {
			int terra = terrain[y];
			Image curImg = null;
			if (terra == 0) {
				curImg = frog.openImageFromSpriteSheet(50, 60, 33, 33);
			}
			else {
				curImg = frog.openImageFromSpriteSheet(83, 60, 33, 33);
			}
			
			for (int x = 0; x < (WINDOW / 25.0); x++) {
				g.drawImage(curImg, x * 25, y * 25, null);
			}
		}
/*		if (objs.size() == 0) {
			for (int x = 1; x < 100; x++) {
				Log es = new Log(((int) (avighnaRandom() * x) * 25), ((int) (avighnaRandom() * x) * 25), (int) (avighnaRandom() * 3));
				objs.add(es);
			}
		}*/
		if (avighnaRandom() > chance) {
			while (true) {
				int x = (int) (avighnaRandom() * terrain.length);
				if (terrain[x] == 1) {
					if (avighnaRandom() < chance) {
						Log es = new Log(700 * (int) (avighnaRandom() * 2), x * 25, (int) (avighnaRandom() * 3));
						objs.add(es);
						break;
					}
				}
				else {
					if (avighnaRandom() > chance) {
						Car es = new Car(700 * (int) (avighnaRandom() * 2), x * 25, (int) (avighnaRandom() * 5));
						objs.add(es);
						break;
					}
				}
			}
		}
		boolean isMoving = false;
		for (int y = 0; y < objs.size(); y++) {
			Moveable x = objs.get(y);
			isMoving = ((isMoving == true) || (x.is_collide(frog)));
			x.move(x);
			if (x.draw(g)) {
				objs.remove(x);
				y--;
			}
		}
		
		if (isMoving == false) {
			frog.x = (int) (Math.round(1.0 * frog.x / Gameobj.size) * Gameobj.size);
			if (terrain[12 + frog.back] == 1) {
				if (frames >= 1) {
					frog.alive = false;
				}
				frames++;	
			}
			else {
				frames = 0;
			}
		}
		
		frog.draw(g);
		change = frog.move_y - move_y;
		move_y = frog.move_y;
//		System.out.println(terrain[12 + frog.back]);
	}
}
