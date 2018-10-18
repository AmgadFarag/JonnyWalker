package models;

import java.util.ArrayList;

public class GenGrid {
	public final int MINIMUM_M = 4;
	public final int MINIMUM_N = 4;
	public final int MAX_DRAGON_STONES;
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
		//mapM = 2;
		//mapN = 3;
		System.out.println(mapM+" * "+mapN);
		
		int mn = (mapN + mapM) /2; 
		//MAX_WALKERS = 2;
		MAX_WALKERS = (int) (Math.random()*mn + 1);
		System.out.println("Walkers: "+MAX_WALKERS);
		
		mn -= MAX_WALKERS;
		//MAX_DRAGON_STONES = 1;
		MAX_DRAGON_STONES = (int) (Math.random() + 1);
		System.out.println("DragonStones: "+ MAX_DRAGON_STONES);
		
		mn -= MAX_DRAGON_STONES;
		//MAX_OBSTACLES = 1;
		MAX_OBSTACLES = (int) (Math.random()*mn + 1);
		System.out.println("obstcals: " + MAX_OBSTACLES);
		
		stones = new ArrayList<int[]>(MAX_DRAGON_STONES);
		walkers = new ArrayList<int[]>(MAX_WALKERS);
		obstacles = new ArrayList<int[]>(MAX_OBSTACLES);
		//MAX_DRAGON_GLASS = 1;
		MAX_DRAGON_GLASS = (int) (Math.random()*MAX_WALKERS/2) + 1;
		int[][] map = new int[mapM][mapN];
		
		System.out.println("Index Gen Start");
		for(int i=0; i<MAX_WALKERS; i++){
//			int tempM =1;
//			int tempN = 0;
//			if(i == 0){
//			tempM =1;
//			tempN = 0;}else{
//				if(i == 1){
//					tempM =0;
//					tempN = 2;	
//				}
//			}
			int tempM = (int)(Math.random()*mapM + 1);
			int tempN = (int)(Math.random()*mapN + 1);
			if(tempM != mapM && tempN != mapN && map[tempM][tempN] != 1){
				map[tempM][tempN] = 1;
				int[] ar = new int[2];
				ar[0] = tempM;
				ar[1] = tempN;
				System.out.println("Walker at: " +ar[0] + " " + ar[1]);
				walkers.add(ar);
			}else
				i--;
		}
		System.out.println("walkers done");
		
		for(int i=0; i<MAX_DRAGON_STONES; i++){
//			int tempM =1;
//			int tempN = 1;
			int tempM = (int)(Math.random()*mapM + 1);
			int tempN = (int)(Math.random()*mapN + 1);
			if(tempM != mapM && tempN != mapN){
				if(map[tempM][tempN] == 1)
					i--;
				else{
					map[tempM][tempN] = 1;
					int[] ar = new int[2];
					ar[0] = tempM;
					ar[1] = tempN;
					System.out.println("Stone at: " +ar[0] + " " + ar[1]);
					stones.add(ar);
				}
			}else
				i--;
		}
		System.out.println("stones done");
		
		for(int i=0; i<MAX_OBSTACLES; i++){
//			int tempM = 0;
//			int tempN = 1;
			int tempM = (int)(Math.random()*mapM + 1);
			int tempN = (int)(Math.random()*mapN + 1);
			if(tempN != mapM && tempM != mapN){
				if(map[tempM][tempN] == 1){
					i--;
				}
				else{
					map[tempM][tempN] = 1;
					int[] ar = new int[2];
					ar[0] = tempM;
					ar[1] = tempN;
					System.out.println("Obstcal at: " +ar[0] + " " + ar[1]);
					obstacles.add(ar);
				}
			}
				else{
					i--;
				}
				
		}
		System.out.println("obstacles done");
	}
	
