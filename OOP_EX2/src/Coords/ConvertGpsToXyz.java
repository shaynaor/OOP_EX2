package Coords;

import java.util.Arrays;

public class ConvertGpsToXyz {

	public static final double a = 6378137;
	public static final double f = 1 / 298.257223563;
	public static final double b = a * (1 - f);
	public static final double e = Math.sqrt((Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(a, 2));

	public static final double e2 = Math.sqrt((Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(b, 2));

	public static double[] ecef2lla(double x, double y, double z) {

		double[] lla = { 0, 0, 0 };
		double lat, lon, height, N, theta, p;

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
		lla[0] = lat;
		lla[1] = lon;
		lla[2] = height;
		return lla;
	}

	public static double[] lla2ecef(double lat, double lon, double h) { // WORKS PERFECT DONT TUCH!!! PLZZ
		double xyz[] = { 0, 0, 0 };

		double cosLat = Math.cos(lat * Math.PI / 180.0);
		double sinLat = Math.sin(lat * Math.PI / 180.0);
		double cosLon = Math.cos(lon * Math.PI / 180.0);
		double sinLon = Math.sin(lon * Math.PI / 180.0);

		double C = 1.0 / Math.sqrt(cosLat * cosLat + (1 - f) * (1 - f) * sinLat * sinLat);
		double S = (1.0 - f) * (1.0 - f) * C;
		xyz[0] = (a * C + h) * cosLat * cosLon;
		xyz[1] = (a * C + h) * cosLat * sinLon;
		xyz[2] = (a * S + h) * sinLat;

		return xyz;
	}

	public static void main(String[] args) {

		double[] la = { 32.10332, 35.20904, 670 };

		double[] xyz = lla2ecef(la[0], la[1], la[2]);
		System.out.println(Arrays.toString(xyz));

		double[] lla = ecef2lla(4419073.632180005, 3118357.8366222475, 3370603.042737164); // +105z

		System.out.println(Arrays.toString(lla));
		// System.out.println(Arrays.toString(la));
	}

}