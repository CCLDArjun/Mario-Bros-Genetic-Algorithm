import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;


public class gui extends javax.swing.JFrame {

	int GenNum;
	/**
	 * Creates new form gui
	 */
	public gui() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jSlider1 = new javax.swing.JSlider();
		jLabel1 = new javax.swing.JLabel();
		jSlider2 = new javax.swing.JSlider();
		jLabel2 = new javax.swing.JLabel();
		jSlider3 = new javax.swing.JSlider();
		JLabel3 = new javax.swing.JLabel();
		button = new JButton("Train");
		button2 = new JButton("Create new Level");
		Jlabel4 = new javax.swing.JLabel();
		
		
		

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jSlider1.setFont(new java.awt.Font("Tahoma", 1, 18)); 
		jSlider1.setForeground(new java.awt.Color(255, 0, 102));
		jSlider1.setMajorTickSpacing(10);
		jSlider1.setPaintLabels(true);
		jSlider1.setPaintTicks(true);
		jSlider1.setValue(0);
		jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				jSlider1StateChanged(evt);
			}
		});
		jSlider2.setFont(new java.awt.Font("Tahoma", 1, 18)); 
		jSlider2.setForeground(new java.awt.Color(255, 0, 102));
		jSlider2.setMajorTickSpacing(10);
		jSlider2.setPaintLabels(true);
		jSlider2.setPaintTicks(true);
		jSlider2.setValue(0);
		jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				jSlider2StateChanged1(evt);
			}
		});
		jSlider3.setFont(new java.awt.Font("Tahoma", 1, 18)); 
		jSlider3.setForeground(new java.awt.Color(255, 0, 102));
		jSlider3.setMajorTickSpacing(10);
		jSlider3.setPaintLabels(true);
		jSlider3.setPaintTicks(true);
		jSlider3.setValue(0);
		jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				jSlider3StateChanged(evt);
			}
		});
		





		jLabel1.setFont(new java.awt.Font("serif", Font.BOLD, 22)); // NOI18N
		jLabel1.setText("0" + "%" + " Mutation Rate");
		jLabel2.setText("Generations: " + "0");
		jLabel2.setFont(new java.awt.Font("serif", Font.BOLD, 22));
		JLabel3.setText("PopSize: " + "0");
		JLabel3.setFont(new java.awt.Font("serif", Font.BOLD, 22 ));
		Jlabel4.setFont(new java.awt.Font("serif", Font.BOLD, 22)); // NOI18N
		Jlabel4.setText("Mario Bros Genetic Algorithm");
		

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup()


				.addComponent(jLabel1)
				
				
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						
						.addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						//				.addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						//						.addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabel1)
						.addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabel2)
						.addComponent(jSlider3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(JLabel3)
						.addComponent(button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

						
						//						.addComponent(JLabel3)
						));	

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				
				
				.addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				//.addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				//		.addComponent(jSlider3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				//
				.addComponent(jLabel1)
				.addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jLabel2)
				.addComponent(jSlider3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(JLabel3)
			

				.addComponent(button,  javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(button2,  javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				//				.addComponent(JLabel3)
				//
				
				);


		jLabel1.getAccessibleContext().setAccessibleName("JLblVal");
		jLabel2.getAccessibleContext().setAccessibleName("JLblVal");


		pack();
	}// </editor-fold>                        

	private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {                                   
		jLabel1.setText(Integer.toString(jSlider1.getValue()) + "%" + "Mutation Rate");

	}

	private void jSlider2StateChanged1(javax.swing.event.ChangeEvent evt) {
		jLabel2.setText("Generations:" + Integer.toString(jSlider2.getValue()));

	}
	private void jSlider3StateChanged(javax.swing.event.ChangeEvent evt) {
		JLabel3.setText("PopSize: " + jSlider3.getValue());

	}




	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new gui().setVisible(true);
			}
		});
	}

	// Variables declaration                  
	private javax.swing.JLabel jLabel1;
	private javax.swing.JSlider jSlider1;
	private javax.swing.JSlider jSlider2; 
	private JLabel jLabel2;
	private JSlider jSlider3; 
	private javax.swing.JLabel JLabel3;
	Image img;
	JButton button;
	JButton button2;
	private javax.swing.JLabel Jlabel4; 
}

