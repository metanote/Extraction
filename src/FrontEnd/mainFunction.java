package FrontEnd;

import java.util.ArrayList;
//import java.util.Scanner;

import mining.*;

public class mainFunction {

	public static void main(String args[]) {
		String file = "file/sample2.csv";
		ExtractTemporalFact ex = new ExtractTemporalFact();

		ArrayList<String> listTempFacts = ex.readFileTemporalFacts(file);
		System.out.println(" liste des faits temporels : ");
		PrintFacts p = new PrintFacts();
		p.printFactList(listTempFacts);
		ArrayList<String> listFacts = ex.readFileFacts(listTempFacts);
		System.out.println(" liste des faits : ");
		p.printFactList(listFacts);
	//	ExtractRelatedFacts re = new ExtractRelatedFacts();
	//	ArrayList<String> listRelaFacts = re.readFileRelatedFacts(listFacts,
		//		file);
		// System.out.println(" liste des faits reliés : ");
		// p.printFactList(listRelaFacts);
		System.out.println("Choix multiple de l'expert (1,2,3...) ");
	//	Scanner sc = new Scanner(System.in);
//		String choix = sc.nextLine();

	}
}
