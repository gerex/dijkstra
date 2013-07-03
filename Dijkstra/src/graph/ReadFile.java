package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadFile {
	private int maxNodes = 4;
	private String zeile;
	
	public WeightedGraph readFile(WeightedGraph wg) throws IOException 
	  {
	    FileReader fr = new FileReader("Graph.txt");
	    BufferedReader br = new BufferedReader(fr);
	    HashMap<String, Integer> dist = new HashMap<String, Integer>();

	    while ((zeile = br.readLine()) != null){
	    	
		    if (zeile.equals("Node")){
		    	for(int i=0; i<maxNodes;i++){
		    		String row = br.readLine();
		    		//System.out.println(row);
		    		String [] splitResult = row.split(", ");
		    		//System.out.println(splitResult[0]);
		    		//System.out.println(splitResult[1]);
		    		if (splitResult[1].equals("0")){
		    			wg.addNode(splitResult[0], dist);
		    		} else if (splitResult[1].equals("-1")){
		    			System.out.println(splitResult[1]);
		    		} else {
		    			dist.put(splitResult[0], Integer.parseInt(splitResult[1]));
		    		}
		    		
		    	}
		    } 
	    }
	    br.close();
	    return wg;
	  }
}
