package FrontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class Interface extends javax.swing.JFrame {
	URL url;
	ArrayList<String> listTempFacts = new ArrayList<String>();
	ArrayList<String> AttList = new ArrayList<String>();

	public Interface() {
		initComponents();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {

		jTextArearObject = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jButton1 = new javax.swing.JButton();
		jButtonchoose= new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		String defData[] = { "Temporal Facts List" };
		String defData2[] = { "Attributs List" };
		String defData3[] = { "Result" };
		MainFunction mf=new MainFunction();
		listTempFacts=mf.geTemporalFact();
		jList1 = new javax.swing.JComboBox(defData);
		
		for (String f:listTempFacts)
			jList1.addItem(f);
		
		for (String s:AttList)
			jList2.addItem(s);
		
		jButtonExtractFile = new javax.swing.JButton();
		jList2 = new javax.swing.JComboBox(defData2);
		resultat = new javax.swing.JComboBox(defData3);

		openBtn = new javax.swing.JButton();
		jLabel7 = new javax.swing.JLabel();
		jLabelpieceJointe = new javax.swing.JLabel();
		fc = new JFileChooser();
		jLabel8 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jLabel2.setText("what?");
		jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
		jLabel1.setText("Extraction From DBpedia");
		jButtonchoose.setText("Expert Choice");
		jButton1.setText("Choose file");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}

			@SuppressWarnings("deprecation")
			private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showOpenDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					try {
						url = chooser.getSelectedFile().toURL();
						System.out.println(url);
						jLabelpieceJointe.setText(url.toString());

					} catch (MalformedURLException exception) {
						System.out.println("The URL was malformed ... ");

					}
				}

			}// GEN-LAST:event_jButton1ActionPerformed

		});
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);
		openBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ fc.getSelectedFile().getName());
					fc.showOpenDialog(null);
				}

			}
		});

		jLabel4.setText("Temporal Facts");

		jLabel5.setText("Related Facts");

		jLabel6.setText("Resultat");

		jButtonExtractFile.setText("Extract");
		jButtonExtractFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonExtractFileActionPerformed(evt);
			}
		});

		setLocationRelativeTo(null);

		jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"dbpedia.png")));

		javax.swing.GroupLayout jTextArearObjectLayout = new javax.swing.GroupLayout(
				jTextArearObject);
		jTextArearObject.setLayout(jTextArearObjectLayout);
		jTextArearObjectLayout
				.setHorizontalGroup(jTextArearObjectLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jTextArearObjectLayout
										.createSequentialGroup()
										.addGroup(
												jTextArearObjectLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																jTextArearObjectLayout
																		.createSequentialGroup()
																		.addGap(5,
																				5,
																				5)
																		.addGroup(
																				jTextArearObjectLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jLabel2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								100,
																								javax.swing.GroupLayout.PREFERRED_SIZE)

																						.addComponent(
																								jLabel1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								200,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(18,
																				18,
																				18)
																		.addContainerGap()
																		.addGroup(
																				jTextArearObjectLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jTextArearObjectLayout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel7)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																												34,
																												Short.MAX_VALUE)
																										.addComponent(
																												jLabelpieceJointe,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												311,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jTextArearObjectLayout
																										.createSequentialGroup()
																										.addGroup(
																												jTextArearObjectLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addGroup(
																																jTextArearObjectLayout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.TRAILING)
																																		.addGroup(
																																				jTextArearObjectLayout
																																						.createParallelGroup(
																																								javax.swing.GroupLayout.Alignment.TRAILING)
																																						.addGroup(
																																								jTextArearObjectLayout
																																										.createParallelGroup(
																																												javax.swing.GroupLayout.Alignment.TRAILING)
																																										.addComponent(
																																												jButton1,
																																												javax.swing.GroupLayout.PREFERRED_SIZE,
																																												190,
																																												javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addComponent(
																																												jLabel3,
																																												javax.swing.GroupLayout.Alignment.LEADING))
																																						.addComponent(
																																								jLabel4,
																																								javax.swing.GroupLayout.Alignment.LEADING))
																																		.addComponent(
																																				jLabel5,
																																				javax.swing.GroupLayout.Alignment.LEADING))
																														.addComponent(
																																jButtonExtractFile,
																																javax.swing.GroupLayout.Alignment.LEADING))
																										.addGap(18,
																												18,
																												18)
																										.addGroup(
																												jTextArearObjectLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING,
																																false)
																														.addComponent(
																																jList1)
																														.addComponent(
																																jList2)
																														.addComponent(
																																resultat,
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																311,
																																Short.MAX_VALUE))))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel8)))
										.addContainerGap()));
		jTextArearObjectLayout
				.setVerticalGroup(jTextArearObjectLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jTextArearObjectLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel1)
										.addGap(27, 27, 27)
										.addGroup(
												jTextArearObjectLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jTextArearObjectLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel8,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				236,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButtonExtractFile,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				50,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jTextArearObjectLayout
																		.createSequentialGroup()
																		.addComponent(
																				jButton1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jLabel3)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jTextArearObjectLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel4)
																						.addComponent(
																								jList1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jTextArearObjectLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel5)
																						.addComponent(
																								jList2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jTextArearObjectLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jButtonExtractFile)
																						.addComponent(
																								resultat,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								103,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				jTextArearObjectLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel7)
																						.addComponent(
																								jLabelpieceJointe,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								34,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				22,
																				Short.MAX_VALUE)
																		.addComponent(
																				jLabel2)))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jTextArearObject,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jTextArearObject,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		pack();
	}

	private void jButtonExtractFileActionPerformed(java.awt.event.ActionEvent evt) {
		String[] lines = jTextArea1.getText().split("\\n");
		String msg = "";
		for (String s : lines) {
			msg = msg + s;
		}

	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Interface.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Interface.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Interface.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Interface.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Interface().setVisible(true);

			}
		});
	}

	private javax.swing.JButton jButtonExtractFile;
	private javax.swing.JButton jButtonchoose;
	private javax.swing.JButton openBtn;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	@SuppressWarnings("rawtypes")
	private javax.swing.JComboBox jList1;
	@SuppressWarnings("rawtypes")
	private javax.swing.JComboBox jList2;
	@SuppressWarnings("rawtypes")
	private javax.swing.JComboBox resultat;
	private javax.swing.JLabel jLabelpieceJointe;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JPanel jTextArearObject;
	private JFileChooser fc;

}
