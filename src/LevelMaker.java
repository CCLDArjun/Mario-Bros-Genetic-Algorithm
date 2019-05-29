import java.awt.*;
//import java.awt.Graphics;
import java.awt.event.*;
import java.io.EOFException;
//import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class LevelMaker {

	JFrame frame = new JFrame("Mario Bros Level Maker");
	JPanel panel;
	GameGrid grid = new GameGrid(50);
	private JLabel label = new JLabel("<html>Green Squares represent blocks, black squares are holes</html>");
	private Dimension DIM;
	int[] dims;
	
	public static void main(String[] args) {
		new LevelMaker();
	}
	
	public LevelMaker() {
		start();
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
		frame.setLayout(new BorderLayout());
		setupButtons();
		frame.add(panel, BorderLayout.PAGE_START);
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

	public void setupButtons() {
		JButton saveButton = new JButton("Genetic Algorithm");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendBack();
			}
		});
		
		JButton gameButton = new JButton("Play Game");
		gameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playGame();
			}
		});
		
		JButton randomButton = new JButton("Random");
		randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grid.addRandom(7);
				frame.repaint();
			}
		});
		
		JPanel subPanel = new JPanel();
	    subPanel.add(gameButton);
	    subPanel.add(randomButton);
	    
		
		label.setForeground(Color.BLACK);
		frame.add(label, BorderLayout.CENTER);
		frame.add(saveButton, BorderLayout.WEST);
		frame.add(subPanel, BorderLayout.EAST);
	}
	
	
	public void sendBack() {
		Gui gui = new Gui(grid.grid);
		gui.setVisible(true);
		frame.setVisible(false);
		
	}
//	grid.addRandom(25);
	public void playGame()  {
		Game game = new Game();
		game.start();
		Game.setLevel(grid.grid);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
