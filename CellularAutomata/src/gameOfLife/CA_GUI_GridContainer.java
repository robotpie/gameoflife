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

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

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