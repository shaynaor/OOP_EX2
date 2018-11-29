package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class Element implements GIS_element{
   
	private Point3D gps;
	private String metaData;
	
	public Element(String line) {
		
	}

	public Geom_element getGeom() {
		return null;
	}

	public Meta_data getData() {
		return null;
	}

	public void translate(Point3D vec) {
		
	}
	

}
