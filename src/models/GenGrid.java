package models;

public class GenGrid {
	public final int MINIMUM_M = 4;
	public final int MINIMUM_N = 4;
	public final int MAX_DRAGON_STONES = 1;
	public final int MAX_WALKERS;
	public final int MAX_OBSTACLES;
	public final int MAX_DRAGON_GLASS;

	public final int mapM, mapN;
	public int[][] stones;
	public int[][] walkers;
	public int[][] obstacle;
	
	public GenGrid(){
		mapM = (int) (Math.random()*10 + MINIMUM_M);
		mapN = (int) (Math.random()*10 + MINIMUM_N);
		int mn = (mapN + mapM) /2; 
		MAX_WALKERS = (int) (Math.random()*mn + 1);
		MAX_OBSTACLES = (int) (Math.random()*mn + 1);
		MAX_DRAGON_GLASS = (int) (Math.random()*mn +1);
		
		stones = new int[MAX_DRAGON_STONES][2];
		walkers = new int[MAX_WALKERS][2];
		obstacle = new int[MAX_OBSTACLES][2];
		
		int[][] map = new int[mapM][mapN];
		
		for(int i=MAX_WALKERS; i>0; i--){
			int tempM = (int)Math.random()*mapM;
			int tempN = (int)Math.random()*mapN;
			map[tempM][tempN] = 1;
			walkers[i][0] = tempM;
			walkers[i][1] = tempN;
		}
		
		for(int i=MAX_DRAGON_STONES; i>0; i--){
			int tempM = (int)Math.random()*mapM;
			int tempN = (int)Math.random()*mapN;
			if(map[tempM][tempN] == 1)
				i++;
			else{
				map[tempM][tempN] = 1;
				stones[i][0] = tempM;
				stones[i][1] = tempN;
			}
		}
		for(int i=MAX_OBSTACLES; i>0; i--){
			int tempM = (int)Math.random()*mapM;
			int tempN = (int)Math.random()*mapN;
			if(map[tempM][tempN] == 1)
				i++;
			else{
				map[tempM][tempN] = 1;
				obstacle[i][0] = tempM;
				obstacle[i][1] = tempN;
			}
		}
	}

}
