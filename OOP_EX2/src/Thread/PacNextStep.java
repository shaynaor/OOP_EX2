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

/**
 * This class is a thread which finds the position of the pacmans 
 * on the route from their starting position to the last fruit.
 * the pacmans will be following the path which is visible in the gui.
 * the thread will run till it reaches the time of the longest path , or its told to shutdown.
 * @author Alex vaisman, Shay naor
 *
 */
public class PacNextStep implements Runnable {

	private MyFrame frame;


	public PacNextStep(MyFrame frame) {
		this.frame = frame;

	}
/**
 * This function will update the position of all the pacmans in the solution 
 * and will put them into a Vector.
 * after putting them in a Vector the function will call a set function from MyFrame and that will cause the gui
 * to update.
 * after that the thread will sleep for some time.
 * during the run time of the gui the speed can be changed within the gui and will be updated while
 * the thread is running.
 * 
 */
	public void run() {
		double currentTime = this.frame.getCurrentTime();
		double endTime = this.frame.getAlgo().getSolution().getTime();
		Path path = new Path();
		Fruit[] fruits = new Fruit[2];
		Range range = new Range();
		Vector<Pacman> pacmans = new Vector<Pacman>();
		Point3D point = new Point3D(0, 0, 0);
       

       //will find the next location of the pacmans for current time
	  //if current time goes longer then the longest path the function will stop.
		while (currentTime <= endTime) {
			if(this.frame.isKill()==true) {// this kills the thread 
				currentTime = endTime + 50;
				break;
			}

			pacmans.clear();
			Iterator<Path> pathIt = frame.getAlgo().getSolution().getSolution().iterator();
			while (pathIt.hasNext()) {
				path = pathIt.next();
				fruits = findBounds(currentTime, path);
				if (fruits[0].getTimeEaten()==-50) {
					Pacman pac = new Pacman(fruits[0].getGps().x(), fruits[0].getGps().y(), 0);
					pacmans.add(pac);
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
			currentTime += 0.5;        
			try {
				Thread.sleep(this.frame.getSpeed());   
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This function finds for a given time two fruits.
	 * It will find the fruits using the time eaten.
	 * the first fruit will be less or equal to the given time.
	 * the second fruit will be more or equal to the given time.
	 * if the first fruit is a pacman the function will convert it to a fruit.
	 * This function exist to make sure that range receives correct values.
	 * 
	 * @param time ,  the time given.
	 * @param path ,  the path with the fruits.
	 * @return two fruits bounding the time.
	 */
	public Fruit[] findBounds(double time, Path path) {
		Fruit[] fruitsArr = new Fruit[2];

		/* If the path contains only one pacman */
		if (path.getPath().size() == 1) {
			Pacman temp = new Pacman(((Pacman)(path.getPath().get(0))).getGps().x(),((Pacman)(path.getPath().get(0))).getGps().y(),0);
			Fruit ftemp =  new Fruit(temp.getGps().x(),temp.getGps().y(),0);
			ftemp.eaten(-50);
			fruitsArr[0] =ftemp;
			fruitsArr[1] =ftemp;
			return fruitsArr;
		}

		/* Check if the time is not above the path time. */
		if (time > path.finalTime()) {
			fruitsArr[0] =(Fruit)path.getPath().get(path.getPath().size()-1);
			fruitsArr[1] =(Fruit)path.getPath().get(path.getPath().size()-1);
			return fruitsArr;
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
