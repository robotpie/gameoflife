package gameOfLife;
/*
 * should this class be immutable?
 * memory vs. time vs. parallelism considerations
 * assumes square array
 * 
 */

///TODO: add in timestep field
//TODO: add method to initialize by string
//TODO: add method to compute next state use conway rules

/*
 * right now interface is tied to internal representation -> how to generalize?
 * should this be a generic class?
 * 
 */
public class GameOfLife {
private int lattice[][]; //TODO: stores an int should this be an enum, or class?
private int rows;
private int columns;
private int currentStep;

	public GameOfLife(int rows, int columns){
		currentStep = 0;
		if(rows <= 0 || columns <= 0)
		{
			throw new IllegalArgumentException("rows and columns must both be nonzero") ;
		}
		lattice = new int[rows][columns];//assume Java initializes to 0
		this.rows = rows;
		this.columns = columns;
	};
	
	public GameOfLife(int [][] initialState){
		//GameOfLife(initialState.length, initialState[0].length); - can't chain ctor calls apparently
		currentStep = 0;
		this.rows = initialState.length;
		this.columns = initialState[0].length;
		lattice = new int[rows][columns];
		
		if(rows <= 0 || columns <= 0 || rows != columns )
		{
			throw new IllegalArgumentException("rows and columns must both be nonzero and equal length. rows: "+rows+" , columns: "+columns);
		}
		for(int row = 0; row < rows; row++)
		{
			for(int column = 0; column < columns; column++)
			{
				lattice[row][column] = initialState[row][column];
			}
		}	
		
	};
	
	public int getStateAt(int row, int column)
	{
		return lattice[row][column];
	}
	
	
	/*
	 * return the size of 1st dimension (number of rows)
	 */
	public int getRows()
	{
		return rows;
	}
	/*
	 * return the size of 2nd dimension (number of columns)
	 */
	public int getColumns()
	{
		return columns;
	}
	/*
	 * since Java returns by reference here, this is leaking internals
	 * what can we do about it?
	 */
	
	public int[][] getCurrentState()
	{
		return lattice;
	}
	
	public int getCurrentStep()
	{
		return currentStep;
	}
	
	public void computeNextState(){
		int nextState [][] = new int[rows][columns];
		
		//perform "concurrent" computation of next state
		for(int row = 0; row < rows; row++)
		{
			for(int column = 0; column < columns; column++)
			{
				nextState[row][column] = applyRule(row,column,lattice);
			}
		}		
		
		//replace lattice with next state
		lattice = nextState;		
		currentStep++;
	}
	
	/*
	 * for now implement conway's game of life:
	 * 
    Any live cell with fewer than two live neighbours dies, as if caused by under-population.
    Any live cell with two or three live neighbours lives on to the next generation.
    Any live cell with more than three live neighbours dies, as if by overcrowding.
    Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	
	Also: 8-connected
	Also: circular array
	 */
	private int applyRule(int row, int column, int [][] a_lattice)
	{
		/*
		 * TODO: clever way to do this with iteration
		 * 
		 */
		int liveNeighbours = 0;
		liveNeighbours += getLeft(row, column, a_lattice);
		liveNeighbours += getRight(row, column, a_lattice);
		liveNeighbours += getUp(row, column, a_lattice);
		liveNeighbours += getDown(row, column, a_lattice);
		liveNeighbours += getUpLeft(row, column, a_lattice);
		liveNeighbours += getUpRight(row, column, a_lattice);
		liveNeighbours += getDownLeft(row, column, a_lattice);
		liveNeighbours += getDownRight(row, column, a_lattice);

		if(a_lattice[row][column] == 0) //dead
		{
			if(liveNeighbours == 3)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		else //alive
		{
			if(liveNeighbours < 2)
			{
				return 0;
			}
			else if(liveNeighbours == 2 || liveNeighbours == 3)
			{
				return 1;
			}
			else//greater than 3
				return 0;
		}
		
		
	}
	
	//TODO: validate arguments
	private int getLeft(int row, int column, int [][] a_lattice)
	{
		if(column ==0)
		{
			return a_lattice[row][a_lattice[row].length - 1];
		}
		else
		{
			return a_lattice[row][column - 1];
		}
	}
	
	private int getRight(int row, int column, int [][] a_lattice)
	{
		if(column == a_lattice[row].length - 1)
		{
			return a_lattice[row][0];
		}
		else
		{
			return a_lattice[row][column + 1]; 
		}
		
	}
	
	private int getUp(int row, int column, int [][] a_lattice)
	{
		if(row == 0)
		{
			return a_lattice[a_lattice.length - 1][column];
		}
		else
		{
			return a_lattice[row-1][column]; 
		}
		
	}
	
	private int getDown(int row, int column, int [][] a_lattice)
	{
		if(row == a_lattice.length - 1)
		{
			return a_lattice[0][column];
		}
		else
		{
			return a_lattice[row+1][column]; 
		}
		
	}
	
	private int getUpRight(int row, int column, int [][] a_lattice)
	{
		if(row == 0)
		{
		
			if(column == a_lattice[row].length - 1)
			{
				return a_lattice[a_lattice.length - 1][0];
			}
			else
			{
				return a_lattice[a_lattice.length - 1][column + 1]; 
			}
			
		}
		else
		{
			
			if(column == a_lattice[row].length - 1)
			{
				return a_lattice[row-1][0];
			}
			else
			{
				return a_lattice[row-1][column + 1]; 
			}
		}
	
	}
	private int getUpLeft(int row, int column, int [][] a_lattice)
	{
		if(row == 0)
		{
		
			if(column ==0)
			{
				
				return a_lattice[a_lattice.length - 1][a_lattice[row].length - 1];
			}
			else
			{
				return a_lattice[a_lattice.length - 1][column - 1]; 
			}
			
		}
		else
		{
			
			if(column == 0)
			{
				return a_lattice[row-1][a_lattice[row].length - 1];
			}
			else
			{
				return a_lattice[row-1][column - 1]; 
			}
		}
	
	}
	private int getDownRight(int row, int column, int [][] a_lattice)
	{
		if(row == a_lattice.length - 1)
		{
		
			if(column == a_lattice[row].length - 1)
			{
				return a_lattice[0][0];
			}
			else
			{
				return a_lattice[0][column + 1]; 
			}
			
		}
		else
		{
			
			if(column == a_lattice[row].length - 1)
			{
				return a_lattice[row+1][0];
			}
			else
			{
				return a_lattice[row+1][column + 1]; 
			}
		}
	
	}
	private int getDownLeft(int row, int column, int [][] a_lattice)
	{
		if(row == a_lattice.length - 1)
		{
		
			if(column == 0)
			{
				return a_lattice[0][a_lattice[row].length - 1];
			}
			else
			{
				return a_lattice[0][column - 1]; 
			}
			
		}
		else
		{
			
			if(column == 0)
			{
				return a_lattice[row+1][a_lattice[row].length - 1];
			}
			else
			{
				return a_lattice[row+1][column - 1]; 
			}
		}
	
	}
	public String toString()
	{
		String result = "";
		for(int row = 0; row < rows; row++)
		{
			for(int column = 0; column < columns; column++)
			{
				result += getStateAt(row, column);
			}
			result += "\n";
		}
		return result;
	}
	
	public void toggleState(int row, int column)
	{
		if(lattice[row][column] == 0)
		{
			lattice[row][column] =1;
		}
		else
		{
			lattice[row][column] =0;

		}
	}
}
