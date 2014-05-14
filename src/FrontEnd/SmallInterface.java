package FrontEnd;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import mining.ExtractTemporalFact;

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
		jTextfileName1 = new javax.swing.JTextField();
		jTextfileName2 = new javax.swing.JTextField();
		jTextfileName1.setText("Input File Name .txt");
		jTextfileName2.setText("OutPut Quads File");

		jLabel1 = new JLabel("", JLabel.CENTER);
		jLabel2 = new javax.swing.JLabel();
		jLabelRst = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();

		fc = new JFileChooser();

		jButtonSendRequest = new javax.swing.JButton();
		jPic = new javax.swing.JLabel("", JLabel.CENTER);

		jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 16));
		jLabel1.setText("@Temporal Annotation@");
		jPic.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"dbpedia.png")));
		jTextArea1.setColumns(40);
		jTextArea1.setRows(12);
		jTextArea1.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2,
				Color.GRAY));
		jTextArea1.setEditable(false);
		scroll = new JScrollPane(jTextArea1);

		jButtonSendRequest.setText("Get SelectedItems");

		jLabel5.setText("Construct Quads File");
		jLabel2.setText("Find SelectedItems");
		jLabel2.setText("Extract all possibilities");
		jLabel3.setText("Request Results");
		jLabel4.setText("Results With Label");
		mainFrame = new JFrame("Quads DBpedia Extractor");
		mainFrame.setSize(1200, 670);
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

		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void showGroupLayoutDemo() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		jButtonValidate = new javax.swing.JButton();
		jButtonClean = new javax.swing.JButton();
		jButtonValidate.setText("Save in File");
		String defData[] = { "Temporal Facts List" };
		jList1 = new javax.swing.JComboBox(defData);
		fc.setDialogTitle("Choose file");
		JButton jButtonClean = new JButton("Clear Textarea");
		jButtonClean.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCleanActionPerformed(evt);
			}

			private void jButtonCleanActionPerformed(ActionEvent evt) {
				jTextArea1.setText("");

			}
		});
		JButton jButtonExtract = new JButton("Extract From DBpedia");
		jButtonExtract.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonExtractActionPerformed(evt);
			}

			private void jButtonExtractActionPerformed(ActionEvent evt) {
				SaveDomain sd = new SaveDomain();
				String fileName = jTextfileName1.getText();

				if (fileName.endsWith(".txt")) {

					String file = sd.fileProperties(fileName);
					jLabel3.setText(file + " has been created");
				} else
					jLabel3.setText("Verify your input");
			}
		});
		JButton jButtonSetQuads = new JButton("Validation : Build Quads file");

		jButtonSetQuads.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonSetQuadsActionPerformed(evt);
			}

			private void jButtonSetQuadsActionPerformed(ActionEvent evt) {
				String fileQuads = jTextfileName2.getText();
				String SelectedItem = jList1.getSelectedItem().toString();
				String tempProp = SelectedItem.substring(0,
						SelectedItem.indexOf(","));

				String relatedProp = SelectedItem.substring(
						SelectedItem.indexOf(",") + 1, SelectedItem.length());
				String mynewQuery = "  PREFIX dbp:<http://dbpedia.org/ontology/> select ?x ?y ?z "
						+ "where {?x dbp:"
						+ relatedProp
						+ " ?y;"
						+ "dbp:"
						+ tempProp + " ?z.}";
				ExtractTemporalFact ex = new ExtractTemporalFact();
				ex.saveQuads(fileQuads, mynewQuery, tempProp, relatedProp);
				jLabelRst.setText(fileQuads + " is saved now");

			}
		});

		JButton jButtonFindCouple = new JButton("Find Couples");
		jButtonFindCouple
				.addActionListener(new java.awt.event.ActionListener() {
					public String inits[] = new String[1000];

					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonFindCoupleActionPerformed(evt);
					}

					private void jButtonFindCoupleActionPerformed(
							ActionEvent evt) {
						MainFunction mf = new MainFunction();
						ExtractTemporalFact ex = new ExtractTemporalFact();
						ArrayList<String> cp = ex.tFactsList("file/"
								+ jTextfileName1.getText(), mf.geTemporalFact());
						String fileSelectedItem = mf.getPairListAtt("file/"
								+ jTextfileName1.getText(), cp);
						Set<String> lines = new HashSet<String>();
						try {

							@SuppressWarnings("resource")
							BufferedReader r2 = new BufferedReader(
									new FileReader(new File(fileSelectedItem)));

							while (r2.readLine() != null) {
								lines.add(r2.readLine());

							}
							for (String s : lines)
								if(s!=null)
								jList1.addItem(s);

							// Set storedSet = new TreeSet(lines);
							// Iterator<String> iterator = storedSet.iterator();
							// iterator.next();
							// while (iterator.hasNext())
							// jList1.addItem(iterator.next());

						}

						catch (Exception e) {
							System.out.println(e.getMessage());
						}

					}
				});
		JButton jButtonRequest = new JButton("Send Request ");
		jButtonRequest.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonRequestActionPerformed(evt);
			}

			private void jButtonRequestActionPerformed(ActionEvent evt) {
				String SelectedItem = jList1.getSelectedItem().toString();
				String tempProp = SelectedItem.substring(0,
						SelectedItem.indexOf(","));

				String relatedProp = SelectedItem.substring(
						SelectedItem.indexOf(",") + 1, SelectedItem.length());

				String myQuery = " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX dbp:<http://dbpedia.org/ontology/> select (CONCAT(?label1, ' "
						+ relatedProp
						+ " ', ?label2, ' : ', ?date) AS ?result)"
						+ "where {?subject dbp:"
						+ relatedProp
						+ " ?place;"
						+ "dbp:"
						+ tempProp
						+ " ?date;"
						+ "rdfs:label ?label1 ."
						+ "?place rdfs:label ?label2 ."
						+ "FILTER(lang(?label1)='en' && lang(?label2)='en')}"
						+ "LIMIT 100 OFFSET 200";

				Query query = QueryFactory.create(myQuery);
				QueryExecution qexec = QueryExecutionFactory.sparqlService(
						"http://dbpedia.org/sparql", query);
				ResultSet results = qexec.execSelect();
				String rst = "";
				if (!results.hasNext())

					rst += "No result";
				else
					while (results.hasNext()) {
						QuerySolution qs = results.nextSolution();

						if (qs.getLiteral("result") != null) {

							rst += qs.getLiteral("result").toString() + "\n";
						}

					}
				jTextArea1.setText(rst);
			}
		});
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
																		scroll)
																.addComponent(
																		jButtonClean)
																.addComponent(
																		jTextfileName2)
																.addComponent(
																		jButtonSetQuads)

																.addComponent(
																		jLabelRst)))

				)

		);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(jLabel1).addComponent(jTextfileName1)
				.addComponent(jButtonExtract).addComponent(jLabel3)
				.addComponent(jLabel2).addComponent(jButtonFindCouple)
				.addComponent(jList1).addComponent(jButtonRequest)
				.addComponent(jLabel4).addComponent(scroll)
				.addComponent(jButtonClean).addComponent(jTextfileName2)
				.addComponent(jButtonSetQuads).addComponent(jLabelRst));
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
	private javax.swing.JButton jButtonClean;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabelRst;
	private javax.swing.JScrollPane scroll;
	private javax.swing.JTextArea jTextArea1;
	private JFileChooser fc;
	private javax.swing.JLabel jPic;
	private javax.swing.JComboBox jList1;
	private javax.swing.JTextField jTextfileName1;
	private javax.swing.JTextField jTextfileName2;
}