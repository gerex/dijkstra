package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * representation of a weighted graph
 * @author Oliver
 *
 */
public class WeightedGraph {
	
	// table of edge distances
	private int[][] edges;
	
	// maximum number of nodes in graph
	private int maxElements;
	
	// index of next node to be added
	private static int currentElement;
	
	private HashMap<String, Integer> indexMap;
	private HashMap<Integer, String> nameMap;
	
	/**
	 * weighted undirected graph, weights must be positive
	 * @param initSize
	 */
	public WeightedGraph(int initSize)
	{
		indexMap = new HashMap<String, Integer>();
		
		maxElements = initSize;
		currentElement = 0;
		
		edges = new int[initSize][initSize];
		
		// initialize all connections to not exist (negative distance)
		for(int i = 0; i < initSize; i++)
			for(int j = 0; j < initSize; j++)
				if(i!=j)edges[i][j] = -1;
	}
	
	/**
	 * adds a new node to the graph
	 * the hashmap stores the names of all nodes connected to the new node and the distance from the new node to the connected nodes
	 * @param nodeName name of the node
	 * @param distances list of distances to other nodes
	 */
	public void addNode(String nodeName, HashMap<String, Integer> distances)
	{
		// check if there is still place in the edge table
		// if not, double the size of the edge table and add the new node
		if(currentElement == maxElements)
		{
			maxElements *= 2;
			int[][] temp = new int[maxElements][maxElements];
			
			// copy contents of edge table to top left quadrant of temporary table, preserving existing node->index mapping
			for(int i = 0; i < maxElements/2; i++)
				for(int j = 0; j < maxElements/2; j++)
					if(i!=j)temp[i][j] = edges[i][j];
			// fill remainder of temp table with -1, indicating no connection
			for(int i = maxElements/2; i < maxElements; i++) // top right and bottom right quadrant of table
				for(int j = 0; j < maxElements; j++)
					if(i!=j)temp[i][j] = -1;
			for(int i = 0; i < maxElements/2; i++) // bottom left quadrant of table
				for(int j = maxElements/2; j < maxElements; j++)
					if(i!=j)temp[i][j] = -1;
			
			// copy temp table to edge table, discarding old table
			edges = temp;
		}
		if(!indexMap.containsKey(nodeName)) // only add new node if the no node with this name exists yet
		{
			indexMap.put(nodeName, currentElement); // assign index to node by adding to node->index hashmap
			currentElement++;					
		}
		
		for(String s : distances.keySet()) // for all directly connected targets, check if they already exist, if they do not, add them to the graph without any connection yet
		{
			if(!indexMap.containsKey(s))
			{
				addNode(s, new HashMap<String, Integer>());
			}
		}
		
		// now all target nodes exist in edge table, add distances
		for(String s : distances.keySet()) // set distances to all connected nodes
		{
			edges[indexMap.get(nodeName)][indexMap.get(s)] = distances.get(s);
			edges[indexMap.get(s)][indexMap.get(nodeName)] = distances.get(s);
		}
		
		// create nameMap
		nameMap = new HashMap<Integer, String>();
		for(String s : indexMap.keySet())
			nameMap.put(indexMap.get(s), s);
	}
	
	/**
	 * returns the direct distance between to nodes
	 * if no direct connection exists or one or both of the nodes does not exist in the graph, returns -1 (infinite distance)
	 * @param from
	 * @param to
	 * @return
	 */
	public int getDirectDistance(String from, String to)
	{		
		if(indexMap.containsKey(from) && indexMap.containsKey(to))
			return edges[indexMap.get(from)][indexMap.get(to)];
		else
			return -1; // if from node or to node does not exist, return infinite distance
	}
	
	public int getMaxSize()
	{
		return maxElements;
	}
	
	public int getCurrentSize()
	{
		return currentElement;
	}
	
	public int[][] getEdgeTable()
	{	
		return edges;
	}
	
	public Route getShortestDistance(String from, String to)
	{
		int startNode = indexMap.get(from);
		int endNode = indexMap.get(to);
		
		int closest; // node currently closest to start node
		int[] distance = new int[currentElement]; // distance of nodes to start node
		int[] previous = new int[currentElement]; // predecessor of node on path 
		HashSet<Integer> available = new HashSet<Integer>(); // nodes still available for being selected as closest
		HashSet<Integer> neighbours = new HashSet<Integer>();
		// initialize
		for(int i = 0; i < currentElement; i++)
		{
			distance[i] = Integer.MAX_VALUE; // indicates infinite distance
			previous[i] = -1; // indicates no predecessor
			available.add(i); // initially, all nodes are available
		}
		closest = startNode; // start at the start node
		distance[startNode] = 0;
		
		while(closest != endNode)
		{
			// determine node closest to start node, i.e. minimal distance
			int minDist = Integer.MAX_VALUE;
			for(int i : available)
				if(distance[i] < minDist)
				{
					closest = i;
					minDist = distance[i];
				}
			// remove closest node from set of available nodes
			available.remove(closest);
			
			// get all neighbours of closest node
			neighbours.clear();
			for(int i = 0; i < currentElement; i++)
				if(edges[closest][i] > 0)
					neighbours.add(i);
			
			for(int i : neighbours) // check for each neighbour
				if(distance[closest] + edges[closest][i] < distance[i]) // if the path through closest is shorter than the neighbours current distance, set its path to go through closest
				{
					distance[i] = distance[closest] + edges[closest][i];
					previous[i] = closest;
				}
		}
		
		// print indices of shortest path, path will be backwards
		ArrayList<String> route = new ArrayList<String>();
		int totalDistance = 0;
		int node = endNode;
		while(node != -1)
		{
			route.add(0, nameMap.get(node));
			
			if(previous[node] != -1)
				totalDistance += edges[node][previous[node]];
			
			node = previous[node];
		}
		
		return new Route(route, totalDistance);
	}
	
	public void printIndexMap()
	{
		System.out.println(indexMap);
	}
}
