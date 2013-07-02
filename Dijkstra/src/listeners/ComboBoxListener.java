package listeners;

import graph.Route;
import graph.WeightedGraph;
import gui.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ComboBoxListener implements ActionListener {

	private GUI gui;
	
	private WeightedGraph wg;
	
	public static void main(String[] args)
	{
		ComboBoxListener cbl = new ComboBoxListener();
	}
	
	public ComboBoxListener()
	{
		gui = new GUI(this);
		wg = new WeightedGraph(3);

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
		
		wg.printIndexMap();
		
		gui.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Route route = wg.getShortestDistance(gui.getFrom(), gui.getTo());
		ArrayList<String> path = route.getPath();
		StringBuilder sb = new StringBuilder();
		for(String s : path)
			sb.append(s+"\n");
		gui.setRouteText(sb.toString());
		gui.setTotalDistance(route.getDistance());
	}
}
