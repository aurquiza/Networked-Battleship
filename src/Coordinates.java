import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Coordinates extends JButton
{
	private int X;
	private int Y;
	
	public Coordinates(String s,int x, int y/*, Icon i*/)
	{
		super(s);
		//setIcon(i);
		//setBackground(Color.blue);
		//setForeground(Color.white);
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
