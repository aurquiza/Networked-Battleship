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
	private JPanel shipsDisplay;
	
	private Menus menus;
	
	UserOceanGrid player1;
	EnemyOceanGrid player2;
	Ships ships;
	
	public Gui()
	{
		super("CS 342 Project 4 (Networked-Battleship)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create master panel w/ borderlayout for nicer positioning
		masterPanel = new JPanel(new BorderLayout());
		userShipsInterface = new JPanel(new GridBagLayout());
		enemyShipsInterface = new JPanel(new GridBagLayout());
		shipsDisplay = new JPanel(new GridBagLayout());
		
		player1 = new UserOceanGrid();
		player2 = new EnemyOceanGrid();
		organizeUserGrid();
		organizeEnemyGrid();
		
		ships = new Ships();
		organizeShipsGrid();
		
		
		// add master panel to the content pane to show gui components
		masterContainer = getContentPane();
		masterContainer.add(masterPanel);
		
		// creates the menu bar and adds it to the content pane
		masterBar = new JMenuBar();
		setJMenuBar(masterBar);
		
		// create menus and add them to menu bar
		menus = new Menus(this);
		masterBar.add(menus.getFileMenu());
		masterBar.add(menus.getHelpMenu());
		masterBar.add(menus.getConnectMenu());
		
		masterPanel.add(userShipsInterface, BorderLayout.WEST);
		masterPanel.add(enemyShipsInterface, BorderLayout.EAST);
		masterPanel.add(shipsDisplay, BorderLayout.CENTER);
		
		
		setSize(1930, 850);
		setVisible(true);
	}
	
	public void organizeUserGrid()
	{
		GridBagConstraints c = new GridBagConstraints();
		
		
		c.gridx = 1;
		c.gridy = 0;
		userShipsInterface.add(player1.getLetterPanel(),c);
		c.gridx = 1;
		c.gridy = 1;
		userShipsInterface.add(player1.getOceanPanel(),c);
		c.gridx = 0;
		c.gridy = 1;
		userShipsInterface.add(player1.getNumberPanel(),c);
	}
	
	public void organizeEnemyGrid()
	{
		GridBagConstraints b = new GridBagConstraints();
		
		b.gridx = 1;
		b.gridy = 0;
		enemyShipsInterface.add(player2.getLetterPanel(),b);
		b.ipadx = 0;
		b.gridx = 1;
		b.gridy = 1;
		enemyShipsInterface.add(player2.getOceanPanel(),b);
		b.gridx = 0;
		b.gridy = 1;
		enemyShipsInterface.add(player2.getNumberPanel(),b);
	}
	
	public void organizeShipsGrid() {
		GridBagConstraints a = new GridBagConstraints();
		
		a.gridx = 0;
		a.gridy = 0;
		shipsDisplay.add(ships.getCarrierPanel(),a);
		a.gridx = 1;
		a.gridy = 0;
		shipsDisplay.add(ships.getBattleshipPanel(),a);
		a.gridx = 2;
		a.gridy = 0;
		shipsDisplay.add(ships.getDestroyerPanel(),a);
		a.gridx = 3;
		a.gridy = 0;
		shipsDisplay.add(ships.getSubmarinePanel(),a);
		a.gridx = 4;
		a.gridy = 0;
		shipsDisplay.add(ships.getPatrolPanel(),a);
		
	}


}
