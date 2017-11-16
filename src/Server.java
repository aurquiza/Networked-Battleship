import java.net.*;
import java.io.*;

public class Server
{
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Gui gui;

	private ObjectOutputStream out;
	private ObjectInputStream in;

	private Coordinates recievedCoord;

	private boolean endGame = false;
	private boolean isServerCreated = false;
	
	public Server(Gui gui)
	{
		serverSocket = null;
		clientSocket = null;
		out = null;
		in = null;
		this.gui = gui;

		if(createServer())
		{
			isServerCreated = true;
			new CommunicationThread();
		}
		else
			System.err.println("Attempt failed!");
	}
	
	public void closeServer()
	{
		// close client if not already closed
		// Note: may cause some error
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
			System.err.println("Unable to client socket");
		}


		System.out.println("closing server...");
		// this might break something...
		try
		{
			if(serverSocket != null)
				serverSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("Unable to close port!!");
		}
	}


	public void sendData(Coordinates c) throws IOException
	{
		out.writeObject(c);
		out.flush();
	}

	public boolean serverCreated()
	{
		return isServerCreated;
	}
	
	
	public boolean createServer()
	{
		System.out.println("Attempting to create server...");
		
		try
		{
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


private class CommunicationThread implements Runnable
{
	public CommunicationThread()
	{
		new Thread(this).start();
	}
	
	public void run()
	{
		System.out.println("Searching for client connection...");

		try 
		{
			clientSocket = serverSocket.accept();
			System.out.println("Connection with client found!!");
			System.out.println("Waiting for input...");
			gui.changeStatus("Status: Connection Found! place ships on grid");
			gui.enableOceanButtons();

			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			
			while(!endGame)
			{
				recievedCoord = (Coordinates) in.readObject();
				System.out.println("Server: " + recievedCoord.getCoordX() + " " + recievedCoord.getCoordY());
			}
			
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
