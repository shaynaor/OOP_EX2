package GIS;

import Geom.Point3D;

public class Mdata implements Meta_data{
//	private String name;
//	private String time;
	private String[] description;
	
	public Mdata(String[] meta) {
//		this.name = meta[1];
//		this.time = meta[3];
		this.description = meta;
		
	}
	
	
	public long getUTC() {
		long t = Long.parseLong(this.description[3]);
		return t;
	}

	
	public Point3D get_Orientation() {
		return null;
	}
	
	public String toString() {
		String s = "";
		for(int i = 0 ; i < description.length ; i++) {
			s += description[i] + ", ";
		}
		return s;
	}
	
	

}
