package Algorithms;

import java.util.Iterator;

import Coords.MyCoords;

import GIS.Fruit;
import GIS.Game;
import GIS.Pacman;
import GIS.Path;
import Geom.Point3D;

public class ShortestPathAlgo {

	private Game game;
    private Path path;


	public ShortestPathAlgo(Game game) {
		this.game = game;
		findPath() ;

	}



	public Path getPath() {
		return path;
	}



	private void findPath() {
		Path path = new Path();
		double min = Double.MAX_VALUE;
		Pacman pacman = new Pacman(0,0,0);
		pacman = game.getPacmans().get(0); 
		Iterator<Fruit >itFruit = game.getFruits().iterator();   // iterator
		Fruit fruit = new Fruit(0,0,0);
		int index = 0;
		int iteratorIndex = -1;
		double distance = 0;
		MyCoords convert = new MyCoords();
		Fruit fruitMin = new Fruit(0,0,0);
		Point3D CurrentPoint = pacman.getGps();
        
        path.getPath().add(pacman);
        
        //====== Find closest fruit to pacman , eat it , move pack man to the fruits position, repeat======
		for(int i = 0 ; i<game.getFruits().size() ; i++) {
			while(itFruit.hasNext()) {
				fruit = itFruit.next();
				iteratorIndex++;
				distance = convert.distance3d(CurrentPoint, fruit.getGps());
                
				
               //=======If fruit is not eaten find the closest fruit to the current position==========
				if(game.getFruits().get(iteratorIndex).getEaten()==false) {	  
					if(distance < min) {
						fruitMin = fruit;
						min = distance;
						index = iteratorIndex;
					}
				}
			}
		   //=======Eating fruit adding to path=======
            game.getFruits().get(index).eaten();   
            path.getPath().add(fruitMin);     
			
            //==========Reseting values=======
            CurrentPoint = fruitMin.getGps();
            itFruit = game.getFruits().iterator();
			index = 0; 
			iteratorIndex = -1;
			min = Double.MAX_VALUE;
			distance = 0;
		}
		this.path = path;
 





		//=============print=============================
//		        for(int i =0 ;i<path.getPath().size();i++) {
//		        	if(path.getPath().get(i) instanceof Pacman) {
//		        	 System.out.println(((Pacman)(path.getPath().get(i))).getGps()+" Pac");
//		        	}
//		        	else
//		        		 System.out.println(((Fruit)(path.getPath().get(i))).getGps()+" Fruit "+ (i-1));
//		        }




	}

}
