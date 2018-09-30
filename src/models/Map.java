package models;

import models.cells.DragonStoneCell;
import models.cells.EmptyCell;
import models.cells.MapCell;
import models.cells.ObstacleCell;
import models.cells.WalkerCell;

public class Map {
	public final int MINIMUM_M = 4;
	public final int MINIMUM_N = 4;
	public final int MAX_DRAGON_STONES = 1;

	public final int mapM, mapN;
	public int[][] stones;
	public int[][] walkers;
	public int[][] obstacles;
	private MapCell[][] map;

	public Map(int m, int n, int[][] walker, 
			int[][] stone, int[][] obstacle) {
		mapM = m;
		mapN = n;
		walkers = walker;
		stones = stone;
		obstacles = obstacle;
		map = new EmptyCell[mapM][mapN];
		
		populateMap();
	}
	
	public void populateMap(){
		for(int i=0; i<walkers.length; i++)
			map[walkers[i][0]][walkers[i][1]] = new WalkerCell();
		
		for(int i=0; i<stones.length; i++)
			map[stones[i][0]][stones[i][1]] = new DragonStoneCell();
		
		for(int i=0; i<obstacles.length; i++)
			map[obstacles[i][0]][obstacles[i][1]] = new ObstacleCell();
	}
	
	
	public MapCell[][] getMap(){
		return map;
	}

}
