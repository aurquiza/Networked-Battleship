import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UserOceanGrid extends JFrame
{
	private JPanel oceanPanel;
	
	private GridLayout letterPositions;
	private GridLayout numberPositions;
	
	private Coordinates oceanButtons[][];
	private JLabel letterButtons[];
	private JLabel numberButtons[];
	
	public UserOceanGrid()
	{
		createOceanGrid();
	}
	
	public JPanel getOceanPanel()
	{
		return oceanPanel;
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
				oceanButtons[x][y] = new Coordinates(x,y);
				oceanButtons[x][y].setText("-");
				oceanPanel.add(oceanButtons[x][y]);
			}
		}
		
	}
	
	
}
