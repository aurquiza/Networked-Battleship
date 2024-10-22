/*
 * CS 342 Programming Project 4: Networked-Battleship
 * 
 * Names: Alexis Urquiza, Robert Barrera, Muhammad Arsalan Chaudry
 * NetID: aurqui7, rbarre4, mchaud25
 * 
 * - This program is a gui simulation of the game Battleship, where two players can go against each other and attempt
 *   to sink the other player's fleet. The first player to sink the other player's fleet wins.
 * 
 * - This is the Menus class that will create the server and client as well as other menus and menu items
     it also manages which program will be the server or the client
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


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
	
	//HTML Files
	private final File ABOUTFILE = new File("src/aboutAuthors.html"); 		//File: about file
	private final File HOWTOPLAY = new File("src/howToPlay.html"); 			//Help: how to play file
	private final File HOWTOCONNECT = new File("src/howToConnect.html"); 	//Help: how to connect file
	
	// constructor
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

	// method that will send a 'complete' status to the server or client
	// meaning that the actual game can begin after placing the ships
	public void sendCompleteStatus()
	{
		try
		{
			// send validation to server or client 
			Coordinates doneButton = new Coordinates("done", -1 , -1);
			if(isServer)
				server.sendData(doneButton);
			else
				client.sendData(doneButton);
		}
		catch(IOException e)
		{
			System.err.println("Attempt to send object failed!!");
		}
	}
	
	// method that adds file menu items to the file menu
	private void addFileMenuItems()
	{
		// about message
        aboutItem = new JMenuItem("About");
        fetch(aboutItem, ABOUTFILE);
        fileMenu.add(aboutItem);
		
		statsItem = new JMenuItem("Game Statistics");
		fileMenu.add(statsItem);
		
        //Exit option
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
        
		fileMenu.add(exitItem);
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
					// disconnect button is enabled 
					disconnect.setEnabled(true);
					if(isServer)
					{
						/* create server */
						server = new Server(gui);
						EnemyOceanGrid e = gui.getEnemyGrid();
						e.setServer(server);

						if(!server.serverCreated())
							disconnect.setEnabled(false);
						else
						{
							serverOrClientItem.setEnabled(false);
							gui.changeStatus("Status: Server is waiting for client");
						}
					}
					else 
					{
						/* look for a connection */
						client = new Client(gui);
						EnemyOceanGrid e = gui.getEnemyGrid();
						e.setClient(client);

						if(!client.isClientConnected())
							disconnect.setEnabled(false);
						else
						{
							serverOrClientItem.setEnabled(false);
							gui.enableOceanButtons();
						}
					}
				}
			}
		);
		
		//  create button action listener for disconnect
		disconnect = new JMenuItem("disconnect from server");
		disconnect.setEnabled(false);
		connectMenu.add(disconnect);
		disconnect.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					// disable disconnect button
					disconnect.setEnabled(false);
					serverOrClientItem.setEnabled(true);
					gui.disableOceanButtons();
					gui.changeStatus("Status: Connect to server or become server");
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
		fetch(helpRulesItem, HOWTOPLAY);
		helpMenu.add(helpRulesItem);
		
		helpConnectItem = new JMenuItem("Help: How to connect");
		fetch(helpConnectItem, HOWTOCONNECT);
		helpMenu.add(helpConnectItem);
	}
	
	// returns file menu
	public JMenu getFileMenu()
	{
		return fileMenu;
	}
	
	// returns connect menu
	public JMenu getConnectMenu()
	{
		return connectMenu;
	}
	
	// returns help menu
	public JMenu getHelpMenu()
	{
		return helpMenu;
	}
	
	
    /**
     * function to get the content of the file and display in dialog box
     */
    private void fetch(JMenuItem menuItem, File source) {
        try {
            String content;
            if(source.isFile()){
                content = new Scanner(source).useDelimiter("\\Z").next();
            }else{
                content = "on " + menuItem.getText() + " failed to fine source file \"" + source.getName() + "\". Please check the path.";
            }
            JLabel message = new JLabel(content);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JMenuItem self = (JMenuItem)e.getSource();
                    JOptionPane.showMessageDialog(Menus.this, message, self.getText(), JOptionPane.INFORMATION_MESSAGE);
                }
            });

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
}
