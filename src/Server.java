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
	
	public Server(Gui gui)
	{
		serverSocket = null;
		clientSocket = null;
		this.gui = gui;
		
		System.out.println("Creating Server...");
		ConnectionThread connect = new ConnectionThread();
	}
	
	public void closeServer()
	{
		// close client if not already closed
		// Note: may cause some error
		try
		{
			out.close();
			in.close();
			clientSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("Unable to client socket");
		}


		System.out.println("closing server...");
		// this might break something...
		try
		{
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
	
	
private class ConnectionThread implements Runnable
{
	public ConnectionThread()
	{
		new Thread(this).start();
	}
	
	public void run()
	{
		System.out.println("Looking for client...");
		
		try
		{
			serverSocket = new ServerSocket(10008);
			clientSocket = serverSocket.accept();
			
			new CommunicationThread();
			
		}
		catch (IOException e)
		{
			System.err.println("One of the errors occured: ");
			System.err.println("could not listen on port: 10007.");
			System.err.println("Accept Failed");
		}
		
	}
		
} // end of ConnectionThread Class


private class CommunicationThread implements Runnable
{
	public CommunicationThread()
	{
		new Thread(this).start();
	}
	
	public void run()
	{
		System.out.print("Connection with client found!!");
		System.out.print("Waiting for input...");

		try 
		{
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
		catch(Exception ex)
		{
			System.err.println("Problem reading object");
		}
		
		
	}
	
} // end of CommunicationThread class


} // end of Server Class
