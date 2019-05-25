import java.awt.*;
//import java.awt.Graphics;
import java.awt.event.*;
import java.io.EOFException;
//import java.awt.event.MouseEvent;
import java.io.File;

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
		frame.setLayout(new BorderLayout());
		setupButtons();
		frame.add(panel, BorderLayout.NORTH);
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
		JButton saveButton = new JButton("save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		JButton loadButton = new JButton("load");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		frame.add(saveButton, BorderLayout.WEST);	
		frame.add(loadButton, BorderLayout.EAST);	
	}
	
	public File showDialog() {
		FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
	    dialog.setMode(FileDialog.LOAD);
	    dialog.setVisible(true);
	    File file = new File(dialog.getFile());
	    return file;
	}
	
	public void save() {
		grid.save("/Users/arjunbemarkar/eclipse-workspace/Mario-Bros-Genetic-Algorithm/"+"level.gg");
	}
	
	public void load()  {
		File file = showDialog();
	    try {
	    	grid = GameGrid.getFromFile(file.getAbsolutePath());
	    }
	    catch (Exception e) {}
	    frame.setVisible(false);
	    start();
	}
}
