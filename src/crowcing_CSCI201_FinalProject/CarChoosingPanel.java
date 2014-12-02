package crowcing_CSCI201_FinalProject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;


//Display image and information of three cars
public class CarChoosingPanel extends JPanel implements Runnable{

	private JLabel speedLabel;
	private JLabel accelerationLabel;
	private JLabel handlingLabel;
	private Car car[]=new Car[3];
	private JButton carButton[]=new JButton[3];//buttons for car
	private int carNumSelect;
	
	public static Car chosenCar=null;
	public static Car opponentCar=null;
	public static int chosenCarID;
	public static int opponentCarID;
	
	private PrintWriter pw;
	private BufferedReader br;
	private JButton startButton;
	
	private ImageIcon backgroundImage= new ImageIcon("image/Motor.jpg");
	
	private String opponentCarName;
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Image image= backgroundImage.getImage().getScaledInstance(800, 560, Image.SCALE_SMOOTH);
		ImageIcon background= new ImageIcon(image);
		
		 g.drawImage(background.getImage(), 0, 0, this);
		
	}
	
	public CarChoosingPanel()
	{
		this.setSize(800,600);
		this.setLayout(null);
		this.setVisible(true);
		//pw.println(" are using car"+(carNumSelect+1)); 
		Socket s;
		try {
			s = new Socket(Crowcing.ipAdress, 2232);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		for (int i=0;i<3;i++)//set imageIcon and absolute location of the button	
		{
			ImageIcon icon = new ImageIcon("image/car"+((1+i)+"")+".png");	
			Image image= icon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
			ImageIcon icon1=new ImageIcon(image);
			
			carButton[i]=new JButton(icon1);
			carButton[i].setBounds(50+i*250, 200, 200, 100);
			
			//car[i]=new Car("car"+((i+1)+""),i+1,i+2,i+3);
		}
		
		car[0]=new Car("car"+(1+""),7,8,9);
		car[1]=new Car("car"+(2+""),8,9,7);
		car[2]=new Car("car"+(3+""),9,7,8);
		
		
		startButton=new JButton("start");//set up start button
		startButton.setPreferredSize(new Dimension(80,60));
		
		startButton.setBounds(650, 430,  (int)startButton.getPreferredSize().getWidth(), (int)startButton.getPreferredSize().getHeight());
		
		
		
		
		
		JLabel title=new JLabel("Choose the car you like...");//set Labels
		speedLabel=new JLabel("Top speed score: N/A");
		accelerationLabel=new JLabel("Acceleration score: N/A");
		handlingLabel=new JLabel("handling score: N/A");
		
		title.setFont(new Font("Dialog",   1,   30));
		speedLabel.setFont(new Font("Dialog",   1,   18));
		accelerationLabel.setFont(new Font("Dialog",   1,   18));
		handlingLabel.setFont(new Font("Dialog",   1,   18));
		title.setBounds(100,100,(int)title.getPreferredSize().getWidth(), (int)title.getPreferredSize().getHeight());
		speedLabel.setBounds(50,350,(int)speedLabel.getPreferredSize().getWidth(), (int)speedLabel.getPreferredSize().getHeight());
		accelerationLabel.setBounds(50,370,(int)accelerationLabel.getPreferredSize().getWidth(), (int)accelerationLabel.getPreferredSize().getHeight());
		handlingLabel.setBounds(50,390,(int)handlingLabel.getPreferredSize().getWidth(), (int)handlingLabel.getPreferredSize().getHeight());
		
		/*MiniMapPanel miniMap=new MiniMapPanel(); ///for test only
		miniMap.setPreferredSize(new Dimension(100, 100)); 
		miniMap.setBounds(300,300,(int)miniMap.getPreferredSize().getWidth(), (int)miniMap.getPreferredSize().getHeight());
		this.add(miniMap);*/
		
		class carListener implements ActionListener
		{
			private int index;
			
			public carListener(int i)
			{
				index=i;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				for (int i=0;i<3;i++)
				{
					Border blackline;
	    			blackline = BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK);	        				
	    			carButton[i].setBorder(blackline);
				}
				
				Border redline;
    			redline = BorderFactory.createMatteBorder(3,3,3,3,Color.RED);	        				
    			carButton[index].setBorder(redline);
    			
				carNumSelect=index;
				speedLabel.setText("Top speed score: "+(car[index].getTopSpeed()+""));
				accelerationLabel.setText("Acceleration score: "+(car[index].getAcceleration())+"");
				handlingLabel.setText("handling score: "+(car[index].getHandling())+"");
				
			}
			
			
		}
		
		for (int i=0;i<3;i++)
		{
			carButton[i].addActionListener(new carListener(i));
			
		}
		
		
		for (int i=0;i<3;i++)//add objects on the panel
		{
			this.add(carButton[i]);
		}
		
		this.add(startButton);
		this.add(title);
		this.add(speedLabel);
		this.add(accelerationLabel);
		this.add(handlingLabel);
		
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		startButton.addActionListener(new ActionListener()//actionListener for startButton
		{

			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int userNo=(int)(Math.random()*(9999-1000+1)+1000);
				chosenCarID=userNo;
				pw.println(userNo+" is using car"+(carNumSelect+1));
				
//				JDialog jj= new JDialog();
//				
//				jj.setLocationRelativeTo(Crowcing.outerPanel);
//				//jj.setLayout(new BorderLayout());
//				jj.setSize(500, 100);
//				jj.setModal(true);
//				JLabel label=new JLabel("Waiting for another user..");
//				label.setFont(new Font("Dialog",   1,   16));
//				
//				
//				jj.add(label);
//				jj.setVisible(true);
//				
               
               
				
				while (true)
			    {	
					System.out.println(userNo);
					pw.println(userNo+" is using car"+(carNumSelect+1));
					pw.flush();
					
					
					
					
			    	String temp;
					try {
						temp = br.readLine();
						System.out.println(temp);
				    	String delims = "[. ]";
				    	String[] tokens = temp.split(delims);
				    	if (tokens.length>=3)
				    	{
				    		if(!tokens[0].equals((userNo+"")) && tokens[1].equals("is") && tokens[2].equals("using") )
					    	{
				    			opponentCarName=tokens[3];
				    			System.out.println(opponentCarName+"   !!");
				    			int carIndex=tokens[3].charAt(3)-'1';
				    			opponentCar=car[carIndex];
				    			opponentCarID=Integer.parseInt(tokens[0]);
				    			//jj.setVisible(false);
				    			
					    		break;
					    	}
				    	}
				    	
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    	

			    	
			    }
				
				CardLayout cl = (CardLayout)Crowcing.outerPanel.getLayout();
				cl.show(Crowcing.outerPanel, "mainScreen");
				Crowcing.bomb.setVisible(true);
				MainScreenPanel.miniMapPanel.setVisible(true);
				MainScreenPanel.chatPanel.setVisible(true);
				
				chosenCar=car[carNumSelect];
				Thread t1=new Thread(MainScreenPanel.miniMapPanel);
				t1.start();
				Thread t2=new Thread(MainScreenPanel.racingPanel);
				t2.start();
				System.out.println("Start car "+(carNumSelect+1));
				
			}
			
		});
		
	}
	
	
	
}
