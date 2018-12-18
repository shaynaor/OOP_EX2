package Coords;

import GIS.Fruit;
import GIS.Path;
import Geom.Point3D;


public class Range {

	public Range( ) {
		
	}

	public Point3D getPosInTime(double time , Fruit a, Fruit b) {

		double n = (time-a.getTimeEaten()) / (b.getTimeEaten()-a.getTimeEaten());
		
		double dx = Math.abs(a.getGps().x()-b.getGps().x());
		double dy = Math.abs(a.getGps().y()-b.getGps().y());

		Point3D ans = new Point3D(a.getGps().x()+dx*n,a.getGps().y()+dy*n,0);

		return ans;
	}
}
