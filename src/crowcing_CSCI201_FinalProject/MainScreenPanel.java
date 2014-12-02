package crowcing_CSCI201_FinalProject;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//add all components inside this panel when racing starts.
public class MainScreenPanel extends JPanel implements Runnable// contain ChatPanel, miniMapPanel and RacingPanel
{
	
	public static MiniMapPanel miniMapPanel=new MiniMapPanel();
	public static ChatPanel chatPanel=new ChatPanel();
	public static RacingPanel racingPanel=new RacingPanel();

	public static Timer timer;
	public static Timer cooldown;
	
	public static int id;

	private PrintWriter pw;
	private BufferedReader br;
	
	public MainScreenPanel()
	{
		this.setSize(800,600);
		this.setLayout(null);
		this.setVisible(true);
		Crowcing.bomb = new JButton("BOMB!");
		//racingPanel.setPreferredSize(new Dimension(600,600));
		Crowcing.bomb.setFont(new Font("Dialog", 1, 18));
		chatPanel.setBounds(600, 0, 200, 600);
		miniMapPanel.setBounds(500, 0, 100, 100);
		racingPanel.setBounds(0, 0, 800, 600);
		Crowcing.bomb.setBounds(25, 525, 100, 25);
		Crowcing.whitePanel.setBounds(0,0,800,600);
		Crowcing.whitePanel.setBackground(Color.WHITE);
		//whitePanel.setOpacity(0f);
		
		id = (int)(Math.random()*(9999-1000+1)+1000);
		
		Socket s;
		try {
			s = new Socket("localhost", 2232);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			Crowcing.bomb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					pw.println("BOMB!" + id);
					pw.flush();
//					Crowcing.whitePanel.setVisible(true);
					Crowcing.bomb.setEnabled(false);
//					timer.start();
					cooldown.start();
				}
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		timer = new Timer(1000, new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				Crowcing.whitePanel.setVisible(false);
//				((Timer)e.getSource()).stop();
//			}
//		});
		
		cooldown = new Timer(10000, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Crowcing.bomb.setEnabled(true);
				((Timer)e.getSource()).stop();
			}
		});
		
		//Thread t = new Thread(racingPanel);
		
		
		this.add(racingPanel);
		this.add(chatPanel);
		this.add(miniMapPanel);
		
		
	}
	
	public void run(){
		revalidate();
	}
}
