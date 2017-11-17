/*
 * CS 342 Programming Project 4: Networked-Battleship
 * 
 * Names: Alexis Urquiza, Robert Barrera, Muhammad Arsalan Chaudry
 * NetID: aurqui7, rbarre4, mchaud25
 * 
 * - This program is a gui simulation of the game Battleship, where two players can go against each other and attempt
 *   to sink the other player's fleet. The first player to sink the other player's fleet wins.
 * 
 * - This class is contains all the ship information to be used in the Battleship game.
 * 
 */

import javax.swing.ImageIcon;

public class Ship {
	private int sizeOfShip;	// Size of a ship
 	private boolean vertical = true;	// Sets ship to vertical or horizontal position	
 	private boolean toggle = true;	// A boolean toggle
 	private boolean successfulConstruct;	// True of construct is successful	
 	private int health;	// The remaining hits a ship can receive
 	private Coordinates shipButtons[];	// Array of ship buttons
 	private Coordinates oceanGrid[][];	// Array of ocean grid buttons
	private Coordinates startOfShip;	// The coordinate of the start of the ship on the grid
	 	
	//Constructor 
	public Ship(int size, boolean vertical, Coordinates ocean[][], Coordinates origin)
	{
		this.sizeOfShip = size;
		this.vertical = vertical;
		health = sizeOfShip;

		successfulConstruct = true;

		shipButtons = new Coordinates[size];
		oceanGrid = ocean;
		startOfShip = origin;

		setShipIcons();	// Sets up ship icons
	}

	// Checks if coordinates are valid
	public boolean checkValidCoords()
	{
		return successfulConstruct;
	}
	
	// De-increments health with a succesful hit
	public void hit()
	{
		health--;
	}

	// Checks if ship is not destroyed
	public boolean isAlive()
	{
		return health > 0;
	}
	
	// Returns the ships array
	public Coordinates[] getShip()
	{
		return shipButtons;
	}

	// Places ship on board at the given coordinate
	public void setShipPieceAt(Coordinates c, int index)
	{
		shipButtons[index] = c;
	}
	
	// Sets up ship icons
	public void setShipIcons() 
	{	
		// Switch statement, sets up a ship base on size of ship
		switch(sizeOfShip) 
		{
			case 5:	// Aircraft Carrier
				createAircraftCarrier();
				break;
			case 4:	// Battleship
				createBattleship();
				break;
			case 3: // Destroyer / Submarine
				createDestroyer();
				break;
			default:	// Patrol Boat
				createPatrolBoat();
				break;
		}
	}
	
	// Creates the Aircraft Carrier icons
	private void createAircraftCarrier()
	{
		int x = startOfShip.getCoordX();
		int y = startOfShip.getCoordY();

		if(vertical)	// Vertical set up
		{
			for(int i = 0; i < 5; i++)
				if(oceanGrid[x-i][y].getOccupation())
				{
					successfulConstruct = false;
					return;
				}

			for(int i = 0; i < 5; i++)
			{
				shipButtons[i] = oceanGrid[x - i][y];
				oceanGrid[x - i][y].setOccupation(true);
			}

			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt10.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt7.gif")));	
			shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt8.gif")));	
			shipButtons[3].setIcon(new ImageIcon( getClass().getResource("batt9.gif")));	
			shipButtons[4].setIcon(new ImageIcon( getClass().getResource("batt6.gif")));	
		}
		else	// Horizontal set up
		{
			for(int i = 0; i < 5; i++)
				if(oceanGrid[x][y - i].getOccupation())
				{
					successfulConstruct = false;
					return;
				}

			for(int i = 0; i < 5; i++)
			{
				shipButtons[i] = oceanGrid[x][y - i];
				oceanGrid[x][y - i].setOccupation(true);
			}

			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt5.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt2.gif")));	
			shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt3.gif")));	
			shipButtons[3].setIcon(new ImageIcon( getClass().getResource("batt4.gif")));	
			shipButtons[4].setIcon(new ImageIcon( getClass().getResource("batt1.gif")));
		}

		
	}

	// Creates the Battleship icons
	private void createBattleship()
	{
		int x = startOfShip.getCoordX();
		int y = startOfShip.getCoordY();

		if(vertical)	// Vertical set up
		{
			for(int i = 0; i < 4; i++)
				if(oceanGrid[x-i][y].getOccupation())
				{
					successfulConstruct = false;
					return;
				}

			for(int i = 0; i < 4; i++)
			{
				shipButtons[i] = oceanGrid[x-i][y];
				oceanGrid[x - i][y].setOccupation(true);
			}

			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt10.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt7.gif")));	
			shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt9.gif")));	
			shipButtons[3].setIcon(new ImageIcon( getClass().getResource("batt6.gif")));	
		}
		else	// Horizontal set up
		{
			for(int i = 0; i < 4; i++)
				if(oceanGrid[x][y - i].getOccupation())
				{
					successfulConstruct = false;
					return;
				}

			for(int i = 0; i < 4; i++)
			{
				shipButtons[i] = oceanGrid[x][y - i];
				oceanGrid[x][y - i].setOccupation(true);
			}

			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt5.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt3.gif")));	
			shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt4.gif")));	
			shipButtons[3].setIcon(new ImageIcon( getClass().getResource("batt1.gif")));	
		}


	}

	// Creates the Destroyer / Submarine icons
	private void createDestroyer()
	{
		int x = startOfShip.getCoordX();	// Initial coordinates
		int y = startOfShip.getCoordY();	// Initial coordinates

		if(vertical)	// Vertical set up
		{
			for(int i = 0; i < 3; i++)
				if(oceanGrid[x-i][y].getOccupation())
				{
					successfulConstruct = false;
					return;
				}

			for(int i = 0; i < 3; i++)
			{
				shipButtons[i] = oceanGrid[x-i][y];
				oceanGrid[x - i][y].setOccupation(true);
			}

			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt10.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt9.gif")));	
			shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt6.gif")));	
		}
		else	// Horizontal set up
		{
			for(int i = 0; i < 3; i++)
				if(oceanGrid[x][y - i].getOccupation())
				{
					successfulConstruct = false;
					return;
				}

			for(int i = 0; i < 3; i++)
			{
				shipButtons[i] = oceanGrid[x][y - i];
				oceanGrid[x][y - i].setOccupation(true);
			}

			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt5.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt3.gif")));	
			shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt1.gif")));	
		}
	}

	// Creates the Patrol Boat icons
	private void createPatrolBoat()
	{
		int x = startOfShip.getCoordX();
		int y = startOfShip.getCoordY();

		if(vertical)	// Vertical set up
		{
			for(int i = 0; i < 2; i++)
				if(oceanGrid[x-i][y].getOccupation())
				{
					successfulConstruct = false;
					return;
				}
			for(int i = 0; i < 2; i++)
			{
				shipButtons[i] = oceanGrid[x-i][y];
				oceanGrid[x - i][y].setOccupation(true);
			}
			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt10.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt6.gif")));
		}
		else	// Horizontal set up
		{
			for(int i = 0; i < 2; i++)
				if(oceanGrid[x][y - i].getOccupation())
				{
					successfulConstruct = false;
					return;
				}
			for(int i = 0; i < 2; i++)
			{
				shipButtons[i] = oceanGrid[x][y - i];
				oceanGrid[x][y - i].setOccupation(true);
			}
			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt5.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt1.gif")));
		}

	
	}
}