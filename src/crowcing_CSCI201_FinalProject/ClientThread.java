package crowcing_CSCI201_FinalProject;

import java.io.BufferedReader;
import java.io.IOException;

class ClientThread extends Thread {
		private BufferedReader br;
//		private Client fp
		public ClientThread(BufferedReader br) {
			this.br = br;
		}
		public void run() {
			while(true){
				try {
					
					while(true){
						String line = br.readLine();
						if(line.substring(0, 1).equals("<")){
							
						}
						if (!line.equals("-1")){
//							fp.setYourStatus(line);
						}
						else{
//							fp.startGame();
						}
					}
				} catch (IOException ioe) {
					System.out.println("IOExceptionin ServerThreadconstructor: " + ioe.getMessage());
				}
			}
		}
	}