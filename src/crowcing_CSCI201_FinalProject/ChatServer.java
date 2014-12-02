//package crowcing_CSCI201_FinalProject;
//
//import java.io.IOException;
//import java.io.PrintStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//
//public class ChatServer {
//
//	private static ServerSocket serverSocket = null;
//	private static Socket clientSocket = null;
//	private static final int maxClientsCount = 10;
//	private static final ChatThread[] threads = new ChatThread[maxClientsCount];
//
//	public static void main(String args[]) {
//
//	int portNumber = 2232;
//
//	try {
//		serverSocket = new ServerSocket(portNumber);
//	} catch (IOException e) {
//		System.out.println(e);
//	}
//
//	while (true) {
//		try {
//			clientSocket = serverSocket.accept();
//		    int i = 0;
//		    for (i = 0; i < maxClientsCount; i++) {
//		    	if (threads[i] == null) {
//		    		(threads[i] = new ChatThread(clientSocket, threads)).start();
//		        break;
//		    	}
//		    }
//		    if (i == maxClientsCount) {
//		    	PrintStream os = new PrintStream(clientSocket.getOutputStream());
//		    	os.println("Server too busy. Try later.");
//		    		os.close();
//		    		clientSocket.close();
//		        }
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//	}
//} 
//}
