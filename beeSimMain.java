package beeSim;

public class beeSimMain {

	public static void main(String[] args) {
		SimMap s = new SimMap(10, 10);
		Bee b = new Bee();
		Hive h = new Hive();
		s.addUnit(0, 0, b);
		s.addUnit(9,9,h);
		System.out.println(s.toString());
		System.out.println();

	}

}
