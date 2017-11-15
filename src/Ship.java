import javax.swing.ImageIcon;

public class Ship {
	public int sizeOfShip;
 	public boolean vertical = true; 	
 	public boolean toggle = true;
 	private int health;
 	private Coordinates shipButtons[];
	 	
	 	
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
	
	public void setShipIcons(int size) {
		switch(size) {
		case 5:	// Aircraft Carrier
			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt6.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt7.gif")));	
			shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt8.gif")));	
			shipButtons[3].setIcon(new ImageIcon( getClass().getResource("batt9.gif")));	
			shipButtons[4].setIcon(new ImageIcon( getClass().getResource("batt10.gif")));	
			break;
		case 4:	// Battleship
			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt6.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt7.gif")));	
			shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt9.gif")));	
			shipButtons[3].setIcon(new ImageIcon( getClass().getResource("batt10.gif")));	
			break;
		case 3:
			if (toggle) {	// Destroyer
				shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt6.gif")));	
				shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt9.gif")));	
				shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt10.gif")));	
				toggle = !toggle;
			}
			else {	// Submarine
				shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt6.gif")));	
				shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt8.gif")));	
				shipButtons[2].setIcon(new ImageIcon( getClass().getResource("batt10.gif")));	
				toggle = !toggle;
			}
			break;
		default:	// Patrol Boat
			shipButtons[0].setIcon(new ImageIcon( getClass().getResource("batt6.gif")));	
			shipButtons[1].setIcon(new ImageIcon( getClass().getResource("batt10.gif")));	
			break;
		}
	}
}