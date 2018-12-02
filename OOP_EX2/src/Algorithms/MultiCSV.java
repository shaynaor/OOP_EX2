package Algorithms;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import File_format.MyFileUtils;
import GIS.Element;
import GIS.Layer;
import GIS.Project;

/**
 * This class reads all the csv files from a folder and creates a new project.
 * each layer in the project is one file the function read. each layer is made
 * of elements and each element represents a point with metadata. The MultiCSV
 * function will return a project and make a ksm file of all the data.
 * 
 * @author Alex Vaisman , Shay Naor.
 *
 */
public class MultiCSV {
	private ArrayList<String[]> AllFileDataMatrix; // contains all the input from the csv files.

	/**
	 * This fucntion will read all the csv files from a folder and will make a
	 * project. the project is made of of layers when each layer represents one
	 * file. and each layer is made out of elements when each element represents a
	 * gps point with metadata.
	 * 
	 * @param folder     the folder with the csv files
	 * @param outPutPath output destination for the ksm file
	 * @return returns a project
	 */
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

	/**
	 * this function receives a path to csv file . the function will read the file
	 * and make a layer of elements from it.
	 * 
	 * @param path
	 * @return
	 */
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

}
