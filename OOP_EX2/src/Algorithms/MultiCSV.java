package Algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import File_format.MyFileUtils;
import GIS.Element;
import GIS.Layer;
import GIS.Project;

/**
 * This class reads all the csv files from a folder and creates....
 * 
 * @author Alex Vaisman , Shay Naor.
 *
 */
public class MultiCSV {
	private ArrayList<String[]> AllFileDataMatrix; // contains all the input from the csv files.

	public Project MultiCSV(String folder, String outPutPath) {
		this.AllFileDataMatrix = new ArrayList<String[]>();

		// ******finding path on all csv files in folder******
		ArrayList<String> CSVFiles = findCsvPath(folder);
		// ******creates layer for each csv file******
		GIS.Project project = new GIS.Project();
		for (int i = 0; i < CSVFiles.size(); i++) {
			Layer layer = new Layer();
			layer = readCsvToLayer(CSVFiles.get(i));
			project.add(layer);
		}
		// ******Writes a kml file of the entire project******
		try {
			MyFileUtils.writeKMLFile(outPutPath, project);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;

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
			AllFileDataMatrix.addAll(dataMatrix);
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

	public static void main(String[] args) {
		String folder = "C:\\Users\\User\\Desktop\\מטלה 2 מונחה\\Ex2\\data";
		MultiCSV test = new MultiCSV();
		Project pro = test.MultiCSV(folder, "C:\\Users\\User\\Desktop\\test-IO\\shaytest.kml");

	}
}
