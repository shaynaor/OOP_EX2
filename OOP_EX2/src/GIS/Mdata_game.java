package GIS;

import java.text.ParseException;

import Geom.Point3D;

public class Mdata_game implements Meta_data {
	private int id;
	private double speedWeight;
	private double radius;

	public Mdata_game(String[] meta) throws ParseException {
		this.id = Integer.parseInt(meta[1]);
		this.speedWeight = Double.parseDouble(meta[5]);
		if (meta[0].contains("P")) {
			this.radius = Double.parseDouble(meta[6]);
		}
	}

	public Mdata_game(int id, boolean isPacman) {
		this.id = id;
		this.speedWeight = 1;
		if (isPacman)
			this.radius = 1;
		
	}

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

