package File_format;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import GIS.Fruit;
import GIS.Pacman;
import GIS.Path;
import GIS.Solution;

public class Solution2KML {
	private Solution solution;

	public Solution2KML(Solution solution, File path) {
		this.solution = solution;
		createKMLSol(path);
	}

	private void createKMLSol (File path){
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
		sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n");
		
		Iterator<Path> pathIt = this.solution.getSolution().iterator();//create path iterator.
		Path pathTemp = new Path();
//		Pacman pacTemp = new Pacman(0, 0, 0);
//		Fruit fruitTemp = new Fruit(0, 0, 0);
		
		/* Go over all paths */
		while(pathIt.hasNext()) {
			pathTemp = pathIt.next();
			sb.append("<Folder><name>Pacman number: "+ ((Pacman)pathTemp.getPath().get(0)).getMetaData().getId() + "</name>\n");
			
		}
		
		
		pw.write(sb.toString());
		pw.close();
		
	}

}
