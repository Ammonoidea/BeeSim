package beeSim;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Bee extends Unit{
	private boolean honey = false;
	private SimMap map;

	public Bee(SimMap s) {
		this.map = s;
	}
	
	public String toString() {
		return "b";
	}
	
	public Tile getDirection(SimMap s) {
		return new Tile(0,0);
	}
	
	public HashMap<Tile, Tile> breadthFirstSearch(Tile t, Tile goal) {
		Tile[][] grid = map.getGrid();
		Queue<Tile> frontier = new ArrayDeque<Tile>();
		frontier.add(t);
		HashMap<Tile, Tile> cameFrom = new HashMap<Tile, Tile>();
		while (!frontier.isEmpty()) {
			Tile current = frontier.poll();
			System.out.println(current.printCoord());
			
			if (current.isEqual(goal)) {
				break;
			}
			//switch this to tile
			for (Tile next : map.getNeighbors(current.getX(), current.getY())) {
				frontier.add(next);
				cameFrom.put(next, current);
				
			}
		}
		
		return cameFrom;
	}
	//I shouldn't really use the Java stack...
	public Stack<Tile> breadthFirstSearchPath(Tile t, Tile goal) {
		Tile[][] grid = map.getGrid();
		Queue<Tile> frontier = new ArrayDeque<Tile>();
		frontier.add(t);
		HashMap<Tile, Tile> cameFrom = new HashMap<Tile, Tile>();
		while (!frontier.isEmpty()) {
			Tile current = frontier.poll();
			
			if (current.isEqual(goal)) {
				break;
			}
			//switch this to tile
			for (Tile next : map.getNeighbors(current.getX(), current.getY())) {
				frontier.add(next);
				cameFrom.put(next, current);
				
			}
		}
		
		Stack<Tile> path = new Stack<Tile>();
		Tile current = cameFrom.get(goal);
		path.add(current);
		while (cameFrom.containsKey(current)) {
			System.out.println(current.printCoord());
			current = cameFrom.get(current);
			path.add(current);
			if (current.isEqual(t)) {
				break;
			}
		}
		
		return path;
	}
	
	
	public static void main(String[] args) {

	}

}
