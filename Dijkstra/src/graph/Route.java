package graph;

import java.util.ArrayList;

public class Route {
	
	private ArrayList<String> path;
	private int distance;
	
	public Route(ArrayList<String> p, int d)
	{
		path = p;
		distance = d;
	}
	
	public ArrayList<String> getPath()
	{
		return path;
	}
	
	public int getDistance()
	{
		return distance;
	}
}
