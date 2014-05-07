package FrontEnd;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.hp.hpl.jena.query.*;

import mining.*;

public class MainFunction {
	ExtractTemporalFact ex = new ExtractTemporalFact();
	ExtractRelatedFacts re = new ExtractRelatedFacts();
	PrintFacts p = new PrintFacts();
	String file = "file/Volcano" + ".csv";

	ArrayList<String> temporalPossibilities = new ArrayList<String>();

	private static Scanner sc;

	public static void main(String args[]) throws IOException {
		ExtractTemporalFact ex = new ExtractTemporalFact();
		ArrayList<String> temporalPossibilities = new ArrayList<String>();

		try {
			sc = new Scanner(System.in);
			System.out
					.println("Introduire le nombre d'indications temporelles à ajouter / 0 pour la liste par défaut / 1 pour ajouter un élément  ");
			int n = sc.nextInt();
			if (n == 0)
				temporalPossibilities = ex.putTemporalPoss();
			else if (n == 1) {
				System.out.println("Nombre d'element ajouter 2");
				int elem = sc.nextInt();
				for (int i = 0; i < elem + 1; i++) {
					System.out.println("Add another token");
					String s = sc.nextLine();
					if (!s.equalsIgnoreCase("")) {
						temporalPossibilities = ex.putTemporalPoss();
						temporalPossibilities.add(s);

					}
				}

			} else
				for (int i = 0; i < n + 1; i++) {
					String s = sc.nextLine();
					if (!s.equalsIgnoreCase(""))
						temporalPossibilities.add(s);
					System.out.println("Add new temporal token");

				}
		} catch (Exception Ex) {
			System.out.println("Exception il faut introduire un chiffre "
					+ Ex.getMessage());
		}

		
		// read properties from file and make list of couple.

		System.out.println("Do you want to check properties ? yes/no");
		@SuppressWarnings("resource")
		Scanner scc = new Scanner(System.in);
		String rep = scc.nextLine().toLowerCase();

		String file2;
		if (rep.equalsIgnoreCase("yes")) {
			SaveDomain sd = new SaveDomain();
			String fileName = "properties.txt";
			file2 = sd.fileProperties(fileName);
		} else
			file2 = "file/properties.txt";

		ArrayList<String> cp = ex.tFactsList(file2, temporalPossibilities);

		// print in file
		System.out.println("Now Expert choice ");

		// return line pair
		@SuppressWarnings("resource")
		Scanner sca = new Scanner(System.in);
		int lineNumber = sca.nextInt();

		String pair = ex.returnPair(lineNumber, "file/rst.txt");

		// System.out.println("your couple is " + pair);

		String tempProp = pair.substring(0, pair.indexOf(","));

		String relatedProp = pair.substring(pair.indexOf(",") + 1,
				pair.length());

		

		System.out.println("SPARQL Query");

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

		// Execute the query and obtain results QueryExecution
		QueryExecution qexec = QueryExecutionFactory.sparqlService(
				"http://dbpedia.org/sparql", query);
		ResultSet results = qexec.execSelect();
		// Output query results
		ResultSetFormatter.out(System.out, results, query);

		// save result dans un file

		System.out.println("choose file name ");
		@SuppressWarnings("resource")
		Scanner scf = new Scanner(System.in);
		String fileName = scf.nextLine();

		String mynewQuery = "  PREFIX dbp:<http://dbpedia.org/ontology/> select ?x ?y ?z "
				+ "where {?x dbp:"
				+ relatedProp
				+ " ?y;"
				+ "dbp:"
				+ tempProp
				+ " ?z.}";
		ex.saveQuads(fileName, mynewQuery, tempProp, relatedProp);

	}

	public ArrayList<String> geTemporalFact() {
		temporalPossibilities.add("year");
		temporalPossibilities.add("date");

		ArrayList<String> temp = ex.readFileTemporalFacts(file,
				temporalPossibilities);

		return p.duplicationDetector(temp);

	}

	public String getPairListAtt(String file2, ArrayList<String> cp) {

		ArrayList<ArrayList<StringPair>> listPairs = new ArrayList<ArrayList<StringPair>>();
		ArrayList<String> listMotifs = new ArrayList<String>();
		listMotifs = ex.findMotifs(cp, temporalPossibilities);

		ArrayList<String> listMWD = new ArrayList<String>();

		// filter duplicate

		listMWD = ex.removeDuplicates(listMotifs);
		listPairs = ex.findPairs(listMWD, file2);

		// Liste des vrais couples

		ArrayList<StringPair> listTPFR = new ArrayList<StringPair>();

		listTPFR = ex.findListPairs(listPairs, temporalPossibilities);

		// filter

		ArrayList<StringPair> finalpairlist = new ArrayList<StringPair>();
		for (StringPair sp : listTPFR)
			for (ArrayList<StringPair> p : listPairs)
				for (StringPair s : p) {
					if (s.getRightString().equalsIgnoreCase(sp.getLeftString())) {
						StringPair ss = new StringPair(s.getRightString(),
								sp.getRightString());
						finalpairlist.add(ss);
					}
				}

		// last filter

		ArrayList<StringPair> finallist = new ArrayList<StringPair>();

		for (StringPair p : finalpairlist)
			for (String t : temporalPossibilities) {
				if (p.getRightString().toLowerCase().endsWith(t.toLowerCase()) == false) {
					StringPair so = new StringPair(p.getLeftString(),
							p.getRightString());

					finallist.add(so);
				}
			}

		ArrayList<StringPair> temp = new ArrayList<StringPair>();
		temp = ex.removeDuplicatesPairs(finallist);
		Writer w2 = null;
		try {

			w2 = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("file/FilePairs.txt"), "utf-8"));
			for (StringPair p : temp) {
				w2.write(p.getLeftString() + "," + p.getRightString() + "\n");
			}
			w2.close();
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}

		return "file/FilePairs.txt";
	}

	public ArrayList<String> getAttribut() {
		return p.selectAllAtribut(geTemporalFact(), temporalPossibilities);

	}

}
