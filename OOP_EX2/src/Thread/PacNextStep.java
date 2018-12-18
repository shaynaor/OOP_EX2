package Thread;

import java.util.Iterator;

import Coords.Range;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Path;
import GIS.Solution;
import Geom.Point3D;

public class PacNextStep implements Runnable {

	private Solution solution;


	public PacNextStep(Solution solution) {
		this.solution = new Solution();
	}

	public void run() {
		double currentTime=0;
		Iterator<Path> itPath =  this.solution.getSolution().iterator();
		Fruit fruit = new Fruit(0,0,0);
		Range range = new Range();

		while(currentTime<=this.solution.getTime()) {

			while(itPath.hasNext()) {
				Path path =  itPath.next();
				fruit =	findBounds(currentTime,path);

			}





			//==========Reseting values=======
			itPath =  this.solution.getSolution().iterator();
			currentTime = currentTime + 0.1;
		}

	}





	/**
	 * finds the location of given time.
	 * 
	 * @param time the time given.
	 * @param path the path of pacman.
	 * @return the fruit pacman going to eat next.
	 */
	public Fruit[] findBounds(double time, Path path) {
		Fruit fruit = new Fruit(0, 0, 0);
		Iterator<GIS_element> itFruit = path.getPath().iterator();
	    Fruit[] limit = new Fruit[2];
		
	    itFruit.next();
		fruit	= (Fruit) itFruit.next();
		limit[0] = fruit;
		while (itFruit.hasNext()) {
			fruit = (Fruit) itFruit.next();

			if (time < fruit.getTimeEaten()) {
				return limit;
			}
		}
		return limit;
	}
	// Range range = new Range();
	// Point3D t = new Point3D(range.getPosInTime(40,
	// (Fruit)algo.getPath().getPath().get(1),
	// (Fruit)algo.getPath().getPath().get(2)));
	// Fruit test = new Fruit(t.x(),t.y(),0);
	// game.getFruits().add(test);

}
