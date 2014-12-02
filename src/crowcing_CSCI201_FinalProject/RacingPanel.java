package crowcing_CSCI201_FinalProject;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Polygon;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//Display the move of cars and statistics
//One car or two?
//Constantly changing background to indicate moving
public class RacingPanel extends JPanel implements Runnable{
	private CarThread carThread;
//	private Car car1 = new Car("car"+(1+""),5,8,10);
//	private Car car2 = new Car("car"+(2+""),7,8,9);
	private CarThread carThread2;// = new CarThread(car2, 1);
	private BombThread bombThread;
	
	private Map map = new Map("map.txt");
	private int[][] mapPosition = new int[50][50];
	private int[] position = new int[2], prevPosition = new int[2], position2 = new int[2];
	private int type = 0, odd = 0, type1 = 0, type2 = 0;
	private final int len = 200;
	private Polygon poly = new Polygon();
	private Statistics s;// = new Statistics(carThread, carThread2);
	private int chosenID, opponentID;

	
	public RacingPanel( ){
		this.setSize(600,600);
		//this.setLayout(new GridLayout(50,50));
		this.setVisible(true);
		int[][] tempMap = new int[50][50];
		tempMap = map.getMap();
		for (int i = 0; i < 50; i++){
			for (int j = 0; j < 50; j++){
				mapPosition[i][j] = tempMap[j][i];
			}
		}
	}

