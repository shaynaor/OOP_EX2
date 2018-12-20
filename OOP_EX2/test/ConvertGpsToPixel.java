import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Coords.Convert_pixel_gps;
import Geom.Pixel;
import Geom.Point3D;
import Gui.Map;

class ConvertGpsToPixel {



	@Test
	/**
	 * This test generates random map with width and height as well random map boarders represented
	 * as two corners bottom right and top left.
	 * then the test will generate a random gps point within this boarders convert it to pixel and back to gps.
	 * if the gps point moved in the lat or lon more then 0.01 the test will fail.
	 * the test will check 10000 random maps and points.
	 * the offset of the gps point is depending on the map resolution which is random.
	 * the higher the resolution the lower the offset.
	 * resolution moves from 200X200 to 5000X5000.
	 */
	void GpsToPixelAndBack() {
		int height;
		int width;
		double lat;
		double lon;
		Map map = new Map();
		for(int i = 0 ; i<10000 ;i++) {
			//====Generating random topleft botright points with difference of 0.5======
			lat = ThreadLocalRandom.current().nextDouble(30, 40 + 1);
			lon = ThreadLocalRandom.current().nextDouble(30, 40 + 1);
			Point3D TopLeft = new Point3D (lat,lon,0);
			Point3D BotRight = new Point3D (lat-0.5,lon+0.5,0);
			//====Generating random point within the area=========
			double x = ThreadLocalRandom.current().nextDouble(0.001, -0.920 + 1);
			double y = ThreadLocalRandom.current().nextDouble(0.001, -0.920 + 1);
			Point3D gps = new Point3D (TopLeft.x()-x, BotRight.y() - y,0 );
			//====Generating random ratios ============
			height = ThreadLocalRandom.current().nextInt(200, 5000 + 1);
			width = ThreadLocalRandom.current().nextInt(200, 5000 + 1);
			//====Setting map =====
			map.setHeight(height);
			map.setWidth(width);
			map.setTopLeft(TopLeft);
			map.setBotRight(BotRight);
			//====Testing converter=====
			Convert_pixel_gps convert = new Convert_pixel_gps(map);
			Pixel p = new Pixel(0,0);
			p = convert.convertGPStoPixel(gps);
			Point3D gpsAfter = new Point3D(0,0,0);
			gpsAfter = convert.convertPixeltoGPS(p);

			if(Math.abs((gps.x()-gpsAfter.x()))>0.01 || Math.abs((gps.y()-gpsAfter.y()))>0.01) {
				System.out.println(gps);
				System.out.println(gpsAfter);
				fail("Not yet implemented");
			}
		}

	}

}
