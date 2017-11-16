import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Ships {
	private JPanel vCarrierPanel, vBattleshipPanel, vDestroyerPanel, vSubmarinePanel, vPatrolPanel;
	
	private Coordinates CarrierButtons[] = new Coordinates[5]; 
	private Coordinates BattleshipButtons[] = new Coordinates[4]; 
	private Coordinates DestroyerButtons[] = new Coordinates[3]; 
	private Coordinates SubmarineButtons[] = new Coordinates[3]; 
	private Coordinates PatrolButtons[] = new Coordinates[2]; 
	
 	private String vCarrierNames[] = {"batt6.gif", "batt7.gif", "batt8.gif", "batt9.gif", "batt10.gif"};
 	private String vBattleshipNames[] = {"batt6.gif", "batt7.gif", "batt9.gif", "batt10.gif"};
 	private String vDestroyerNames[] = {"batt6.gif", "batt9.gif", "batt10.gif"};
 	private String vSubmarineNames[] = {"batt6.gif", "batt8.gif", "batt10.gif"};
 	private String vPatrolNames[] = {"batt6.gif", "batt10.gif"};
 	
 	private int sizeOfShip;
	private boolean vertical = true; 	
 	private boolean toggle = true;
 	private int health;
	 	
	 	
	 	//Constructor 
	public Ships(){
//		this.sizeOfShip = size;
//		this.vertical = vertical;
//		health = sizeOfShip;
		createShipsPanels();
	}
	
	public void hit(){
		health--;
	}
	public boolean isAlive(){
		return health > 0;
	}
	
//	public Coordinates[] getShip() {
//		return shipButtons;		
//	}
	
	public void changePosition() {
		vertical = !vertical;
	}
	
	public JPanel getCarrierPanel()
	{
		return vCarrierPanel;
	}
	
	public JPanel getBattleshipPanel()
	{
		return vBattleshipPanel;
	}
	
	public JPanel getDestroyerPanel()
	{
		return vDestroyerPanel;
	}
	
	public JPanel getSubmarinePanel()
	{
		return vSubmarinePanel;
	}
	
	public JPanel getPatrolPanel()
	{
		return vPatrolPanel;
	}
	
	
	private void createShipsPanels()
	{
		vCarrierPanel = new JPanel(new GridLayout(5, 1));
		vBattleshipPanel = new JPanel(new GridLayout(4, 1));
		vDestroyerPanel = new JPanel(new GridLayout(3, 1));
		vSubmarinePanel = new JPanel(new GridLayout(3, 1));
		vPatrolPanel = new JPanel(new GridLayout(2, 1));
		
		for(int i = 0; i < 5; i++)	// Aircraft Carrier
		{
			CarrierButtons[i] = new Coordinates(0,0);
			CarrierButtons[i].setPreferredSize(new Dimension(70,70));
			CarrierButtons[i].setIcon(new ImageIcon( getClass().getResource(vCarrierNames[i])));
			vCarrierPanel.add(CarrierButtons[i]);
		}
		
		for(int i = 0; i < 4; i++)	// Battleship
		{
			BattleshipButtons[i] = new Coordinates(0,0);
			BattleshipButtons[i].setPreferredSize(new Dimension(70,70));
			BattleshipButtons[i].setIcon(new ImageIcon( getClass().getResource(vBattleshipNames[i])));
			vBattleshipPanel.add(BattleshipButtons[i]);
		}
		
		for(int i = 0; i < 3; i++)	// Destroyer
		{
			DestroyerButtons[i] = new Coordinates(0,0);
			DestroyerButtons[i].setPreferredSize(new Dimension(70,70));
			DestroyerButtons[i].setIcon(new ImageIcon( getClass().getResource(vDestroyerNames[i])));
			vDestroyerPanel.add(DestroyerButtons[i]);
		}
		
		for(int i = 0; i < 3; i++)	// Submarine
		{
			SubmarineButtons[i] = new Coordinates(0,0);
			SubmarineButtons[i].setPreferredSize(new Dimension(70,70));
			SubmarineButtons[i].setIcon(new ImageIcon( getClass().getResource(vSubmarineNames[i])));
			vSubmarinePanel.add(SubmarineButtons[i]);
		}
		
		for(int i = 0; i < 2; i++)	// Patrol Boat
		{
			PatrolButtons[i] = new Coordinates(0,0);
			PatrolButtons[i].setPreferredSize(new Dimension(70,70));
			PatrolButtons[i].setIcon(new ImageIcon( getClass().getResource(vPatrolNames[i])));
			vPatrolPanel.add(PatrolButtons[i]);
		}
	}
	
}
