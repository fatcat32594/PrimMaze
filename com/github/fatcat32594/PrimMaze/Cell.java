package com.github.fatcat32594.PrimMaze;
import java.util.ArrayList;
import java.util.Random;

public class Cell {
	private boolean inMaze;
	private boolean inFrontier;
	private boolean entered;
	
	private boolean eastWall;
	private boolean southWall;
	private int x;
	private int y;
	
	public Cell(int x, int y) {
		this.entered = false;
		this.inMaze = false;
		this.inFrontier = false;
		this.eastWall = true;
		this.southWall = true;
		this.x = x;
		this.y = y;
	}
	
	public void moveFrontier(ArrayList<Tuple> frontier) {
		frontier.add(new Tuple(this.x,this.y));
		this.entered = true;
		this.inFrontier = true;
	}
	
	public void moveMaze(Cell[][] maze, int m_width, int m_height, ArrayList<Tuple> frontier) {
		this.inMaze = true;
		this.inFrontier = false;
		
		//Get list of neighbors that are in the maze
		ArrayList<Character> neighbors = new ArrayList<Character>(0);
		if ((x > 0) && maze[x - 1][y].isInMaze())
			neighbors.add('w');
		if ((y > 0) && maze[x][y - 1].isInMaze())
			neighbors.add('n');
		if ((x < (m_width - 1)) && maze[x + 1][y].isInMaze())
			neighbors.add('e');
		if ((y < (m_height - 1)) && maze[x][y + 1].isInMaze())
			neighbors.add('s');

		//Choose one to connect to
		if (neighbors.size() > 0) {
			Random r = new Random(System.currentTimeMillis());
			int choice = r.nextInt(neighbors.size());
			char dir = neighbors.get(choice);
			if (dir == 'n')
				maze[x][y - 1].removeSouth();
			else if (dir == 's')
				maze[x][y].removeSouth();
			else if (dir == 'e')
				maze[x][y].removeEast();
			else if (dir == 'w')
				maze[x - 1][y].removeEast();
		}
		
		//move all neighbors to frontier that haven't started yet
		if ((x > 0) && !maze[x - 1][y].isEntered())
			maze[x - 1][y].moveFrontier(frontier);
		if ((y > 0) && !maze[x][y - 1].isEntered())
			maze[x][y - 1].moveFrontier(frontier);
		if ((x < (m_width - 1)) && !maze[x + 1][y].isEntered())
			maze[x + 1][y].moveFrontier(frontier);
		if ((y < (m_height - 1)) && !maze[x][y + 1].isEntered())
			maze[x][y + 1].moveFrontier(frontier);
		
	}
	
	public void removeEast() {
		this.eastWall = false;
	}
	
	public void removeSouth() {
		this.southWall = false;
	}

	public boolean isInMaze() {
		return inMaze;
	}

	public boolean isInFrontier() {
		return inFrontier;
	}

	public boolean isEastWall() {
		return eastWall;
	}

	public boolean isSouthWall() {
		return southWall;
	}

	public boolean isEntered() {
		return entered;
	}

}
