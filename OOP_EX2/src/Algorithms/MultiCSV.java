package Algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import File_format.MyFileUtils;

/**
 * This class reads all the csv files from a folder and creates....
 * 
 * @author Alex Vaisman , Shay Naor.
 *
 */
public class MultiCSV {
	private ArrayList<String[]> Project;     // contains all the input from the csv files.
	private ArrayList<String[]> layer;       // contains one csv file.
	
    
	/**
	 * This Constructor recives a folder and reads the csv files in that folder and
	 * creates a Project contains all the input from the csv files.
	 * 
	 * @param path The folder location with the csv files.
	 */
	public MultiCSV(String path) {
		ArrayList<String[]> temp = new ArrayList<String[]>();// contains one csv file data.
		ArrayList<String> CSVFiles = new ArrayList<String>();// contains the csv file paths in the folder.
		int counter = 0;// counts number of csv files the function read already.
		File f = new File(path);
		try {
			CSVFiles = MyFileUtils.listFilesForFolder(f);
		} catch (NullPointerException e) {
			System.err.println("Wrong file location!!");
			e.printStackTrace();
		}

		while (counter < CSVFiles.size()) {// while there are still files that the function didnt read yet.
			String CSVFile = CSVFiles.get(counter);
			try {
				if (counter == 0) {// if this is the first file read from the second line.
					Project = MyFileUtils.readCSVFile(CSVFile, 1);
					counter++;
				} else {// read from the third line.
					temp = MyFileUtils.readCSVFile(CSVFile, 2);
					Project.addAll(temp);
					counter++;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	
	
    /**
     * Returns a line in the Project.
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

		MultiCSV data = new MultiCSV("C:\\\\Users\\\\User\\\\Desktop\\\\מטלה 2 מונחה\\\\Ex2\\\\data");
		for (int i = 0; i < data.Project.size(); i++) {
			System.out.println(Arrays.toString(data.Project.get(i)));

			String path1 = "C:\\Users\\User\\Desktop\\test-IO\\shaytest.kml";

			try {
				MyFileUtils.writeKMLFile(path1, data.Project);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
