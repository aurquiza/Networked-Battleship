import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UserOceanGrid
{
	private JPanel oceanPanel;
	private JPanel numberPanel;
	private JPanel letterPanel;
	
	private Coordinates oceanButtons[][];
	private JButton letterButtons[];
	private JButton numberButtons[];
	
	public UserOceanGrid()
	{
		oceanPanel = null;
		numberPanel = null;
		letterPanel = null;
		
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
			numberPanel.add(numberButtons[i]);
		}
	}
	
	private void createOceanGrid()
	{
		
		GridLayout oceanGrid = new GridLayout(10,10,1,1);
		oceanPanel = new JPanel(oceanGrid);
		oceanButtons = new Coordinates[10][10];
		
		for(int x = 0; x < 10; x++)
		{
			for(int y = 0; y < 10; y++)
			{
				oceanButtons[x][y] = new Coordinates(Integer.toString(x), x, y);
				//oceanButtons[x][y].setText(Integer.toString(x));
				oceanPanel.add(oceanButtons[x][y]);
			}
		}
		
	}
	
	
}
