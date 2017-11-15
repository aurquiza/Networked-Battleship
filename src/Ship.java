
public class Ship {
	public int sizeOfShip;
	public boolean vertical = true;
	
	private int health;
	
	
	//Constructor 
	public Ship(int size, boolean vertical){
		this.sizeOfShip = size;
		this.vertical = vertical;
		health = sizeOfShip;
	}
	
	
	
	
	
	public void hit(){
		health--;
	}
	public boolean isAlive(){
		return health > 0;
	}

}
