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
		dist.put("Berlin", 660);
		dist.put("Chemnitz", 500);
		wg.addNode("Aachen", dist);
		
		// add B
		dist = new HashMap<String, Integer>();
		dist.put("Aachen", 660);
		dist.put("Chemnitz", 150);
		dist.put("Dresden", 250);
		wg.addNode("Berlin", dist);
		
		// add C
		dist = new HashMap<String, Integer>();
		dist.put("Aachen", 500);
		dist.put("Berlin", 150);
		dist.put("Dresden", 100);
		wg.addNode("Chemnitz", dist);
		
		// add D
		dist = new HashMap<String, Integer>();
		dist.put("Berlin", 250);
		dist.put("Chemnitz", 100);
		wg.addNode("Dresden", dist);
		
		wg.printIndexMap();
		
		gui.setNodeNames(new String[]{"Aachen","Berlin","Chemnitz","Dresden"});
		
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
