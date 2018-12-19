package GIS;

import java.util.ArrayList;
/**
 * This class represents a Solution.
 * Solution is an ArrayList of Paths.
 * a Solution will be created to represent all the paths all the pacmans will make in a given game.
 * @author Alex vaisman, Shay naor.
 */
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
