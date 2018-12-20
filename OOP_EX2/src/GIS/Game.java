
package GIS;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import File_format.MyFileUtils;

/**
 * This class represents a Game.
 * A game is an object with two ArrayList associated with it.
 * one ArrayList for fruits, and one for pacmans.
 * This class will receive a path to a CSV file.
 * it will read the file and add each element fruit or pacman to the correct array.
 * @author Shay Naor , Alex VAisman
 *
 */
public class Game {
	private ArrayList<Fruit> fruits;// contains all the Fruits.
	private ArrayList<Pacman> pacmans;// contains all the Pacmans.

	
	public Game() {
		this.fruits = new ArrayList<Fruit>();
		this.pacmans = new ArrayList<Pacman>();
	}
	
	/**
	 * This function receives a path to a CSV file.
	 * it will read the file and then read each line.
	 * if the object in the line is pacman it will add it to ArrayList<Pacman>.
	 * if the object in the line is a fruit it will add it to ArrayList<Fruit>.
	 * Will throw ParseException if a CSV line is not written in the correct format.
	 * @param path, the path to the CSV file.
	 */
	public Game(String path) {
		this.fruits = new ArrayList<Fruit>();
		this.pacmans = new ArrayList<Pacman>();

		ArrayList<String[]> gameData = new ArrayList<String[]>();// contains the csv data.

		try {
			gameData = MyFileUtils.readCSVFile(path, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String line[] = {};

		/*
		 * Goes throw the gameData and if its Fruit adds to Fruit and if its Pacman adds
		 * to Pacman.
		 */
		for (int i = 1; i < gameData.size(); i++) {
			
			line = gameData.get(i);
			
			if (line[0].contains("P")) {
				
				try {
					Pacman pac = new Pacman(line);
					this.pacmans.add(pac);
				
				} catch (ParseException e) {
					System.err.println("The csv file is corrupted");
					System.err.println("Make sure each line in cvs file writen in the following format");
					System.err.println(
							"type:string/char, id:int/double, lat:double, lon:double, lat:double, speed:int/double, radius:int/double ");
					e.printStackTrace();
				}

			}
			if (line[0].contains("F")) {
				try {
					Fruit fruit = new Fruit(line);
					this.fruits.add(fruit);
					
				} catch (ParseException e) {
					System.err.println("The csv file is corrupted");
					System.err.println("Make sure each line in cvs file writen in the following format");
					System.err.println(
							"type:string/char, id:int/double, lat:double, lon:double, lat:double, speed:int/double, radius:int/double ");
					e.printStackTrace();
				}
			}
		}

	}
	
	
	
	//======Getters======
	

	public void setFruits(ArrayList<Fruit> fruits) {
		this.fruits = fruits;
	}


	public void setPacmans(ArrayList<Pacman> pacmans) {
		this.pacmans = pacmans;
	}


	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	public ArrayList<Pacman> getPacmans() {
		return pacmans;
	}

}
