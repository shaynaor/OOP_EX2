package Gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;
/**
 * This class represents a map.
 * a map is an object that receives an image of an area,
 * and the gps points of the top left and bottom right corners of the image.
 * it will also same the value of how much pixels are in the image. as heightXwidth.
 * @author Alex vaisman, Shay naor.
 *
 */
public class Map {
	private BufferedImage myImage;
	private int width;
	private int height;
	private Point3D topLeft;
	private Point3D botRight;

	/**
	 * This function loads an image and inputs the topleft and bot right gps values.
	 */
	public Map() {
		/* INIT myImge filed */
		try {
			this.myImage = ImageIO.read(new File("C:\\Users\\User\\Desktop\\מונחה- מטלה 3\\data\\Ariel1.png"));
		} catch (IOException e) {
			System.err.println("ERROR: incorrect path for picture!");
			e.printStackTrace();
		}

		this.width = myImage.getWidth();
		this.height = myImage.getHeight();
		this.topLeft = new Point3D(32.105689, 35.202411);
		this.botRight = new Point3D(32.101931, 35.212397);
	}







	//=======Getters========
	public BufferedImage getMyImage() {
		return myImage;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Point3D getTopLeft() {
		return topLeft;
	}

	public Point3D getBotRight() {
		return botRight;
	}
	
	public void setTopLeft(Point3D topLeft) {
		this.topLeft = topLeft;
	}


	public void setBotRight(Point3D botRight) {
		this.botRight = botRight;
	}

}