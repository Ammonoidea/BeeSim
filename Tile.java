package beeSim;

import java.util.ArrayList;

public class Tile {
	private ArrayList<Unit> units;
	private boolean passable = true;
	private int x;
	private int y;
	
	public Tile(int x, int y) {
		units = new ArrayList<Unit>();
		this.x = x;
		this.y = y;
	}
	
	public Tile(int x, int y, Unit u) {
		units = new ArrayList<Unit>();
		units.add(u);
		this.x = x;
		this.y = y;
	}
	
	public void addUnit(Unit u)  {
		units.add(u);
	}
	
	public boolean removeUnit(Unit u) {
		return units.remove(u);
	}
	
	public String toString() {
		if (units.isEmpty()) {
			return "-";
		} else {
			return units.get(0).toString();
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
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
