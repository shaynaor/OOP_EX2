package GIS;

import Geom.Pixel;
import Geom.Point3D;

public class Map {

	private int width;
	private int height;
	private double maxLat;
	private double minLat;
	private double maxLon;
	private double minLon;





	public Map(int width, int height,double maxLat,double minLat,double maxLon,double minLon) {
		this.width = width;
		this.height = height;
		this.maxLat = maxLat;
		this.minLat = minLat;
		this.maxLon = maxLon;
		this.minLon = minLon;
	}

	public Pixel convertGPStoPixel(Point3D gps) {


		return null;
	}

	public Point3D convertPixeltoGPS(Pixel pixel) {


		return null;
	}
	
	
	public double distancePixel (Pixel a, Pixel b) {
		
		return 0;
	}
	
	public double AzimutPixel (Pixel a, Pixel b) {
		
		return 0;
	}




}
