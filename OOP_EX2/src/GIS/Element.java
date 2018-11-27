package GIS;

import Geom.Geom_element;
import Geom.Point3D;

/**
 * This class cus emek.
 * @author Shay Naor Alex vaisman
 *
 */
public class Element implements GIS_element {

	private String name;
	private String time;
	private String lat;
	private String lon;
	private String alt;
	private String heading;
	private String tilt;

	public Geom_element getGeom() {
		return null;
	}

	public Meta_data getData() {
		return null;
	}

	@Override
	public void translate(Point3D vec) {

	}

}
