import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Coordinates extends JButton
{
	private int X;
	private int Y;
	
	public Coordinates(int x, int y)
	{
		X = x;
		Y = y;
	}
	
	
	public int getCoordX()
	{
		return X;
	}
	
	public int getCoordY()
	{
		return Y;
	}
}
