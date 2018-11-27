package Algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import File_format.MyFileUtils;

public class MultiCSV {

	private ArrayList<String[]> dataMatrix;

	public MultiCSV(String path) {
		ArrayList<String[]> temp = new ArrayList<String[]>();
		ArrayList<String> CSVFiles = new ArrayList<String>();
		int counter = 0;
		File f = new File(path);
		try {
			CSVFiles = MyFileUtils.listFilesForFolder(f);
		}catch (NullPointerException e){
			System.err.println("Wrong file location!!");
			e.printStackTrace();
		}
		
		while (counter < CSVFiles.size()) {
			String CSVFile = CSVFiles.get(counter);
			try {
				if (counter == 0) {
					dataMatrix = MyFileUtils.readCSVFile(CSVFile, 1);
					counter++;
				} else {
					temp = MyFileUtils.readCSVFile(CSVFile, 2);
					dataMatrix.addAll(temp);
					counter++;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static String getData(ArrayList<String[]> dataMatrix, int i, int j) {
		String[] str = dataMatrix.get(i);

		return str[j];
	}
	
	public static void main(String[]args) {
		
		MultiCSV data = new MultiCSV("C:\\\\Users\\\\User\\\\Desktop\\\\מטלה 2 מונחה\\\\Ex2\\\\data1212");
		for(int i = 0 ; i < data.dataMatrix.size() ; i++) {
		System.out.println(Arrays.toString(data.dataMatrix.get(i)));
	}
	}

}
