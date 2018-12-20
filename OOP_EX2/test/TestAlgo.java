import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Algorithms.ShortestPathAlgo;
import GIS.Fruit;
import GIS.GIS_element;
import GIS.Game;
import GIS.Pacman;

class TestAlgo {

    /**
     * this test creates a game with 5 pacmans and 20 fruits.
     * checks if all the fruits are eaten in the end of the algo.
     */
	@Test
	void TestIfAteAllFruits() {
		ArrayList<Fruit> fruits = new ArrayList<Fruit>();
		ArrayList<Pacman> pacmans = new ArrayList<Pacman>();
		
		//====Generate furits====
		for(int i = 0; i < 2000; i++) {
			int x = ThreadLocalRandom.current().nextInt(0, 5000 + 1);
			int y = ThreadLocalRandom.current().nextInt(0, 5000 + 1);
			Fruit f = new Fruit(x,y,0);
			fruits.add(f);
		}
		//====Generate pacmans====
		for(int i = 0; i < 5; i++) {
			int x = ThreadLocalRandom.current().nextInt(0, 5000 + 1);
			int y = ThreadLocalRandom.current().nextInt(0, 5000 + 1);
			Pacman p = new Pacman(x,y,0);
			pacmans.add(p);
		}
		//====Generate game====
		Game game = new Game();
		game.setFruits(fruits);
		game.setPacmans(pacmans);
		//====Start algo====
		ShortestPathAlgo algo = new ShortestPathAlgo(game);
		
		//====Check algo====
	
		Iterator<Fruit> itPath = game.getFruits().iterator();
		itPath.next();
		Fruit f =  new Fruit(0,0,0);
		while(itPath.hasNext()){
			f = (Fruit)itPath.next();
			if(!f.getEaten()) {
				fail("Not yet implemented");
			}
		}
		
		
		
	}

}
