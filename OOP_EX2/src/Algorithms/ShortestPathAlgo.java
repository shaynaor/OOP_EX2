package Algorithms;

import java.util.Iterator;

import Coords.MyCoords;
import GIS.Fruit;
import GIS.Game;
import GIS.Pacman;
import GIS.Path;

public class ShortestPathAlgo {

	private Game game;
	
	
	
	    public ShortestPathAlgo(Game game) {
	    	this.game = game;
	    	findPath();
	  
	    }
	    
	    
	
	   private void findPath() {
		   Pacman pac = game.getPacmans().get(0);
		   Iterator<Fruit> itFruit = this.game.getFruits().iterator();
		   double distance = 0;
		   double min = Double.MAX_VALUE;
		   MyCoords coords = new MyCoords();
		   Fruit fruitMin = new  Fruit(0,0,0);
		   
		
		   while(itFruit.hasNext()) {
			   Fruit fruit = itFruit.next();
			   distance = coords.distance3d(pac.getGps(), fruit.getGps());
			   
			   if(distance<min) {
				   min = distance;
				   fruitMin = fruit;
			   } 
		   }
		   Pacman tempPac = new Pacman(fruitMin.getGps().x(), fruitMin.getGps().y(),0);
	   }
}
