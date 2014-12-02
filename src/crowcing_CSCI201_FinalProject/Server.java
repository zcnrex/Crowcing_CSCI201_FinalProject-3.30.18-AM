package crowcing_CSCI201_FinalProject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

	public class Server {
		
		private Vector<ServerThread> ctVector = new Vector<ServerThread>();
		
		public Server(int port) {
			try {
				ServerSocket ss = new ServerSocket(port);
				while(true) {
					Socket s = ss.accept();
					ServerThread st = new ServerThread(s,this);
					ctVector.add(st);
					st.start();
				}
			} catch (IOException ioe) {
				System.out.println("IOException in Server constructor: " + ioe.getMessage());
			}
		}
		public static void main(String [] args) {
			/*if (args.length != 1) {
				System.out.println("USAGE: java Server [port]");
				return;
			}*/
			Server server = new Server(2232);
		}
		public void sendMessage(String line, ServerThread st) {
			for(ServerThread c : ctVector) {
				if (!c.equals(st)) {
					c.send(line);
				}
			}
			
		}
	}
	class ServerThread extends Thread {
		private Socket s;
		private Server cs;
		private BufferedReader br ;
		private PrintWriter pw;
		
		
		public ServerThread(Socket s,Server cs) {
			this.s = s;
			this.cs = cs;
		}
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				pw = new PrintWriter(s.getOutputStream());
				while (true)
				{
					System.out.println("Line received from client ");
					String line = br.readLine();
					System.out.println("Line received from client " + s.getInetAddress() + ": " + line);
					//pw.println("Line going to client");
					cs.sendMessage(line, this);
					//pw.println(line);
					//pw.flush();
				}
				
			} catch (IOException ioe) {
				System.out.println("IOException in ServerThread constructor: " + ioe.getMessage());
			}
 }
		
		public void send(String msg)
		{
			pw.println(msg);
			pw.flush();
		}
 }