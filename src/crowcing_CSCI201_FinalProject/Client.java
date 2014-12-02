package crowcing_CSCI201_FinalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


//In charge of all the communication with server except chat.
//Hub of all data from player
//Only sends actions, not locations of cars.

public class Client {
//	private ClientPanel cp;
	private Socket s;
	private BufferedReader br;
	private PrintWriter pw;
	public Client( String hostname, int port ) {
		try {
			s = new Socket (hostname, port );
			pw = new PrintWriter( s.getOutputStream() );
			br= new BufferedReader( new InputStreamReader(s.getInputStream() ) );
//			cp = new FightPanel(pw);
			ClientThread ct = new ClientThread(br);
			ct.start();
		} catch (IOException ioe) {
			System.out.println( "IOExceptionin Client constructor: " + ioe.getMessage() );
		}
	}
}