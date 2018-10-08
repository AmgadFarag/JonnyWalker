package models;

public class MiniMap {
	public final int mapM, mapN;
	public int[][] stones;
	public int[][] walkers;
	public int[][] obstacles;
	public final int MAX_DRAGON_GLASS;
	public int x,y,dragonGlass;

	public MiniMap(int mapM, int mapN, int max, 
			int[][] stones, int[][] walkers, int[][] obstacles, 
			int x, int y, int dragonGlass){
		this.mapM = mapM;
		this.mapN = mapN;
		MAX_DRAGON_GLASS = max;
		this.stones = stones;
		this.walkers = walkers;
		this.obstacles = obstacles;
		this.x = x;
		this.y = y;
		this.dragonGlass = dragonGlass;
	}
	
	public void kill(){
		int index = -1;
		for(int i=0; i<walkers.length; i++)
			if(walkers[i][0] == x && walkers[i][1]+1 == y ||
					walkers[i][0] == x && walkers[i][1]-1 == y ||
					walkers[i][0]+1 == x && walkers[i][1] == y ||
					walkers[i][0]-1 == x && walkers[i][1] == y ){
				index = i;
				break;
			}
		if(index != -1){
			int[] trash = new int[2], temp = new int[2];
			for(int i=walkers.length; i>index; i--){
				temp = walkers[i];
				walkers[i] = trash;
				trash = walkers[i-1];
				walkers[i-1] = temp;
			}
		}
	}
	
	public boolean ifAttack() {
		for(int i=0; i<walkers.length; i++)
			if(walkers[i][0] == x && walkers[i][1]+1 == y ||
					walkers[i][0] == x && walkers[i][1]-1 == y ||
					walkers[i][0]+1 == x && walkers[i][1] == y ||
					walkers[i][0]-1 == x && walkers[i][1] == y )
				return true;
		
		return false;
	}

	public boolean ifPickUp() {
		for(int i=0; i<stones.length; i++)
			if(stones[i][0] == x && stones[i][1]+1 == y ||
					stones[i][0] == x && stones[i][1]-1 == y ||
					stones[i][0]+1 == x && stones[i][1] == y ||
					stones[i][0]-1 == x && stones[i][1] == y )
				return true;
		
		return false;
	}
	
	public boolean ifMoveUp() {
		if(0 >= x)
			return false;
		for(int i=0; i<obstacles.length; i++)
			if(obstacles[i][0] == x && obstacles[i][1]+1 == y)
				return false;
		return true;
	}
	public boolean ifMoveDown() {
		if(mapM <= x)
			return false;
		for(int i=0; i<obstacles.length; i++)
			if(obstacles[i][0] == x && obstacles[i][1]-1 == y)
				return false;
		return true;
	}
	public boolean ifMoveRight() {
		if(mapN <= y)
			return false;
		for(int i=0; i<obstacles.length; i++)
			if(obstacles[i][0]+1 == x && obstacles[i][1] == y)
				return false;
		return true;
	}
	public boolean ifMoveLeft() {
		if(0 >= y)
			return false;
		for(int i=0; i<obstacles.length; i++)
			if(obstacles[i][0]-1 == x && obstacles[i][1] == y)
				return false;
		return true;
	}
}
