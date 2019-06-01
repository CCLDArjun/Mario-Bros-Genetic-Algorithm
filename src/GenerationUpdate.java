import javax.swing.*;

import java.awt.Dimension;
import java.awt.Font;
public class GenerationUpdate {
	private JFrame frame = new JFrame();
	
	public GenerationUpdate(String text) {
		JLabel jtf = new JLabel("<html>"+text+"</html>");
		jtf.setFont(new Font(jtf.getName(), Font.PLAIN, 20));
		frame.add(jtf);
		frame.pack();
		frame.setVisible(true);
		frame.setTitle("Generation Update!");
	}
	
	public GenerationUpdate(String text, int height) {
		frame.setPreferredSize(new Dimension(400, height));
		JLabel jtf = new JLabel("<html>"+text+"</html>");
		jtf.setFont(new Font(jtf.getName(), Font.PLAIN, 20));
		frame.add(jtf);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		GenerationUpdate gu = new GenerationUpdate("lol<br>lol");
	}
}
