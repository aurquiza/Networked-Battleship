/*
 * CS 342 Programming Project 4: Networked-Battleship
 * 
 * Names: Alexis Urquiza, Robert Barrera, Muhammad Arsalan Chaudry
 * NetID: aurqui7, rbarre4, mchaud25
 * 
 * - This program is a gui simulation of the game Battleship, where two players can go against each other and attempt
 *   to sink the other player's fleet. The first player to sink the other player's fleet wins.
 * 
 * - This class is contains all the Enemys's game grid information to be used in the Battleship game.
 * 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.ImageIcon;

//User's Ocean Grid 
public class EnemyOceanGrid
{
	private Gui gui;	// Gui to be passed to this class

	private JPanel oceanPanel;	// Panel for ocean buttons
	private JPanel numberPanel;	// Panel for number buttons
	private JPanel letterPanel;	// Panel for letter buttons
	
	private Coordinates oceanButtons[][];	// Ocean buttons array
	private JButton letterButtons[];	// Letter buttons array
	private JButton numberButtons[];	// number buttons array

	private Server server;	// Server
	private Client client;	// Client
	private boolean isSever = false;	// Whether application is server or not
	private int round = 1;	// For each round, unimplemented
	
	// Constructor to set up the ocean grids, number and letter panels
	public EnemyOceanGrid(Gui gui)
	{
		this.gui = gui;
		oceanPanel = null;
		numberPanel = null;
		letterPanel = null;
		oceanButtons = null;

		server = null;
		client = null;
		
		createOceanGrid();
		createNumberPanel();
		createLetterPanel();
	}
	
	// returns ocean panel
	public JPanel getOceanPanel()
	{
		return oceanPanel;
	}
	
	// returns the numbers panel
	public JPanel getNumberPanel()
	{
		return numberPanel;
	}
	
	// returns the letters panel
	public JPanel getLetterPanel()
	{
		return letterPanel;
	}

	// set server to valid object
	public void setServer(Server s)
	{
		server = s;
		isSever = true;
	}

	// set client to valid object
	public void setClient(Client c)
	{
		client = c;
		isSever = false;
	}

	// Sets all ocean buttons to enabled
	public void enableButtons()
	{
		for(int x = 0; x < 10; x++)
			for(int y = 0; y < 10; y++)
				oceanButtons[x][y].setEnabled(true);
	}

	// Sets all ocean buttons to disabled
	public void disableButtons()
	{
		for(int x = 0; x < 10; x++)
			for(int y = 0; y < 10; y++)
				oceanButtons[x][y].setEnabled(false);
	}

	// Sets up button images
	public void setButtonImage(Coordinates shot, ImageIcon i)
	{
		oceanButtons[shot.getCoordX()][shot.getCoordY()].setIcon(i);
		oceanButtons[shot.getCoordX()][shot.getCoordY()].setDisabledIcon(i);
		oceanButtons[shot.getCoordX()][shot.getCoordY()].setEnabled(false);
	}
	
	// Creates the panel with letter buttons
	private void createLetterPanel()
	{
		GridLayout letterGrid = new GridLayout(1, 10);
		letterPanel = new JPanel(letterGrid);
		letterButtons = new JButton[10];
		
		for(int i = 0; i < 10; i++)	// Initializes letter buttons
		{
			letterButtons[i] = new JButton(Character.toString((char)(65 + i)));
			letterButtons[i].setPreferredSize(new Dimension(50,50));
			letterPanel.add(letterButtons[i]);
		}
	}
	
	// Creates the panel with number buttons
	private void createNumberPanel()
	{
		GridLayout numberGrid = new GridLayout(10,1);
		numberPanel = new JPanel(numberGrid);
		numberButtons = new JButton[10];
		
		for(int i = 0; i < 10; i++)	// Initializes number buttons
		{
			numberButtons[i] = new JButton(Integer.toString(i + 1));
			numberButtons[i].setPreferredSize(new Dimension(50,50));
			numberPanel.add(numberButtons[i]);
		}
	}
	
	// Creates the ocean grid panel with grid buttons
	private void createOceanGrid()
	{
		
		GridLayout oceanGrid = new GridLayout(10,10,0,0);	// Set ups gridlayout
		oceanPanel = new JPanel(oceanGrid);	// Sets up gridlayout in panel
		oceanButtons = new Coordinates[10][10];	// 2D array for grid
		
		for(int x = 0; x < 10; x++)
		{
			for(int y = 0; y < 10; y++)	// Sets up buttons on grid with icons
			{
				oceanButtons[x][y] = new Coordinates(Integer.toString(x), x, y);
				oceanButtons[x][y].setPreferredSize(new Dimension(50,50));
				oceanButtons[x][y].setIcon(new ImageIcon( getClass().getResource("batt101.gif")));
				oceanButtons[x][y].setDisabledIcon(new ImageIcon( getClass().getResource("batt101.gif")));
				oceanButtons[x][y].setEnabled(false);

				oceanButtons[x][y].addActionListener(new buttonAction());

				oceanPanel.add(oceanButtons[x][y]);

			}
		}
		
	}

	
	private class buttonAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			// convert event to a jbutton
			Coordinates buttonClicked = (Coordinates) event.getSource();

			try
			{
				// checks if the program is the server and sends data
				// based on server or client
				if(isSever)
				{

					server.sendData(buttonClicked);
				}
				else
				{
					client.sendData(buttonClicked);
				}

			}
			catch(IOException e)
			{
				System.err.println("Attempt to send object failed!!");
			}


		}
	}
}