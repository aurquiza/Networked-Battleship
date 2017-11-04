/*
 * CS 342 Programming Project 4: Networked-Battleship
 * 
 * Names: Alexis Urquiza, Robert Barrera, Muhammad Arsalan Chaudry
 * NetID: aurqui7, rbarre4, mchaud25
 * 
 * - This program is a gui simulation of the game Battleship, where two players can go against each other and attempt
 *   to sink the other player's fleet. The first player to sink the other player's fleet wins.
 * 
 * - This class is the heart of the program where main is located as well as a custom JFrame class that helps put all of 
 *   the components of the gui together, such as the menus, jpanels, sockets, and buttons.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Gui extends JFrame
{
	private Container mainContainer;
	private JMenuBar masterBar;
	
	private JPanel masterPanel;
	
	public Gui()
	{
		super("CS 342 Project 4 (Networked-Battleship)");
		
		
		setSize(300,300);
		setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("Testing");
		

	}

}
