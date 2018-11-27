package Algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import File_format.MyFileUtils;

public class MultiCSV {

	private ArrayList<String[]> dataMatrix;

	public void MultiCSV(String path) {
		ArrayList<String> CSVFiles = new ArrayList<String>();
		int counter = 0;
		File f = new File(path);
		CSVFiles = MyFileUtils.listFilesForFolder(f);
		try {
			if(counter == 0) {
				dataMatrix = MyFileUtils.readCSVFile(path, 1);
				counter++;
			}else
				dataMatrix = MyFileUtils.readCSVFile(path, 2);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getData(ArrayList<String[]> dataMatrix, int i, int j) {
		String[] str = dataMatrix.get(i);

		return str[j];
	}

}
