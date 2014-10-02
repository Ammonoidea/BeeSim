package beeSim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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
				//sb.append(grid[i][j].toString());
				sb.append("(" + grid[i][j].getX() + "," + grid[i][j].getY() + ")");
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
		
		if (x < grid[0].length - 1) {
			neighbors.add(grid[y][x+1]);
		}
		
		if (y > 0) {
			neighbors.add(grid[y - 1][x]);
		}
		
		if (y < grid.length - 1) {
			neighbors.add(grid[y + 1][x]);
		}
		
		return neighbors;
	}
	
	public Tile[][] getGrid() {
		return grid;
	}
	
	//testing method
	private String tileNeighbors(ArrayList<Tile> n) {
		StringBuilder sb = new StringBuilder();
		for (Tile t : n) {
			sb.append("(" + t.getX() + "," + t.getY() + ")");
		}
		return sb.toString();
	}
	
	private String drawDepthFirstSearch(HashMap<Tile, Tile> cameFrom, Tile start, Tile end) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j].isEqual(start)) {
					sb.append("S");
				}
				
				if (grid[i][j].isEqual(end)) {
					sb.append("F");
				}
				
				if (cameFrom.containsKey(grid[i][j])) {
					//if cameFrom is higher prints ^, if cameFrom is lower prints v, so on
					sb.append(grid[i][j].drawDir(cameFrom.get(grid[i][j])));
				} else {
					sb.append(grid[i][j].toString());
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		SimMap s = new SimMap(3, 3);
		Bee b = new Bee(s);
		Hive h = new Hive();
		s.addUnit(0, 0, b);
		s.addUnit(2,2,h);
		
		//System.out.println(s.drawDepthFirstSearch(b.breadthFirstSearch(s.getGrid()[1][2], s.getGrid()[4][4]), s.getGrid()[1][2], s.getGrid()[4][4]));
		Stack<Tile> path = b.breadthFirstSearchPath(s.getGrid()[0][0], s.getGrid()[2][2]);
		Tile t = path.pop();
		StringBuilder sb = new StringBuilder();
		while (!path.empty()) {
			sb.append(t.printCoord());
			sb.append(",");
			t = path.pop();
		}
		System.out.println(sb.toString());
			
	}

}
