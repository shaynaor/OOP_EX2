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

	public Map(int width, int height, Point3D topLeft, Point3D botRight) {
		this.width = width;
		this.height = height;
		this.topLeft = topLeft;
		this.botRight = botRight;
		RpixelMeters();
	}

	private void RpixelMeters() {
		Point3D botLeft = new Point3D(botRight.x(), topLeft.y());
		MyCoords coords = new MyCoords();
		double distanceHeight;
		double distanceWidth;

		/* Finding Height in meters */
		distanceHeight = coords.distance3d(this.topLeft, botLeft);
		distanceHeight /= this.height;

		/* inding Width in meters */
		distanceWidth = coords.distance3d(botLeft, this.botRight);
		distanceWidth /= this.width;

		this.ratioHeight = distanceHeight;
		this.ratioWidth = distanceWidth;
	}

	public Pixel convertGPStoPixel(Point3D gps) {
		if (!isValid_GPS_Point(gps)) {
			System.err.println("Ivalid gps point, the gps point is outside the map!");
			throw new RuntimeException();
		}
		MyCoords coords = new MyCoords();

		double[] info = coords.azimuth_elevation_dist(gps, this.topLeft);
		double azi = info[0];
		double distance = coords.distance3d(gps, this.topLeft);
		double dy;
		double dx;
		int pixelX;
		int pixelY;
		/* Converting azi to degrees and then to radians */
		azi = azi % 90;
		azi = Math.toRadians(azi);

		/* Finding right triangle a side */
		dy = distance * Math.sin(azi);
		/* Finding right triangle b side */
		dx = distance * Math.cos(azi);

		pixelX = (int) (dx / ratioHeight);
		pixelY = (int) (dy / ratioWidth);

		Pixel pixel = new Pixel(pixelX, pixelY);

		return pixel;
	}

	public Point3D convertPixeltoGPS(Pixel pixel) {

		double pixelX = pixel.getX();
		double pixelY = pixel.getY();

		/* Calculate lon */
		double deltaLon = this.botRight.y() - this.topLeft.y();
		double y = pixelX * deltaLon;
		y /= this.width;
		y += this.topLeft.y();

		/* Calculate lat */
		double deltaLat = this.topLeft.x() - this.botRight.x();
		double x = pixelY * deltaLat;
		x /= this.height;
		x -= this.topLeft.x();
		x = Math.abs(x);

		Point3D gps = new Point3D(x, y);

		return gps;
	}

	public double distancePixel(Pixel a, Pixel b) {
		double x1 = a.getX();
		double x2 = b.getX();
		double y1 = a.getY();
		double y2 = b.getY();
		double distance;

		distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return distance;
	}

	public double anglePixel(Pixel a, Pixel b) {
		double dist = distancePixel(a, b);
		double dx;
		double angle;
		dx = b.getX() - a.getX();
		angle = Math.acos(dx / dist);
		angle = Math.toDegrees(angle);
		return angle;
	}

	public boolean isValid_GPS_Point(Point3D p) {
		if (p == null)
			return false;

		double lat = p.x(), lon = p.y();

		if (lat > this.topLeft.x() || lat < this.botRight.x())
			return false;

		if (lon > this.botRight.y() || lon < this.topLeft.y())
			return false;

		return true;
	}

	public static void main(String[] args) {
		Point3D topLeft = new Point3D(32.105689, 35.202411);
		Point3D botRight = new Point3D(32.101931, 35.212397);

		Point3D check = new Point3D(32.102495, 35.207474);

		Map map = new Map(1433, 642, topLeft, botRight);

		Pixel pi = new Pixel(731, 543);

		Point3D gps = map.convertPixeltoGPS(pi);

		pi = map.convertGPStoPixel(gps);

		System.out.println(pi.getX() + " ," + pi.getY());

	}

}