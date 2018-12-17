package Geom;

public class Pixel {
	private int x;
	private int y;

	public Pixel(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

	public String toString() {
		return "("+ x + "," + y + ")";
	}
	

}
