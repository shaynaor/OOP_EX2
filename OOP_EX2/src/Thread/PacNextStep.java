package Thread;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.Range;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Pacman;
import GIS.Path;
import GIS.Solution;
import Geom.Point3D;

public class PacNextStep implements Runnable {

	private Solution solution;
	private ArrayList<Point3D> nextPacmans;

	public PacNextStep(Solution solution) {
		this.solution = new Solution();
	}

	public void run() {
		double currentTime = 0.1;
		Iterator<Path> itPath = this.solution.getSolution().iterator();
		Fruit[] fruitArr = new Fruit[2];
		Range range = new Range();
		this.nextPacmans = new ArrayList<Point3D>();
		Point3D pointNext = new Point3D(0, 0, 0);

		/* While */
		while (currentTime <= this.solution.getTime()) {
			/* Goes over the paths */
			while (itPath.hasNext()) {
				Path path = itPath.next();
				fruitArr = findBounds(currentTime, path);
				pointNext = range.getPosInTime(currentTime, fruitArr[0], fruitArr[1]);
				this.nextPacmans.add(pointNext);

			}

			// ==========Reseting values=======
			currentTime = currentTime + 0.1;
		}

	}
	
	public ArrayList<Point3D> nextPacmansFunc(){
		
		
		
		return null;
	}

	/**
	 * finds the location in given time.
	 * 
	 * @param time the time given.
	 * @param path the path of pacman.
	 * @return the fruit pacman going to eat next and the fruit before.
	 */
	public Fruit[] findBounds(double time, Path path) {
		Fruit fruit = new Fruit(0, 0, 0);
		Iterator<GIS_element> itFruit = path.getPath().iterator();
		Fruit[] limit = new Fruit[2];

		itFruit.next();
		if (itFruit instanceof Fruit) {
			fruit = (Fruit) itFruit;
			limit[0] = fruit;
		} else {
			fruit = new Fruit(((Pacman) itFruit).getGps().x(), ((Pacman) itFruit).getGps().y(), 0);
			fruit.eaten(0);
			limit[0] = fruit;
		}

		while (itFruit.hasNext()) {
			limit[0] = fruit;
			fruit = (Fruit) itFruit.next();

			if (time < fruit.getTimeEaten()) {
				limit[1] = fruit;
				return limit;
			}
		}
		return limit;
	}

	public ArrayList<Point3D> getNextPacmans() {
		return nextPacmans;
	}

}
