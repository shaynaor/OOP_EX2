package GIS;

import java.util.Arrays;

import Geom.Geom_element;
import Geom.Point3D;

public class Element implements GIS_element {

	private Point3D gps;
	private Meta_data metaData;

	public Element(String[] line) {
		double lat, lon, alt;
		lat = Double.parseDouble(line[6]);
		lon = Double.parseDouble(line[7]);
		alt = Double.parseDouble(line[8]);
		this.gps = new Point3D(lat, lon, alt);
		String[] meta = new String [line.length -3] ;
		int j=0;
		for(int i = 0 ; i < line.length ; i++) {
			if(i == 6) i = 9;
			meta[j++] = line[i];
		}
		this.metaData = new Mdata(meta);
	}

	public Geom_element getGeom() {
		return this.gps;
	}

	public Meta_data getData() {
		return this.metaData;
	}

	public void translate(Point3D vec) {

	}
	
	public static void main(String []args) {
		String[] test = {"14:ae:db:58:73:75", "love", "[WPA2-PSK-CCMP][ESS]", "2017-12-01 11:01:26", "1", "-58", "32.17229583572982","34.814265874458584", "32.03668527946496", "3", "WIFI"};
		Element elem = new Element(test);
		System.out.println(elem.metaData.toString());
	}

}
