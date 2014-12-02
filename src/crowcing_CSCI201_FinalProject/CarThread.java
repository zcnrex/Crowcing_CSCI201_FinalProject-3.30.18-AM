package crowcing_CSCI201_FinalProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.Timer;

// send action only
// handling: get arbitrary function first
// get index

//Updates position of a car, 
//consider speed, acceleration, handling, NO2, bombing
public class CarThread extends Thread
{
	/*private ImageIcon carImg;
	private int topSpeed, acceleration, handling, bombValue;
	private String carName, bombName;
*/
	private double currentTopSpeed; 
	private double currentAcceleration;
	private double currentHandling;
	private double currentBottomSpeed;
	private int index;

	private int totalDistanceTraveled = 0;
	private int positionx = 0;
	private int positiony = 0;
	private Map map;
	private int mapArray[][];
	private Vector<Vector<Integer>> indexOfPosition;
	private double currentSpeed = 0;
	private String carName;
	
	private PrintWriter pw;
	private BufferedReader br;
	
	public CarThread(Car carInfo, int index)
	{
		this.currentAcceleration = carInfo.getAcceleration();
		this.currentTopSpeed = carInfo.getTopSpeed()*4;
		this.currentHandling = carInfo.getHandling();
		this.currentBottomSpeed = currentHandling*2;
		this.index = index;
		this.carName=carInfo.getCarName();
		
		Socket s;
		try {
			s = new Socket("localhost", 2232);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		map = new Map("map.txt");
		mapArray = map.getMap();
		indexOfPosition = map.getIndexOfPosition();
	}

	@Override
	public void run() {
		while(totalDistanceTraveled<=indexOfPosition.size()){
			
			totalDistanceTraveled++;
			if(totalDistanceTraveled>=indexOfPosition.size()){
				//finish round
				break;
			}
			positionx = map.getIndexOfPosition().get(totalDistanceTraveled).get(1);
			positiony = map.getIndexOfPosition().get(totalDistanceTraveled).get(0);
			
			if(currentSpeed < currentBottomSpeed){
				currentSpeed+=2;
			} else if((getRoadType()%2) == (getPrevRoadType()%2)){ //on straight line
				if(currentSpeed < currentTopSpeed){
					currentSpeed+=2;
				}
			} else {
				currentSpeed = currentBottomSpeed;
			}
			
			
			//For test
			//System.out.println(" Distance:" + totalDistanceTraveled + " x:" + positionx + " y:" + positiony);
			//System.out.println(" Speed:" + currentSpeed);
			
			try {
				Thread.sleep((int)(5000/currentSpeed));
			} catch (InterruptedException e) {
				
			}
		}
		
		
		
				
		/*while (true)
		{
			if get message from server, then start
			break;
		}*/
		
		
		
	}
	
	public void reduceCurrentTopSpeed(double speedReduced,int time)
	{
		currentTopSpeed-=speedReduced;
	}
	
	public void reduceCurrentAcceleration(double accelerationReduced,int time)
	{
		currentAcceleration-=accelerationReduced;
	}
	
	public void reduceCurrentHandling(double handlingReduced,int time)
	{
		currentHandling-=handlingReduced;
	}
	
	public int getTotalDistanceTraveled(){
		return totalDistanceTraveled;
	}
	
	public int getPositionX(){
		return positionx;
	}
	
	public int getPositionY(){
		return positiony;
	}
	
	public int getRoadType(){
		int position=totalDistanceTraveled%indexOfPosition.size();
		int type=mapArray[indexOfPosition.get(position).get(0)][indexOfPosition.get(position).get(1)];
		return type;
	}
	
	public int getPrevRoadType(){
		int position=(totalDistanceTraveled-1)%indexOfPosition.size();
		int type=mapArray[indexOfPosition.get(position).get(0)][indexOfPosition.get(position).get(1)];
		return type;
	}
	
	public double getCurrentSpeed()
	{
		return  currentSpeed;
	}

	public String getCarName() {
		return carName;
	}

	public void bombed(){
		currentSpeed-=5;
	}
	
	
}
