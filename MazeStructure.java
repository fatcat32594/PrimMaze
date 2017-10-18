import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class MazeStructure {
	private int width;
	private int height;
	private int[][] cells;
	private boolean[][][] walls;
	
	public MazeStructure(int width, int height) {
		this.height = height;
		this.width = width;
		
		this.cells = new int[this.width][this.height];
		this.walls = new boolean[this.width][this.height][2];
		
		for (int i = 0; i < this.width; i++) {
			for (int j = 0; j < this.height; j++) {
				this.cells[i][j] = -1;
				this.walls[i][j][0] = true;
				this.walls[i][j][1] = true;
			}
		}
		
		Random r = new Random(System.currentTimeMillis());
		int y = r.nextInt(this.height);
		int x = r.nextInt(this.width);
		
		this.cells[x][y] = 1;
		
		ArrayList<Tuple> frontier = new ArrayList<Tuple>(0);
		
		if (x > 0) {
			frontier.add(new Tuple(x-1,y));
			this.cells[x-1][y] = 0;
		}
		if (y > 0) {
			frontier.add(new Tuple(x,y-1));
			this.cells[x][y-1] = 0;
		}
		if (x < this.width-1) {
			frontier.add(new Tuple(x+1,y));
			this.cells[x+1][y] = 0;
		}
		if (y < this.height-1) {
			frontier.add(new Tuple(x,y+1));
			this.cells[x][y+1] = 0;
		}
		
		while (frontier.size() > 0) 
		{
			int index = r.nextInt(frontier.size());
			Tuple pick = frontier.get(index);
			frontier.remove(index);
			x = pick.getX();
			y = pick.getY();
			
			ArrayList<Character> neighbors = new ArrayList<Character>(0);
			if (x > 0 && cells[x-1][y] == 1)
				neighbors.add('w');
			if (y > 0 && cells[x][y-1] == 1)
				neighbors.add('n');
			if (x < this.width-1 && cells[x+1][y] == 1)
				neighbors.add('e');
			if (y < this.height-1 && cells[x][y+1] == 1)
				neighbors.add('s');
			
			int choice = r.nextInt(neighbors.size());
			char dir = neighbors.get(choice);
			if (dir == 'n')
				this.walls[x][y-1][1] = false;
			else if (dir == 's')
				this.walls[x][y][1] = false;
			else if (dir == 'e')
				this.walls[x][y][0] = false;
			else if (dir == 'w')
				this.walls[x-1][y][0] = false;
			
			this.cells[x][y] = 1;
			
			if (x > 0 && cells[x-1][y] == -1) {
				frontier.add(new Tuple(x-1,y));
				this.cells[x-1][y] = 0;
			}
			if (y > 0 && cells[x][y-1] == -1){
				frontier.add(new Tuple(x,y-1));
				this.cells[x][y-1] = 0;
			}
			if (x < this.width-1 && cells[x+1][y] == -1){
				frontier.add(new Tuple(x+1,y));
				this.cells[x+1][y] = 0;
			}
			if (y < this.height-1 && cells[x][y+1] == -1){
				frontier.add(new Tuple(x,y+1));
				this.cells[x][y+1] = 0;
			}
		}
		
						
	}
	
	public void print() {
		System.out.println(String.join("", Collections.nCopies(this.width, "+---")) + "+");
		
		for (int i = 0; i < this.height; i++) {
			System.out.print("|");
			
			for (int j = 0; j < this.width; j++) {
				if (cells[j][i] == 1)
					System.out.print("   ");
				else
					System.out.print("XXX");
				
				if (this.walls[j][i][0])
					System.out.print("|");
				else
					System.out.print(" ");
			}
			System.out.print("\n|");

			for (int j = 0; j < this.width; j++) {
				if (this.walls[j][i][1])
					System.out.print("---+");
				else
					System.out.print("   +");
			}
			
			System.out.print("\n");

		}
	}

}
