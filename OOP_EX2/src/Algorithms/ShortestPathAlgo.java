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
		findPath() ;

	}



	private Path findPath() {
		Pacman pac = game.getPacmans().get(0);
		Iterator<Fruit> itFruit = this.game.getFruits().iterator();
		double distance = 0;
		double min = Double.MAX_VALUE;
		MyCoords coords = new MyCoords();
		Fruit fruitMin = new  Fruit(0,0,0);
		Path path = new Path();
		path.getPath().add(pac);

		for(int i = 0; i<game.getFruits().size();i++) {
			while(itFruit.hasNext()) {
				Fruit fruit = itFruit.next();
				distance = coords.distance3d(pac.getGps(), fruit.getGps());

				if(fruit.getEaten()==false) {
					if(distance<min) {
						min = distance;
						fruitMin = fruit;
					} 
				}
			}
			Pacman tempPac = new Pacman(fruitMin.getGps().x(), fruitMin.getGps().y(),0);
			
			path.getPath().add(fruitMin);               // might be buuugg
			fruitMin.eaten();
			min = Double.MAX_VALUE;
			distance = 0;
		}
    

        for(int i =0 ;i<path.getPath().size();i++) {
        	if(path.getPath().get(i) instanceof Pacman) {
        	 System.out.println(((Pacman)(path.getPath().get(i))).getGps()+" Pac");
        	}
        	else
        		 System.out.println(((Fruit)(path.getPath().get(i))).getGps()+" Fruit "+ (i-1));
        }
		return path;
	}

}
