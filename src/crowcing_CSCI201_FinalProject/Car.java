package crowcing_CSCI201_FinalProject;

import java.util.Random;

import javax.swing.ImageIcon;

//Image, specifications, skills
public class Car {
	private ImageIcon carImg;
	private int topSpeed, acceleration, handling, bombValue;
	private String carName, bombName;
	private Random rand;
	
	public Car(){
		rand = new Random();
	}
	
	public Car(String carName){
		this.setCarName(carName);
		this.carImg = new ImageIcon(carName + ".jpg");
		this.rand = new Random();
	}
	
	public Car(String carName, int topSpeed, int acceleration, int handling){
		this.setCarName(carName);
		this.carImg = new ImageIcon(carName + ".jpg");
		this.topSpeed = topSpeed;
		this.acceleration = acceleration;
		this.handling = handling;
		this.rand = new Random();
	}
	
	public void setTopSpeed(int n){
		this.topSpeed = n;
	}
	
	public void setAcceleration(int n){
		this.acceleration = n;
	}
	
	public void setHandling(int n){
		this.handling = n;
	}
	
	public int getTopSpeed(){
		return this.topSpeed;
	}
	
	public int getAcceleration(){
		return this.acceleration;
	}
	
	public int getHandling(){
		return this.handling;
	}
	
	public int getBombValue(){
		return rand.nextInt(5);
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}
}
