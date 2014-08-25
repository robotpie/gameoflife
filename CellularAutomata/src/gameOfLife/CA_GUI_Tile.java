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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * GUI element representing one cell in the game of life
 * all methods here are framework methods defined by the Java API, so I would have a look there...
 */
public class CA_GUI_Tile extends JLabel implements MouseListener {
	
	private static final long serialVersionUID = 7832885560545657999L;
    Dimension minSize = new Dimension(1,1);
    GameOfLife model;
    int row_id, column_id;

    public CA_GUI_Tile(int row_id, int column_id, GameOfLife model) {
    	this.row_id = row_id;
    	this.column_id = column_id;
    	this.model = model;
    	if(model.getStateAt(row_id, column_id) == 1)
		{
			setBackground(Color.black);
		}
		else
		{
			setBackground(Color.white);
		}
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(this);
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		//toggle the state of the correct lattice
		model.toggleState(row_id, column_id);
		if(model.getStateAt(row_id, column_id) == 1)
		{
			//setBackground(Color.black);
			setBackground(Color.green);
		}
		else
		{
			//setBackground(Color.white);
			setBackground(Color.black);
		}
		repaint();
		System.out.println("grid was clicked: " + row_id + ", " + column_id);
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		//determine color
		if(model.getStateAt(row_id, column_id) == 1)
		{
			//setBackground(Color.black);
			setBackground(Color.green);
		}
		else
		{
			//setBackground(Color.white);
			setBackground(Color.black);
		}
		//System.out.println("grid was painted: " + row_id + ", " + column_id);
		super.paint(g);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}