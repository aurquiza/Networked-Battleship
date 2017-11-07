import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Coordinates extends JButton
{
	int X;
	int Y;
	
	public Coordinates(int x, int y)
	{
		super(" ");
		X = x;
		Y = y;
	}
	
	public int getX()
	{
		return X;
	}
	
	public int getY()
	{
		return Y;
	}
}
