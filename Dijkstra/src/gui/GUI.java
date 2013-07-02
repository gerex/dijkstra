package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import listeners.ComboBoxListener;


public class GUI {
	
	private JFrame mainWindow;
	
	private JComboBox fromBox;
	private JComboBox toBox;
	
	private JTextArea routeArea;
	
	private JLabel totalDistLabel;
	
	private ActionListener comboBoxListener;
	
	public GUI(ComboBoxListener cbl)
	{
		comboBoxListener = cbl;
		
		mainWindow = getMainWindow();
		
		mainWindow.add(getMainPanel(), BorderLayout.CENTER);
	}
	
	public void setVisible(boolean visible)
	{
		mainWindow.setVisible(visible);
	}
	
	private JFrame getMainWindow()
	{
		JFrame f = new JFrame();
		
		f.setTitle("Routenplaner - Oppan Dijkstra Style");
		f.setSize(300, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		
		return f;
	}
	
	private JPanel getMainPanel()
	{
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,1));
		
		p.add(getSelectPanel());
		p.add(getRoutePanel());
		p.add(getDistancePanel());
		
		return p;
	}
	
	private JPanel getSelectPanel()
	{
		JPanel s = new JPanel();
		s.setLayout(new GridLayout(2,2));
		
		s.add(new JLabel("Start:"));
		fromBox = getFromBox();
		s.add(fromBox);
		s.add(new JLabel("Ziel:"));
		toBox = getToBox();
		s.add(toBox);
		
		return s;
	}
	
	private JComboBox getFromBox()
	{ // TODO aus WeightedGraph auslesen
		JComboBox cb = new JComboBox(new String[]{"A","B","C","D"});
		cb.addActionListener(comboBoxListener);
		return cb;
	}
	
	private JComboBox getToBox()
	{ // TODO aus WeightedGraph auslesen
		JComboBox cb = new JComboBox(new String[]{"A","B","C","D"});
		cb.addActionListener(comboBoxListener);
		return cb;
	}
	
	private JPanel getRoutePanel()
	{
		JPanel rp = new JPanel();
		rp.setLayout(new BorderLayout());
		routeArea = getRouteArea();
		rp.add(routeArea);
		
		return rp;
	}
	
	private JTextArea getRouteArea()
	{
		JTextArea rf = new JTextArea();
		
		return rf;
	}
	
	private JPanel getDistancePanel()
	{
		JPanel dp = new JPanel();
		dp.setLayout(new GridLayout(1,2));
		dp.add(new JLabel("Gesamtstrecke:"));
		totalDistLabel = new JLabel();
		dp.add(totalDistLabel);
		
		return dp;
	}
	
	public String getFrom()
	{
		return fromBox.getSelectedItem().toString();
	}
	
	public String getTo()
	{
		return toBox.getSelectedItem().toString();
	}
	
	public void setRouteText(String text)
	{
		routeArea.setText(text);
	}
	
	public void setTotalDistance(int dist)
	{
		totalDistLabel.setText(""+dist);
	}
	
}
