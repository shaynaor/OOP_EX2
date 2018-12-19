package GIS;

import java.text.ParseException;

import Geom.Point3D;
/**
 * This class represents the Meta data of pacman and fruit .
 * for pacman it will save his id , speed and eating radius.
 * for fruit it will save its id and  Weight.
 * @author Alex vaisman, Shay naor.
 *
 */
public class Mdata_game implements Meta_data {
	private int id;
	private double speedWeight;
	private double radius;

	/**
	 * Constructor , receives a string.
	 * will input the correct value depending on if the line is a pacman or fruit.
	 * @param meta
	 * @throws ParseException
	 */
	public Mdata_game(String[] meta) throws ParseException {
		this.id = Integer.parseInt(meta[1]);
		this.speedWeight = Double.parseDouble(meta[5]);
		if (meta[0].contains("P")) {
			this.radius = Double.parseDouble(meta[6]);
		}
	}
  /**
   * Constructor, creates a default pacman or fruit with default values of
   * speedWeight = 1, radius - 1.
   * @param id
   * @param isPacman
   */
	public Mdata_game(int id, boolean isPacman) {
		this.id = id;
		this.speedWeight = 1;
		if (isPacman)
			this.radius = 1;
		
	}
    
	
	//========Getters=========
	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getId() {
		return id;
	}

	public double getSpeedWeight() {
		return speedWeight;
	}

	public double getRadius() {
		return radius;
	} 
}

