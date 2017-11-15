import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.ImageIcon;

public class EnemyOceanGrid
{
	private JPanel oceanPanel;
	private JPanel numberPanel;
	private JPanel letterPanel;
	
	private Coordinates oceanButtons[][];
	private JButton letterButtons[];
	private JButton numberButtons[];
	
	public EnemyOceanGrid()
	{
		oceanPanel = null;
		numberPanel = null;
		letterPanel = null;
		oceanButtons = null;
		
		createOceanGrid();
		createNumberPanel();
		createLetterPanel();
	}
	
	public JPanel getOceanPanel()
	{
		return oceanPanel;
	}
	
	public JPanel getNumberPanel()
	{
		return numberPanel;
	}
	
	public JPanel getLetterPanel()
	{
		return letterPanel;
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
			letterButtons[i].setBackground(Color.black);
			letterButtons[i].setForeground(Color.white);
			letterPanel.add(letterButtons[i]);
		}
	}
	
	private void createNumberPanel()
	{
		GridLayout numberGrid = new GridLayout(10,1);
		numberPanel = new JPanel(numberGrid);
		numberButtons = new JButton[10];
		
		for(int i = 0; i < 10; i++)
		{
			numberButtons[i] = new JButton(Integer.toString(i));
			numberButtons[i].setPreferredSize(new Dimension(50,50));
			numberButtons[i].setBackground(Color.black);
			numberButtons[i].setForeground(Color.white);
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
				oceanButtons[x][y].setIcon(new ImageIcon( getClass().getResource("batt101.gif")));				
				oceanPanel.add(oceanButtons[x][y]);
			}
		}
		
	}
}
