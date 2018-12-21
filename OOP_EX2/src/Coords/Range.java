package Coords;

import GIS.Fruit;
import GIS.Path;
import Geom.Point3D;


public class Range {

	public Range( ) {
	}

	
	/**
	 * This function receives two Fruits and a given time.
	 * The points must have time associated to them.
	 * The function will return the position of a point on a line between the two given fruits
	 * for a certain time.
	 * The time given must be greater then the time of a and lower then the time of b.
	 * 
	 * @param time ,The time for which a point must be found.
	 * @param a , The first point.
	 * @param b , The second point.
	 * @return , a point between point a and b relative to the given time.
	 */
	public Point3D getPosInTime(double time , Fruit a, Fruit b) {

		double n = (time-a.getTimeEaten()) / (b.getTimeEaten()-a.getTimeEaten());
		
		double dx = a.getGps().x()-b.getGps().x();
		double dy = a.getGps().y()-b.getGps().y();

		Point3D ans = new Point3D(a.getGps().x()+ (-dx)*n,a.getGps().y()+(-dy)*n,0);

		return ans;
	}
}
