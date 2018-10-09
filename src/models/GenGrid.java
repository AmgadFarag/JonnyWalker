package models;

import java.util.ArrayList;

public class GenGrid {
	public final int MINIMUM_M = 4;
	public final int MINIMUM_N = 4;
	public final int MAX_DRAGON_STONES = 1;
	public final int MAX_WALKERS;
	public final int MAX_OBSTACLES;
	public final int MAX_DRAGON_GLASS;

	public final int mapM, mapN;
	public ArrayList<int[]> stones;
	public ArrayList<int[]> walkers;
	public ArrayList<int[]> obstacles;
	
	public GenGrid(){
		mapM = (int) (Math.random()*6 + MINIMUM_M);
		mapN = (int) (Math.random()*6 + MINIMUM_N);
		
		int mn = (mapN + mapM) /2; 
		MAX_WALKERS = (int) (Math.random()*mn + 1);
		mn = (mn-MAX_WALKERS>3)? mn-MAX_WALKERS:3;
		MAX_DRAGON_GLASS = (int) (Math.random()*mn +1);
		mn = (mn-MAX_DRAGON_GLASS>1)? mn-MAX_DRAGON_GLASS:1;
		MAX_OBSTACLES = (int) (Math.random()*mn);
		
		stones = new ArrayList<int[]>(MAX_DRAGON_STONES);
		walkers = new ArrayList<int[]>(MAX_WALKERS);
		obstacles = new ArrayList<int[]>(MAX_OBSTACLES);
		
		int[][] map = new int[mapM][mapN];
		
		for(int i=MAX_WALKERS-1; i>0; i--){
			int tempM = (int)Math.random()*mapM;
			int tempN = (int)Math.random()*mapN;
			map[tempM][tempN] = 1;
			int[] ar = new int[2];
			ar[0] = tempM;
			ar[1] = tempN;
			walkers.add(ar);
		}
		System.out.println("walkers done");
		for(int i=MAX_DRAGON_STONES-1; i>0; i--){
			int tempM = (int)Math.random()*mapM;
			int tempN = (int)Math.random()*mapN;
			if(map[tempM][tempN] == 1)
				i++;
			else{
				map[tempM][tempN] = 1;
				int[] ar = new int[2];
				ar[0] = tempM;
				ar[1] = tempN;
				stones.add(ar);
			}
		}
		System.out.println("stones done");
		for(int i=MAX_OBSTACLES-1; i>0; i--){
			int tempM = (int)Math.random()*mapM;
			int tempN = (int)Math.random()*mapN;
			if(map[tempM][tempN] == 1)
				i++;
			else{
				map[tempM][tempN] = 1;
				int[] ar = new int[2];
				ar[0] = tempM;
				ar[1] = tempN;
				obstacles.add(ar);
			}
		}
		System.out.println("obstacles done");
	}

}
