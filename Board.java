import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Board implements Comparable<Board>, Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SIZE = 8;
	private int[][] board = new int[SIZE][SIZE];
	private ArrayList<Queen> queens;
	private int heur;
	
	/**
	 * Creates new board with random placement of queens
	 * with the following constraint: 1 per column.
	 */
	public Board()
	{
		this.randomizeBoard();
		this.computeHeuristic();	
	}
	
	/**
	 * accepts another board object for use with the deepClone method.
	 * @param b
	 */
	public Board(Board b)
	{
		this.board 	= b.board;
		this.queens = b.queens;
		this.heur 	= b.heur;
	}
	public void moveQueen(int i)
	{
				
		int currentRow = queens.get(i).getRow();
		
		board[currentRow][i] = 0;
		
		if(currentRow >= 0 && currentRow < 7)
		{
			currentRow++;
		}
		else
		{
			currentRow = 0;
		}
		
		queens.get(i).setRow(currentRow);
		board[currentRow][i] = 1;
		
		this.computeHeuristic();
			
	}

	
	/**
	 * Just to hard code a known solution for testing.
	 */
	public void testSolution()
	{
		queens = new ArrayList<Queen>(SIZE);
		
		this.board[3][0] = 1;
		queens.add(new Queen(3, 0));
		
		this.board[5][1] = 1;
		queens.add(new Queen(5, 1));
		
		this.board[7][2] = 1;
		queens.add(new Queen(7, 2));
		
		this.board[1][3] = 1;
		queens.add(new Queen(1, 3));
		
		this.board[6][4] = 1;
		queens.add(new Queen(6, 4));
		
		this.board[0][5] = 1;
		queens.add(new Queen(0, 5));
		
		this.board[2][6] = 1;
		queens.add(new Queen(2, 6));
		
		this.board[4][7] = 1;
		queens.add(new Queen(4, 7));
	}
	public void randomizeBoard()
	{
		queens = new ArrayList<Queen>(SIZE);
		for(int i = 0; i < SIZE;i++)
		{	
			Random rand = new Random();
			int rInt 	= rand.nextInt(SIZE);
			
			this.board[rInt][i] = 1;
			queens.add(new Queen(rInt, i));
			
		}
	}

	public void computeHeuristic()
	{
		int h = 0;
		for(int i=0; i<SIZE-1; i++)
		{
			for(int j = i + 1; j < SIZE; j++)
			{
				if(queens.get(i).conflictDetected(queens.get(j)))
				{
						h++;
				}
			}
		}
		
		this.heur = h;
	}

	
	public int compareTo(Board b)
	{
		final int BEFORE = -1;
		final int EQUAL = 0;
		final int AFTER = 1;
		
		if(this.getHeuristic() == b.getHeuristic()) return EQUAL;
		else if(this.getHeuristic() < b.getHeuristic()) return BEFORE;
		else return AFTER;
		
	}
	
	public int getHeuristic()
	{
		return heur;
	}
	public ArrayList<Queen> getQueens()
	{
		return queens;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < SIZE; i++) 
		{
		    for (int j = 0; j < SIZE; j++) 
		    {
		        sb.append(board[i][j] + " ");
		    }
		    sb.append("\n");
		}
		sb.append("\n");
		//sb.append(queens);
		sb.append("h = ");
		sb.append(heur);
		sb.append("\n");
		return sb.toString();
	}
}