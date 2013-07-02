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
		 * aachen - k�ln
		 * aachen - bonn
		 * 
		 * k�ln - aachen
		 * k�ln - bonn
		 * 
		 * bonn - aachen
		 * bonn - k�ln
		 */
		
		// add aachen
		HashMap<String, Integer> dist = new HashMap<String, Integer>();
		dist.put("K�ln", 10);
		dist.put("Bonn", 500);
		wg.addNode("Aachen", dist);
		
		// add k�ln
		dist = new HashMap<String, Integer>();
		dist.put("Aachen", 10);
		dist.put("Bonn", 30);
		wg.addNode("K�ln", dist);
		
		// add bonn
		dist = new HashMap<String, Integer>();
		dist.put("Aachen", 500);
		dist.put("K�ln", 30);
		wg.addNode("Bonn", dist);
		
		
		System.out.println("Aachen - Bonn: " + wg.getDirectDistance("Aachen", "Bonn"));
		System.out.println("Bonn - K�ln: " + wg.getDirectDistance("Bonn", "K�ln"));
		System.out.println("K�ln - Aachen: " + wg.getDirectDistance("K�ln", "Aachen"));
		
		int[][] edges=wg.getEdgeTable();
		for(int i = 0; i < edges.length; i++)
		{
			for(int j = 0; j < edges[0].length; j++)
				System.out.print("\t" + edges[i][j]);
			System.out.println();
		}
		
	}

}
