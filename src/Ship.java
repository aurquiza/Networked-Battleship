import java.awt.Dimension;

import javax.swing.ImageIcon;

public class Ship {
	public int sizeOfShip;
 	public boolean vertical = true; 	
 	public boolean toggle = true;
 	private int health;
 	private Coordinates shipButtons[];
 	private String vCarrierNames[] = {"batt6.gif", "batt7.gif", "batt8.gif", "batt9.gif", "batt10.gif"};
 	private String vBattleshipNames[] = {"batt6.gif", "batt7.gif", "batt9.gif", "batt10.gif"};
 	private String vDestroyerNames[] = {"batt6.gif", "batt9.gif", "batt10.gif"};
 	private String vSubmarineNames[] = {"batt6.gif", "batt8.gif", "batt10.gif"};
 	private String vPatrolNames[] = {"batt6.gif", "batt10.gif"};
	 	
	 	
	 	//Constructor 
	public Ship(int size, boolean vertical){
		this.sizeOfShip = size;
		this.vertical = vertical;
		health = sizeOfShip;
		shipButtons = new Coordinates[size];
	}
	
	public void hit(){
		health--;
	}
	public boolean isAlive(){
		return health > 0;
	}
	
	public Coordinates[] getShip() {
		return shipButtons;		
	}
	
	public void changePosition() {
		vertical = !vertical;
	}
	
	public void setShipIcons(int size) {
		switch(size) {
		case 5:	// Aircraft Carrier
			for (int i = 0; i < 5; i++) {
				shipButtons[i] = new Coordinates(0,0);
				shipButtons[i].setPreferredSize(new Dimension(50,50));
				shipButtons[i].setIcon(new ImageIcon( getClass().getResource(vCarrierNames[i])));
			}
			break;
		case 4:	// Battleship
			for (int i = 0; i < 4; i++) {
				shipButtons[i] = new Coordinates(0,0);
				shipButtons[i].setPreferredSize(new Dimension(50,50));
				shipButtons[i].setIcon(new ImageIcon( getClass().getResource(vBattleshipNames[i])));
			}
			break;
		case 3:
			if (toggle) {	// Destroyer
				for (int i = 0; i < 3; i++) {
					shipButtons[i] = new Coordinates(0,0);
					shipButtons[i].setPreferredSize(new Dimension(50,50));
					shipButtons[i].setIcon(new ImageIcon( getClass().getResource(vDestroyerNames[i])));
				}	
				toggle = !toggle;
			}
			else {	// Submarine
				for (int i = 0; i < 3; i++) {
					shipButtons[i] = new Coordinates(0,0);
					shipButtons[i].setPreferredSize(new Dimension(50,50));
					shipButtons[i].setIcon(new ImageIcon( getClass().getResource(vSubmarineNames[i])));
				}	
				toggle = !toggle;
			}
			break;
		default:	// Patrol Boat
			for (int i = 0; i < 2; i++) {
				shipButtons[i] = new Coordinates(0,0);
				shipButtons[i].setPreferredSize(new Dimension(50,50));
				shipButtons[i].setIcon(new ImageIcon( getClass().getResource(vPatrolNames[i])));
			}
			break;
		}
	}
}