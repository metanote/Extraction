package FrontEnd;



import org.apache.http.client.protocol.RequestAcceptEncoding;
import org.apache.http.client.protocol.ResponseContentEncoding;
import org.apache.http.impl.client.DefaultHttpClient;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
//import com.hp.hpl.jena.query.ResultSet;
//import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.sparql.engine.http.QueryExceptionHTTP;

public class TestMain {
	public static void main(String args[]) {
//		System.getProperties().put("proxySet","true");
//		System.getProperties().put("http.proxyHost", "http://username:pwd@172.31.1.6");
//		System.getProperties().put("proxyPort",8181);
		String service = "http://dbpedia.org/sparql";
		String query = "ASK{}";//select * {<http://dbpedia.org/resource/Beijing> ?p ?o.} LIMIT 100
		System.out.println("start");
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.addRequestInterceptor(new RequestAcceptEncoding());
		httpclient.addResponseInterceptor(new ResponseContentEncoding());
		QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
		
		
		
		try {
			System.out.println("hi");
	///		System.out.println("rst"+qe.execSelect());
//			ResultSet results = qe.execSelect();
			//ResultSetFormatter.out(results);
			if (qe.execAsk())
				System.out.println(service + " IS UP");
		} catch (QueryExceptionHTTP e) {
			System.out.println(service + " IS DOWN "+e.getMessage());
		} finally {
			qe.close();
		}

	}
}
