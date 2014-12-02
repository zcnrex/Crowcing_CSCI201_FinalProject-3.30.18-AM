package crowcing_CSCI201_FinalProject;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


import javax.swing.JLabel;



//Stores information of one map.
//Does not draw map here
public class Map {
	
	private int map[][];
	private int pred[][];//used for get indexOfPosition, 1==there is a road but not visited, 0=no road, -1==there is a road and visited
	private JLabel label[][];
	private Vector<Vector<Integer>> indexOfPosition=new Vector<Vector<Integer>>();//return the coordinates of map by given traveled distance, 
	//E.g. indexOfPosition.get(0)=coordinates of start point of map, indexOfPosition.get(0).get(0)=y of start point, indexOfPosition.get(0).get(1)=x of start point
	
	public Map(String fileName)
	{
		
		
		map=new int [50][]; //initialize the size of 2D array for original data
		pred=new int [50][]; 
		for (int i=0;i<50;i++)
		{
			map[i]=new int [50];
			pred[i]=new int [50];
		}
		
		label=new JLabel [50][]; //initialize the size of 2D array for JLabel
		for (int i=0;i<50;i++)
		{
			label[i]=new JLabel [50];
		}
		
		for (int i=0;i<50;i++)
		{
			for (int j=0;j<50;j++)
			{
				
				label[i][j]=new JLabel();
				
			}
			//System.out.println(i);
		}
		
		try// read through 
		{
			FileReader fr=new FileReader(fileName);
			//BufferedReader br=new BufferedReader(fr);
			Scanner scan=new Scanner(fr);
			
			for (int i=0;i<50;i++)
			{
				for (int j=0;j<50;j++)
				{
					int tempValue=scan.nextInt();
					map[i][j]=tempValue;
					
					if (tempValue==0)//set initial value of pred
					{
						pred[i][j]=0;
					}
					else 
					{
						pred[i][j]=1;
					}
				}
			}
		}
		catch (IOException ioe) {
			System.out.println("IOException: " + ioe.getMessage());
		}
		
		int y=4, x=39; //start point of map.txt, hardcode!!!!!
		pred[y][x]=-1;
		Vector<Integer> temp=new Vector<Integer>();
		temp.add(y);
		temp.add(x);
		indexOfPosition.add(temp);
		
		while (pred[y+1][x]==1 || pred[y-1][x]==1 || pred[y][x+1]==1 || pred[y][x-1]==1 || pred[y-1][x-1]==1 || pred[y+1][x-1]==1 || pred[y-1][x+1]==1 || pred[y+1][x+1]==1)
			//list the coordinates in the sequence of race
		{
			
			if (pred[y-1][x]==1)
			{
				y--;
			}
			else if (pred[y+1][x]==1)
			{
				y++;
			}
			else if (pred[y][x-1]==1)
			{
				x--;
			}
			else if (pred[y][x+1]==1)
			{
				x++;
			}
			
			else if (pred[y-1][x-1]==1)
			{
				y--;
				x--;
			}
			else if (pred[y+1][x-1]==1)
			{
				y++;
				x--;
			}
			else if (pred[y+1][x+1]==1)
			{
				y++;
				x++;
			}
			else if (pred[y-1][x+1]==1)
			{
				y--;
				x++;
			}
			
			pred[y][x]=-1;
			Vector<Integer> temp1=new Vector<Integer>();
			temp1.add(y);
			temp1.add(x);
			indexOfPosition.add(temp1);
		}
		
		/*System.out.println(indexOfPosition.size()+"!!!");
		for (int i=0;i<indexOfPosition.size();i++)
		{
			System.out.println(indexOfPosition.get(i).get(0)+"  "+indexOfPosition.get(i).get(1));
		}*/
		
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int map[][]) {
		this.map = map;
	}

	public JLabel[][] getLabel() {
		return label;
	}

	public void setLabel(JLabel label[][]) {
		this.label = label;
	}
	
	public Vector<Vector<Integer>> getIndexOfPosition() {
		return indexOfPosition;
	}

	public void setIndexOfPosition(Vector<Vector<Integer>> indexOfPosition) {
		this.indexOfPosition = indexOfPosition;
	}
	
	public int distancePerLap()
	{
		return indexOfPosition.size();
	}
	
	
}




