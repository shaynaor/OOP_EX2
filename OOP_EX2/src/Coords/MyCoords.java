package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter {

	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D p = new Point3D(ConvertGpsToXyz.lla2ecef(gps));
		p.add(local_vector_in_meter);
		p = ConvertGpsToXyz.ecef2lla(p);
		return p;
	}

	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D p0 = new Point3D(ConvertGpsToXyz.lla2ecef(gps0));
		Point3D p1 = new Point3D(ConvertGpsToXyz.lla2ecef(gps1));
		double distance = p0.distance3D(p1);
		return distance;
	}

	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		Point3D p0 = new Point3D(ConvertGpsToXyz.lla2ecef(gps0));
		Point3D p1 = new Point3D(ConvertGpsToXyz.lla2ecef(gps1));

		double x = p1.x() - p0.x();
		double y = p1.y() - p0.y();
		double z = p1.z() - p0.z();

		Point3D p0Top1 = new Point3D(x, y, z);

		return p0Top1;
	}

	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

		return null;
	}

	public boolean isValid_GPS_Point(Point3D p) {
		if (p == null)
			return false;

		double lat = p.x(), lon = p.y(), alt = p.z();

		if (lat < -90 && lat > 90)
			return false;

		if (lon < -180 && lon > 180)
			return false;

		if (alt < -450)
			return false;

		return true;
	}

}
