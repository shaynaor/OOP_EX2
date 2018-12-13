package Gui;

import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	private BufferedImage myImage;

	public MyFrame() {
		initGUI();
	}

	private void initGUI() {
		/* Create the menu bar. */
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem loadFile = new MenuItem("Load");
		MenuItem saveFile = new MenuItem("Save");
		MenuItem clearFile = new MenuItem("Clear");
		MenuItem exitFile = new MenuItem("Exit");

		fileMenu.add(loadFile);
		fileMenu.add(saveFile);
		fileMenu.add(clearFile);
		fileMenu.add(exitFile);

		menuBar.add(fileMenu);

		Menu inputMenu = new Menu("Input");
		MenuItem pacmanInput = new MenuItem("Pacman");
		MenuItem fruitInput = new MenuItem("Fruit");

		inputMenu.add(pacmanInput);
		inputMenu.add(fruitInput);

		menuBar.add(inputMenu);

		setMenuBar(menuBar);
		/* End to create the menu bar. */

		try {
			myImage = ImageIO.read(new File("C:\\Users\\User\\Desktop\\מונחה- מטלה 3\\Ex3\\Ariel1.png"));
		} catch (IOException e) {
			System.err.println("ERROR: incorrect path for picture!");
			e.printStackTrace();
		}


	}

	public void paint(Graphics g) {
		g.drawImage(myImage, 0, 0, this);
		
	}

	public BufferedImage getMyImage() {
		return myImage;
	}

}