//	public GenGrid(){
//		mapM = (int) (Math.random()*6 + MINIMUM_M);
//		mapN = (int) (Math.random()*6 + MINIMUM_N);
////		mapM = 2;
////		mapN = 2;
//		System.out.println(mapM+" * "+mapN);
//		
//		int mn = (mapN + mapM) /2; 
//		//MAX_WALKERS = 2;
//		MAX_WALKERS = (int) (Math.random()*mn + 1);
//		System.out.println("Walkers: "+MAX_WALKERS);
//		
//		mn -= MAX_WALKERS;
//		//MAX_DRAGON_STONES = 1;
//		MAX_DRAGON_STONES = (int) (Math.random() + 1);
//		System.out.println("DragonStones: "+ MAX_DRAGON_STONES);
//		
//		mn -= MAX_DRAGON_STONES;
//		//MAX_OBSTACLES = 0;
//		MAX_OBSTACLES = (int) (Math.random()*mn + 1);
//		System.out.println("obstcals: " + MAX_OBSTACLES);
//		
//		stones = new ArrayList<int[]>(MAX_DRAGON_STONES);
//		walkers = new ArrayList<int[]>(MAX_WALKERS);
//		obstacles = new ArrayList<int[]>(MAX_OBSTACLES);
//		//MAX_DRAGON_GLASS = 1;
//		MAX_DRAGON_GLASS = (int) (Math.random()*MAX_WALKERS/2) + 1;
//		int[][] map = new int[mapM][mapN];
//		
//		System.out.println("Index Gen Start");
//		for(int i=0; i<MAX_WALKERS; i++){
//			//int tempM =1;
//			//int tempN = 0;
//			//if(i == 0){
//			//tempM =1;
//			//tempN = 0;}else{
//				//if(i == 1){
//					//tempM =0;
//					//tempN = 0;	
//				//}else{
//				//tempM =0;
//				//tempN = 0;
//			//	}
//			//}
//			int tempM = (int)(Math.random()*mapM + 1);
//			int tempN = (int)(Math.random()*mapN + 1);
//			if(tempM != mapM && tempN != mapN && map[tempM][tempN] != 1){
//				map[tempM][tempN] = 1;
//				int[] ar = new int[2];
//				ar[0] = tempM;
//				ar[1] = tempN;
//				System.out.println("Walker at: " +ar[0] + " " + ar[1]);
//				walkers.add(ar);
//			}else
//				i--;
//		}
//		System.out.println("walkers done");
//		
//		for(int i=0; i<MAX_DRAGON_STONES; i++){
//			//int tempM =0;
//			//int tempN = 1;
//			int tempM = (int)(Math.random()*mapM + 1);
//			int tempN = (int)(Math.random()*mapN + 1);
//			if(tempM != mapM && tempN != mapN){
//				if(map[tempM][tempN] == 1)
//					i--;
//				else{
//					map[tempM][tempN] = 1;
//					int[] ar = new int[2];
//					ar[0] = tempM;
//					ar[1] = tempN;
//					System.out.println("Stone at: " +ar[0] + " " + ar[1]);
//					stones.add(ar);
//				}
//			}else
//				i--;
//		}
//		System.out.println("stones done");
//		
//		for(int i=0; i<MAX_OBSTACLES; i++){
//			//int tempM = 1;
//			//int tempN = 3;
//			int tempM = (int)(Math.random()*mapM + 1);
//			int tempN = (int)(Math.random()*mapN + 1);
//			if(tempN != mapM && tempM != mapN){
//				try{if(map[tempN][tempM] == 1)
//					i--;
//				}catch(Exception e){
//					if(map[tempM][tempN] == 1)
//						i--;
//				}
//			}else{
//				try{map[tempN][tempM] = 1;
//				
//				}catch(Exception e){
//					map[tempM][tempN] = 1;
//				}
//					int[] ar = new int[2];
//					ar[0] = tempM;
//					ar[1] = tempN;
//					System.out.println("Obstcal at: " +ar[0] + " " + ar[1]);
//					obstacles.add(ar);
//				}
//			}
////				else{
////					i--;
////				}
//				
//		
//		System.out.println("obstacles done");
//	}

}
