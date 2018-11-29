package GIS;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Geom.Point3D;

public class Mdata implements Meta_data{

	private String[] description;
	private long time;
	
	public Mdata(String[] meta) {
		this.description = meta;
		
	}
	public Mdata() {
		this.time = getUTC();	
	}
	
	
	public long getUTC() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		long milliseconds = 0;
		try {
			Date d = (Date) format.parse(this.description[3]);
			format.getCalendar().getTime();
		    milliseconds = d.getTime();
			} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(milliseconds);
		return milliseconds;
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
