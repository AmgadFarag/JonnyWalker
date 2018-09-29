package models;

import models.cells.MapCell;

public class Map {
	private MapCell[][] map;

	public Map(int m, int n, int[] walkers, int[] stones, int[] obstacle) {
		
		//map = new EmptyCell[mapM][mapN];
	}
	
	public MapCell[][] getMap(){
		return map;
	}

}
