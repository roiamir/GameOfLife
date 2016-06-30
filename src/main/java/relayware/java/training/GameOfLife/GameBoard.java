package relayware.java.training.GameOfLife;

import java.awt.Point;

public class GameBoard {
	private boolean [][][] state = null;
	private int rows  = 0;
	private int cols = 0;
	private int currentState = 0;
	private int nextState = 1;
	
	private int [][] neighborsDistance = {	
											{-1,-1}, 
											{-1,0}, 
											{-1,1}, 
											{0,-1}, 
											{0,1}, 
											{1,-1},
											{1,0}, 
											{1,1}
											};
	
	public GameBoard(int rows, int cols, Point [] liveCellsCordnates) {
		state = new boolean [2][rows][cols];
		this.rows = rows;
		this.cols = cols;
		
		for (Point point : liveCellsCordnates) {
			state[currentState][point.x][point.y] = true;
		}
	}
	
	public void runGenerations(int generationsToCalc) {
		for (int g = 0; g < generationsToCalc; g++){
			runGeneration();
		}
	}
	
	public boolean [][] getCurrentState()
	{
		return state[currentState];
	}
	
	public int countNeighbours(Point cell)
	{
		int neighbors = 0;
		
		for (int neighbor = 0; neighbor <  neighborsDistance.length; neighbor++){
			int neighborX = cell.x + neighborsDistance[neighbor][0];
			int neighborY = cell.y + neighborsDistance[neighbor][1];
			
			// check location is not out of the board
			if (neighborX >= 0 && neighborY >= 0 && neighborX < rows && neighborY < cols) {
				if (state[currentState][neighborX][neighborY]){
					neighbors++;
				}
			}
		}
		return neighbors;
	}

	private void runGeneration() {
		
		for(int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				Point point = new Point(r,c);
				int neighbors = countNeighbours(point);
				state[nextState][r][c] = liveInNextGeneration(state[currentState][r][c], neighbors); 
			}
		}
		switchGeneration();
	}
	
	public boolean liveInNextGeneration(boolean currState, int numberOfNeighbours) {
		if (currState) {
			//  alive cell
			if (numberOfNeighbours < 2 || numberOfNeighbours > 3){
				// Any live cell with fewer than two live neighbours dies, as if caused by under-population.
				// Any live cell with more than three live neighbours dies, as if by over-population.
				return false;
			}
			else {
				// Any live cell with two or three live neighbours lives on to the next generation.
				return true;
			}
		}
		else {
			// dead cell
			if (numberOfNeighbours == 3){
				//Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
				return true;
			}
			else {
				// stay dead
				return false;				
			}
		}
	}

	
	private void switchGeneration () {
		for(int r = 0; r < rows; r++){
			for (int c=0; c<cols; c++){
				state[currentState][r][c] = false;
			}
		}
		currentState = (currentState + 1)%2;
		nextState = (nextState + 1)%2;
	}
}
