package FrontEnd;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;

public class SaveDomain {

	public String fileProperties(String fileName)

	{ // save all properties from dbpedia
		Writer writer = null;
		try {
			
	
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("file/"+fileName), "utf-8"));
			String sQuery = "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> SELECT DISTINCT ?prop WHERE { ?prop rdfs:domain ?y }";

			Query query = QueryFactory.create(sQuery);

			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);

			ResultSet results = qexec.execSelect();

			// ResultSetFormatter.out(results);
			System.out.println("write result in file");
			while (results.hasNext()) {
				QuerySolution qs = results.nextSolution();
				if (qs.getResource("prop") != null) {
					String uri = qs.getResource("prop").getURI();
					if (uri.contains("http://dbpedia.org/ontology/"))
					{
					String newuri=uri.substring(28, uri.length()) ;
					if(newuri.length()>2)
					if (newuri.contains("/")){
						String str = newuri.substring(newuri.indexOf("/") + 1, newuri.length());
						writer.write(str+"\n");
					} else
						writer.write(newuri+"\n");
					}
					}}
				
			
			writer.close();
			System.out.println("All properties have been written in file");
		} catch (Exception e) {
			System.out.print("You have an exception  " + e.getMessage());
		}
		return "file/"+fileName;
	}
}
