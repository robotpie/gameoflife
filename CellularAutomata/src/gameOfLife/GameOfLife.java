package gameOfLife;
/*
 * should this class be immutable?
 * memory vs. time vs. parallelism considerations
 * 
 */

///TODO: add in timestep field
//TODO: add method to initialize by string
//TODO: add method to compute next state use conway rules

public class GameOfLife {
private int lattice[][];
private int rows;
private int columns;
	public GameOfLife(int rows, int columns){
		if(rows <= 0 || columns <= 0)
		{
			throw new IllegalArgumentException("rows and columns must both be nonzero") ;
		}
		lattice = new int[rows][columns];//assume Java initializes to 0
		this.rows = rows;
		this.columns = columns;
	};
	
	public int getState(int row, int column)
	{
		return lattice[row][column];
	}
	
	public String toString()
	{
		String result = "";
		for(int row = 0; row < rows; row++)
		{
			for(int column = 0; column < columns; column++)
			{
				result += getState(row, column);
			}
			result += "\n";
		}
		return result;
	}
}
