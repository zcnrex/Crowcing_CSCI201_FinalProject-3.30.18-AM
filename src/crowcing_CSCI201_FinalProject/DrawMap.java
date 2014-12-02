package crowcing_CSCI201_FinalProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;



public class DrawMap implements Icon {

	//private int degree;
	//private String type; 
	private int type;
	
	public DrawMap(int type)
	{
		//this.degree=t.degree;
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
		

	}

}