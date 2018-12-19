package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import GIS.Fruit;
import GIS.Game;
import GIS.Pacman;

/**
 * This class receives a game and saves it to a CSV file.
 * @author Alex vaisman, Shay naor.
 *
 */
public class Game2CSV {
	private Game game;

	public Game2CSV(Game game, File path) {
		this.game = game;
		createCSVGame(path);
	}

	/**
	 * This function receives a game and converts it to a CSV format,
	 * then writes that format into a path where the file will be created and saved. 
	 * @param path
	 */
	private void createCSVGame(File path) {
		PrintWriter pw = null;
		FileWriter fw = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			fw = new FileWriter(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pw = new PrintWriter(fw);

		/* Create the first row. */
		sb.append("Type,");
		sb.append("id,");
		sb.append("Lat,");
		sb.append("Lon,");
		sb.append("Alt,");
		sb.append("Speed/Weight,");
		sb.append("Radius,");
		sb.append(this.game.getPacmans().size());
		sb.append(",");
		sb.append(this.game.getFruits().size());
		sb.append("\n");
		/* End to create the first row. */

		/* Write the pacmans. */
		Iterator<Pacman> pacIt = this.game.getPacmans().iterator();

		while (pacIt.hasNext()) {
			Pacman pac = pacIt.next();
			sb.append("P");
			sb.append(",");
			sb.append(pac.getMetaData().getId());
			sb.append(",");
			sb.append(pac.getGps().x());
			sb.append(",");
			sb.append(pac.getGps().y());
			sb.append(",");
			sb.append(pac.getGps().z());
			sb.append(",");
			sb.append(pac.getMetaData().getSpeedWeight());
			sb.append(",");
			sb.append(pac.getMetaData().getRadius());
			sb.append("\n");
		}

		/* Write the fruits. */
		Iterator<Fruit> fruitIt = this.game.getFruits().iterator();

		while (fruitIt.hasNext()) {
			Fruit fruit = fruitIt.next();
			sb.append("F");
			sb.append(",");
			sb.append(fruit.getMetaData().getId());
			sb.append(",");
			sb.append(fruit.getGps().x());
			sb.append(",");
			sb.append(fruit.getGps().y());
			sb.append(",");
			sb.append(fruit.getGps().z());
			sb.append(",");
			sb.append(fruit.getMetaData().getSpeedWeight());
			sb.append("\n");
		}

		pw.write(sb.toString());
		pw.close();
	}

}