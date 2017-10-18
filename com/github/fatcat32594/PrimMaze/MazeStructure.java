package com.github.fatcat32594.PrimMaze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MazeStructure {
	private int width;
	private int height;
	private Cell[][] cells;
	private ArrayList<Tuple> frontier;

	public MazeStructure(int width, int height) {
		this.height = height;
		this.width = width;
		this.frontier = new ArrayList<Tuple>(0);

		// Creation of cell matrix
		this.cells = new Cell[this.width][this.height];
		for (int i = 0; i < this.width; i++)
			for (int j = 0; j < this.height; j++)
				this.cells[i][j] = new Cell(i, j);

		//Select random cell to start in
		Random r = new Random(System.currentTimeMillis());
		int y = r.nextInt(this.height);
		int x = r.nextInt(this.width);

		//Move that cell into the maze, and it's neighbors to the frontier
		this.cells[x][y].moveMaze(this.cells, width, height, this.frontier);

		//Keep picking cells to add to the maze until there are none left
		while (this.frontier.size() > 0) {
			int index = r.nextInt(this.frontier.size());
			Tuple pick = this.frontier.get(index);
			this.frontier.remove(index);
			
			x = pick.getX();
			y = pick.getY();
			this.cells[x][y].moveMaze(this.cells, width, height, this.frontier);
		}

	}
	
	public String toString() {
		String output = String.join("", Collections.nCopies(this.width, "+---"));
		output += "+\n";

		for (int y = 0; y < this.height; y++) {
			output += "|";

			for (int x = 0; x < this.width; x++) {
				String cellStr = (this.cells[x][y].isInMaze()) ? "   " : "XXX";
				output += cellStr;
				String eWallStr = (this.cells[x][y].isEastWall()) ? "|" : " ";
				output += eWallStr;
			}
			output += "\n+";
			for (int x = 0; x < this.width; x++) {
				String sWallStr = (this.cells[x][y].isSouthWall()) ? "---+" : "   +";
				output += sWallStr;
			}
			output = output + "\n";
		}
		return output;
	}

	public void print() {
		System.out.println(toString());

	}
	
	public static void main(String[] args) {
		if (args.length != 2)
			System.err.println("Error: Wrong number of arguments");
		else {
			try {
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				if (x > 0 && y > 0) {
					MazeStructure maze = new MazeStructure(x, y);
					maze.print();
				} else {
					System.err.println("Errpr: Width and height must be greater than zero");
				}
			} catch (NumberFormatException e) {
				System.err.println("Error: Width and height should be integers");
			}
		}
			
	}

}
