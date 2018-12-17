package GIS;

import java.util.ArrayList;

public class Path {
             ArrayList<GIS_element> path;
             double time;
             double distance;
             double score;
             double fruitsEaten;
             
            public Path() {
            	this.path = new ArrayList<GIS_element>();
            	this.time = 0;
            	this.distance = 0;
            	this.score = 0;
            	this.fruitsEaten = 0;
            }
             
}
