package crowcing_CSCI201_FinalProject;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Test {

	public static void main(String []args)
	{
		JFrame jf=new JFrame();
//<<<<<<< HEAD
//
		jf.setSize(800,600);
////		CarChoosingPanel choosePanel=new CarChoosingPanel();
		RacingPanel racingpanel = new RacingPanel();
		Thread t = new Thread(racingpanel);
		t.start();
		jf.add(racingpanel);
//		
////		jf.setSize(100,100);
////		MiniMapPanel miniMap=new MiniMapPanel();
////		miniMap.setPreferredSize(new Dimension(200, 200));  
////		jf.add(miniMap);
//=======
////
////		//jf.setSize(800,600);
////		//CarChoosingPanel choosePanel=new CarChoosingPanel();
////		//jf.add(choosePanel);
////		
//		jf.setSize(100,100);
//		MiniMapPanel miniMap=new MiniMapPanel();
//		miniMap.setPreferredSize(new Dimension(200, 200));
//		Thread t=new Thread(miniMap);
//		t.start();
//		jf.add(miniMap);
//>>>>>>> 7c3b28779b4348d4bb422af12cb0513903a78da9
////
////		//jf.setSize(800,600);
////		//LoginPanel lp = new LoginPanel();
////		//jf.add(lp);
//////		while(true){
//////			if (lp.getDone()) {
//////				jf.remove(lp);
//////				CarChoosingPanel choosePanel=new CarChoosingPanel();
//////				jf.add(choosePanel);
//////				break;
//////			}
//////		}
////		
////
//<<<<<<< HEAD
		jf.setVisible(true);
//=======
//	jf.setVisible(true);
//>>>>>>> 7c3b28779b4348d4bb422af12cb0513903a78da9
////		Car car1 = new Car("car1", 7, 8, 9);
////		CarThread ct = new CarThread(car1, 1);
////		ct.run();
	}
}
