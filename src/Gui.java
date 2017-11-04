/*
 * CS 342 Programming Project 4: Networked-Battleship
 * 
 * Names: Alexis Urquiza, Robert Barrera, Muhammad Arsalan Chaudry
 * NetID: aurqui7, rbarre4, mchaud25
 * 
 * - This program is a gui simulation of the game Battleship, where two players can go against each other and attempt
 *   to sink the other player's fleet. The first player to sink the other player's fleet wins.
 * 
 * - This class is the heart of the program where main is located as well as a custom JFrame class that helps put all of 
 *   the components of the gui together, such as the menus, jpanels, sockets, and buttons.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Gui extends JFrame
{
	// master containers that will help keep better organization of the gui
	private Container masterContainer;
	private JMenuBar masterBar;
	private JPanel masterPanel;
	
	//panels where components to play game will be added
	private JPanel userShipsInterface;
	private JPanel enemyShipsInterface;
	private JPanel statusBar;
	
	private Menus menus;
	
	public Gui()
	{
		super("CS 342 Project 4 (Networked-Battleship)");
		
		// create master panel w/ borderlayout for nicer positioning
		masterPanel = new JPanel(new BorderLayout());
		
		// add master panel to the content pane to show gui components
		masterContainer = getContentPane();
		masterContainer.add(masterPanel);
		
		// creates the menu bar and adds it to the content pane
		masterBar = new JMenuBar();
		setJMenuBar(masterBar);
		
		// create menus and add them to menu bar
		menus = new Menus();
		masterBar.add(menus.getFileMenu());
		masterBar.add(menus.getHelpMenu());
		masterBar.add(menus.getConnectMenu());
		
		
		setSize(300,300);
		setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("Testing");
		Gui app = new Gui();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
