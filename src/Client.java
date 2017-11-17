import java.net.*;
import java.io.*;
import javax.swing.ImageIcon;

public class Client
{
	private Gui gui;
	private Socket clientSocket;

	private ObjectOutputStream out;
	private ObjectInputStream in;

	private Coordinates recievedCoord;
	private Coordinates validationShot = new Coordinates(" ", -5, -5);

	private boolean endGame = false;
	private boolean connectionMade = false;
	
	public Client(Gui gui)
	{
		this.gui = gui;
		
		if(LookForServer())
		{
			System.out.println("Server found!");
			connectionMade = true;
			gui.changeStatus("Status: Connection Found! place ships on grid");
			new ReadingInput();
		}
		else
			System.err.println("Server not found!");
		
		
	}
	
	public void closeClient()
	{
		System.out.println("Closing client...");
		try
		{
			out.close();
			in.close();
			clientSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("Error in closing socket");
		}
	}

	public void sendData(Coordinates c) throws IOException
	{
		out.writeObject(c);
		out.flush();
	}

	public boolean isClientConnected()
	{
		return connectionMade;
	}

	private boolean LookForServer()
	{
		try
		{
			System.out.println("creating socket..");
			clientSocket = new Socket("127.0.0.1", 10008);
			System.out.println("Success!");


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

			while(!endGame)
			{
				recievedCoord = (Coordinates) in.readObject();
				System.out.println("Client: " + recievedCoord.getCoordX() + " " + recievedCoord.getCoordY());


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
					gui.updateAttackBoard(recievedCoord, new ImageIcon("batt103.gif"));
				}
				// validation check, this means that the shot was a miss
				else if(recievedCoord.getText().equals("n"))
				{
					gui.updateAttackBoard(recievedCoord, new ImageIcon("batt102.gif"));
				}
				else 
				{
					if(gui.checkShot(recievedCoord))
						recievedCoord.setText("y");
					else
						recievedCoord.setText("n");

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
