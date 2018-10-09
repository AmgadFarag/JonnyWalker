package models;

import java.util.ArrayList;

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
	public ArrayList<int[]> stones;
	public ArrayList<int[]> walkers;
	public ArrayList<int[]> obstacles;
	private MapCell[][] map;

	public Map(int m, int n, ArrayList<int[]> walker, 
			ArrayList<int[]> stone, ArrayList<int[]> obstacle) {
		mapM = m;
		mapN = n;
		walkers = walker;
		stones = stone;
		obstacles = obstacle;
		map = new EmptyCell[mapM][mapN];
		
		populateMap();
	}
	
	public void populateMap(){
		for(int i=0; i<walkers.size(); i++)
			map[walkers.get(i)[0]][walkers.get(i)[1]] = new WalkerCell();
		
		for(int i=0; i<stones.size(); i++)
			map[stones.get(i)[0]][stones.get(i)[1]] = new DragonStoneCell();
		
		for(int i=0; i<obstacles.size(); i++)
			map[obstacles.get(i)[0]][obstacles.get(i)[1]] = new ObstacleCell();
	}
	
	
	public MapCell[][] getMap(){
		return map;
	}

}
