import java.net.*;
import java.io.*;

public class Client
{
	private Gui gui;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private BufferedReader stdIn;
	
	public Client(Gui gui)
	{
		this.gui = gui;
		
		if(LookForServer())
		{
			System.out.println("Server found!");
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
			stdIn.close();
			clientSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("Error in closing socket");
		}
	}

	private boolean LookForServer()
	{
		try
		{
			System.out.println("creating socket..");
			clientSocket = new Socket("127.0.0.1", 10008);
			
			System.out.println("creating in and out pipes...");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream() ) );
			
			System.out.println("Creating standard input pipe");
			stdIn = new BufferedReader(new InputStreamReader( System.in ) );
			
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
		try
		{
			String userInput;
			while((userInput = stdIn.readLine()) != null)
			{
				System.out.println("Client: " + userInput);
				out.println(userInput);
				if(userInput.equals("Close Socket"))
					break;
			}
		}
		catch(IOException e)
		{
			System.err.println("Error: unable to read input from client");
		}
	}
} // end of Reading Input class

}
