package Algorithms;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;

import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Pacman;
import GIS.Path;
import GIS.Solution;
import Geom.Point3D;
/**
 * This class receives a game and finds a path for each Pacman ,such as
 * each fruit is eaten in the end of the game.
 * @author Alex vaisman , Shay naor.
 */
public class ShortestPathAlgo {
	private Game game;
	private Solution solution;
	private final ComperByDist distCmpFruit = new ComperByDist();
	private final ComperByDistPacman distCmpPac = new ComperByDistPacman();

	/**
	 * This function is the Constructor.
	 * it calls algo function.
	 * @param game
	 */
	public ShortestPathAlgo(Game game) {
		this.game = game;
		this.solution = new Solution();
		/* findPath() ; full search algo */
		algo();
	}

	/**
	 * This function checks if the input is valid.
	 * if we receive 0 pacman or 0 fruits there is no calculation to be done.
	 * 
	 */
	private void algo() {
		if (this.game.getFruits().isEmpty() || this.game.getPacmans().isEmpty())
			return;
		else
			startAlgo();
	}

	/**
	 * This function finds the distance of all pacmans and fruits from the top left corner of the game.
	 * it will sort the packmans and the fruits according to that distance.
	 * then the function depending on how much fruits there are will assign each packman what fruits
	 * he will have to eat and create a path.
	 * then the function will send the path to pathAlgo which will find the shortest route given the path.
	 */
	private void startAlgo() {

		/* Create array list of all the fruits */
		ArrayList<Fruit> fruits = new ArrayList<Fruit>();
		fruits = game.getFruits();
		fruits.sort(distCmpFruit);

		/* Create array list of all the pacmans */
		ArrayList<Pacman> pacmans = new ArrayList<Pacman>();
		pacmans = game.getPacmans();
		pacmans.sort(distCmpPac);

		int numOfPac = pacmans.size();
		int numOfFru = fruits.size();
		int numOfFruInPath = numOfFru / numOfPac;// need to fix (not divided - so have more fruits in the end)

		/*
		 * Create paths with the pacmans in the beginning of the paths and after the
		 * fruits that the pacman need to eat.
		 */

		Iterator<Pacman> pacIt = pacmans.iterator();
		Iterator<Fruit> fruitIt = fruits.iterator();
		Pacman pac = new Pacman(0, 0, 0);//ask alex if need to be in while loop******
		Fruit fruit = new Fruit(0, 0, 0);

		while (pacIt.hasNext() && fruitIt.hasNext()) {
			pac = pacIt.next();
			Path path = new Path();
			path.getPath().add(pac);
			int startWith = 0;
			for (int i = startWith; i < numOfFruInPath; i++) {
				fruit = fruitIt.next();
				path.getPath().add(fruit);
			}
			pathAlgo(path);
			startWith += numOfFruInPath;
		}

	}
	/**
	 * This function will receive a path containing one pacman and several fruits.
	 * it will find the shortest path for the pacman to go between the fruits.
	 * @param path , the path the function receives.
	 */
	private void pathAlgo(Path path) {
		Path ans =  new Path();
		double min = Double.MAX_VALUE;
		Pacman pac =  new Pacman(0,0,0);
		Fruit fruit = new Fruit(0, 0, 0);
		pac = (Pacman)path.getPath().get(0);
		Iterator<GIS_element> itFruit = path.getPath().iterator();
		itFruit.next();
		int index = 1;
		int iteratorIndex = 0;
		Point3D CurrentPoint = pac.getGps();
		double distance = 0;
		MyCoords convert = new MyCoords();
		Fruit fruitMin = new Fruit(0, 0, 0);
		double time = 0;
		ans.getPath().add(pac);
		// ====== Find closest fruit to pacman , eat it , move pack man to the fruits
		// position, repeat======
		for(int i = 1;i<path.getPath().size();i++) {
			while(itFruit.hasNext()) {
				fruit = (Fruit)itFruit.next();
				iteratorIndex++;
				distance = convert.distance3d(CurrentPoint, fruit.getGps());

				// =======If fruit is not eaten find the closest fruit to the current position==========
				if (((Fruit)(path.getPath().get(iteratorIndex))).getEaten() == false) {
					if (distance < min) {
						fruitMin = fruit;
						min = distance;
						index = iteratorIndex;
					}
				}

			}
			// =======Eating fruit adding to ans=======
			time = time + (min / pac.getMetaData().getSpeedWeight());
			((Fruit)(path.getPath().get(index))).eaten(time);
			ans.getPath().add(fruitMin);
			ans.addDistance(min);
			// ==========Reseting values=======
			CurrentPoint = fruitMin.getGps();
			itFruit = path.getPath().iterator();
			itFruit.next();
			index = 1;
			iteratorIndex = 0;
			min = Double.MAX_VALUE;
			distance = 0;
		}
		this.solution.getSolution().add(ans);
	}



	public Solution getSolution() {
		return solution;
	}

}