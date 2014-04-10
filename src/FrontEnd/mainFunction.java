package FrontEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import mining.*;

public class mainFunction {

	private static Scanner sc;

	public static void main(String args[]) throws IOException {
		String file = "file/Volcano.csv";
		ExtractTemporalFact ex = new ExtractTemporalFact();

		ArrayList<String> listTempFacts = ex.readFileTemporalFacts(file);
		System.out.println(" liste des faits temporels : ");
		PrintFacts p = new PrintFacts();
		p.printFactList(listTempFacts);
		ArrayList<String> listFacts = ex.readFileFacts(listTempFacts);
		System.out.println(" liste des faits : ");
		p.printFactList(listFacts);
		ExtractRelatedFacts re = new ExtractRelatedFacts();
		ArrayList<String> listRelaFacts = re.readFileRelatedFacts(listFacts,
				file);
		ArrayList<String> relatedWithoutTemp = re.relatedFactsWithoutTemp(
				listRelaFacts, listTempFacts);
		System.out.println(" liste des faits reliés : ");
		p.printFactList(relatedWithoutTemp);
//		sc = new Scanner(System.in);
//		System.out.println("Choix d'un fait temporelle dans la liste");
//		int choix = sc.nextInt();
//		System.out.println("Choix d'un fait relié");
//		int choix2 = sc.nextInt();
//		System.out
//				.println("Choix de l'expert : Le premier choix est un fait temporel "
//						+ listTempFacts.get(choix - 1)
//						+ " Le deuxième choix est un fait relié "
//						+ relatedWithoutTemp.get(choix2 - 1));
//
//		// Open the bloggers RDF graph from the filesystem
//		Logger rootLogger = Logger.getRootLogger();
//		rootLogger.setLevel(Level.INFO);
//		rootLogger.addAppender((Appender) new ConsoleAppender(
//				new PatternLayout("%-6r [%p] %c - %m%n")));
//
//		InputStream in = new FileInputStream(new File("file/bloggers.rdf"));
//
//		// Create an empty in-memory model and populate it from the graph
//		Model model = ModelFactory.createMemModelMaker().createModel(null);
//		model.read(in, null); // null base URI, since model URIs are absolute
//		in.close();
//
//		// Create a new query
//		String queryString = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
//				+ "SELECT ?name ?depiction{" + "  ?person foaf:name ?name ."
//				+ "OPTIONAL {" + "?person foaf:depiction ?depiction ." + "} ."
//				+ "}";
//
//		Query query = QueryFactory.create(queryString);
//
//		// Execute the query and obtain results
//		QueryExecution qe = QueryExecutionFactory.create(query, model);
//		ResultSet results = qe.execSelect();
//
//		// Output query results
//		ResultSetFormatter.out(System.out, results, query);
//
//		// Important - free up resources used running the query
//		qe.close();

	}
}
