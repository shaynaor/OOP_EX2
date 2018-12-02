package Algorithms;

import GIS.Project;

public class Main {

	public static void main(String[] args) {
		String folder = "C:\\Users\\User\\Desktop\\מטלה 2 מונחה\\Ex2\\data";
		MultiCSV test = new MultiCSV();
		Project pro = test.MultiCSV(folder, "C:\\Users\\User\\Desktop\\test-IO\\shaytest.kml");
	}

}
