import java.awt.*;
//import java.awt.Graphics;
import java.awt.event.*;
//import java.awt.event.MouseEvent;

import javax.swing.*;

public class LevelMaker {

	JFrame frame = new JFrame("Mario Bros Level Maker");
	JPanel panel;
	GameGrid grid = new GameGrid(50);
	private Dimension DIM;
	int[] dims;
	public static void main(String[] args) {
		new LevelMaker().start();
	}
	
	

	private void start() {
		dims = grid.recommendedDims();
		DIM = new Dimension(dims[0], dims[1]+50);
		panel = new JPanel() {
			@Override 
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				grid.draw(g);
			}
		};
		panel.setBackground(Color.BLACK);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				clickedAt(me);
				
			}
		});
		panel.setPreferredSize(DIM);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		setupButtons();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	private void clickedAt(MouseEvent me) {
		try {
			grid.updateClick(me.getX(), me.getY());
			panel.repaint();
		}
		catch (IndexOutOfBoundsException e) {}
	}
	JButton button = null;
	public void setupButtons() {
		if (button == null) {
			button = new JButton("save");
			button.setBounds(0, (int)dims[1]+10, 100, 20);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("DONE");
				}
			});
		}
		frame.add(button);	
	}
}
