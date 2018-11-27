package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class does multiple actions on files. such as: read csv file and write
 * kml file.
 * 
 * @author Shay Naor and Alex Vaisman.
 *
 */
public class MyFileUtils {

	/**
	 * This function recived a path to a csv file and create a ArrayList<String[]>
	 * that contains the csv file data.
	 * 
	 * @param path      location of csv file.
	 * @param startLine from which line to read.
	 * @return ArrayList<String[]> contains the csv file data.
	 * @throws IOException if the path is incorrect the function throws IOException.
	 */
	public static ArrayList<String[]> readCSVFile(String path, int startLine) throws IOException {
		int counter = 0;
		String line = "";
		String cvsSplitBy = ",";
		String[] userInfo = {};
		ArrayList<String[]> container = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(path));

		while ((line = br.readLine()) != null) {
			userInfo = line.split(cvsSplitBy);
			if (counter >= startLine)
				container.add(userInfo);
			counter++;

		}
		br.close();
		return container;
	}

	/**
	 * This function recived a path and ArrayList<String[]> that contains the csv
	 * file data and write a kml file.
	 * 
	 * @param path      location of kml file.
	 * @param container contains the csv file data that we want to convert to kml
	 *                  file.
	 * @throws IOException if the path is incorrect the function throws IOException.
	 */
	public static void writeKMLFile(String path, ArrayList<String[]> container) throws IOException {
		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle>"
				+ "<Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\">"
				+ "<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle>"
				+ "</Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>"
				+ "</Icon></IconStyle></Style><Folder><name>Wifi Networks</name>";
		content.add(kmlstart);

		String kmlend = "</Folder>\r\n" + "</Document></kml>";

		FileWriter fw = new FileWriter(path);
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 2; i < container.size(); i++) {
			String[] str = container.get(i);
			String kmlelement = "\t<Placemark>\n" + "\t<name>" + str[1] + "</name>\n" + "\t<description>" + str[2]
					+ "</description>\n" + "\t<Point>\n" + "\t\t<coordinates>" + str[7] + "," + str[6]
					+ "</coordinates>\n" + "\t</Point>\n" + "\t</Placemark>\n";
			content.add(kmlelement);
		}
		content.add(kmlend);

		String ans = "";

		for (int i = 0; i < content.size(); i++) {
			ans += content.get(i);
		}
		bw.write(ans);
		bw.close();

	}

	/**
	 * This function recives folder path and return a ArrayList<String> that
	 * contains all the paths for csv files in folder. The algorithem taken from :
	 * https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java.
	 * 
	 * @param folder that contains the csv files.
	 * @return ArrayList<String> that contains all the paths for csv files in
	 *         folder.
	 */
	public static ArrayList<String> listFilesForFolder(final File folder) throws NullPointerException {
		ArrayList<String> listFiles = new ArrayList<>();
		String path = "";
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				if (fileEntry.isFile()) {
					String temp = fileEntry.getName();
					if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("csv")) {
						path = folder.getAbsolutePath() + "\\" + fileEntry.getName();
						listFiles.add(path);
					}
				}
			}
		}
		return listFiles;
	}

}