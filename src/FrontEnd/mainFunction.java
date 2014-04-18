package FrontEnd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.hp.hpl.jena.query.*;

import mining.*;

public class mainFunction {

	private static Scanner sc;

	public static void main(String args[]) throws IOException {
		ExtractTemporalFact ex = new ExtractTemporalFact();
		ArrayList<String> listTempFacts = new ArrayList<String>();
		ArrayList<String> listFacts = new ArrayList<String>();
		ArrayList<String> listAtt = new ArrayList<String>();
		String file = "file/President" + ".csv";
		ArrayList<String> temporalPossibilities = new ArrayList<String>();
		sc = new Scanner(System.in);
		System.out
				.println("Input temporal indication number or 0 for default list ");
		int n = sc.nextInt();
		if (n == 0)
			temporalPossibilities = ex.putTemporalPoss();
		else
			for (int i = 0; i < n + 1; i++) {
				String s = sc.nextLine();
				if (!s.equalsIgnoreCase(""))
					temporalPossibilities.add(s);
				System.out.println("Add new temporal token");

			}

		ArrayList<String> temp = ex.readFileTemporalFacts(file,
				temporalPossibilities);
		System.out.println("**** liste des token temporels : ****");
		PrintFacts p = new PrintFacts();
		listTempFacts = p.duplicationDetector(temp);

		p.printFactList(listTempFacts);

		// la liste des attributs
		System.out.println("**** liste des Attributs : ****");
		listFacts = p.selectAllAtribut(listTempFacts, temporalPossibilities);
		p.printFactList(p.duplicationDetector(listFacts));

		// choix d'un fait temporel

		System.out
				.println("**** Choix liste des attributs intéressants : ****");
		System.out.println("Nombre de choix inf " + listFacts.size());
		int nbChoix = sc.nextInt();
		int choix;
		for (int i = 0; i < nbChoix; i++) {
			System.out.println("ajouter le " + i + "eme element");
			choix = sc.nextInt();
			String fact = listFacts.get(choix - 1);
			listAtt.add(fact);
		}
		System.out.println("***La liste des attributs***");
		p.printFactList(p.duplicationDetector(listAtt));

		// related facts
		ExtractRelatedFacts re = new ExtractRelatedFacts();

		ArrayList<String> listRelaFacts = re
				.readFileRelatedFacts(listAtt, file);

		ArrayList<String> relatedWithoutTemp = re.relatedFactsWithoutTemp(
				listRelaFacts, listTempFacts, temporalPossibilities);
		System.out.println("***La liste des attributs reliés***");
		
		p.printFactList(p.duplicationDetector(relatedWithoutTemp));

		System.out.println("****Choix de l'expert un fait temporel entre 1 et "
				+ listTempFacts.size() + "****");
		int choix1 = sc.nextInt();
		System.out.println("****Choix de l'expert un fait reliée entre 1 et "
				+ relatedWithoutTemp.size() + "****");
		int choix2 = sc.nextInt();

		System.out
				.println("**** Choix de l'expert : Le premier choix est un fait temporel --"
						+ listTempFacts.get(choix1 - 1)
						+ "-- Le deuxième choix est un fait relié --"
						+ relatedWithoutTemp.get(choix2 - 1) + "-- ****");

		// Open the bloggers RDF graph from the filesystem
		Logger rootLogger = Logger.getRootLogger();
		rootLogger.setLevel(Level.INFO);
		rootLogger.addAppender((Appender) new ConsoleAppender(
				new PatternLayout("%-6r [%p] %c - %m%n")));

		// InputStream in = new FileInputStream(new File("file/bloggers.rdf"));
		//
		// Create an empty in-memory model and populate it from the graph
		// Model model = ModelFactory.createMemModelMaker().createModel(null);
		// model.read(in, null); // null base URI, since model URIs are absolute
		// in.close();

		try {

			// Create a new query
			String queryString = "PREFIX dbo:<http://dbpedia.org/resources/Volcano> "
					+ "SELECT ?x ?y ?z{  ?x dbo:"
					+ listTempFacts.get(choix1 - 1)
					+ " ?y."
					+ "?x dbo:"
					+ relatedWithoutTemp.get(choix2 - 1)
					+ " ?z."
					+ "} LIMIT 100	";

			// String queryString =
			// " PREFIX foaf:  <http://xmlns.com/foaf/0.1/>"
			// + "SELECT ?x ?p" + " WHERE {" + "?x dbpedia-owl:abstract ?p  ."
			// + "}";

			// String queryString =
			// "PREFIX ot:<http://www.opentox.org/api/1.1#>"
			// + "PREFIX ota:<http://www.opentox.org/algorithmTypes.owl#>"
			// + "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
			// + "PREFIX dc:<http://purl.org/dc/elements/1.1/>"
			// + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
			// + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
			// + "PREFIX bibrdf:<http://zeitkunst.org/bibtex/0.1/bibtex.owl#>"
			// +
			// "PREFIX bo:<http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#>"
			// +
			// "select ?descriptor ?label ?cites ?doi ?definition ?requires ?category ?contributor"
			// + "	where {"
			// +
			// "{{?descriptor rdf:type bo:Algorithm} UNION {?descriptor rdf:type bo:MolecularDescriptor}}."
			// + "	OPTIONAL {?descriptor rdfs:label ?label}."
			// + "          OPTIONAL {?descriptor bo:definition ?definition}."
			// + "        OPTIONAL {?descriptor dc:contributor ?contributor}."
			// + "      OPTIONAL {?descriptor bo:isClassifiedAs ?category}."
			// + "    OPTIONAL {?descriptor bo:requires?requires}."
			// + "  OPTIONAL {?descriptor bo:cites ?cites."
			// + "  ?cites bibrdf:hasTitle ?title."
			// + " OPTIONAL {?cites bo:DOI ?doi.}}.}";

			Query query = QueryFactory.create(queryString);

			// // Execute the query and obtain results
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://ambit.uni-plovdiv.bg:8080/ontology", query);//
			// http://dbpedia.org
			// http://mappings.dbpedia.org/server/ontology/classes/Volcano
			// // ??
			//
			ResultSet results = qexec.execSelect();

			// Output query results
			ResultSetFormatter.out(System.out, results, query);

			// Important - free up resources used running the query
			qexec.close();
		} catch (Exception e) {
			System.out.println("Une Exception " + e.getMessage().toString());
		}
	}
}
