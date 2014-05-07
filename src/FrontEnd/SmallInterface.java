package FrontEnd;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class SmallInterface {
	private JFrame mainFrame;
	private JPanel controlPanel;

	public SmallInterface() {
		prepareGUI();
	}

	public static void main(String[] args) {
		SmallInterface swingInterface = new SmallInterface();
		swingInterface.showGroupLayoutDemo();
	}

	private void prepareGUI() {

		jTextArea1 = new javax.swing.JTextArea();
		jTextArea2 = new javax.swing.JTextArea();
		jTextfileName1 = new javax.swing.JTextField();
		jTextfileName2 = new javax.swing.JTextField();
		jTextfileName1.setText("File Name");
		jTextfileName2.setText("File Name");
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();

		jLabel5 = new javax.swing.JLabel();
		jLabel1 = new JLabel("", JLabel.CENTER);
		jLabel4 = new javax.swing.JLabel();
		fc = new JFileChooser();

		jButtonSendRequest = new javax.swing.JButton();
		jPic = new javax.swing.JLabel("", JLabel.CENTER);

		jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
		jLabel1.setText("@Temporal Annotation@");
		jPic.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"dbpedia.png")));
		jTextArea1.setColumns(35);
		jTextArea1.setRows(12);
		jTextArea1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
				Color.GRAY));
		jButtonSendRequest.setText("Get Pairs");

		jLabel5.setText("Construct Quads File");
		jLabel2.setText("Find Pairs");
		jLabel2.setText("Extract all possibilities");
		jLabel3.setText("Request Results");
		jLabel4.setText("Results With Label");
		mainFrame = new JFrame("Quads DBpedia Extractor");
		mainFrame.setSize(1200, 600);
		mainFrame.setDefaultLookAndFeelDecorated(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new GridLayout(1, 1));

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		// mainFrame.add(jLabel1);
		// mainFrame.add(jPic);
		mainFrame.add(controlPanel);
		// mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void showGroupLayoutDemo() {
		JPanel panel = new JPanel();
		// panel.setBackground(Color.WHITE);
		// panel.setSize(200, 200);
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		jButtonValidate = new javax.swing.JButton();
		jButtonValidate.setText("Save in File");
		String defData[] = { "Temporal Facts List" };
		jList1 = new javax.swing.JComboBox(defData);
		fc.setDialogTitle("Choose file");
		JButton jButtonExtract = new JButton("Extract From DBpedia");
		jButtonExtract.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonExtractActionPerformed(evt);
			}

			private void jButtonExtractActionPerformed(ActionEvent evt) {
				SaveDomain sd = new SaveDomain();
				String fileName = jTextfileName1.getText();
				String rst = sd.fileProperties(fileName);
				if (rst.endsWith(".txt"))
					jLabel3.setText(rst + " have been created");
				else
					jLabel3.setText("Verify your input");
			}
		});
		JButton jButtonSetQuads = new JButton("Validation : Build Quads file");
		JButton jButtonFindCouple = new JButton("Find Couples");
		jButtonFindCouple.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonFindCoupleActionPerformed(evt);
			}

			private void jButtonFindCoupleActionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				
			}});
		JButton jButtonRequest = new JButton("Send Request ");

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1)
												.addComponent(jTextfileName1)
												.addComponent(jButtonExtract)
												.addComponent(jLabel3))
								.addGroup(
										layout.createSequentialGroup()
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.LEADING)
																.addComponent(
																		jLabel2)
																.addComponent(
																		jButtonFindCouple)
																.addComponent(
																		jList1)
																.addComponent(
																		jButtonRequest)))
								.addGroup(
										layout.createSequentialGroup()
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.LEADING)
																.addComponent(
																		jLabel4)
																.addComponent(
																		jTextArea1)

																.addComponent(
																		jButtonSetQuads)))

				)

		);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(jLabel1).addComponent(jTextfileName1)
				.addComponent(jButtonExtract).addComponent(jLabel3)
				.addComponent(jLabel2).addComponent(jButtonFindCouple)
				.addComponent(jList1).addComponent(jButtonRequest)
				.addComponent(jLabel4).addComponent(jTextArea1)
				.addComponent(jButtonSetQuads));
		panel.setLayout(layout);
		Border paddingBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);

		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY),
				paddingBorder));
		controlPanel.add(panel);

		mainFrame.setVisible(true);
	}

	private javax.swing.JButton jButtonSendRequest;
	private javax.swing.JButton jButtonValidate;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private JFileChooser fc;
	private javax.swing.JLabel jPic;
	private javax.swing.JComboBox jList1;
	private javax.swing.JTextField jTextfileName1;
	private javax.swing.JTextField jTextfileName2;
}