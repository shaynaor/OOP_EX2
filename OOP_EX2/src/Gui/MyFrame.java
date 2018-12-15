package Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import GIS.Game;
import Geom.Point3D;

public class MyFrame extends JFrame implements MouseListener {
	private BufferedImage myImage;
	private ArrayList<Point3D> arrPoint;
	private Game game;

	public MyFrame() {

		this.arrPoint = new ArrayList<Point3D>();
		initGUI();
		this.addMouseListener(this);
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
		
		/* INIT myImge filed */
		try {
			myImage = ImageIO.read(new File("C:\\Users\\User\\Desktop\\מונחה- מטלה 3\\Ex3\\Ariel1.png"));
		} catch (IOException e) {
			System.err.println("ERROR: incorrect path for picture!");
			e.printStackTrace();
		}
		
		/* Add action to load File button */
		loadFile.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        ChooseButton1MouseClicked(e);
		    }
		});
       
		

	}
	/* Add file chooser to load button.
	 * https://stackoverflow.com/questions/27709758/file-chooser-display-with-a-button-click-java-swing */
	private void ChooseButton1MouseClicked(ActionEvent e) {

	    JFileChooser fileChooser = new JFileChooser();
	    int returnValue = fileChooser.showOpenDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) 
	    {
	    File selectedFile = fileChooser.getSelectedFile();
	    System.out.println(selectedFile.getName());
	    }
	}

	public void paint(Graphics g) {
		g.drawImage(this.myImage, -9, -9, this.getWidth(), this.getHeight(), this);

		Iterator<Point3D> pIterator = arrPoint.iterator();
		while (pIterator.hasNext()) {
			Point3D p = pIterator.next();
			int r = 20;
			int x = p.ix() - (r / 2);
			int y = p.iy() - (r / 2);
			g.setColor(Color.blue);
			g.fillOval(x, y, r, r);
		}
	}

	public BufferedImage getMyImage() {
		return myImage;
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		Point3D point = new Point3D(x, y);
		this.arrPoint.add(point);
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
