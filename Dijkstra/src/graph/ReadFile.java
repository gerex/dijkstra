package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	private int maxNodes = 4;
	private boolean read = true;
	private String zeile;
	
	public void readFile() throws IOException 
	  {
	    FileReader fr = new FileReader("Graph.txt");
	    BufferedReader br = new BufferedReader(fr);

	    while ((zeile = br.readLine()) != null){
	    	
		    if (zeile.equals("Node")){
		    	for(int i=0; i<maxNodes;i++){
		    		String row = br.readLine();
		    		System.out.println(row);
		    		
		    	}
		    } 
	    }
	    br.close();
	  }
}
