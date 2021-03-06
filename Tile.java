package beeSim;

import java.util.ArrayList;

public class Tile {
	private ArrayList<Unit> units;
	private boolean passable = true;
	private int x;
	private int y;
	int cost;
	
	public Tile(int x, int y) {
		units = new ArrayList<Unit>();
		this.x = x;
		this.y = y;
		this.cost = 1;
	}
	
	public Tile(int x, int y, Unit u) {
		units = new ArrayList<Unit>();
		units.add(u);
		this.x = x;
		this.y = y;
		this.cost = 1;
	}
	
	
	
	public boolean setPassability(boolean b) {
		this.passable = b;
		return this.passable;
	}
	
	public boolean getPassable() {
		return this.passable;
	}
	
	public int getCost() {
		return cost;
	}
	
 	public void addUnit(Unit u)  {
		units.add(u);
	}
	
	public boolean removeUnit(Unit u) {
		return units.remove(u);
	}
	
	public String toString() {
		if (!passable) {
			return "@";
		}else if (units.isEmpty()) {
			return "-";
		} else {
			return units.get(0).toString();
		}
	}
	
	public String printCoord() {
		return ("(" + x + "," + y + ")");
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isEqual(Tile t) {
		return (this.x == t.getX() && this.y == t.getX());
	}
	
	public String drawDir(Tile t) {
		if (t.getY() > this.y) {
			return "v";
		} else if (t.getX() > this.x) {
			return "<";
		} else if (t.getY() <  this.x) {
			return "^";
		} else if (t.getX() < this.x) {
			return ">";
		} else {
			return "v";
		}
	}
	

	public static void main(String[] args) {
		Tile t = new Tile(0,0);
		Unit u = new Unit();
		System.out.println(t.toString());
		t.addUnit(u);
		System.out.println(t.toString());
		t.removeUnit(u);
		System.out.println(t.toString());
		Unit y = new Unit();
		System.out.println(t.removeUnit(y));
	}

}
