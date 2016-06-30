package relayware.java.training.GameOfLife;

import java.awt.Point;

import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;

import junit.framework.TestCase;

public class GameBoardTest extends TestCase {

	@Test
	public void testInit2x2(){
		Point[] liveCells = {new Point(1,1), new Point (0,0)};
		
		GameBoard board = new GameBoard(2,2, liveCells);
		boolean [][] actual = board.getCurrentState();
		boolean [][] expected = {{true, false}, {false, true}};

		for (int row = 0; row < expected.length; row++) {
			assertArrayEquals(expected[row], actual[row]);	
		}
	}
	
	@Test
	public void testCountNeighbours2x2(){
		Point[] liveCells = {new Point(1,1), new Point (0,0)};
		
		GameBoard board = new GameBoard(2,2, liveCells);
		assertEquals(1, board.countNeighbours(new Point(0,0)));
		assertEquals(2, board.countNeighbours(new Point(0,1)));
		assertEquals(2, board.countNeighbours(new Point(1,0)));
		assertEquals(1, board.countNeighbours(new Point(1,1)));
	}
	
	@Test
	public void testRunGeneration2x2(){
		Point[] liveCells = {new Point(1,1), new Point (0,0)};
		
		GameBoard board = new GameBoard(2,2, liveCells);
		board.runGenerations(1);
		
		boolean [][]actual = board.getCurrentState();
		boolean [][]expected = new  boolean [][] {{false, false}, {false, false}};
		
		for (int row = 0; row < expected.length; row++) {
			assertArrayEquals(expected[row], actual[row]);	
		}
	}


	public void testInit4x2(){
		Point[] liveCells = {new Point(1,1), new Point (0,0), new Point (0,1), new Point (2,1)};
		
		GameBoard board = new GameBoard(4,2, liveCells);
		boolean [][] actual = board.getCurrentState();
		boolean [][] expected = {{true, true}, {false, true}, {false, true}, {false, false}};

		for (int row = 0; row < expected.length; row++) {
			assertArrayEquals(expected[row], actual[row]);	
		}
	}
	
	
	public void testCountNeighbours4x2(){
		Point[] liveCells = {new Point(1,1), new Point (0,0), new Point (0,1), new Point (2,1)};
		
		GameBoard board = new GameBoard(4,2, liveCells);
		assertEquals(2, board.countNeighbours(new Point(0,0)));
		assertEquals(2, board.countNeighbours(new Point(0,1)));
		assertEquals(4, board.countNeighbours(new Point(1,0)));
		assertEquals(3, board.countNeighbours(new Point(1,1)));
		assertEquals(2, board.countNeighbours(new Point(2,0)));
		assertEquals(1, board.countNeighbours(new Point(2,1)));
		assertEquals(1, board.countNeighbours(new Point(3,0)));
		assertEquals(1, board.countNeighbours(new Point(3,1)));
	}

	public void testRunGenerations4x2(){
		Point[] liveCells = {new Point(1,1), new Point (0,0), new Point (0,1), new Point (2,1)};
		
		GameBoard board = new GameBoard(4,2, liveCells);

		
		board.runGenerations(1);
		
		boolean [][] actual = board.getCurrentState();
		boolean [][] expected = new  boolean [][] {{true, true}, {false, true}, {false, false}, {false, false}};
		
		for (int row = 0; row < expected.length; row++) {
			assertArrayEquals(expected[row], actual[row]);	
		}

		board.runGenerations(1);
		
		actual = board.getCurrentState();
		expected = new  boolean [][] {{true, true}, {true, true}, {false, false}, {false, false}};
		
		for (int row = 0; row < expected.length; row++) {
			assertArrayEquals(expected[row], actual[row]);	
		}

		board.runGenerations(10);
		
		actual = board.getCurrentState();
		for (int row = 0; row < expected.length; row++) {
			assertArrayEquals(expected[row], actual[row]);	
		}

	}
	

}
