package Coords;

import Geom.Pixel;
import Geom.Point3D;
import Gui.Map;

public class Convert_pixel_gps {


	private Map map;
	private double ratioHeight;
	private double ratioWidth;

	public Convert_pixel_gps(Map map) {
		this.map = map;
		RpixelMeters();
	}

	private void RpixelMeters() {

		Point3D botLeft = new Point3D(map.getBotRight().x(), map.getTopLeft().y());
		MyCoords coords = new MyCoords();
		double distanceHeight;
		double distanceWidth;

		/* Finding Height in meters */
		distanceHeight = coords.distance3d(map.getTopLeft(), botLeft);
		distanceHeight /= this.map.getHeight();
		
		/* Finding Width in meters */
		distanceWidth = coords.distance3d(botLeft, map.getBotRight());
		distanceWidth /= this.map.getWidth();

		this.ratioHeight =distanceWidth; 
		this.ratioWidth = distanceHeight;
	}

	public Pixel convertGPStoPixel(Point3D gps) {
		if (!isIn(gps)) {
			System.err.println("Ivalid gps point, the gps point is outside the map!");
			throw new RuntimeException();
		}
		MyCoords coords = new MyCoords();

		double[] info = coords.azimuth_elevation_dist(gps, map.getTopLeft());
		double azi = info[0];
		double distance = coords.distance3d(gps, map.getTopLeft());
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
		double deltaLon = map.getBotRight().y() - map.getTopLeft().y();
		double y = pixelX * deltaLon;
		y /= this.map.getWidth();
		y += map.getTopLeft().y();

		/* Calculate lat */
		double deltaLat = map.getTopLeft().x() - map.getBotRight().x();
		double x = pixelY * deltaLat;
		x /= this.map.getHeight();
		x -= map.getTopLeft().x();
		x = Math.abs(x);

		Point3D gps = new Point3D(x, y);

		return gps;
	}

	public boolean isIn(Point3D p) {
		if (p == null)
			return false;

		double lat = p.x(), lon = p.y();

		if (lat > map.getTopLeft().x() || lat < map.getBotRight().x())
			return false;

		if (lon > map.getBotRight().y() || lon < map.getTopLeft().y())
			return false;

		return true;
	}
	

}
