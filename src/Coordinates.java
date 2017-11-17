/*
 * CS 342 Programming Project 4: Networked-Battleship
 * 
 * Names: Alexis Urquiza, Robert Barrera, Muhammad Arsalan Chaudry
 * NetID: aurqui7, rbarre4, mchaud25
 * 
 * - This program is a gui simulation of the game Battleship, where two players can go against each other and attempt
 *   to sink the other player's fleet. The first player to sink the other player's fleet wins.
 * 
 * - The Coordinates class inherits the JButton that is used to keep track and send information between two 
     programs that are connected through sockets
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Coordinates extends JButton implements Serializable
{
	// private instances
	private int X;
	private int Y;
	private boolean occupied;
		
	// constructor
	public Coordinates(String s,int x, int y)
	{
		super(s);
		X = x;
		Y = y;
		occupied = false;
	}
	
	// get coordinates for x
	public int getCoordX()
	{
		return X;
	}
	
	// get coordinates for y
	public int getCoordY()
	{
		return Y;
	}

	// checks if the button is occupied with a ship piece
	public boolean getOccupation()
	{
		return occupied;
	}

	// sets the occupation to true
	public void setOccupation(boolean b)
	{
		occupied = b;
	}

	// set coordinate for x
	public void setCoordX(int x)
	{
		X = x;
	}
}
