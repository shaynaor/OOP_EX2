import Coords.MyCoords;
import Geom.Point3D;

public class test {

	public static void main(String[] args) {
		Point3D p1 = new Point3D(32.10332,35.20904,200);
		Point3D p2 = new Point3D(32.10635,35.20523,400);
		MyCoords azi = new MyCoords();
		double [] ans;
		ans = azi.azimuth_elevation_dist(p1, p2);
		double elevation =ans[1];
		double azimuth = ans[0];
		double distance = azi.distance3d(p1, p2);
		

		


        System.out.println("elveation: "+ elevation);
		System.out.println("azi: "+ azimuth);
		System.out.println("d: "+distance);
	}

}
