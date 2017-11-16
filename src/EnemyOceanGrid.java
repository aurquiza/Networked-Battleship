import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.ImageIcon;

public class EnemyOceanGrid
{
	private JPanel oceanPanel;
	private JPanel numberPanel;
	private JPanel letterPanel;
	
	private Coordinates oceanButtons[][];
	private JButton letterButtons[];
	private JButton numberButtons[];

	private Server server;
	private Client client;
	private boolean isSever = false;
	
	public EnemyOceanGrid()
	{
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
				oceanButtons[x][y].setIcon(new ImageIcon("batt101.gif"));
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
			Coordinates buttonClicked = (Coordinates) event.getSource();

			try
			{
				if(isSever)
					server.sendData(buttonClicked);
				else
					client.sendData(buttonClicked);
			}
			catch(IOException e)
			{
				System.err.println("Attempt to send object failed!!");
			}

		}
	}
}
