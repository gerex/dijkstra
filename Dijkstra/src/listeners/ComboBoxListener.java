package listeners;

import gui.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxListener implements ActionListener {

	private GUI gui;
	
	public ComboBoxListener(GUI g)
	{
		gui = g;
	}
	
//	@Override
	public void itemStateChanged(ItemEvent arg0)
	{	
		System.out.println("BLUB");
		gui.setRouteText(gui.getFrom() + " nach " + gui.getTo());
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("BLUB2)");
		gui.setRouteText(gui.getFrom() + " nach " + gui.getTo());
	}
}
