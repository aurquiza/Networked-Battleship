import javax.swing.ImageIcon;

public class Ship {
	private int sizeOfShip;
 	private boolean vertical = true; 	
 	private boolean toggle = true;
 	private boolean successfulConstruct;
 	private int health;
 	private Coordinates shipButtons[];
 	private Coordinates oceanGrid[][];
	private Coordinates startOfShip;
	 	
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

		setShipIcons();
	}

	public boolean checkValidCoords()
	{
		return successfulConstruct;
	}
	
	public void hit()
	{
		health--;
	}

	public boolean isAlive()
	{
		return health > 0;
	}
	
	public Coordinates[] getShip()
	{
		return shipButtons;
	}

	public void setShipPieceAt(Coordinates c, int index)
	{
		shipButtons[index] = c;
	}
	
	public void setShipIcons() 
	{
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

	private void createAircraftCarrier()
	{
		int x = startOfShip.getCoordX();
		int y = startOfShip.getCoordY();

		if(vertical)
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
		else
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

	private void createBattleship()
	{
		int x = startOfShip.getCoordX();
		int y = startOfShip.getCoordY();

		if(vertical)
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
		else
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

	private void createDestroyer()
	{
		int x = startOfShip.getCoordX();
		int y = startOfShip.getCoordY();

		if(vertical)
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
		else
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

	private void createPatrolBoat()
	{
		int x = startOfShip.getCoordX();
		int y = startOfShip.getCoordY();

		if(vertical)
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
		else
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