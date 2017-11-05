import java.net.*;
import java.io.*;

public class Server
{
	private Coordinates point;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Gui gui;
	
	public Server(Gui gui)
	{
		serverSocket = null;
		clientSocket = null;
		point = new Coordinates(3,3);
		this.gui = gui;
		
		System.out.println("Creating Server...");
		ConnectionThread connect = new ConnectionThread();
	}
	
	public void closeServer()
	{
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
		try 
		{
			PrintWriter out = new PrintWriter( clientSocket.getOutputStream(), true );
			BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
			
			String inputLine;
			
			System.out.print("Connection with client found!!");
			while((inputLine = in.readLine()) != null)
			{
				System.out.println("Server: " + inputLine);
				out.println("server recieved message!");
				
				if(inputLine.equals("Close Socket"))
					break;
					
			}
			
			System.out.println("Closing client socket...");
			out.close();
			in.close();
			clientSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("Problem with Communication Server");
		}
		
		
	}
	
} // end of CommunicationThread class


} // end of Server Class
