package crowcing_CSCI201_FinalProject;

import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

//Main class for client that setup window and go through all phases
//We also need JMenu
public class Crowcing extends JFrame{
	public static JPanel outerPanel = new JPanel();
	
	private LoginPanel loginPanel = new LoginPanel();
	public static CarChoosingPanel choosePanel;//=new CarChoosingPanel();
	public static ResultPanel resultPanel=new ResultPanel();
	//public static String finalRank;
	private MainScreenPanel mainScreenPanel=new MainScreenPanel();
	

	public static JButton bomb;
	public static JPanel whitePanel = new JPanel();
	
	//public Crowcing(){

	private JLayeredPane lp = null;//Override miniMap an racingPanel
	
	public static String ipAdress;
	
	public Crowcing(String ip){

		super("Crowcing");
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ipAdress=ip;
		
		//Client client = new Client("10.120.71.144", 2222);
		
		lp = this.getLayeredPane(); 
		choosePanel=new CarChoosingPanel();
		
		outerPanel.setLayout(new CardLayout());
		
		outerPanel.add(loginPanel, "login");
		outerPanel.add(choosePanel, "chooseCar");
		outerPanel.add(resultPanel,"result");
		outerPanel.add(mainScreenPanel,"mainScreen");
		
		lp.add(outerPanel, new Integer(200));
		lp.add(MainScreenPanel.miniMapPanel, new Integer(300));
		lp.add(bomb, new Integer(300));
		lp.add(MainScreenPanel.chatPanel, new Integer(500));
		lp.add(whitePanel, new Integer(600));
		
		bomb.setVisible(false);
		MainScreenPanel.miniMapPanel.setVisible(false);
		MainScreenPanel.chatPanel.setVisible(false);
		whitePanel.setVisible(false);
		outerPanel.setVisible(true);
		
		
		
		Thread t = new Thread(loginPanel);
		t.start();
		//add(loginPanel);
		add(outerPanel);
		
		
		//result panel
		/*
		JPanel result = new ResultPanel(2, 3, 4);
		add(result);
		*/
		setVisible(true);
	}
	

	
	public static void main(String[] args) {
		System.out.println("IP address for server:");
		
		Scanner scan=new Scanner(System.in);
		String ip=scan.nextLine();
		Crowcing crowcing = new Crowcing(ip);

	}

}
