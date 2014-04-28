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
	SaveDomain sd=new SaveDomain();
	String file2=sd.fileProperties();
	ArrayList<String> temporalPossibilities = new ArrayList<String>();

	private static Scanner sc;

	
	public static void main(String args[]) throws IOException {
		ExtractTemporalFact ex = new ExtractTemporalFact();
		ArrayList<String> listTempFacts = new ArrayList<String>();
		ArrayList<String> listFacts = new ArrayList<String>();
		ArrayList<String> listAtt = new ArrayList<String>();
		String file = "file/Volcano" + ".csv";
		ArrayList<String> temporalPossibilities = new ArrayList<String>();
		try {
			sc = new Scanner(System.in);
			System.out
					.println("Introduire le nombre d'indications temporelles à ajouter / 0 pour la liste par défaut / 1 pour ajouter un élément  ");
			int n = sc.nextInt();
			if (n == 0)
				temporalPossibilities = ex.putTemporalPoss();
			else if (n == 1) {
				System.out.println("Nombre d'element ajouter2");
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
		
		//***new Proposition***
		
		//read properties from file and make list of couple.
		
		ArrayList<StringPair> cp=ex.CoupleList(file);
		
		
		
		
		
		
		
		
		
		
		
		
		//***newProposition***
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		ArrayList<String> temp = ex.readFileTemporalFacts(file,
				temporalPossibilities);
		System.out.println("**** liste des tokens temporels : ****");
		PrintFacts p = new PrintFacts();
		listTempFacts = p.duplicationDetector(temp);

		p.printFactList(listTempFacts);

		// la liste des attributs
		System.out.println("**** liste des Attributs : ****");
		listFacts = p.selectAllAtribut(listTempFacts, temporalPossibilities);
		p.printFactList(p.duplicationDetector(listFacts));

		// choix d'un fait temporel
		try {
			System.out
					.println("**** Choisir une liste intéressante d'attributs  : ****");
			System.out.println("Introduire le nombre d'attributs "
					+ listFacts.size());
			int nbChoix = sc.nextInt();
			int choix;
			for (int i = 0; i < nbChoix; i++) {
				System.out.println("Ajouter le " + (i + 1) + " élèment");
				choix = sc.nextInt();
				String fact = listFacts.get(choix - 1);
				listAtt.add(fact);
			}
			System.out.println("***La liste des attributs***");
			p.printFactList(p.duplicationDetector(listAtt));

			// related facts
			ExtractRelatedFacts re = new ExtractRelatedFacts();

			ArrayList<String> listRelaFacts = re.readFileRelatedFacts(listAtt,
					file);

			ArrayList<String> relatedWithoutTemp = re.relatedFactsWithoutTemp(
					listRelaFacts, listTempFacts, temporalPossibilities);
			System.out.println("***La liste des attributs reliés***");

			p.printFactList(p.duplicationDetector(relatedWithoutTemp));

			System.out
					.println("****Choix de l'expert un fait temporel entre 1 et "
							+ listTempFacts.size() + "****");
			int choix1 = sc.nextInt();
			System.out
					.println("****Choix de l'expert un fait reliée entre 1 et "
							+ relatedWithoutTemp.size() + "****");
			int choix2 = sc.nextInt();

			System.out
					.println("****Choix de l'expert : Le premier choix est un fait temporel --"
							+ listTempFacts.get(choix1 - 1)
							+ "-- Le deuxième choix est un fait relié --"
							+ relatedWithoutTemp.get(choix2 - 1) + "-- ****");
			System.out.println("Résultat du requête SPARQL ");

			// Open the bloggers RDF graph from the filesystem
//			 Logger rootLogger = Logger.getRootLogger();
//			 rootLogger.setLevel(Level.INFO);
//			 rootLogger.addAppender((Appender) new ConsoleAppender(
//			 new PatternLayout("%-6r [%p] %c - %m%n")));

			// InputStream in = new FileInputStream(new
			// File("file/bloggers.rdf"));
			//
			// Create an empty in-memory model and populate it from the graph
			// Model model =
			// ModelFactory.createMemModelMaker().createModel(null);
			// model.read(in, null); // null base URI, since model URIs are
			// absolute
			// in.close();

			// Create a new query

			String queryString = "PREFIX dbo:<http://dbpedia.org/resources/Volcano> "
					+ "PREFIX dbpo: <http://dbpedia.org/ontology/>"
					+ "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
					+ "SELECT ?x ?y ?z{  ?x dbpedia-owl:"+listTempFacts.get(choix1 - 1)
					+ " ?y." + "?x dbpedia-owl:"+ relatedWithoutTemp.get(choix2 - 1)
					+ " ?z." + "} LIMIT 100	";
	
			String s2 = "PREFIX ot:<http://www.opentox.org/api/1.1#>"
					+ "PREFIX ota:<http://www.opentox.org/algorithmTypes.owl#>"
					+ "PREFIX owl:<http://www.w3.org/2002/07/owl#>"
					+ "PREFIX dc:<http://purl.org/dc/elements/1.1/>"
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
					+ "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
					+ "PREFIX bibrdf:<http://zeitkunst.org/bibtex/0.1/bibtex.owl#>"
					+ "PREFIX bo:<http://www.blueobelisk.org/ontologies/chemoinformatics-algorithms/#>"
					+ "select ?descriptor ?label ?cites ?doi ?definition ?requires ?category ?contributor"
					+ "	where {"
					+ "{{?descriptor rdf:type bo:Algorithm} UNION {?descriptor rdf:type bo:MolecularDescriptor}}."
					+ "	OPTIONAL {?descriptor rdfs:label ?label}."
					+ "          OPTIONAL {?descriptor bo:definition ?definition}."
					+ "        OPTIONAL {?descriptor dc:contributor ?contributor}."
					+ "      OPTIONAL {?descriptor bo:isClassifiedAs ?category}."
					+ "    OPTIONAL {?descriptor bo:requires?requires}."
					+ "  OPTIONAL {?descriptor bo:cites ?cites."
					+ "  ?cites bibrdf:hasTitle ?title."
					+ " OPTIONAL {?cites bo:DOI ?doi.}}.}";

			String test = "PREFIX dbp:<http://dbpedia.org/ontology/>"
					+ "PREFIX dbpprop:<http://dbpedia.org/property/>"
					+ "SELECT ?name ?bday WHERE{"
					+ "?p dbp:birthPlace <http://dbpedia.org/resource/Berlin>."
					+ "?p dbpprop:dateOfBirth ?bday."
					+ "?p dbpprop:name ?name.}";

			String testQuery = "PREFIX dbpedia-owl:<http://dbpedia.org/ontology/> "
					+ "SELECT ?x ?y ?z{  ?x dbpedia-owl:birthDate ?y."
					+ "?x dbpedia-owl:activeYearsStartDate ?z." + "} LIMIT 10";

			Query query = QueryFactory.create(testQuery);

			// // Execute the query and obtain results
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);
			// http://ambit.uni-plovdiv.bg:8080/ontology
			// http://mappings.dbpedia.org/server/ontology/classes/Volcano
			
			ResultSet results = qexec.execSelect();

			// Output query results
			ResultSetFormatter.out(System.out, results, query);
			Scanner s = new Scanner(System.in);
			System.out.println("Valider les resultats Y/N");
			String ctn = s.nextLine();
			if (ctn.toLowerCase().equalsIgnoreCase("y"))
				System.out.println("Save Result");
			else
				System.out.println("Ignore file");

			Writer writer = null;

			try {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("file/spotbase.txt"), "utf-8"));
				writer.write("Something");
				while (results.next() != null) {
					writer.write("s " + results.getRowNumber());
				}

			} catch (IOException ez) {
				System.out.println("Une Exception 1 "
						+ ez.getMessage().toString());

			} finally {
				try {
					writer.close();
				} catch (Exception e) {
					System.out.println("Une Exception 2"
							+ e.getMessage().toString());
				}
			}

			qexec.close();
		} catch (Exception e) {
			System.out.println("Une Exception 3" + e.getMessage().toString());
		}
	}

	

	public ArrayList<String> geTemporalFact() {
		temporalPossibilities.add("year");
		temporalPossibilities.add("date");
		
		ArrayList<String> temp = ex.readFileTemporalFacts(file,
				temporalPossibilities);

		return p.duplicationDetector(temp);

	}

	public ArrayList<String> getAttribut() {
		return p.selectAllAtribut(geTemporalFact(), temporalPossibilities);

	}

	// public ArrayList<String> geRelatedFact() {
	//
	//
	// ArrayList<String> listRelaFacts = re
	// .readFileRelatedFacts(listAtt, file);
	//
	// ArrayList<String> relatedWithoutTemp = re.relatedFactsWithoutTemp(
	// listRelaFacts, geTemporalFact(), temporalPossibilities);
	// System.out.println("***La liste des attributs reliés***");
	//
	// return p.duplicationDetector(relatedWithoutTemp);
	//
	// }
}
