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
	public void testArraysEquals(){
		int [] [] anotherArray = new int[][]{
				  { 0, 1, 0, 0 },
				  { 0, 0, 1, 0 },
				  { 1, 1, 1, 0 },
				  { 0, 0, 0, 0 },
				};
		int [] [] aThirdArray = new int[][]{
				  { 0, 1, 0, 0 },
				  { 0, 1, 1, 0 },
				  { 1, 1, 1, 0 },
				  { 0, 0, 0, 0 },
				};
		prettyPrint(initialState);
		prettyPrint(anotherArray);
		prettyPrint(aThirdArray);
		
		//confirm that pedanticCompare will reject arrays that aren't the same
		assertFalse(pedanticCompare(initialState, aThirdArray));
		//confirm that initialState and anotherArray are the same
		assertTrue(pedanticCompare(initialState, anotherArray));
		//confirm that Arrays.equal will reject these!
		assertFalse(Arrays.equals(initialState,  anotherArray));
		
		System.out.println("conclusion: Arrays.equals sucks");
	}
	
	@Test
	public void testSetGetState(){
		
		GameOfLife gol = new GameOfLife(initialState);		
		System.out.println(gol.toString());
		int testState[][] = gol.getCurrentState();
		prettyPrint(testState);
		assertTrue(pedanticCompare(initialState, testState));		
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
		System.out.println(gol.toString());
		gol.computeNextState();
		int testState[][] = gol.getCurrentState();
		System.out.println(gol.toString());
		prettyPrint(expectedState);
		assertTrue(Arrays.equals(expectedState, testState));		
		
	}
	
	public void prettyPrint(int [][] a_lattice)
	{
		int rows = a_lattice.length;
		int columns = a_lattice[0].length;
		String result = "";
		for(int row = 0; row < rows; row++)
		{
			for(int column = 0; column < columns; column++)
			{
				result += a_lattice[row][column];
			}
			result += "\n";
		}
		System.out.println(result);
	}
	
	/*
	 * does what Arrays.equals claims to do
	 * this method exists because Arrays.equals is "badly broken"
	 * 
	 */
	
	public boolean pedanticCompare(int [][] left, int [][] right)
	{
		int l_rows = left.length;
		int l_columns = left[0].length;
		int r_rows = right.length;
		int r_columns = right[0].length;
		
		if(l_rows != r_rows || l_columns != r_columns) return false;
	
		for(int row = 0; row < l_rows; row++)
		{
			for(int column = 0; column < l_columns; column++)
			{
				if(left[row][column] != right[row][column]) return false;
			}
		}
		return true;
	}

}
