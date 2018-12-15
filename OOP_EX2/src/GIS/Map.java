package GIS;

import Coords.MyCoords;
import Geom.Pixel;
import Geom.Point3D;

public class Map {

	private int width;
	private int height;
	private Point3D topLeft;
	private Point3D botRight;
	private double ratioHeight;
	private double ratioWidth;



	public Map(int width, int height,Point3D topLeft, Point3D botRight) {
		this.width = width;
		this.height = height;
		this.topLeft = topLeft;
		this.botRight = botRight;
		RpixelMeters();

	}


	private void RpixelMeters() {
		Point3D botLeft = new Point3D(botRight.x(),topLeft.y());
		MyCoords coords = new MyCoords();
		double distanceHeight;
		double distanceWidth;

		//=====finding Height in meters=======
		distanceHeight = coords.distance3d(this.topLeft, botLeft);
		distanceHeight/=this.height;

		//=====finding Width in meters========
		distanceWidth = coords.distance3d(botLeft, this.botRight);
		distanceWidth/=this.width;

		this.ratioHeight = distanceHeight;
		this.ratioWidth = distanceWidth;	
	}





	public Pixel convertGPStoPixel(Point3D gps) {
		//======check if gps in pic?==========


		//======math============
		MyCoords coords = new MyCoords();


		double [] info = coords.azimuth_elevation_dist(gps,this.topLeft);
		double azi = info[0];
		double distance = coords.distance3d(gps, this.topLeft);
		double dy;
		double dx;
		int pixelX;
		int pixelY;
		// === converting azi to degrees and then to radians ====
		azi = azi % 90;
		azi = Math.toRadians(azi);

		//==== finding right triangle a side =====
		dy = distance*Math.sin(azi);
		//==== finding right triangle b side =====
		dx = distance*Math.cos(azi);

		pixelX = (int)(dx/ratioHeight);
		pixelY = (int)(dy/ratioWidth);

		Pixel pixel = new Pixel(pixelX,pixelY);

		return pixel;
	}






	public Point3D convertPixeltoGPS(Pixel pixel) {


		return null;
	}


	public double distancePixel (Pixel a, Pixel b) {
		double x1 = a.getX();
		double x2 = b.getX();
		double y1 = a.getY();
		double y2 = b.getY();
		double distance;

		distance = Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
		return distance;
	}

	public double anglePixel (Pixel a, Pixel b) {
		double dist = distancePixel(a, b);
		double dx;
		double angle;
		dx = b.getX() - a.getX();
		angle = Math.acos(dx/dist);
		angle = Math.toDegrees(angle);
		return angle;
	}


	public static void main(String[] args) {
		Point3D topLeft = new Point3D(32.105689,35.202411 );
		Point3D botRight = new Point3D(32.101931, 35.212397 );
        
		Point3D check =  new Point3D(32.102495, 35.207474);
        
		Pixel p1 = new Pixel(10,10);
		Pixel p2 =  new Pixel(0,0);
		Map map = new Map(1433,642,topLeft,botRight);

		Pixel pixel =map.convertGPStoPixel(check);
		//System.out.println(map.distancePixel(p2, p1));
		System.out.println(map.anglePixel(p2, p1));
	
	}



}
