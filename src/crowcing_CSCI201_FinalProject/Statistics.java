package crowcing_CSCI201_FinalProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

//Calculate and store lap time, ranking
public class Statistics extends Thread{
	private CarThread self, rival;
	private int minute = 0, second = 0, milisecond = 0;
	private String name = "", rank = "";
	private Map map;

	public Statistics(CarThread self, CarThread rival, Map map){
		this.self = self;
		this.rival = rival;
		this.name = LoginPanel.userName;
		this.map=map;
	}
	//Draw
	public void drawStatistics(Graphics g){
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier", Font.BOLD, 20));
		g.drawString("Player: " + name, 10, 30);
		g.drawString("Rank: " + rank, 10, 50);
		g.drawString("Time: " + minute + ":" + second + ":" + milisecond, 10, 70);
		g.drawString("Speed: " + self.getCurrentSpeed(), 10, 90);
		
		int percent=(int)((self.getTotalDistanceTraveled()*100/map.distancePerLap()));
		g.drawString("Match Finished: "+percent+"%", 10, 110);
//		g.drawString("Your Position: " + self.getTotalDistanceTraveled(), 10, 90);

//		g.drawString("Rival's Position: " + rival.getTotalDistanceTraveled(), 10, 110);
	}
	
	//Calculate
	public void run(){
		while(true){
//			System.out.println("sdf");
			if (self.getTotalDistanceTraveled() > rival.getTotalDistanceTraveled()){
				rank = "1";
			}
			else if (self.getTotalDistanceTraveled() < rival.getTotalDistanceTraveled()){
				rank = "2";
			}
			else rank = "draw";
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (milisecond == 99){
				if (second == 59){
					minute++;
					second = 0;
				}
				else second++;
				milisecond = 0;
			}
			else milisecond++;
		}
	
	}
	
	public String getLapTime(){
		String lapTime = minute + ":" + second + ":" + milisecond;
		return lapTime;
	}
	
	public String getRank(){
		return rank;
	}
}
