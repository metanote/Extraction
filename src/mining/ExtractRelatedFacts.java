package mining;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ExtractRelatedFacts {
	ArrayList<String> relatedFacts = new ArrayList<String>();
	HashMap<Integer, ArrayList<String>> mp = new HashMap<Integer, ArrayList<String>>();

	public ArrayList<String> readFileRelatedFacts(
			ArrayList<String> temporalFact, String file) {

		try {
			InputStream flux = new FileInputStream(file);
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String line;
			int i = 0;

			while ((line = buff.readLine()) != null) {
				String[] splitLine = line.split(",");

				for (String x : splitLine) {
					for (String y : temporalFact)
						if (x.length() < 25)
							if (x.toLowerCase().matches(
									"(.*)" + y.toLowerCase() + "(.*)")) {
								relatedFacts.add(x);

								boolean bon = true;
								for (String e : relatedFacts)
									if (x.equalsIgnoreCase(e))
										bon = false;
								if (bon)
									relatedFacts.add(x);

							}
				}
				if (relatedFacts.size() > 0) {
					mp.put(i, relatedFacts);
					i++;
				}

			}
			buff.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		return relatedFacts;

	}

	public ArrayList<String> relatedFactsWithoutTemp(
			ArrayList<String> listRelaFacts, ArrayList<String> listTempFacts) {
		ArrayList<String> temp = new ArrayList<String>();

		for (String s : listRelaFacts) {
			if (!listTempFacts.contains(s)) {
				temp.add(s);
			}
		}
		listRelaFacts.clear();
		listRelaFacts.addAll(temp);

		// TODO Auto-generated method stub
		return listRelaFacts;
	}

}