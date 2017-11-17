import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class UserOceanGrid
{
	private Gui gui;

	private JPanel oceanPanel;
	private JPanel numberPanel;
	private JPanel letterPanel;
	
	private Coordinates oceanButtons[][];
	private JButton letterButtons[];
	private JButton numberButtons[];

	private boolean isVertical = true;
	private int chosenShipSize = -1;

	private JButton carrier;
	private JButton battleship;
	private JButton destroyer;
	private JButton submarine;
	private JButton patrolBoat;
	private JButton vertical;
	private JButton horizontal;

	private Vector<Ship> allShips;
	
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
	
	private void createLetterPanel()
	{
		GridLayout letterGrid = new GridLayout(1, 10);
		letterPanel = new JPanel(letterGrid);
		letterButtons = new JButton[10];
		
		for(int i = 0; i < 10; i++)
		{
			letterButtons[i] = new JButton(Character.toString((char)(65 + i)));
			letterButtons[i].setPreferredSize(new Dimension(50,50));
			letterPanel.add(letterButtons[i]);
		}
	}

	public void enableButtons()
	{
		for(int x = 0; x < 10; x++)
			for(int y = 0; y < 10; y++)
				oceanButtons[x][y].setEnabled(true);
	}

	public void disableButtons()
	{
		for(int x = 0; x < 10; x++)
			for(int y = 0; y < 10; y++)
				oceanButtons[x][y].setEnabled(false);
	}
	
	private void createNumberPanel()
	{
		GridLayout numberGrid = new GridLayout(10,1);
		numberPanel = new JPanel(numberGrid);
		numberButtons = new JButton[10];
		
		for(int i = 0; i < 10; i++)
		{
			numberButtons[i] = new JButton(Integer.toString(i + 1));
			numberButtons[i].setPreferredSize(new Dimension(50,50));
			numberPanel.add(numberButtons[i]);
		}
	}
	
	private void createOceanGrid()
	{
		
		GridLayout oceanGrid = new GridLayout(10,10,0,0);
		oceanPanel = new JPanel(oceanGrid);
		oceanButtons = new Coordinates[10][10];
		
		for(int x = 0; x < 10; x++)
		{
			for(int y = 0; y < 10; y++)
			{
				oceanButtons[x][y] = new Coordinates(Integer.toString(x), x, y);
				oceanButtons[x][y].setPreferredSize(new Dimension(50,50));
				oceanButtons[x][y].setIcon(new ImageIcon( getClass().getResource("batt100.gif")));
				oceanButtons[x][y].setDisabledIcon(new ImageIcon( getClass().getResource("batt100.gif")));
				oceanButtons[x][y].setEnabled(false);

				oceanButtons[x][y].addActionListener(new OceanGridEvent());
				oceanPanel.add(oceanButtons[x][y]);
			}
		}
		
	}
	
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
		if(allShips.size() == 5)
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
