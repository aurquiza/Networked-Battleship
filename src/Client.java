/*
 * CS 342 Programming Project 4: Networked-Battleship
 * 
 * Names: Alexis Urquiza, Robert Barrera, Muhammad Arsalan Chaudry
 * NetID: aurqui7, rbarre4, mchaud25
 * 
 * - This program is a gui simulation of the game Battleship, where two players can go against each other and attempt
 *   to sink the other player's fleet. The first player to sink the other player's fleet wins.
 * 
 * - This is a client class that will attempt to connect to a server and send and recieve data objects

 */
import java.net.*;
import java.io.*;
import javax.swing.ImageIcon;

public class Client
{
	// gui and client socket 
	private Gui gui;
	private Socket clientSocket;

	// object output and input streams
	private ObjectOutputStream out;
	private ObjectInputStream in;

	// coordinates that will 
	private Coordinates recievedCoord;
	private Coordinates validationShot = new Coordinates(" ", -5, -5);

	// boolean that help with the continuation of the game
	private boolean endGame = false;
	private boolean connectionMade = false;
	
	// constructor
	public Client(Gui gui)
	{
		this.gui = gui;
		
		// checks if the client found a connection
		if(LookForServer())
		{
			System.out.println("Server found!");
			connectionMade = true;
			gui.changeStatus("Status: Connection Found! place ships on grid");

			// create a new thread
			new ReadingInput();
		}
		else
			System.err.println("Server not found!");
		
		
	}
	
	// close the client and output and input streams
	public void closeClient()
	{
		System.out.println("Closing client...");
		try
		{
			// close output, input streams and client socket
			out.close();
			in.close();
			clientSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("Error in closing socket");
		}
	}

	// sends data that was passed from the coordiantes and sends it to 
	// the server socket
	public void sendData(Coordinates c) throws IOException
	{
		out.writeObject(c);
		out.flush();
	}

	// checks if there is a valid connection
	public boolean isClientConnected()
	{
		return connectionMade;
	}

	// method that returns true or false based on whether the client socket made a 
	// connection
	private boolean LookForServer()
	{
		try
		{
			// make a connection
			System.out.println("creating socket..");
			clientSocket = new Socket("127.0.0.1", 10008);
			System.out.println("Success!");

			// initialize object streams
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		}
		catch(UnknownHostException e)
		{
			System.err.println("couldn't find host!");
			return false;
		}
		catch(IOException e)
		{
			System.err.println("couldn't get I/O from server!");
			return false;
		}
		
		return true;
	}
	
	
// private class that runs a thread that waits for input from the object stream
private class ReadingInput implements Runnable
{
	public ReadingInput()
	{
		new Thread(this).start();
	}
	public void run()
	{
		System.out.println("Waiting for Input..");
		try
		{

			// loop that will continue to read until the game is over
			while(!endGame)
			{
				// read object
				recievedCoord = (Coordinates) in.readObject();
				//System.out.println("Client: " + recievedCoord.getCoordX() + " " + recievedCoord.getCoordY());

				// validation check that will enable shooting buttons
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
		}
		catch(IOException e)
		{
			System.err.println("Problem sending object");
		}
		catch(Exception ex)
		{
			System.err.println("Problem reading object");
		}

	}
} // end of Reading Input class

}
