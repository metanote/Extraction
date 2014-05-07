package mining;

import FrontEnd.*;

import java.io.*;
import java.util.*;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

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
		temporalPossibilities.add("date");

		return temporalPossibilities;

	}

	public ArrayList<String> readFileTemporalFacts(String file,
			ArrayList<String> tp) {

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
								String ch2 = ch.replace("\"", "");
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

	public ArrayList<String> readFileFacts(ArrayList<String> temporalFacts,
			ArrayList<String> tp) {
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

	public ArrayList<String> removeDuplicates(ArrayList<String> list) {
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

	public ArrayList<StringPair> removeDuplicatesPairs(
			ArrayList<StringPair> list) {
		ArrayList<StringPair> temp = new ArrayList<StringPair>();
		int i = 0;
		while (i < list.size() - 2) {
			while ((list.get(i).getRightString().equalsIgnoreCase(list.get(
					i + 1).getRightString()))
					&& (i < list.size() - 1))
				i++;
			temp.add(list.get(i));
			i++;
		}

		return temp;
	}

	public ArrayList<String> tFactsList(String file,
			ArrayList<String> temporalFacts) {
		ArrayList<String> facts = new ArrayList<String>();
		ArrayList<String> filtredFacts = new ArrayList<String>();
		try {
			InputStream flux = new FileInputStream(file);
			InputStreamReader lecture = new InputStreamReader(flux);
			@SuppressWarnings("resource")
			BufferedReader buff = new BufferedReader(lecture);
			String line;

			while ((line = buff.readLine()) != null) {
				for (String tf : temporalFacts)
					if (line.toLowerCase().endsWith(tf))
						facts.add(line);
			}
		} catch (Exception Ex) {
			System.out.println("Exception is detected " + Ex.getMessage());
		}
		filtredFacts = filterFacts(facts);
		return filtredFacts;
	}

	private ArrayList<String> filterFacts(ArrayList<String> facts2) {
		ArrayList<String> flist = new ArrayList<String>();
		for (String f : facts2)
			if (f.contains("/")) {
				String str = f.substring(f.indexOf("/") + 1, f.length());
				flist.add(str);
			} else
				flist.add(f);
		return flist;
	}

	public ArrayList<String> findMotifs(ArrayList<String> cp,
			ArrayList<String> tp) {

		ArrayList<String> motifList = new ArrayList<String>();
		try {

			for (String s : cp)
				for (String t : tp) {
					String motif = "";
					if (s.toLowerCase().endsWith(t)) {
						// System.out.println(s);
						int index = s.toLowerCase().indexOf(t);
						if (index > 0) {

							motif = s.substring(0, index);
							// System.out.println("Motif "+motif);
							motifList.add(motif);
							// buff.close();

						}
					}
				}
		} catch (Exception Ex) {
			System.out.println("Exception is detected " + Ex.getMessage());
		}

		return motifList;
	}

	public ArrayList<ArrayList<StringPair>> findPairs(ArrayList<String> motifs,
			String file) {
		ArrayList<StringPair> pList = new ArrayList<StringPair>();
		ArrayList<ArrayList<StringPair>> pList2 = new ArrayList<ArrayList<StringPair>>();
		for (String motif : motifs) {
			pList = parseFile(motif, file);
			if (pList.size() >= 2)
				pList2.add(pList);
		}
		return pList2;
	}

	private ArrayList<StringPair> parseFile(String motif, String file) {
		InputStream flux = null;
		ArrayList<StringPair> pList = new ArrayList<StringPair>();
		try {
			flux = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		InputStreamReader lecture = new InputStreamReader(flux);
		@SuppressWarnings("resource")
		BufferedReader buff = new BufferedReader(lecture);
		String line;
		StringPair p = null;
		try {
			while ((line = buff.readLine()) != null) {
				if (line.toLowerCase().contains(motif.toLowerCase())) {
					p = new StringPair(motif, line);
					pList.add(p);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pList;
	}

	public ArrayList<StringPair> findListPairs(
			ArrayList<ArrayList<StringPair>> lp, ArrayList<String> tp) {
		ArrayList<StringPair> fl = new ArrayList<StringPair>();

		for (ArrayList<StringPair> pp : lp)
			for (StringPair sp : pp) {
				for (String t : tp) {

					if (!sp.getRightString()
							.toLowerCase()
							.equalsIgnoreCase(
									sp.getLeftString().toLowerCase() + t))

					{
						StringPair s = new StringPair(sp.getLeftString()
								.toLowerCase() + t, sp.getRightString());
						fl.add(s);
					}

				}
			}

		return fl;
	}

	public String returnPair(int lineNumber, String string) {
		String result = "";
		try {

			@SuppressWarnings("resource")
			BufferedReader r2 = new BufferedReader(new FileReader(new File(
					string)));

			int i = 0;
			while (r2.readLine() != null) {
				i++;
				if (i == lineNumber) {
					result = r2.readLine();
					System.out.println(i + " Resultat " + result);
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public String saveQuads(String fileName, String sQuery, String tempProp,
			String relatedProp) {

		Writer writer = null;
		try {

			Query query = QueryFactory.create(sQuery);
			QueryExecution qexec = QueryExecutionFactory.sparqlService(
					"http://dbpedia.org/sparql", query);

			ResultSet results = qexec.execSelect();
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("file/" + fileName), "utf-8"));
			while (results.hasNext()) {
				QuerySolution qs = results.nextSolution();
				if (qs.getResource("x") != null) {

					writer.write(qs.getResource("x").toString() + " -- "
							+ relatedProp + " -- "
							+ qs.getResource("y").toString() + " : "
							+ qs.getLiteral("z").toString().substring(0, 10)
							+ "\n");

				}
			}

			writer.close();
		} catch (Exception ex) {
			System.out.println("Here Exception " + ex.getMessage());
		}
		
		return fileName+" Is saved";

	}

}
