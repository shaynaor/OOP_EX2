package Algorithms;

import java.util.Comparator;

import Coords.MyCoords;
import GIS.Pacman;
import Geom.Point3D;

/**
 * This class is part of the algorithm and it compers the pacmans 
 * by their distance from top left croner of the map. 
 * @author Alex vaisman, shay naor
 */
public class ComperByDistPacman implements Comparator<Pacman> {

	MyCoords coord = new MyCoords();
	Point3D topLeft = new Point3D(32.105689, 35.202411);

	@Override
	public int compare(Pacman arg0, Pacman arg1) {
		double dis1 = coord.distance3d(topLeft, arg0.getGps());
		double dis2 = coord.distance3d(topLeft, arg1.getGps());

		if (dis1 - dis2 < 0)
			return -1;
		else if (dis1 - dis2 > 0)
			return 1;
		else
			return 0;
	}

}