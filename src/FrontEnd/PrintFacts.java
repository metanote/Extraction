package FrontEnd;

import java.util.ArrayList;

public class PrintFacts {
	public void printFactList(ArrayList<String> factsList) {

		int p = 1;
		for (String x : factsList) {
			System.out.println(p + " : " + x);
			p++;
		}
	}
}
