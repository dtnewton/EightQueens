import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.PriorityQueue;

public class EightQueens 
{	
	private static final int SIZE = 8;
	
	public static void main(String[] args)
	{
		PriorityQueue<Board> neighborStates = null;
		Board b 			  = new Board();
		boolean solutionFound = false;
		int statesLower = 0,restartCount = 0,stateChanges = 0;
		
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
				Board currentState  = new Board((Board)copy(b));
	
				for(int i = 0; i < SIZE;i++)//each col
				{		
					for(int j = 0; j < SIZE; j++)//each row
					{				
						currentState.moveQueen(i);
						neighborStates.add((Board)copy(currentState));					
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
					b = (Board)copy(neighborStates.peek());
					stateChanges++;
				}
				else if(b.compareTo(neighborStates.peek()) > 0)
				{
					System.out.println("Current State:");
					System.out.println("---------------------------------");
					System.out.println(b);
					System.out.println("Neighbors found with lower h: " + statesLower +"\n");
					b = (Board)copy(neighborStates.peek());
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
	
	public static Board copy(Board o) 
	{  
		try 
		{
		    
			ByteArrayOutputStream baos  = new ByteArrayOutputStream();
		    ObjectOutputStream objOut 	= new ObjectOutputStream(baos);
		     
		    objOut.writeObject(o);
		    objOut.flush();
		    objOut.close();
		     
		    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		    ObjectInputStream objIn   = new ObjectInputStream(bais);
		     
		     
		    return (Board) objIn.readObject();
		}
		catch (Exception e) 
		{
		    e.printStackTrace();
		    return null;
		}
	}		
}	