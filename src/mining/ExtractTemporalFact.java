package mining;

import java.io.*;
import java.util.*;

public class ExtractTemporalFact {
	// lire les données à partir d'un fichier
	// lire ligne par ligne et extraire des attribut temporels
	ArrayList<String> temporalFacts = new ArrayList<String>();
	ArrayList<String> facts = new ArrayList<String>();

	HashMap<Integer, ArrayList<String>> mp = new HashMap<Integer, ArrayList<String>>();
	private ArrayList<String> newListFact = new ArrayList<String>();

	public ArrayList<String> putTemporalPoss() {
		ArrayList<String> temporalPossibilities = new ArrayList<String>();

		temporalPossibilities.add("year");
		temporalPossibilities.add("day");
		temporalPossibilities.add("years");
		temporalPossibilities.add("days");
		temporalPossibilities.add("month");
		temporalPossibilities.add("time");
		temporalPossibilities.add("months");
		temporalPossibilities.add("times");

		return temporalPossibilities;

	}

	public ArrayList<String> readFileTemporalFacts(String file,ArrayList<String> tp) {
		
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
						if (x.length() < 25)
							if (x.toLowerCase().contains(z)) {
								String ch = x.replace(z, "");
								String ch2= ch.replace("\"", "");
								facts.add(ch2);
								boolean bon = true;
								for (String e : temporalFacts)
									if (x.equalsIgnoreCase(e))
										bon = false;
								if (bon)
									temporalFacts.add(x.replace("\"", ""));

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

	public ArrayList<String> readFileFacts(ArrayList<String> temporalFacts, ArrayList<String> tp) {
		// = putTemporalPoss();

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
							facts.add(f.replace("\"", ""));

					}
				}
		}

		int j = 0;
		String s = facts.get(j);
		while (j < facts.size()) {
			j++;
			for (int i = j; i < facts.size(); i++) {
				if (!s.equalsIgnoreCase("") && (facts.get(i) != s))
					newListFact.add(s);

			}
			if (j < facts.size())
				s = facts.get(j);
		}

		return removeDuplicates(newListFact);

	}

	public static ArrayList<String> removeDuplicates(ArrayList<String> list) {
		ArrayList<String> temp = new ArrayList<String>();

		for (String s : list) {
			if (!temp.contains(s)) {
				temp.add(s);
			}
		}
		list.clear();
		list.addAll(temp);

		return list;
	}
}
