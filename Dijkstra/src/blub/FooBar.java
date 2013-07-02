package blub;

import java.util.HashMap;

import graph.WeightedGraph;

public class FooBar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WeightedGraph wg = new WeightedGraph(3);
		
		/*
		 * aachen - kšln
		 * aachen - bonn
		 * 
		 * kšln - aachen
		 * kšln - bonn
		 * 
		 * bonn - aachen
		 * bonn - kšln
		 */
		
		// add aachen
		HashMap<String, Integer> dist = new HashMap<String, Integer>();
		dist.put("Kšln", 10);
		dist.put("Bonn", 500);
		wg.addNode("Aachen", dist);
		
		// add kšln
		dist = new HashMap<String, Integer>();
		dist.put("Aachen", 10);
		dist.put("Bonn", 30);
		wg.addNode("Kšln", dist);
		
		// add bonn
		dist = new HashMap<String, Integer>();
		dist.put("Aachen", 500);
		dist.put("Kšln", 30);
		wg.addNode("Bonn", dist);
		
		
		System.out.println("Aachen - Bonn: " + wg.getDirectDistance("Aachen", "Bonn"));
		System.out.println("Bonn - Kšln: " + wg.getDirectDistance("Bonn", "Kšln"));
		System.out.println("Kšln - Aachen: " + wg.getDirectDistance("Kšln", "Aachen"));
		
		int[][] edges=wg.getEdgeTable();
		for(int i = 0; i < edges.length; i++)
		{
			for(int j = 0; j < edges[0].length; j++)
				System.out.print("\t" + edges[i][j]);
			System.out.println();
		}
		
	}

}
