package FrontEnd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import mining.ExtractTemporalFact;

public class TestMain {

	     public static void main(String args[]) throws IOException {
	    
	 //   System.out.print("certificationDates".charAt("certificationDates".length()-("date".length()+1))=="Date".toUpperCase().charAt(0));
	  //&&((line.charAt(line.length()-tf.length())==tf.toUpperCase().charAt(0))||(line.charAt(line.length()-(tf.length()+1))==tf.toUpperCase().charAt(0)))  	 
	    //	 MainFunction mf = new MainFunction();
			//	ExtractTemporalFact ex = new ExtractTemporalFact();
				//ArrayList<String> cp = ex.tFactsList("file/fileR.txt", mf.geTemporalFact());
	    	 
	    	 BufferedReader r2;
			try {
				r2 = new BufferedReader(
							new FileReader(new File("file/FilePairsResult.txt")));
				Set<String >lines =new HashSet<String>();
			
				int i = 0;
				while (r2.readLine() != null) {
					lines.add(r2.readLine());
					i++;
				
				}
				for(String s:lines)
					System.out.println(" "+s);
			}
			catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	    	 
	    	 
	    	 
	   }
	}
	
	
	

