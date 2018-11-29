package Algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import File_format.MyFileUtils;
import GIS.Element;
import GIS.Layer;

/**
 * This class reads all the csv files from a folder and creates....
 * 
 * @author Alex Vaisman , Shay Naor.
 *
 */
public class MultiCSV {
	private ArrayList<String[]> AllFileDataMatrix; // contains all the input from the csv files.
	private ArrayList<String[]> layer; // contains one csv file.

	/**
	 * This Constructor recives a folder and reads the csv files in that folder and
	 * creates a Project contains all the input from the csv files.
	 * 
	 * @param path The folder location with the csv files.
	 */
	// public MultiCSV(String path) {
	// ArrayList<String[]> temp = new ArrayList<String[]>();// contains one csv file
	// data.
	// ArrayList<String> CSVFiles = new ArrayList<String>();// contains the csv file
	// paths in the folder.
	// int counter = 0;// counts number of csv files the function read already.
	// File f = new File(path);
	// try {
	// CSVFiles = MyFileUtils.listFilesForFolder(f);
	// } catch (NullPointerException e) {
	// System.err.println("Wrong file location!!");
	// e.printStackTrace();
	// }
	//
	// while (counter < CSVFiles.size()) {// while there are still files that the
	// function didnt read yet.
	// String CSVFile = CSVFiles.get(counter);
	// try {
	// if (counter == 0) {// if this is the first file read from the second line.
	// Project = MyFileUtils.readCSVFile(CSVFile, 1);
	// counter++;
	// } else {// read from the third line.
	// temp = MyFileUtils.readCSVFile(CSVFile, 2);
	// Project.addAll(temp);
	// counter++;
	// }
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }
	public MultiCSV(String folder, String outPutPath) {
		// ******finding path on all csv files in folder*****
		ArrayList<String> CSVFiles = findCsvPath(folder);

		GIS.Project project = new GIS.Project();
		for (int i = 0; i < CSVFiles.size(); i++) {
			Layer layer = new Layer();
			layer = readCsvToLayer(CSVFiles.get(i));
			project.add(layer);
		}
		try {
			MyFileUtils.writeKMLFile(outPutPath, this.AllFileDataMatrix);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Finds the path to all csv files in a folder if any.
	 * 
	 * @param folder the folder with csv files.
	 * @return arry with all the paths to csv files.
	 */
	private ArrayList<String> findCsvPath(String folder) {
		ArrayList<String> CSVFiles = new ArrayList<String>();// contains the csv file paths in the folder.
		File f = new File(folder);
		try {
			CSVFiles = MyFileUtils.listFilesForFolder(f);
		} catch (NullPointerException e) {
			System.err.println("Wrong file location!!");
			e.printStackTrace();
		}
		return CSVFiles;
	}

	private Layer readCsvToLayer(String path) {
		Layer layer = new Layer();
		ArrayList<String[]> dataMatrix = new ArrayList<String[]>();
		try {
			dataMatrix = MyFileUtils.readCSVFile(path, 2);
			this.AllFileDataMatrix.addAll(dataMatrix);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < dataMatrix.size(); i++) {
			String[] line = dataMatrix.get(i);
			Element ele = new Element(line);
			layer.add(ele);
		}
		return layer;
	}

	/**
	 * Returns a line in the Project.
	 * 
	 * @param dataMatrix the Project function works on
	 * @param i
	 * @param j
	 * @return
	 */
	public static String getData(ArrayList<String[]> dataMatrix, int i, int j) {
		String[] str = dataMatrix.get(i);

		return str[j];
	}

	public static void main(String[] args) {
       String folder ="C:\\Users\\A Beast\\Desktop\\data";
		MultiCSV test = new MultiCSV(folder , "C:\\Users\\A Beast\\Desktop\\kml\\test.kml");
		
		
		
	}
}
