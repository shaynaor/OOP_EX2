package GIS;
import java.text.ParseException;
import java.util.Arrays;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Pacman implements GIS_element {

	private Point3D gps;
	private Meta_data metaData;


	public Pacman(String[] line) throws ParseException {
		double lat=0, lon=0, alt =0;
		lat = Double.parseDouble(line[2]);
		lon = Double.parseDouble(line[3]);
		alt = Double.parseDouble(line[4]);
		this.gps = new Point3D(lat, lon, alt);
		
		this.metaData = new Mdata_game(line);
	}
	
	public Point3D getGps() {
		return gps;
	}

	public Meta_data getMetaData() {
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
