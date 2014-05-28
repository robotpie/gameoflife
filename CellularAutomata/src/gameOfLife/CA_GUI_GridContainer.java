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
	 * automagically generated id
	 */
	private static final long serialVersionUID = 7832885560545657000L;

	Dimension minSize = new Dimension(5,5);
    
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