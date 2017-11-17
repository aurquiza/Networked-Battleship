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
import javax.swing.ImageIcon;

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
	private JPanel availableShips;
	
	private Menus menus;
	
	// instances related to the ocean grids where player will shoot and place boats
	private UserOceanGrid player1;
	private EnemyOceanGrid player2;

	// instances related to the status label
	private JLabel statusLabel;
	private boolean selfDone = false;
	private boolean enemyDone = false; 

	public Gui()
	{
		super("CS 342 Project 4 (Networked-Battleship)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create master panel w/ borderlayout for nicer positioning
		masterPanel = new JPanel(new BorderLayout());
		userShipsInterface = new JPanel(new GridBagLayout());
		enemyShipsInterface = new JPanel(new GridBagLayout());
		
		createStatusBar();

		// initialize the grids where the user will place ships and shoot at the enemy
		player1 = new UserOceanGrid(this);
		player2 = new EnemyOceanGrid(this);
		organizeUserGrid();
		organizeEnemyGrid();
		
		createShipsPanel();
		
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
		masterPanel.add(statusBar, BorderLayout.SOUTH);
		masterPanel.add(availableShips, BorderLayout.CENTER);
		
		
		setSize(1280,720);
		setVisible(true);
	}

	public EnemyOceanGrid getEnemyGrid()
	{
		return player2;
	}

	public void enableOceanButtons()
	{
		player1.enableButtons();
		//player2.enableButtons();
	}

	public void enableEnemyButtons()
	{
		player2.enableButtons();
	}

	public void disableOceanButtons()
	{
		player1.disableButtons();
		player2.disableButtons();
	}

	public void changeStatus(String text)
	{
		statusLabel.setText(text);
	}

	public void setSelfStatus(boolean b)
	{
		selfDone = b;
	}

	public void sendDoneStatus()
	{
		menus.sendCompleteStatus();
	}

	public void enemyDoneStatus(boolean b)
	{
		enemyDone = b;
	}

	public boolean getSelfStatus()
	{
		return selfDone;
	}

	public boolean getEnemyStatus()
	{
		return enemyDone;
	}

	public boolean checkShot(Coordinates shot)
	{
		return player1.isHit(shot);
	}

	public void updateAttackBoard(Coordinates shot, ImageIcon icon)
	{
		player2.setButtonImage(shot, icon);
	}
 
	private void organizeUserGrid()
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
	
	private void organizeEnemyGrid()
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

	private void createStatusBar()
	{
		// create status bar that will update player on state of the game
		statusBar = new JPanel(new BorderLayout());
		statusLabel = new JLabel("Status: Connect to server or become server");
		statusLabel.setFont(new Font("Serif", Font.PLAIN, 24));
		statusBar.add(statusLabel);
		statusLabel.setForeground(Color.blue);
		statusLabel.setBackground(Color.lightGray);
	}


	private void createShipsPanel()
	{
		availableShips = new JPanel();
		availableShips.setLayout(new BoxLayout(availableShips, BoxLayout.Y_AXIS));

		JButton arr[] = player1.getShipButtons();

		for(int i = 0; i < 7; i++)
			availableShips.add(arr[i]);
	}

}
