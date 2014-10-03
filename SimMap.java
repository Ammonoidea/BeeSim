package beeSim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
	
	public void addImpassables() {
		Random r = new Random();
		for (Tile[] tArry: grid) {
			for (Tile t : tArry) {
//				if (r.nextInt(10) < 1) {
//					t.setPassability(false);
//				}
				if (t.getX() > grid[0].length/3 && (t.getY() < 5 || t.getY() > grid.length - 5) && t.getX() < (grid[0].length - grid[0].length/3)) {
					t.setPassability(false);
				}
				
				if (t.getY() > 1 && t.getX() == 2) {
					t.setPassability(false);
				}
			}
		}
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
				}else if (grid[i][j].isEqual(end)) {
					sb.append("F");
				}else if (cameFrom.containsKey(grid[i][j])) {
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

	public String getPath(Tile start, Tile goal, Bee b) {
		Stack<Tile> path = b.breadthFirstSearchPath(start, goal);
		StringBuilder sb;
		if (path.size() > 2) {
			Tile t = path.pop();
			sb = new StringBuilder();
			sb.append(t.printCoord());
			sb.append(",");
			while (!path.empty()) {
				t = path.pop();
				sb.append(t.printCoord());
				sb.append(",");
			}
			return sb.toString();
		} else {
			return "No path found.";
		}
	}
	
	public static void main(String[] args) {
		SimMap s = new SimMap(10, 10);
		s.addImpassables();
		Bee b = new Bee(s);
		Hive h = new Hive();
		s.addUnit(0, 0, b);
		s.addUnit(2,2,h);
		Tile start = s.getGrid()[0][0];
		Tile goal = s.getGrid()[7][8];
		
		System.out.println(s.drawDepthFirstSearch(b.breadthFirstSearch(start, goal), start, goal));
		System.out.println(s.getPath(start, goal, b));
		
			
	}

}
