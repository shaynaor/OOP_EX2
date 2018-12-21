package Thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import Coords.Range;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Pacman;
import GIS.Path;
import GIS.Solution;
import Geom.Point3D;
import Gui.MyFrame;

public class PacNextStep implements Runnable {

	private MyFrame frame;
    private int speed;
    
	public PacNextStep(MyFrame frame, int speed) {
		this.frame = frame;
		this.speed = speed;
	}

	public void run() {
		double currentTime = this.frame.getCurrentTime();
		double endTime = this.frame.getAlgo().getSolution().getTime();
		Path path = new Path();
		Fruit[] fruits = new Fruit[2];
		Range range = new Range();
		Vector<Pacman> pacmans = new Vector<Pacman>();
		Point3D point = new Point3D(0, 0, 0);
		while (currentTime <= endTime) {
			pacmans.clear();
			Iterator<Path> pathIt = frame.getAlgo().getSolution().getSolution().iterator();
			while (pathIt.hasNext()) {
				path = pathIt.next();
				fruits = findBounds(currentTime, path);
				if (fruits == null) {
					/* Do nothing */
				}else if(fruits[0].getTimeEaten() == fruits[1].getTimeEaten() ) {
					Pacman pac = new Pacman(fruits[0].getGps().x(), fruits[0].getGps().y(), 0);
					pacmans.add(pac);
				}else {
					point = range.getPosInTime(currentTime, fruits[0], fruits[1]);
					Pacman pac = new Pacman(point.x(), point.y(), 0);
					pacmans.add(pac);
				}

			}
			this.frame.setNextPacman(pacmans);
			pathIt = frame.getAlgo().getSolution().getSolution().iterator();
			currentTime += 0.5;        ///////////////////////////PLAYED WTH THIS TWO
			try {
				Thread.sleep(this.speed);   /////////////////////////////////////
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Fruit[] findBounds(double time, Path path) {
		Fruit[] fruitsArr = new Fruit[2];
		/* Check if the time is not above the path time. */
		if (time > path.finalTime()) {
			fruitsArr[0] =(Fruit)path.getPath().get(path.getPath().size()-1);
			fruitsArr[1] =(Fruit)path.getPath().get(path.getPath().size()-1);
			return fruitsArr;
		}
		/* If the path contains only one pacman */
		if (path.getPath().size() == 1) {
			return null;
		}
		Iterator<GIS_element> gisIt = path.getPath().iterator();
		Pacman pac = (Pacman) gisIt.next();
		Fruit fruitPac = new Fruit(pac.getGps().x(), pac.getGps().y(), 0);
		Fruit fruit = (Fruit) gisIt.next();

		if (time < fruit.getTimeEaten()) {
			fruitsArr[0] = fruitPac;
			fruitsArr[1] = fruit;
			return fruitsArr;
		}
		fruitsArr[0] = fruit;
		while (gisIt.hasNext()) {
			fruit = (Fruit) gisIt.next();// may new
			if (time < fruit.getTimeEaten()) {
				fruitsArr[1] = fruit;
				return fruitsArr;
			}
			fruitsArr[0] = fruit;
		}
		return null;

	}

}
