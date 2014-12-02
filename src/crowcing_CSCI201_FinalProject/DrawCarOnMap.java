package crowcing_CSCI201_FinalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;


import javax.swing.Icon;



public class DrawCarOnMap implements Icon {
	


	private int type;
	private int visible;
	private String carName;

	//int x=0;
	
	
	public DrawCarOnMap(String carName,int type,int visible )
	{
		
		//this.index=index;
		this.carName=carName;
		this.visible=visible;
		//this.carNum=carNum;
		this.type=type;
		
	}
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub

		 g.setColor(Color.LIGHT_GRAY);
		    g.fillRect(x, y, getIconWidth(), getIconHeight());
		    if (type!=0)
		    {
		    	g.setColor(Color.BLACK);
		    	g.fillRect(x, y, getIconWidth(),getIconHeight());
		    	
		    }
		    
		    if (visible==1)
		    {
		    	
		    	if (carName.equals("car1"))
			    {
			    	g.setColor(Color.GREEN);
			    	g.fillRect(x, y, getIconWidth(),getIconHeight());
			    }
			    else if (carName.equals("car2"))
			    {
			    	g.setColor(Color.RED);
			    	g.fillRect(x, y, getIconWidth(),getIconHeight());
			    }
			    else if (carName.equals("car3"))
			    {
			    	g.setColor(Color.BLUE);
			    	g.fillRect(x, y, getIconWidth(),getIconHeight());
			    }
		    }
		    else if (visible==0)
		    {
		    	
		    }
		    
		    
		    

//		    if (visible==1)
//		    {
//		    	Car cc=car.get(carNum);
//		    	
//		    		if (cc.color.equals("red"))
//		    		{
//		    			g.setColor(Color.RED);
//		    		}
//		    		else if (cc.color.equals("blue"))
//		    		{
//		    			g.setColor(Color.BLUE);
//		    		}
//		    		else if (cc.color.equals("yellow"))
//		    		{
//		    			g.setColor(Color.YELLOW);
//		    		}
//		    		else if (cc.color.equals("orange"))
//		    		{
//		    			g.setColor(Color.ORANGE);
//		    		}
//		    		else if (cc.color.equals("green"))
//		    		{
//		    			g.setColor(Color.GREEN);
//		    		}
//		    		g.drawOval(15, 15,30, 30);
//		    		g.fillOval(15, 15,30, 30);
//		    		g.setColor(Color.BLACK);
//		    		g.drawString(""+(carNum+1), 30, 30);
//
//		    }
//		    	else if (visible==0)
//		    	{
//		    		
//		    	}
		    
		    	
		   
		    
		   
		   // g.setColor(color);
		    //g.fillRect(x + borderWidth, y + borderWidth, getIconWidth() - 2 * borderWidth,
		       // getIconHeight() - 2 * borderWidth);

	}

}

