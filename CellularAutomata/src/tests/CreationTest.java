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

package tests;

import static org.junit.Assert.*;
import gameOfLife.GameOfLife;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author marek
 *
 */
public class CreationTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreation() {
		GameOfLife gol = new GameOfLife(10, 10);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testZeroSizeCreation() {
		GameOfLife gol = new GameOfLife(0, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNegativeSizeCreation() {
		GameOfLife gol = new GameOfLife(-1, -1);
	}
	
	@Test
	public void testAccessCorners() {
		GameOfLife gol = new GameOfLife(10, 10);
		assertEquals(0, gol.getStateAt(0,0));
		assertEquals(0, gol.getStateAt(9,9));
		assertEquals(0, gol.getStateAt(0,9));
		assertEquals(0, gol.getStateAt(9,0));
	}
	
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testAccessOutOfBounds() {
		GameOfLife gol = new GameOfLife(10, 10);
		int x = gol.getStateAt(10, 10);
	}
	
	@Test
	public void testToString() {
		GameOfLife gol = new GameOfLife(3, 3);
		assertEquals("000\n000\n000\n", gol.toString());
	}
	
	@Test
	public void testCreateWithInitialState() throws Exception {
		int initialState[][] = new int[][]{
				  { 0, 1, 0, 0 },
				  { 0, 0, 1, 0 },
				  { 1, 1, 1, 0 },
				  { 0, 0, 0, 0 },
				};
		GameOfLife gol = new GameOfLife(initialState);
	}
	

}