	public void paintComponent(Graphics g){
		position[0] = carThread.getPositionX();
		position[1] = carThread.getPositionY();
		
		position2[0] = carThread2.getPositionX();
		position2[1] = carThread2.getPositionY();

		type1 = mapPosition[position[0]][position[1]];
		String carName=carThread.getCarName();
		ImageIcon carImg = new ImageIcon("car/"+carName+"-" + type1 + ".png");
		
		type2 = mapPosition[position2[0]][position2[1]];		
		String carName2=carThread2.getCarName();
		ImageIcon carImg2 = new ImageIcon("car/"+carName2+"-" + type2 + ".png");
		int size = (int) (len/2*1.5);
		
				
		int x1, y1, x, y, pos0, pos1;
		if (chosenID > opponentID){
			x = position2[0] - position[0] + 1;
			y = position2[1] - position[1] + 1;
			x1 = 1;
			y1 = 1;
			pos0 = position[0];
			pos1 = position[1];
			type = type1;
		}
		else{
			x = 1;
			y = 1;
			x1 = position[0] - position2[0] + 1;
			y1 = position[1] - position2[1] + 1;
			pos0 = position2[0];
			pos1 = position2[1];
			type = type2;
		}
		
		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				type = mapPosition[pos0+i][pos1+j];
//				System.out.println("X " + position[0] + " Y " + pos1);
//				System.out.println("YO" + type);
				if(type == 0){
					if(pos0 > 1 && pos0 < 49 && pos1 > 1 && pos1 < 49){
						
						//draw this shape
						//*000
						//**00
						//***0						
						if(mapPosition[pos0+i][pos1+j-1] == 8 || mapPosition[pos0+i][pos1+j-1] == 4 ||
								mapPosition[pos0+i+1][pos1+j] == 8 || mapPosition[pos0+i+1][pos1+j] == 4){
							
							poly = new Polygon();
							poly.addPoint((i+1)*len, (j+1)*len);
							poly.addPoint((i+2)*len, (j+1)*len);
							poly.addPoint((i+2)*len, (j+2)*len);							
					        g.setColor(Color.YELLOW);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
					        
					        poly = new Polygon();
							poly.addPoint((i+1)*len, (j+1)*len);
							poly.addPoint((i+1)*len, (j+2)*len);
							poly.addPoint((i+2)*len, (j+2)*len);							
					        g.setColor(Color.RED);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
							continue;
						}
						
						//draw
						//0***
						//00**
						//000*
						else if(mapPosition[pos0+i][pos1+j+1] == 8 || mapPosition[pos0+i][pos1+j+1] == 4 ||
								mapPosition[pos0+i-1][pos1+j] == 8 || mapPosition[pos0+i-1][pos1+j] == 4){
							
							poly = new Polygon();
							poly.addPoint((i+1)*len, (j+1)*len);
							poly.addPoint((i+2)*len, (j+1)*len);
							poly.addPoint((i+2)*len, (j+2)*len);							
					        g.setColor(Color.RED);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
					        
					        poly = new Polygon();
							poly.addPoint((i+1)*len, (j+1)*len);
							poly.addPoint((i+1)*len, (j+2)*len);
							poly.addPoint((i+2)*len, (j+2)*len);							
					        g.setColor(Color.YELLOW);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
							continue;
						}
						
						else if(mapPosition[pos0+i][pos1+j+1] == 6 || mapPosition[pos0+i][pos1+j+1] == 2 ||
								mapPosition[pos0+i+1][pos1+j] == 6 || mapPosition[pos0+i+1][pos1+j] == 2){
							
							poly = new Polygon();
							poly.addPoint((i+1)*len, (j+2)*len);
							poly.addPoint((i+2)*len, (j+1)*len);
							poly.addPoint((i+2)*len, (j+2)*len);							
					        g.setColor(Color.YELLOW);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
					        
					        poly = new Polygon();
							poly.addPoint((i+1)*len, (j+2)*len);
							poly.addPoint((i+2)*len, (j+1)*len);
							poly.addPoint((i+1)*len, (j+1)*len);							
					        g.setColor(Color.RED);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
							continue;
						}
						else if(mapPosition[pos0+i][pos1+j-1] == 6 || mapPosition[pos0+i][pos1+j-1] == 2 ||
								mapPosition[pos0+i-1][pos1+j] == 6 || mapPosition[pos0+i-1][pos1+j] == 2){
							
							poly = new Polygon();
							poly.addPoint((i+1)*len, (j+2)*len);
							poly.addPoint((i+2)*len, (j+1)*len);
							poly.addPoint((i+2)*len, (j+2)*len);							
					        g.setColor(Color.RED);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
					        
					        poly = new Polygon();
							poly.addPoint((i+1)*len, (j+2)*len);
							poly.addPoint((i+2)*len, (j+1)*len);
							poly.addPoint((i+1)*len, (j+1)*len);							
					        g.setColor(Color.YELLOW);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
							continue;
						}
					}
					g.setColor(Color.RED);
					g.drawRect((i+1)*len, (j+1)*len, len, len);
					g.fillRect((i+1)*len, (j+1)*len, len, len);
				}
				else {
					g.setColor(Color.YELLOW);
					g.drawRect((i+1)*len, (j+1)*len, len, len);
					g.fillRect((i+1)*len, (j+1)*len, len, len);
					if(type == 1 || type == 5){
						if ((pos0 + i + pos1 + j) % 2 == 0){
							g.setColor(Color.WHITE);
							poly = new Polygon();
							poly.addPoint((i+1)*len + 20, (j+1)*len + len/2 - 10);
							poly.addPoint((i+1)*len + 20, (j+1)*len + len/2 + 10);
							poly.addPoint((i+2)*len - 20, (j+1)*len + len/2 + 10);
							poly.addPoint((i+2)*len - 20, (j+1)*len + len/2 - 10);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
						}
					}
					else if(type == 3 || type == 7){
						if ((pos0 + i + pos1 + j) % 2 == 0){
							g.setColor(Color.WHITE);
							poly = new Polygon();
							poly.addPoint((i+1)*len + len/2 - 10, (j+1)*len + 20);
							poly.addPoint((i+1)*len + len/2 + 10, (j+1)*len + 20);
							poly.addPoint((i+1)*len + len/2 + 10, (j+2)*len - 20);
							poly.addPoint((i+1)*len + len/2 - 10, (j+2)*len - 20);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
						}
					}
					else if(type == 2 || type == 6){
						if ((pos0 + i) % 2 == 0){
							g.setColor(Color.WHITE);
							poly = new Polygon();
							poly.addPoint((i+2)*len - 60, (j+1)*len + 40);
							poly.addPoint((i+2)*len - 40, (j+1)*len + 60);
							poly.addPoint((i+1)*len + 60, (j+2)*len - 40);
							poly.addPoint((i+1)*len + 40, (j+2)*len - 60);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
						}
					}
					else if(type == 4 || type == 8){
						if ((pos0 + i) % 2 == 0){
							g.setColor(Color.WHITE);
							poly = new Polygon();
							poly.addPoint((i+1)*len + 40, (j+1)*len + 60);
							poly.addPoint((i+1)*len + 60, (j+1)*len + 40);
							poly.addPoint((i+2)*len - 40, (j+2)*len - 60);
							poly.addPoint((i+2)*len - 60, (j+2)*len - 40);
					        g.drawPolygon(poly);
					        g.fillPolygon(poly);
						}
					}
				}
					
				if (odd == 0) odd = 1;
				else odd = 0;
				
			}
		}

		switch (type1){
			case 1: 
				g.drawImage(carImg.getImage(), x1*len, y1*(len-len/8), size, size, null);
				break;
			case 2:
				g.drawImage(carImg.getImage(), x1*len, y1*(len - len/4), size, size, null);
				break;
			case 3:
				g.drawImage(carImg.getImage(), x1*(len-len/8), y1*len, size, size, null);
				break;
			case 4:
				g.drawImage(carImg.getImage(), x1*len, y1*(len + len/4*3 - len/8), size, size, null);
				break;
			case 5:
				g.drawImage(carImg.getImage(), x1*len, y1*(len*3/2-len/8), size, size, null);
				break;
			case 6: 
				g.drawImage(carImg.getImage(), x1*len*3/2, y1*len*3/2, size, size, null);
				break;
			case 7: 
				g.drawImage(carImg.getImage(), x1*(len*3/2-len/8), y1*len, size, size, null);
				break;
			case 8: 
				g.drawImage(carImg.getImage(), x1*len*3/2, y1*len, size, size, null);
				break;
		}
		
		switch (type2){
		case 1: 
			g.drawImage(carImg2.getImage(), x*len, y*(len*3/2-len/8), size, size, null);
			break;
		case 2:
			g.drawImage(carImg2.getImage(), x*len*3/2, y*(len*3/2-len/8), size, size, null);
			break;
		case 3:
			g.drawImage(carImg2.getImage(), x*(len*3/2-len/8), y*len, size, size, null);
			break;
		case 4:
			g.drawImage(carImg2.getImage(), x*len*3/2, y*len, size, size, null);
			break;
		case 5:
			g.drawImage(carImg2.getImage(), x*len, y*(len-len/8), size, size, null);
			break;
		case 6: 
			g.drawImage(carImg2.getImage(), x*len, y*(len - len/4), size, size, null);
			break;
		case 7: 
			g.drawImage(carImg2.getImage(), x*(len-len/8), y*len, size, size, null);
			break;
		case 8: 
			g.drawImage(carImg2.getImage(), x*len, y*(len + len/2), size, size, null);
			break;
		}

		s.drawStatistics(g);
		
		}	
	
	public void run(){
		chosenID = CarChoosingPanel.chosenCarID;
		opponentID = CarChoosingPanel.opponentCarID;
//		chosenID = 2;
//		opponentID = 1;
		if (chosenID > opponentID){
			carThread = new CarThread(CarChoosingPanel.chosenCar, 1);
//			carThread = new CarThread(car1, 1);
			carThread.start();
			
			carThread2 = new CarThread(CarChoosingPanel.opponentCar, 2);
//			carThread2 = new CarThread(car2, 2);
			carThread2.start();
			s = new Statistics(carThread, carThread2,map);
		}
		else
		{
			carThread = new CarThread(CarChoosingPanel.opponentCar, 1);
//			carThread = new CarThread(car1, 1);
			carThread.start();
			
			carThread2 = new CarThread(CarChoosingPanel.chosenCar, 2);
//			carThread2 = new CarThread(car2, 2);
			carThread2.start();
			s = new Statistics(carThread2, carThread,map);
		}
		

		bombThread = new BombThread();
		bombThread.start();
		
		s.start();
		while(true){
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(carThread.getTotalDistanceTraveled()>=map.getIndexOfPosition().size() || 
					carThread2.getTotalDistanceTraveled()>=map.getIndexOfPosition().size() ){
				Crowcing.resultPanel.setFinalRank(s.getRank());
			}
			if ((chosenID > opponentID && carThread.getTotalDistanceTraveled() >= (map.getIndexOfPosition().size()-1)) ||
					(chosenID < opponentID && carThread2.getTotalDistanceTraveled() >= (map.getIndexOfPosition().size()-1))){
				System.out.println("Finsh");
				MainScreenPanel.chatPanel.setVisible(false);
				MainScreenPanel.miniMapPanel.setVisible(false);

				Crowcing.bomb.setVisible(false);

				Crowcing.resultPanel.setLapTime(s.getLapTime());
//				System.out.println("Rank: " + s.getRank());
				Crowcing.resultPanel.repaint();
//				Crowcing.resultPanel
				CardLayout cl = (CardLayout)Crowcing.outerPanel.getLayout();
				cl.show(Crowcing.outerPanel, "result");
				break;
			}
				//finish round
				
			
		}
	}
	

}
