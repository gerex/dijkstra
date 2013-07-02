package blub;

import java.util.HashMap;
import java.util.HashSet;

import graph.WeightedGraph;

public class FooBar {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
//		test();
		test2();
	}
	
	private static void test()
	{
		WeightedGraph wg = new WeightedGraph(3);

		// add A
		HashMap<String, Integer> dist = new HashMap<String, Integer>();
		dist.put("B", 10);
		dist.put("C", 500);
		wg.addNode("A", dist);
		
		// add B
		dist = new HashMap<String, Integer>();
		dist.put("A", 10);
		dist.put("C", 30);
		dist.put("D", 100);
		wg.addNode("B", dist);
		
		// add C
		dist = new HashMap<String, Integer>();
		dist.put("A", 500);
		dist.put("B", 30);
		dist.put("D", 20);
		wg.addNode("C", dist);
		
		// add D
		dist = new HashMap<String, Integer>();
		dist.put("B", 100);
		dist.put("C", 20);
		wg.addNode("D", dist);
		
		int[][] edges = wg.getEdgeTable();
		
		for(int[] row : edges)
		{
			for(int i : row)
				System.out.print("\t" + i);
			System.out.println();
		}
	}

	private static void test2()
	{
		HashSet<Integer> set = new HashSet<Integer>();
		
		for(int i = 0; i < 10; i++)
			set.add(i);
		
		set.remove(5);
		for(int i : set)
			System.out.print("\t"+i);
		System.out.println();
		set.remove(5);
		for(int i : set)
			System.out.print("\t"+i);
	}
}
