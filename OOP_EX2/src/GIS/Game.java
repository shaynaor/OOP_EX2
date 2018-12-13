
package GIS;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import File_format.MyFileUtils;

public class Game {
	private ArrayList<Fruit> fruits;
	private ArrayList<Pacman> pacmans;
	private int numFruit;
	private int numPacmans;

	public Game(String path) {
		this.fruits = new ArrayList<Fruit>();
		this.pacmans = new ArrayList<Pacman>();

		ArrayList<String[]> gameData = new ArrayList<String[]>();

		try {
			gameData = MyFileUtils.readCSVFile(path, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String line[] = {};

		for (int i = 1; i < gameData.size(); i++) {
			line = gameData.get(i);
			if (line[0] == "P") {
				try {
					Pacman pac = new Pacman(line);
					this.pacmans.add(pac);
					this.numPacmans++;
				} catch (ParseException e) {
					System.err.println("The csv file is corrupted");
					System.err.println("Make sure each line in cvs file writen in the following format");
					System.err.println(
							"type:string/char, id:int/double, lat:double, lon:double, lat:double, speed:int/double, radius:int/double ");
					e.printStackTrace();
				}

			}
			if (line[0] == "F") {
				try {
					Fruit fruit = new Fruit(line);
					this.fruits.add(fruit);
					this.numFruit++;

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

}
