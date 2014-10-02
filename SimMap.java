package beeSim;

import java.util.ArrayList;

public class SimMap {
	Tile[][] grid;
	
	public SimMap(int x, int y) {
		grid = new Tile[y][x];
		
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				grid[i][j] = new Tile(j, i);
			}
		}
	}
	
	public void addUnit(int x, int y, Unit u) {
		grid[x][y].addUnit(u);
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				sb.append(grid[i][j].toString());
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public ArrayList<Tile> getNeighbors(int x, int y) {
		if (x > grid[0].length || y > grid.length) {
			return null;
		}
		
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		if (x > 0) {
			neighbors.add(grid[y][x-1]);
		}
		
		if (x < grid[0].length) {
			neighbors.add(grid[y][x+1]);
		}
		
		if (y > 0) {
			neighbors.add(grid[y - 1][x]);
		}
		
		if (y < grid.length) {
			neighbors.add(grid[y + 1][x]);
		}
		
		return neighbors;
	}
	
	//testing method
	private String tileNeighbors(ArrayList<Tile> n) {
		StringBuilder sb = new StringBuilder();
		for (Tile t : n) {
			sb.append("(" + t.getX() + "," + t.getY() + ")");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		SimMap s = new SimMap(10, 10);
		Bee b = new Bee();
		Hive h = new Hive();
		s.addUnit(1, 2, b);
		s.addUnit(9,9,h);
		System.out.println(s.toString());
		System.out.println(s.tileNeighbors(s.getNeighbors(4, 4)));
		
	}

}
