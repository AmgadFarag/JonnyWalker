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
		map = new MapCell[mapM][mapN];
		for(int i=0; i<mapM; i++){
			map[i] = new MapCell[mapN];
			for(int j=0; j<mapN; j++)
				map[i][j] = new EmptyCell();
		}
		
		populateMap();
	}
	
	public void populateMap(){
		for(int[] walk : walkers)
			map[walk[0]][walk[1]] = new WalkerCell();
		
		for(int[] sto : stones)
			map[sto[0]][sto[1]] = new DragonStoneCell();
		
		for(int[] obs : obstacles)
			map[obs[0]][obs[1]] = new ObstacleCell();
	}
	
	
	public MapCell[][] getMap(){
		return map;
	}

}
