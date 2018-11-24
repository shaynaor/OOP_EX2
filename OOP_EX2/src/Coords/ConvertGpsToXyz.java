package Coords;

import java.util.Arrays;
import Geom.Point3D;

public class ConvertGpsToXyz {

	public static final double a = 6378137;
	public static final double f = 1 / 298.257223563;
	public static final double b = a * (1 - f);
	public static final double e = Math.sqrt((Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(a, 2));
	public static final double e2 = Math.sqrt((Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(b, 2));

	public static Point3D ecef2lla(Point3D vec) {

		double lat, lon, height, N, theta, p;
		double x = vec.x(), y = vec.y(), z = vec.z();

		p = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		theta = Math.atan((z * a) / (p * b));
		lon = Math.atan(y / x);
		lat = Math.atan(((z + Math.pow(e2, 2) * b * Math.pow(Math.sin(theta), 3))
				/ ((p - Math.pow(e, 2) * a * Math.pow(Math.cos(theta), 3)))));
		N = a / (Math.sqrt(1 - (Math.pow(e, 2) * Math.pow(Math.sin(lat), 2))));

		double m = (p / Math.cos(lat));
		height = m - N;

		lon = lon * 180 / Math.PI;
		lat = lat * 180 / Math.PI;

		Point3D point = new Point3D(lat, lon, height);
		return point;
	}

	public static Point3D lla2ecef(Point3D vec) {
		
		double lat = vec.x(), lon = vec.y(), h = vec.z();
		
		double cosLat = Math.cos(lat * Math.PI / 180.0);
		double sinLat = Math.sin(lat * Math.PI / 180.0);
		double cosLon = Math.cos(lon * Math.PI / 180.0);
		double sinLon = Math.sin(lon * Math.PI / 180.0);

		double C = 1.0 / Math.sqrt(cosLat * cosLat + (1 - f) * (1 - f) * sinLat * sinLat);
		double S = (1.0 - f) * (1.0 - f) * C;
		double x = (a * C + h) * cosLat * cosLon;
		double y = (a * C + h) * cosLat * sinLon;
		double z = (a * S + h) * sinLat;
		
		Point3D point = new Point3D(x, y, z);

		return point;
	}

	public static void main(String[] args) {

		Point3D pointLla = new Point3D (32.10332 , 35.20904 , 670);
		Point3D pointEcef = new Point3D ( lla2ecef(pointLla));
		Point3D pointLlaNew = new Point3D (ecef2lla(pointEcef));
		System.out.println(pointLlaNew);
		
		
	}

}