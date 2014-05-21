package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
//import org.junit.Assert.AssertArrayEquals;
import gameOfLife.GameOfLife;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



/*
 * TODO: test non-square i.e. rectangular lattice
 * 
 */
public class TestSetGetNext {

	private int [][] initialState;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		initialState = new int[][]{
				  { 0, 1, 0, 0 },
				  { 0, 0, 1, 0 },
				  { 1, 1, 1, 0 },
				  { 0, 0, 0, 0 },
				};
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCurrentStep() {
		
		
		GameOfLife gol = new GameOfLife(10, 10);
		assertEquals(0, gol.getCurrentStep());	
		gol.computeNextState();
		assertEquals(1, gol.getCurrentStep());	

	}
	
	@Test
	public void testSetGetState(){
		
		GameOfLife gol = new GameOfLife(initialState);		
		int testState[][] = gol.getCurrentState();
		assertTrue(Arrays.equals(initialState, testState));		
	}
	@Test
	public void testNextState(){
		int expectedState[][] = new int[][]{
				  { 0, 0, 0, 0 },
				  { 1, 0, 1, 0 },
				  { 0, 1, 1, 0 },
				  { 0, 1, 0, 0 },
				};
		GameOfLife gol = new GameOfLife(initialState);
		gol.computeNextState();
		int testState[][] = gol.getCurrentState();
		assertTrue(Arrays.equals(expectedState, testState));		
		
	}
	
	public void prettyPrint(GameOfLife toPrint)
	{
		
	}

}
