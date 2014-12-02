package crowcing_CSCI201_FinalProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.Timer;

public class BombThread extends Thread{
	private PrintWriter pw;
	private BufferedReader br;
	public static Timer timer;
	
	public BombThread(){
		Socket s;
		try {
			s = new Socket("localhost", 2232);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		timer = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Crowcing.whitePanel.setVisible(false);
				((Timer)e.getSource()).stop();
			}
		});
	}
	
	public void run(){
		while(true){
			String temp;
			try {
				temp = br.readLine();
				if(temp.contains("BOMB!")){
					String[] tokens = temp.split("!");
					if(!(Integer.valueOf(tokens[1]) == MainScreenPanel.id)){
						Crowcing.whitePanel.setVisible(true);
						timer.start();
						
					}
					
					
				}
			} catch(Exception ee){
				
			}
		}
	}
}
