import java.awt.FlowLayout;
import java.awt.Font;


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
		jtf = new javax.swing.JTextField(7);
		jLabel2 = new javax.swing.JLabel();

		try {
			GenNum = Integer.parseInt(jtf.getText());

		}
		catch (NumberFormatException e) {
			System.out.println("Please enter a valid input.");
		}

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
		
		




		jLabel1.setFont(new java.awt.Font("serif", Font.BOLD, 22)); // NOI18N
		jLabel1.setText("0" + "%" + " Mutation Rate");
		jLabel2.setFont(new java.awt.Font("serif", Font.BOLD, 22)); // NOI18N
		jLabel2.setText("0" + " Generations");


		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGap(206, 206, 206)
						.addComponent(jLabel1)
						.addComponent(jLabel2)
						.addContainerGap(290, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

						.addContainerGap())
				);

		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGap(100, 100, 100)
						.addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

						.addGap(20, 20, 20)
						.addComponent(jLabel1)
						.addComponent(jLabel2)
						.addContainerGap(61, Short.MAX_VALUE))
				);


		jLabel1.getAccessibleContext().setAccessibleName("JLblVal");
		jLabel2.getAccessibleContext().setAccessibleName("JLblVal");


		pack();
	}// </editor-fold>                        

	private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {                                   
		jLabel1.setText(Integer.toString(jSlider1.getValue()) + "%" + "Mutation Rate");

	}

	private void GenerationsInputChanged(javax.swing.event.ChangeEvent evt) {
		jLabel2.setText(GenNum + " Generations");
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
	private JTextField jtf; 
	private JLabel jLabel2;
}



