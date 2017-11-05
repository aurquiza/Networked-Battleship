
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
	
	// items that belong to the connect menu
	private JCheckBoxMenuItem serverOrClientItem;
	private JMenuItem beginConnectionItem;
	private JMenuItem disconnect;
	
	// jmenus that will hold multiple jmenu items
	private JMenu fileMenu;
	private JMenu connectMenu;
	private JMenu helpMenu;
	
	// instance variables that help with server/client connection
	private boolean isServer;
	private Gui gui;
	private Client client;
	private Server server;
	
	public Menus(Gui gui)
	{
		this.gui = gui;
		fileMenu = new JMenu("File");
		connectMenu = new JMenu("Connection");
		helpMenu = new JMenu("Help");
		
		addFileMenuItems();
		addHelpMenuItems();
		addConnectMenuItems();
	}
	
	
	private void addFileMenuItems()
	{
		exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		
		aboutItem = new JMenuItem("About");
		fileMenu.add(aboutItem);
		aboutItem.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JOptionPane.showMessageDialog(Menus.this,
							"Authors: \n" +
							"Alexis Urquiza (aurqui7) \n" +
							"Robert Barrera (rbarre4) \n" +
							"Muhammad Arsalan Chaudry (mchaud25)\n",
							"About",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		);
		
		statsItem = new JMenuItem("Game Statistics");
		fileMenu.add(statsItem);
	}
	
	
	// private method that adds items to the connection menu for the gui
	// this is also where the action listeners are created to have the items
	// perform thier desired action, which in this case is connection to server
	// or creating a server.
	private void addConnectMenuItems()
	{
		// add check box that determines if the process will become a server or a client
		serverOrClientItem = new JCheckBoxMenuItem("Become Server/Client");
		connectMenu.add(serverOrClientItem);
		serverOrClientItem.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					// sets boolean that determines if process is client/server
					isServer = serverOrClientItem.getState();
				}
			}
		);
		
		// add jmenu item that will either create a server or attempt to connect to a server
		// based on the boolean flag of the check box.
		beginConnectionItem = new JMenuItem("Connect/Look For Connection");
		connectMenu.add(beginConnectionItem);
		beginConnectionItem.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(isServer)
					{
						/* create server */
						server = new Server(gui);
					}
					else 
					{
						/* look for a connection */
						client = new Client(gui);
					}
				}
			}
		);
		
		disconnect = new JMenuItem("disconnect from server");
		connectMenu.add(disconnect);
		disconnect.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					if(!isServer)
					{
						client.closeClient();
					}
					else
					{
						server.closeServer();
					}
				}
			}
		);
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
