package gameOfLife;

import javax.swing.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//TODO: should we use MVC pattern here?
public class CA_GUI_GridContainer extends JPanel{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 486835360349182890L;

	Dimension minSize = new Dimension(1,1);
    
    CA_GUI_Tile tileGrid[][];
    int rows, columns;
    GameOfLife model;

    public CA_GUI_GridContainer(int rows, int columns, GameOfLife model) {
    	super(new GridLayout(rows,columns), true);
    	tileGrid = new CA_GUI_Tile[rows][columns];
    	this.model = model;
    	for(int row = 0; row < rows; row++)
    	{
    		for(int column = 0; column < columns; column++)
    		{
    			tileGrid[row][column] = new CA_GUI_Tile(row, column, model);
    			add(tileGrid[row][column]);
    		}
    		
    	}
        //setBackground(color);
    	this.rows = rows;
    	this.columns = columns;
    	
    	
    	
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void reset(int newRows, int newColumns, GameOfLife newModel) {
    	
    	for(int row = 0; row < rows; row++)
    	{
    		for(int column = 0; column < columns; column++)
    		{
    			remove(tileGrid[row][column]);
    		}
    		
    	}
    	this.model = newModel;
    	this.rows = newRows;
    	this.columns = newColumns;
    	this.setLayout(new GridLayout(rows,columns));
    	tileGrid = new CA_GUI_Tile[rows][columns];
    	for(int row = 0; row < rows; row++)
    	{
    		for(int column = 0; column < columns; column++)
    		{
    			tileGrid[row][column] = new CA_GUI_Tile(row, column, model);
    			add(tileGrid[row][column]);
    		}
    		
    	}
    	
    	revalidate();
    	repaint();
    }
    
    
    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }
    /*
    @Override
	public void update(Graphics g)
	{
		System.out.println("entire grid was updated!!!");

    	for(int row = 0; row < rows; row++)
    	{
    		for(int column = 0; column < columns; column++)
    		{
    			tileGrid[row][column].repaint();
    		}
    		
    	}
    	
    	super.update(g);
    	
	}
    */
    @Override
	public void paint(Graphics g)
	{
		//System.out.println("entire grid was painted!!!");

    	for(int row = 0; row < rows; row++)
    	{
    		for(int column = 0; column < columns; column++)
    		{
    			tileGrid[row][column].repaint();
    		}
    		
    	}
    	
		//paintAll(g);
    	super.paint(g);
	}
    
}