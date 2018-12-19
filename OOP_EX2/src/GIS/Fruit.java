package GIS;

import java.text.ParseException;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;
/**
 * This class represents a Fruit.
 * A fruit is an object with a gps point associated to it,
 * a score , how much its worth when eaten.
 * an id, a boolean eaten flag to tell if it was eaten or not.
 * and a timer to tell when it was eaten.
 * @author Alex Vaisman ,Shay naor.
 *
 */
public class Fruit implements GIS_element {
	private Point3D gps;
	private Mdata_game metaData;
	private boolean eaten;
	private double timeEaten;


   /**
    * Fruit Constructor receives a line from a CSV file and creates a fruit from the given data.
    * @param line ,  a line from CSV file.
    * @throws ParseException , Throws ParseException if CSV data is incorrect.
    */
	public Fruit(String[] line) throws ParseException {
		double lat = 0, lon = 0, alt = 0;
		lat = Double.parseDouble(line[2]);
		lon = Double.parseDouble(line[3]);
		alt = Double.parseDouble(line[4]);
		this.gps = new Point3D(lat, lon, alt);
        this.eaten = false;
		this.metaData = new Mdata_game(line);
	}
	
	/**
	 * Fruit constructor , creates a fruit from gps lat and lon.
	 * @param x , the lat value.
	 * @param y , the lon value.
	 * @param id , the id of the fruit.
	 */
	public Fruit(double x, double y, int id) {
		double lat = x, lon = y, alt = 0;
		this.gps = new Point3D(lat, lon, alt);
		
		this.metaData = new Mdata_game(id, false);
	}
    
	
	//======Getters=======
	public Point3D getGps() {
		return gps;
	}

	public Mdata_game getMetaData() {
		return metaData;
	}

	public Geom_element getGeom() {
		return this.gps;
	}

	public Meta_data getData() {
		return this.metaData;
	}
	
	public boolean getEaten() {
		return this.eaten;
	}
	
	public void eaten(double time) {
		setEaten(true);
		this.timeEaten = time;
	}
    
	private void setEaten(boolean flag) {
		this.eaten = flag;
	}

	public double getTimeEaten() {
		return timeEaten;
	}

	/**
	 * Transforms a gps point by a vector point in meters.
	 */
	public void translate(Point3D vec) {
		MyCoords cor = new MyCoords();
		this.gps = cor.add(this.gps, vec);
	}

}