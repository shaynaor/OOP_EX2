package GIS;

import java.util.Arrays;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
/**
 * This class represents an element.
 * an element is an object which has a gps point associated with it.
 * and metadata.
 * @author Alex Vaisman, Shay naor
 *
 */
public class Element implements GIS_element {

	private Point3D gps;
	private Mdata metaData;

	public Element(String[] line) {
		double lat, lon, alt;
		lat = Double.parseDouble(line[6]);
		lon = Double.parseDouble(line[7]);
		alt = Double.parseDouble(line[8]);
		this.gps = new Point3D(lat, lon, alt);
		String[] meta = new String[line.length - 3];
		int j = 0;
		for (int i = 0; i < line.length; i++) {
			if (i == 6)//if lat lon alt comntinue to index 9.
				i = 9;
			meta[j++] = line[i];
		}
		this.metaData = new Mdata(meta);
	}

	public Point3D getGps() {
		return gps;
	}

	public Mdata getMetaData() {
		return metaData;
	}

	public Geom_element getGeom() {
		return this.gps;
	}

	public Meta_data getData() {
		return this.metaData;
	}
	
	/**
	 * Transforms a gps point by a vector point in meters.
	 */
	public void translate(Point3D vec) {
		MyCoords cor = new MyCoords();
		this.gps = cor.add(this.gps, vec);
	}
	


}
