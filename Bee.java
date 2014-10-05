package beeSim;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
		HashSet<Tile> visited = new HashSet<Tile>();
		Tile current;
		while (!frontier.isEmpty()) {
			current = frontier.poll();
			visited.add(current);
			
			if (current.isEqual(goal)) {
				break;
			}
			//switch this to tile
			//need to make sure a tile hasn't already been gone to
			for (Tile next : map.getNeighbors(current.getX(), current.getY())) {
				if (!visited.contains(next) && next.getPassable()) {
					frontier.add(next);
					cameFrom.put(next, current);
				}
				
			}
		}
		
		return cameFrom;
	}
	//I shouldn't really use the Java stack...
	public Stack<Tile> breadthFirstSearchPath(Tile t, Tile goal) {
		Tile[][] grid = map.getGrid();
		Tile current;
		HashMap<Tile, Tile> cameFrom = breadthFirstSearch(t, goal);
		
		Stack<Tile> path = new Stack<Tile>();
		path.add(goal);
		current = cameFrom.get(goal);
		path.add(current);
		while (cameFrom.containsKey(current)) {
			current = cameFrom.get(current);
			path.add(current);
			if (current.isEqual(t)) {
				break;
			}
		}
		
		return path;
	}
	
	public HashMap<Tile, Integer> Dijkstra(Tile start, Tile goal) {
		Tile[][] grid = map.getGrid();
		SimplePairPriorityQueue<Tile, Integer> frontier = new SimplePairPriorityQueue<Tile, Integer>();
		HashMap<Tile, Tile> cameFrom = new HashMap<Tile, Tile>();
		HashMap<Tile, Integer> costSoFar = new HashMap<Tile, Integer>();
		
		frontier.offer(start, 0);
		cameFrom.put(start, null);
		costSoFar.put(start, 0);
		
		while (!frontier.isEmpty()) {
			Tile current = frontier.poll();
			
			if (current == goal) {
				break;
			}
			
			for (Tile next : map.getNeighbors(current.getX(), current.getY())) {
				int newCost = costSoFar.get(current) + next.getCost();
				if ((!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) && next.getPassable()) {
					costSoFar.put(next, newCost);
					int priority = newCost;
					frontier.offer(next, newCost);
					cameFrom.put(next, current);
				}
			}	
		}
		
		ArrayList<HashMap<?, ?>> results = new ArrayList<HashMap<?, ?>>();
		results.add(cameFrom);
		results.add(costSoFar);
		return costSoFar;
	}
	
	
	public static void main(String[] args) {

	}

}
