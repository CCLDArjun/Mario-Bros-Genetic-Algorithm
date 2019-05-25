import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class UserInterface extends JFrame {
    private JButton startButton = new JButton();
    private int width = 400;
    private int height = 320;
    private Component[][] components;
    private String[] fields = {"Population Size", "Mutation Rate", "Generations", "Learning Rate"};
    private JLabel infoLabel = new JLabel();
    public void start() {
    	this.setTitle("Mario Bros Genetic Algorithm");
    	setupInputs();
    	setupButton();
    	setLayout(null);
    	setSize(width, height);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void setupInputs() {
    	infoLabel.setFont(new Font("ComicSans", Font.BOLD, 18));
    	infoLabel.setText("<html>"+ "Mario Bros Genetic Algorithm" +"</html>");
    	infoLabel.setForeground(Color.BLACK);
    	infoLabel.setBounds(50, lastY, width-100, 30);
    	lastY+=50;
    	add(infoLabel);
    	components = new Component[fields.length][2];
    	for (int r=0; r<fields.length; r++) {
    		JLabel label = new JLabel();
    		JSlider slider = new JSlider();
    		
    		slider.addChangeListener( new javax.swing.event.ChangeListener() {
    			public void stateChanged(javax.swing.event.ChangeEvent evt) {
    				sliderChanged(slider.getName());
    			}
    		});
    		
    		slider.setName(fields[r]);
    		label.setText(fields[r]+": 50");
 
    		components[r][0] = label;
    		components[r][1] = slider;
    	}
    	for (Component[] comp : components) {
    		addRow(comp);
    	}	
    }
    
    public void sliderChanged(String name) {
    	for (int i=0; i<fields.length; i++) {
    		if (fields[i].equals(name)) {
    			JLabel label = (JLabel) components[i][0];
    			JSlider slider = (JSlider) components[i][1];
    			if (fields[i].equals("Generations")) {
    				int val = (int) (Math.sqrt(slider.getValue())*100);
    				label.setText(fields[i]+": "+  val);
    			}
    			
    			if (fields[i].equals("Mutation Rate")) {
    				double val = (double)slider.getValue()/100;
    				label.setText(fields[i]+": "+ val+ "%");
    			}
    			else 
    				label.setText(fields[i]+": "+slider.getValue());
    		}
    	}
    }
    
    public void setupButton() {
    	startButton.setText("Start");
    	lastY+=50;
    	startButton.setBounds((width-100)/2, lastY, 100, 20);
    	add(startButton);
    	startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double[] parameters = new double[fields.length];
				for (int r=0;r<components.length;r++) {
					try {
						parameters[r] = Double.parseDouble(((JTextField) components[r][1]).getText());
					}
					catch (Exception e1){
						infoLabel.setFont(new Font("ComicSans", Font.BOLD, 12));
						infoLabel.setForeground(Color.RED);
						infoLabel.setText("<html> Please input a number for "+fields[r]+"</html>");
					}
					System.out.println(fields[r] + ":" +((JTextField)components[r][1]).getText());
				}
			}
    	});
    }
    
    
    
    int lastY = 50;
    public void addRow(Component[] comps) {
    	int lastX = 50;
    	for (Component comp : comps) {
    		if (comp instanceof JSlider)
    			comp.setBounds(lastX, lastY, 50, 20);
    		comp.setBounds(lastX, lastY, width/comps.length, 20);
    		add(comp);
    		lastX += 120;
    	}
    	lastY += 25;
    }
    
    public static void main(String[] args) {
    	UserInterface ui = new UserInterface();
    	ui.start();
    }
}

