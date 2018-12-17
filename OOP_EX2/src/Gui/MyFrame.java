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

import GIS.Fruit;
import GIS.Game;
import GIS.Pacman;
import Geom.Point3D;

public class MyFrame extends JFrame implements MouseListener {
	private BufferedImage myImage;
	private Game game;
	private boolean isPacman;
	private boolean isFruit;

	public MyFrame() {
		this.isPacman = false;
		this.setFruit(false);
		this.game = new Game();
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

		/*
		 * Add action to load File button
		 * https://stackoverflow.com/questions/15703214/save-file-open-file-dialog-box-
		 * using-swing-netbeans-gui-editor
		 */
		loadFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
				setPacman(false);
				setFruit(false);
			}
		});

		/*
		 * Add action to save File button
		 * https://stackoverflow.com/questions/15703214/save-file-open-file-dialog-box-
		 * using-swing-netbeans-gui-editor
		 */
		saveFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser saveFile = new JFileChooser();
				saveFile.showSaveDialog(null);
				setPacman(false);
				setFruit(false);
			}
		});

		/* Add action to clear File button */
		clearFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Game game = getGame();
				game.getFruits().clear();
				game.getPacmans().clear();
				setPacman(false);
				setFruit(false);
				repaint();
			}
		});

		/* Add action to exit File button */
		exitFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		/* Add action to pacman input button */
		pacmanInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				setPacman(true);
				setFruit(false);
			}
		});

		/* Add action to fruit input button */
		fruitInput.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				setFruit(true);
				setPacman(false);
			}
		});

	}

	public void paint(Graphics g) {
		g.drawImage(this.myImage, -9, -9, this.getWidth(), this.getHeight(), this);

		/* Draw pacmans */
		Iterator<Pacman> pacIt = game.getPacmans().iterator();
		while (pacIt.hasNext()) {
			Pacman pac = pacIt.next();
			int r = 30;
			int x = pac.getGps().ix() - (r / 2);
			int y = pac.getGps().iy() - (r / 2);
			g.setColor(Color.yellow);
			g.fillOval(x, y, r, r);
		}
		
		/* Draw fruits */
		Iterator<Fruit> fruitIt = game.getFruits().iterator();
		while (fruitIt.hasNext()) {
			Fruit fruit = fruitIt.next();
			int r = 15;
			int x = fruit.getGps().ix() - (r / 2);
			int y = fruit.getGps().iy() - (r / 2);
			g.setColor(Color.red);
			g.fillOval(x, y, r, r);
		}
	}

	public BufferedImage getMyImage() {
		return myImage;
	}

	public Game getGame() {
		return this.game;
	}

	public boolean isPacman() {
		return isPacman;
	}

	public boolean isFruit() {
		return isFruit;
	}

	public void setFruit(boolean isFruit) {
		this.isFruit = isFruit;
	}

	public void setPacman(boolean isPacman) {
		this.isPacman = isPacman;
	}

	public void mouseClicked(MouseEvent e) {
		/* If want to add pacmans */
		if (isPacman) {
			int x = e.getX();
			int y = e.getY();
			Pacman pac = new Pacman(x, y, this.game.getPacmans().size());
			this.game.getPacmans().add(pac);
		}

		if (isFruit) {
			int x = e.getX();
			int y = e.getY();
			Fruit fruit = new Fruit(x, y, this.game.getFruits().size());
			this.game.getFruits().add(fruit);
		}

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
