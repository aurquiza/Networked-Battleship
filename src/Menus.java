
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Menus extends JFrame
{
	// items that belong to the file menu
	private JMenuItem exitItem;
	private JMenuItem aboutItem;
	private JMenuItem statsItem;
	
	// items that belong to the help menu
	private JMenuItem helpRulesItem;
	private JMenuItem helpConnectItem;
	
	// jmenus that will hold multiple jmenu items
	private JMenu fileMenu;
	private JMenu connectMenu;
	private JMenu helpMenu;
	
	public Menus()
	{
		fileMenu = new JMenu("File");
		connectMenu = new JMenu("Connection");
		helpMenu = new JMenu("Help");
		
		addFileMenuItems();
		addHelpMenuItems();
	}
	
	
	private void addFileMenuItems()
	{
		exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		
		aboutItem = new JMenuItem("About");
		fileMenu.add(aboutItem);
		
		statsItem = new JMenuItem("Game Statistics");
		fileMenu.add(statsItem);
	}
	
	
	private void addHelpMenuItems()
	{
		helpRulesItem = new JMenuItem("Help: How to play");
		helpMenu.add(helpRulesItem);
		
		helpConnectItem = new JMenuItem("Help: How to connect");
		helpMenu.add(helpConnectItem);
	}
	
	
	public JMenu getFileMenu()
	{
		return fileMenu;
	}
	
	public JMenu getConnectMenu()
	{
		return connectMenu;
	}
	
	public JMenu getHelpMenu()
	{
		return helpMenu;
	}
}
