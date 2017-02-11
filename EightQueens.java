import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.PriorityQueue;

public class EightQueens 
{	
	private static final int SIZE = 8;
	//private static ArrayList<Board> pqBoardState;
	public static void main(String[] args)
	{
		PriorityQueue<Board> neighborStates = null;
		Board b;
		boolean solutionFound = false;
		int statesLower = 0,restartCount = 0,stateChanges = 0;
		b = new Board();
		//System.out.println(b);
		
		do
		{
			statesLower = 0;
			if(b.getHeuristic() == 0)
			{
				System.out.println("Solution Reached!");
				System.out.println("---------------------------------");
				System.out.println(b);
				System.out.println("State Changes: " + stateChanges);
				System.out.println("Random Restarts: " + restartCount);
				
				solutionFound = true;
				break;
			}
			else
			{
		
				neighborStates 	    = new PriorityQueue<Board>();
				Board currentState  = new Board((Board)deepClone(b));
				
			
				
				for(int i = 0; i < SIZE;i++)//each col
				{
				
					for(int j = 0; j < SIZE; j++)//each row
					{				
						currentState.moveQueen(i);
						neighborStates.add((Board)deepClone(currentState));
									
					}
					
					
				}
				
				for(Board brd : neighborStates)
				{
					if(b.getHeuristic() > brd.getHeuristic()) statesLower++;
				}
				
				
				if (neighborStates.peek().getHeuristic() == 0)
				{
					System.out.println("Current State:");
					System.out.println("---------------------------------");
					System.out.println(b);
					System.out.println("Neighbors found with lower h: " + statesLower +"\n");
					b = (Board)deepClone(neighborStates.peek());
					stateChanges++;
				}
				else if(b.compareTo(neighborStates.peek()) > 0)
				{
					System.out.println("Current State:");
					System.out.println("---------------------------------");
					System.out.println(b);
					System.out.println("Neighbors found with lower h: " + statesLower +"\n");
					b = (Board)deepClone(neighborStates.peek());
					stateChanges++;
				}
				else
				{	
					System.out.println("Current State:");
					System.out.println("---------------------------------");
					System.out.println(b);
					System.out.println("Neighbors found with lower h: " + statesLower +"\n");
					System.out.println("Random Restart!");
					b = new Board();
					restartCount++;
					
				}
			
			}
						
		}while(!solutionFound);
		
			
	}
	
	 public static Object deepClone(Object o) 
	 {  
		 try 
		 {
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     ObjectOutputStream oos 	= new ObjectOutputStream(baos);
		     
		     oos.writeObject(o);
		     
		     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		     ObjectInputStream ois 	   = new ObjectInputStream(bais);
		     
		     return ois.readObject();
		 }
		 catch (Exception e) 
		 {
		     e.printStackTrace();
		     return null;
		 }
	 }		
}	