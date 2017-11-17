/*
 * CS 342 Programming Project 4: Networked-Battleship
 * 
 * Names: Alexis Urquiza, Robert Barrera, Muhammad Arsalan Chaudry
 * NetID: aurqui7, rbarre4, mchaud25
 * 
 * - This program is a gui simulation of the game Battleship, where two players can go against each other and attempt
 *   to sink the other player's fleet. The first player to sink the other player's fleet wins.
 * 
 * - This is the server class that deals with with creating a server socket that will seek a client and make a connection
	 that will then enable the gui and begin the game
 */
import java.net.*;
import java.io.*;
import javax.swing.ImageIcon;

public class Server
{
	// Sockets that will deal with connections 
	private ServerSocket serverSocket;
	private Socket clientSocket;

	// gui reference
	private Gui gui;

	// object and input streams
	private ObjectOutputStream out;
	private ObjectInputStream in;

	// coordinate buttons that will be used to deal with sending and recieving information
	private Coordinates recievedCoord;
	private Coordinates validationShot = new Coordinates(" ", -5, -5);

	// booleans that will help with server
	private boolean endGame = false;
	private boolean isServerCreated = false;
	
	// Constructor
	public Server(Gui gui)
	{
		serverSocket = null;
		clientSocket = null;
		out = null;
		in = null;
		this.gui = gui;

		// check if creating the server was successful
		if(createServer())
		{
			isServerCreated = true;
			new CommunicationThread();
		}
		else
			System.err.println("Attempt failed!");
	}
	
	// close the server if it was open
	public void closeServer()
	{
		// close any streams and client sockets that may have been open
		try
		{
			if(out != null || in != null || clientSocket != null)
			{
				out.close();
				in.close();
				clientSocket.close();
			} 

		}
		catch(IOException e)
		{
			System.err.println("Unable to close client socket!");
		}


		// close the server
		System.out.println("closing server...");
		try
		{
			if(serverSocket != null)
				serverSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("Unable to close server socket!");
		}
	}


	// sends data to the Client 
	public void sendData(Coordinates c) throws IOException
	{
		// write objects
		out.writeObject(c);
		out.flush();
	}

	// returns whether the server was created or not
	public boolean serverCreated()
	{
		return isServerCreated;
	}
	
	// creates server and return a boolean based on whether it was created or not
	public boolean createServer()
	{
		System.out.println("Attempting to create server...");
		
		try
		{
			// create the server
			serverSocket = new ServerSocket(10008);
			System.out.println("Server Created!");
		}
		catch (IOException e)
		{	
			System.err.println("could not listen on port: 10008.");
			return false;
		}

		return true;
	}	


// private class that creates a thread to avoid the blocking read 
// that will mess up the gui
private class CommunicationThread implements Runnable
{
	// create a thread
	public CommunicationThread()
	{
		new Thread(this).start();
	}
	
	// run the thread method 
	public void run()
	{
		System.out.println("Searching for client connection...");

		try 
		{
			// look for a connection
			clientSocket = serverSocket.accept();
			System.out.println("Connection with client found!!");
			System.out.println("Waiting for input...");
			gui.changeStatus("Status: Connection Found! place ships on grid");
			gui.enableOceanButtons();

			// initialize output and input object streams
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			
			while(!endGame)
			{
				// read object
				recievedCoord = (Coordinates) in.readObject();
				//System.out.println("Server: " + recievedCoord.getCoordX() + " " + recievedCoord.getCoordY());


				// status check to allow player to officially begin game
				if(recievedCoord.getCoordX() == -1 && recievedCoord.getCoordY() == -1)
				{
					gui.enemyDoneStatus(true);
					if(gui.getSelfStatus())
					{
						gui.enableEnemyButtons();
					}
					gui.changeStatus("Status: Server begins first");
				}
				// validation check, this means that the shot was a hit
				else if(recievedCoord.getText().equals("y"))
				{
					gui.updateAttackBoard(recievedCoord, new ImageIcon( getClass().getResource("batt103.gif")));
				}
				// validation check, this means that the shot was a miss
				else if(recievedCoord.getText().equals("n"))
				{
					gui.updateAttackBoard(recievedCoord, new ImageIcon( getClass().getResource("batt102.gif")));
				}
				else
				{
					// sends validation on whether it was a hit or a miss
					if(gui.checkShot(recievedCoord))
						recievedCoord.setText("y");
					else
						recievedCoord.setText("n");
					
					// writes to the object
					out.writeObject(recievedCoord);
					out.flush();
				}
			}
			
			// close sockets
			System.out.println("Closing client socket...");
			out.close();
			in.close();
			clientSocket.close();
		}
		catch(IOException e )
		{
			System.err.println("Accept Failed!");
		}
		catch(Exception ex)
		{
			System.err.println("Problem reading object");
		}
		
	}
	
} // end of CommunicationThread class


} // end of Server Class
