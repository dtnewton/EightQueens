import java.io.Serializable;

public class Queen implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int row;
	private int col;
	
	public Queen(int r, int c)
	{
		this.row = r;
		this.col = c;	
	}
	public int getCol(){
		return col;
	}
	public int getRow(){
		return row;
	}
	public boolean conflictDetected(Queen q)
	{
		boolean conflict = false;
		
		
		if(row == q.getRow() || col == q.getCol())
			conflict = true;
		//test diagonal
		else if(Math.abs(col - q.getCol()) == Math.abs(row - q.getRow()))
			conflict = true;
			
		return conflict;
	}
	public String toString()
	{
		return "[" + row + "][" + col +"]";
	}
	public void setRow(int j) {
		this.row = j;
		
	}
}