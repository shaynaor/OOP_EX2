package GIS;

import java.util.ArrayList;

public class Solution {

	
	private ArrayList<Path> solution;
	double time;
	double distance;
	double score;
	
	
	public Solution() {
		this.solution = new  ArrayList<Path>();
		this.time = 0;
		this.distance = 0 ;
		this.score = 0;
	}

            
	
	
	
	
	
	
	/* Getters */
	public ArrayList<Path> getSolution() {
		return solution;
	}


	public double getTime() {
		return time;
	}


	public double getDistance() {
		return distance;
	}


	public double getScore() {
		return score;
	}
}
