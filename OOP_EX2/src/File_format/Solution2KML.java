package File_format;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import GIS.Fruit;
import GIS.GIS_element;
import GIS.Pacman;
import GIS.Path;
import GIS.Solution;
/**
 * This class receives a solution and converts it to a kml file for google earth.
 * the kml file will have a time slider so it will be possible to see which points disappear first.
 * @author Alex vaisman, Shay naor
 *
 */
public class Solution2KML {
	private Solution solution;

	public Solution2KML(Solution solution, File path) {
		this.solution = solution;
		createKMLSol(path);
	}

	/**
	 * This function receives  a path where to save the kml file.
	 * it goes over each fruit and packman and adds the time the fruit was eaten to the current time.
	 * it writes the kml file in such a way that when you run it in google earth
	 * you can see using the time slider when each fruit was eaten. 
	 * @param path
	 */
	private void createKMLSol(File path) {
		PrintWriter pw = null;
		FileWriter fw = null;
		StringBuilder sb = new StringBuilder();

		try {
			fw = new FileWriter(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pw = new PrintWriter(fw);

		/* First and seconde line in kml file. */
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Pacman Game</name>\n");

		Iterator<Path> pathIt = this.solution.getSolution().iterator();// create path iterator.
		Path pathTemp = new Path();
		Pacman pacTemp = new Pacman(0, 0, 0);
		Fruit fruitTemp = new Fruit(0, 0, 0);

		/* Go over all paths */
		while (pathIt.hasNext()) {
			pathTemp = pathIt.next();
			Iterator<GIS_element> fruitIt = pathTemp.getPath().iterator();// create fruit iterator.
			pacTemp = (Pacman) fruitIt.next();//the first in every path is pacman.
			
			/* UTC of eaten pacman */
			long pacUTC = pacTemp.getMetaData().getThisUTC();
			String strUTCPac = convertTime(pacUTC);
			
			/*create TimeStamp according to the requirements Google Earth. */
			String timePac = strUTCPac.substring(0, 10);
			String datePac = strUTCPac.substring(11,strUTCPac.length());
			strUTCPac = timePac + "T"+datePac+"Z";
			sb.append("<Folder><name>Pacman number: " + pacTemp.getMetaData().getId() + "</name>\n");
			sb.append("<Placemark>\n");
			sb.append("<TimeStamp>\n");
			sb.append("<when>" + strUTCPac + "</when>\n");
			sb.append("</TimeStamp>\n");
			sb.append("<name>Pacman number: " + pacTemp.getMetaData().getId() + "</name>\n");
			sb.append("<Point>\n");
			sb.append("<coordinates>" + pacTemp.getGps().y() + "," + pacTemp.getGps().x()+ "</coordinates></Point>\n");
			sb.append("</Placemark>\n");
			/* Go over the path */
			while (fruitIt.hasNext()) {
				fruitTemp = (Fruit) fruitIt.next();
				/* UTC of eaten fruit */
				long fruUTC = fruitTemp.getMetaData().getThisUTC();
				String strUTC = convertTime(fruUTC);
				
				/*create TimeStamp according to the requirements Google Earth. */
				String time = strUTC.substring(0, 10);
				String date = strUTC.substring(11,strUTC.length());
				strUTC = time + "T"+date+"Z";
				
				sb.append("<Placemark>\n");
				sb.append("<TimeStamp>\n");
				sb.append("<when>" + strUTC + "</when>\n");
				sb.append("</TimeStamp>\n");
				sb.append("<styleUrl>#red</styleUrl>");
				sb.append("<name>Fruit number: " + fruitTemp.getMetaData().getId() + "</name>\n");
				sb.append("<Point>\n");
				sb.append("<coordinates>" + fruitTemp.getGps().y() + "," + fruitTemp.getGps().x()+ "</coordinates></Point>\n");
				sb.append("</Placemark>\n");
			}
			sb.append("\n</Folder>\n");
		}

		sb.append("\n</Folder>\n");
		sb.append("</Document></kml>");// last row in kml file.
		pw.write(sb.toString());
		pw.close();

	}
	
	/**
	 * This function converts a long value to string of time.
	 * @param time  time in long.
	 * @return current time in string.
	 */
	private static String convertTime(long time) {
		Date date = new Date(time);
		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	

}
