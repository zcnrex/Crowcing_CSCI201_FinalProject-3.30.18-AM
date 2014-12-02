package crowcing_CSCI201_FinalProject;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Display result of a player
public class ResultPanel extends JPanel{
	private JButton JBrematch;
	private JButton JBexit;
	private JLabel JLfinalRank;
	private JLabel JLbestLap;
	private JLabel JLtotalLap;
	private JLabel backgroundLabel;
	private String rank;
	private String lapTime;
	private JOptionPane newRecord;
	private ImageIcon backgroundImage= new ImageIcon("image/RedBull.jpg");
	
//	public ResultPanel(int finalRank, int bestLap, int totalLap){
		public ResultPanel(){
		setSize(800,600);
		setLayout(null);
//		backgroundLabel = new JLabel();
//		backgroundLabel.setIcon(backgroundImage);
//		backgroundLabel.setBounds(0, 0, 800, 600);
//		backgroundLabel.setOpaque(true);
//		add(backgroundLabel);
		repaint();
		
//		JLfinalRank = new JLabel("Final Rank: " + rank);
//		JLfinalRank.setForeground(Color.WHITE);
//		JLfinalRank.setBackground(Color.BLACK);
//		JLfinalRank.setOpaque(true);
//		JLfinalRank.setFont(new Font("Dialog", 1, 24));
//		JLfinalRank.setBounds(300, 120, 200, 30);
//		add(JLfinalRank);
//		
//		JLbestLap = new JLabel("Best Lap Time: " + lapTime);
//		JLbestLap.setForeground(Color.WHITE);
//		JLbestLap.setBackground(Color.BLACK);
//		JLbestLap.setOpaque(true);
//		JLbestLap.setFont(new Font("Dialog", 1, 24));
//		JLbestLap.setBounds(300, 180, 250, 30);
//		add(JLbestLap);
//		
//		JLtotalLap = new JLabel("Total Lap Time: " + String.valueOf(1));
//		JLtotalLap.setForeground(Color.WHITE);
//		JLtotalLap.setBackground(Color.BLACK);
//		JLtotalLap.setOpaque(true);
//		JLtotalLap.setFont(new Font("Dialog", 1, 24));
//		JLtotalLap.setBounds(300, 240, 250, 30);
//		add(JLtotalLap);
		
		JBrematch = new JButton("Rematch");
		JBrematch.setFont(new Font("Dialog", 1, 24));
		JBrematch.setBounds(330, 380, 140, 50);
		add(JBrematch);
		
		JBexit = new JButton("Exit");
		JBexit.setFont(new Font("Dialog", 1, 24));
		JBexit.setBounds(330, 480, 140, 50);
		add(JBexit);
		
		JBrematch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CardLayout cl = (CardLayout)Crowcing.outerPanel.getLayout();
				
				cl.show(Crowcing.outerPanel, "chooseCar");
				//call carChoosingPanel
			}
		});
		JBexit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
	}
	
	public void paintComponent(Graphics g){
//		JLfinalRank.setForeground(Color.WHITE);
//		JLfinalRank.setBackground(Color.BLACK);
//		JLfinalRank.setOpaque(true);
//		JLfinalRank.setFont(new Font("Dialog", 1, 24));
		g.drawImage(backgroundImage.getImage(), 0, 0, this);
		g.setColor(Color.BLACK);
		g.drawRect(300, 120, 200, 30);
		g.fillRect(300, 120, 200, 30);
		g.drawRect(300, 180, 300, 30);
		g.fillRect(300, 180, 300, 30);
		g.setFont(new Font("Dialog", 1, 24));
		g.setColor(Color.WHITE);
		g.drawString("Final Rank: " + rank, 300, 140);
		g.drawString("Best Lap Time: " + lapTime, 300, 200);
	}
	
	public void setFinalRank(String rank){
		this.rank = rank;
	}
	
	public void setLapTime(String lapTime){
		this.lapTime = lapTime;
	}
}
