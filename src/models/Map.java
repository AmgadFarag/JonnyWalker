package models;

import models.cells.DragonStoneCell;
import models.cells.EmptyCell;
import models.cells.MapCell;
import models.cells.ObstacleCell;
import models.cells.WalkerCell;

public class Map {
	private final int MINIMUM_M = 4;
	private final int MINIMUM_N = 4;
	private final int MAX_WALKERS;
	private final int MAX_DRAGON_STONES;
	private final int MAX_OBSTACLES;
	
	private final int mapM, mapN;
	private MapCell[][] map;

	public Map(int m, int n, int walkers, int stones, int obstacle) {
		MAX_WALKERS = walkers;
		MAX_DRAGON_STONES = stones;
		MAX_OBSTACLES = obstacle;
		mapM = (int) (Math.random()*m + MINIMUM_M);
		mapN = (int) (Math.random()*n + MINIMUM_N);
		map = new EmptyCell[mapM][mapN];
		
		for(int i=MAX_WALKERS; i>0; i--){
			int tempM = (int)Math.random()*mapM;
			int tempN = (int)Math.random()*mapN;
			map[tempM][tempN] = new WalkerCell();
		}
		
		for(int i=MAX_DRAGON_STONES; i>0; i--){
			int tempM = (int)Math.random()*mapM;
			int tempN = (int)Math.random()*mapN;
			if(map[tempM][tempN] instanceof WalkerCell)
				i++;
			else
				map[tempM][tempN] = new DragonStoneCell();
		}
		for(int i=MAX_OBSTACLES; i>0; i--){
			int tempM = (int)Math.random()*mapM;
			int tempN = (int)Math.random()*mapN;
			if(map[tempM][tempN] instanceof WalkerCell ||
					map[tempM][tempN] instanceof DragonStoneCell)
				i++;
			else
				map[tempM][tempN] = new ObstacleCell();
		}
	}
	
	public MapCell[][] getMap(){
		return map;
	}
	public int getM(){
		return mapM;
	}
	public int getN(){
		return mapN;
	}

}
