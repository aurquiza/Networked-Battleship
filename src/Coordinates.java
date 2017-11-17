import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Coordinates extends JButton implements Serializable
{
	private int X;
	private int Y;
	private boolean occupied;
	
	public Coordinates(String s,int x, int y)
	{
		super(s);
		X = x;
		Y = y;
		occupied = false;
	}
	
	
	public int getCoordX()
	{
		return X;
	}
	
	public int getCoordY()
	{
		return Y;
	}

	public boolean getOccupation()
	{
		return occupied;
	}

	public void setOccupation(boolean b)
	{
		occupied = b;
	}

	public void setCoordX(int x)
	{
		X = x;
	}
}
