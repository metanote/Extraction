package mining;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.*;

public class ExtractTemporalFact {
	// lire les données à partir d'un fichier
	// lire ligne par ligne et extraire des attribut temporels
	ArrayList<String> temporalFacts = new ArrayList<String>();
	ArrayList<String> facts = new ArrayList<String>();

	HashMap<Integer, ArrayList<String>> mp = new HashMap<Integer, ArrayList<String>>();

	public ArrayList<String> putTemporalPoss() {
		ArrayList<String> temporalPossibilities = new ArrayList<String>();

		temporalPossibilities.add("year");
		temporalPossibilities.add("day");
		temporalPossibilities.add("month");
		temporalPossibilities.add("time");
		return temporalPossibilities;

	}

	public ArrayList<String> readFileTemporalFacts(String file) {
		ArrayList<String> tp = putTemporalPoss();
		try {
			InputStream flux = new FileInputStream(file);
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String line;
			int i = 0;

			while ((line = buff.readLine()) != null) {
				String[] splitLine = line.split(",");

				for (String x : splitLine) {
					for (String z : tp)

						if (x.toLowerCase().contains(z)) {

							facts.add(x.replace(z, ""));
							boolean bon = true;
							for (String e : temporalFacts)
								if (x.equalsIgnoreCase(e))
									bon = false;
							if (bon)
								temporalFacts.add(x);

						}
				}
				if (temporalFacts.size() > 0) {
					mp.put(i, temporalFacts);
					i++;
				}

			}
			buff.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return temporalFacts;

	}

	public ArrayList<String> readFileFacts(ArrayList<String> temporalFacts) {
		ArrayList<String> tp = putTemporalPoss();
		ArrayList<String> newListFact;
		for (String x : temporalFacts) {
			for (String z : tp)

				if (x.toLowerCase().contains(z)) {

					boolean bon = true;
					for (String e : facts)
						if (x.equalsIgnoreCase(e))
							bon = false;
					if (bon) {
						String f = x.replace(z, "");
						if (f.length() != 0)
							facts.add(f);

					}
				}
		}

//		for (Iterator<String> it=facts.iterator(); it.hasNext();) {
//		    if (it.next().contains("make"))
//		        it.remove();
//		}
		System.out.println("Size " + facts.size());

		return facts;

	}
}
