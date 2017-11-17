/*
 * CS 342 Programming Project 4: Networked-Battleship
 * 
 * Names: Alexis Urquiza, Robert Barrera, Muhammad Arsalan Chaudry
 * NetID: aurqui7, rbarre4, mchaud25
 * 
 * - This program is a gui simulation of the game Battleship, where two players can go against each other and attempt
 *   to sink the other player's fleet. The first player to sink the other player's fleet wins.
 * 
 * - This class is contains all the User's game grid information to be used in the Battleship game.
 * 
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

// User's Ocean Grid 
public class UserOceanGrid
{
	private Gui gui;	// Gui to be passed to this class

	private JPanel oceanPanel;	// Panel for ocean buttons
	private JPanel numberPanel;	// Panel for number buttons
	private JPanel letterPanel;	// Panel for letter buttons
	
	private Coordinates oceanButtons[][];	// Ocean buttons array
	private JButton letterButtons[];	// Letter buttons array
	private JButton numberButtons[];	// number buttons array

	private boolean isVertical = true;	// For ships position on board
	private int chosenShipSize = -1;	// For ships size when created

	private JButton carrier;	// Button for placing carrier ship on grid
	private JButton battleship;	// Button for placing battleship ship on grid
	private JButton destroyer;	// Button for placing destroyer ship on grid
	private JButton submarine;	// Button for placing submarine ship on grid
	private JButton patrolBoat;	// Button for placing patrol ship on grid
	private JButton vertical;	// Button for placing vertical ship on grid
	private JButton horizontal;	// Button for placing horizontal ship on grid

	private Vector<Ship> allShips;	// Vector to hold all the ships
	
	// Constructor to set up the ocean grids, number and letter panels
	public UserOceanGrid(Gui gui)
	{
		oceanPanel = null;
		numberPanel = null;
		letterPanel = null;
		allShips = new Vector<Ship>(1);
		
		this.gui = gui;

		createOceanGrid();
		createNumberPanel();
		createLetterPanel();
		createShipButtons();
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

	// Get the array of ship buttons
	public JButton[] getShipButtons()
	{
		JButton arr[] = new JButton[7];
		arr[0] = carrier;
		arr[1] = battleship;
		arr[2] = destroyer;
		arr[3] = submarine;
		arr[4] = patrolBoat;
		arr[5] = vertical;
		arr[6] = horizontal;

		return arr;
	}

	// Returns true if button had a ship on it and was hit
	public boolean isHit(Coordinates shot)
	{
		if(oceanButtons[shot.getCoordX()][shot.getCoordY()].getOccupation())
		{
			oceanButtons[shot.getCoordX()][shot.getCoordY()].setIcon(new ImageIcon( getClass().getResource("batt202.gif")));
			oceanButtons[shot.getCoordX()][shot.getCoordY()].setDisabledIcon(new ImageIcon( getClass().getResource("batt202.gif")));
			oceanButtons[shot.getCoordX()][shot.getCoordY()].setEnabled(false);
			return true;
		}
		else
		{
			oceanButtons[shot.getCoordX()][shot.getCoordY()].setIcon(new ImageIcon( getClass().getResource("batt102.gif")));
			oceanButtons[shot.getCoordX()][shot.getCoordY()].setDisabledIcon(new ImageIcon( getClass().getResource("batt102.gif")));
			oceanButtons[shot.getCoordX()][shot.getCoordY()].setEnabled(false);
		}

		return false;
	}

	// Create ship buttons to be placed on the grid
	private void createShipButtons()
	{
		carrier = new JButton("Aircraft Carrier");
		carrier.addActionListener(new shipAdderEvent(5));

		battleship = new JButton("Battleship");
		battleship.addActionListener(new shipAdderEvent(4));

		destroyer = new JButton("Destroyer");
		destroyer.addActionListener(new shipAdderEvent(3));

		submarine = new JButton("Submarine");
		submarine.addActionListener(new shipAdderEvent(3));

		patrolBoat = new JButton("Patrol Boat");
		patrolBoat.addActionListener(new shipAdderEvent(2));

		vertical = new JButton("Vertical");
		vertical.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					isVertical = true;
				}
			});

		horizontal = new JButton("Horizontal");
		horizontal.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					isVertical = false;
				}
			});
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

				oceanButtons[x][y].addActionListener(new OceanGridEvent());
				oceanPanel.add(oceanButtons[x][y]);
			}
		}
		
	}
	
	// Event listener for ocean grid buttons
	private class OceanGridEvent implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Coordinates clickedButton = (Coordinates) e.getSource();
	
			Ship newAirCarrier;
			Ship newBattleShip;
			Ship newSub;
			Ship newPatrol;
	
			switch(chosenShipSize)
			{
				case 5:
					// checks if the origin of the button that was clicked can be used to create the ship if it's coordinates
					// range bigger than or equal to 5
					if((clickedButton.getCoordX() >= 4 && isVertical) || (clickedButton.getCoordY() >= 4 && !(isVertical)))
					{
						newAirCarrier = new Ship(chosenShipSize, isVertical, oceanButtons, clickedButton);
	
						if(newAirCarrier.checkValidCoords())
						{
							allShips.add(newAirCarrier);
							carrier.setEnabled(false);
							chosenShipSize = -1;
						}
	
					}
					break;
				case 4:
					// checks if the origin of the button that was clicked can be used to create the ship if it's coordinates
					// range bigger than or equal to 4
					if((clickedButton.getCoordX() >= 3 && isVertical) || (clickedButton.getCoordY() >= 3 && !(isVertical)))
					{
						newBattleShip = new Ship(chosenShipSize, isVertical, oceanButtons, clickedButton);
	
						if(newBattleShip.checkValidCoords())
						{
							allShips.add(newBattleShip);
							battleship.setEnabled(false);
							chosenShipSize = -1;
						}
					}
					break;
				case 3:
					// checks if the origin of the button that was clicked can be used to create the ship if it's coordinates
					// range bigger than or equal to 3
					if((clickedButton.getCoordX() >= 2 && isVertical) || (clickedButton.getCoordY() >= 2 && !(isVertical)))
					{
						newSub = new Ship(chosenShipSize, isVertical, oceanButtons, clickedButton);
						if(newSub.checkValidCoords())
						{
							allShips.add(newSub);
							if(destroyer.isEnabled())
								destroyer.setEnabled(false);
							else
								submarine.setEnabled(false);
							chosenShipSize = -1;
						}
					}
					break;
				case 2:
					// checks if the origin of the button that was clicked can be used to create the ship if it's coordinates
					// range bigger than or equal to 2
					if((clickedButton.getCoordX() >= 1 && isVertical) || (clickedButton.getCoordY() >= 1 && !(isVertical)))
					{
						newPatrol = new Ship(chosenShipSize, isVertical, oceanButtons, clickedButton);
	
						if(newPatrol.checkValidCoords())
						{
							allShips.add(newPatrol);
							patrolBoat.setEnabled(false);
							chosenShipSize = -1;
						}
					}
					break;
				default:
					break;
			}
			if(allShips.size() == 5)	// Checks that all ships have been placed on grid
			{
				gui.setSelfStatus(true);
				gui.sendDoneStatus();
				if(gui.getEnemyStatus())
				{
					gui.enableEnemyButtons();
				}
			}
		}
	
	} // end of OceanGridEvent class

	// Event listener to increase ship size
	private class shipAdderEvent implements ActionListener
	{
		private int shipSize;
		public shipAdderEvent(int size)
		{
			shipSize = size;
		}
	
		public void actionPerformed(ActionEvent e)
		{
			chosenShipSize = shipSize;
		}
	}

} // end of UserOceanGrid
