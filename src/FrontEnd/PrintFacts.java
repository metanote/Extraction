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

	// duplication

	public ArrayList<String> duplicationDetector(ArrayList<String> list) {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			boolean show = true;
			for (int j = 0; j < i; j++)
				if (list.get(j).equalsIgnoreCase(list.get(i))) {
					show = false;
					break;
				}
			if (show&&!list.get(i).equalsIgnoreCase("")) {
				temp.add(list.get(i));
		
			}
		}
		return temp;
	}

	public String filterTemp(String fact,
			ArrayList<String> tp) {
		String f="";
		for (String x : tp) 
				if (fact.toLowerCase().contains(x)){
					f = fact.toLowerCase().replaceAll(x.toLowerCase(), "");
					
				}
		return f;
	}

	public ArrayList<String> selectAllAtribut(ArrayList<String> lf, ArrayList<String> tp) {
		// TODO Auto-generated method stub
		ArrayList<String> rst=new ArrayList<String>();
		for(String f: lf)
			rst.add(filterTemp(f,tp));	
			
		return rst;
	}

}
