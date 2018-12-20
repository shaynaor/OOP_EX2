import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Coords.Range;
import GIS.Fruit;
import Geom.Point3D;

class TestRange {



	@Test
	void test() {
		Range range = new Range();
		double x;
		double y;
		double time;
		double delta;
		double distanceAB;
		double distance;
		MyCoords Convert = new  MyCoords();

		for(int i = 0;i<1000;i++) {
			//====Creating fruits with random time====
			x = 32.105689;
			y = 35.212397;
			time = ThreadLocalRandom.current().nextInt(0, 100 + 1);
			Fruit a = new Fruit(x,y,0);
			a.eaten(time);
			x = 32.101931;
			y = 35.202411;
			time = ThreadLocalRandom.current().nextInt(1, 100 + 1);
			Fruit b = new Fruit(x,y,0);
			b.eaten(a.getTimeEaten()+time);
			//====Finding with Range the point between a and b for some random time====
			delta= b.getTimeEaten() - a.getTimeEaten();
			time = b.getTimeEaten() - ThreadLocalRandom.current().nextDouble(0, delta );
			Point3D ans = range.getPosInTime(time,b, a);
			//====Checking if the distance between a and b is diffent from distance of ans to a + ans to b,
			//====with offset of more then 1 meter then fail.====
			distanceAB = Convert.distance3d(a.getGps(), b.getGps());
			distance = Convert.distance3d(ans, a.getGps());
			distance = distance + Convert.distance3d(ans, b.getGps());

			if(Math.abs(distance-distanceAB)>1) {
				fail("Not yet implemented");
			}
		}


	}

}
