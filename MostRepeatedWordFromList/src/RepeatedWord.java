import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class RepeatedWord {

	public static void main(String[] args){		
		ArrayList<String> al= new ArrayList<String>();
		al.add("AAA");
	

		Map<String, Integer> map = new HashMap<String, Integer>();

		for (String temp : al) {
			Integer count = map.get(temp);
			map.put(temp, (count == null) ? 1 : count + 1);
		}

		System.out.println(Arrays.toString(entriesSortedByValues(map)));
		
	    }

	  public static String[] entriesSortedByValues(Map<String,Integer> map) {
		  
		  List<Entry<String,Integer>> sortedEntries = new ArrayList<Entry<String,Integer>>(map.entrySet());
			  		
			  		Collections.sort(sortedEntries, new Comparator<Entry<String,Integer>>() {
			  				public int compare(Entry<String,Integer> e1, Entry<String,Integer> e2) {
			  				return e2.getValue().compareTo(e1.getValue());
			  				}
			  			}
			  		);
	
			  		int loopCount = sortedEntries.size() > 5 ? 5 : sortedEntries.size() ;
					 String[] s = new String[loopCount];
			  		for(int i = 0; i< loopCount; i++){
			  			Entry<String,Integer> entry = sortedEntries.get(i);
			  			s[i] = entry.getKey();
			  		}
			  		
			  	return s;
			  	
			  	
		  	}
	
}
