/*
 * This file is part of CellularAutomata - a virtual laboratory for experiementing with CA's
 * Copyright (C) Lassonde School of Engineering 2014
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */


package gameOfLife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * should this class be immutable?
 * memory vs. time vs. parallelism considerations
 * assumes square array
 * 
 */


/*
 * right now interface is tied to internal representation -> how to generalize?
 * should this be a generic class?
 * 
 */
public class GameOfLife {
private int lattice[][]; //stores an int should this be an enum, or class?
private int rows;
private int columns;
private int currentStep;

	/*
	 * constructor for empty lattice
	 */
	public GameOfLife(int rows, int columns){
		currentStep = 0;
		if(rows <= 0 || columns <= 0)
		{
			throw new IllegalArgumentException("rows and columns must both be nonzero") ;
		}
		lattice = new int[rows][columns];// Java initializes to 0
		this.rows = rows;
		this.columns = columns;
	};
	
	/*
	 * constructor that copies an initial state array
	 */
	public GameOfLife(int [][] initialState){
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
	/*
	 * resets state to all empty
	 */
	public void reset()
	{
		for(int row = 0; row < rows; row++)
		{
			for(int column = 0; column < columns; column++)
			{
				lattice[row][column] = 0;
			}
		}	
		currentStep = 0;
	}
	
	/*
	 * loads an initial state from file
	 * 
	 */
	public void loadStateFromFile(File cellsFile)
	{
		BufferedReader reader = null;
		
		reset();

		try {
		    reader = new BufferedReader(new FileReader(cellsFile));
		    String text = null;
		    int rowIdx = 0;

		    while ((text = reader.readLine()) != null) {
		        //parse text string
		    	if(text.charAt(0) != '!'){//else is a comment
		    		
		    		for(int columnIdx = 0; columnIdx < text.length(); columnIdx ++)
		    		{
		    			if(columnIdx < columns && rowIdx < rows) 
		    			{
		    				if(text.charAt(columnIdx) == '.')
		    				{
		    					lattice[rowIdx][columnIdx] = 0;
		    				} 
		    				else if(text.charAt(columnIdx) == 'O')
		    				{
		    					lattice[rowIdx][columnIdx] = 1;
		    				}
		    			}
		    		}
		    		rowIdx++;
		    	}
		    	
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}
		
	}
	
	/*
	 * 
	 * returns the state at a particular row and column
	 */
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
	 * Used for testing. Critique: leaks the class privates 
	 * TODO: change tests to use public methods
	*/	
	public int[][] getCurrentState()
	{
		return lattice;
	}
	
	public int getCurrentStep()
	{
		return currentStep;
	}
	
	/*
	 * Advance the state of the game by one time step
	 * 
	 */
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
	 *  implement conway's game of life:
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
	
	/*
	 * get the value of a neighbouring cell, taking into account edge or border conditions
	 */
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
	/*
	 * get the value of a neighbouring cell, taking into account edge or border conditions
	 */
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
	/*
	 * get the value of a neighbouring cell, taking into account edge or border conditions
	 */
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
	/*
	 * get the value of a neighbouring cell, taking into account edge or border conditions
	 */
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
	/*
	 * get the value of a neighbouring cell, taking into account edge or border conditions
	 */
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
	/*
	 * get the value of a neighbouring cell, taking into account edge or border conditions
	 */
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
	/*
	 * get the value of a neighbouring cell, taking into account edge or border conditions
	 */
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
	/*
	 * get the value of a neighbouring cell, taking into account edge or border conditions
	 */
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
	/*
	 * returns a string representation of the current state
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
	
	/*
	 * allow for interactivity; the user clicks cells on the GUI to flip their state
	 */
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
