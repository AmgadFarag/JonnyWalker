package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public GenGrid() throws IOException{
		int count = 0;
		String path = "C:/Users/ASUS/Documents/AmrAhmed/Sem9/AI/AI_Proj2/";
		String text ="";
		text = "map(";
		File idea=new File(path+"gridData.pl");
		while(idea.exists()){
			idea = new File(path+"gridData"+count+".pl");
			count++;
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(idea));
		mapM = (int) (Math.random()*6 + MINIMUM_M);
		mapN = (int) (Math.random()*6 + MINIMUM_N);
		
		text+= mapN +", " + mapM+"). \n";
		text += "jonnyAt("+ (mapN-1) +", "+ (mapM-1) +", s0). \n";
		
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

		MAX_DRAGON_GLASS = (int) (Math.random()*MAX_WALKERS/2) + 1;
		
		text += "maxDragonGlass("+ MAX_DRAGON_GLASS +"). \n";
		
		int[][] map = new int[mapM][mapN];
		
		System.out.println("Index Gen Start");
		for(int i=0; i<MAX_WALKERS; i++){

			int tempM = (int)(Math.random()*(mapM-1) + 1);
			int tempN = (int)(Math.random()*(mapN-1) + 1);
			if(tempM != mapM && tempN != mapN && map[tempM][tempN] != 1){
				map[tempM][tempN] = 1;
				int[] ar = new int[2];
				ar[0] = tempM;
				ar[1] = tempN;
				text += "walkerAt("+ ar[0] +", "+ ar[1] +", s0). \n";
				System.out.println("Walker at: " +ar[0] + " " + ar[1]);
				walkers.add(ar);
			}else
				i--;
		}
		System.out.println("walkers done");
		
		for(int i=0; i<MAX_DRAGON_STONES; i++){
//			int tempM =1;
//			int tempN = 1;
			int tempM = (int)(Math.random()*(mapM-1) + 1);
			int tempN = (int)(Math.random()*(mapN-1) + 1);
			if(tempM != mapM && tempN != mapN){
				if(map[tempM][tempN] == 1)
					i--;
				else{
					map[tempM][tempN] = 1;
					int[] ar = new int[2];
					ar[0] = tempM;
					ar[1] = tempN;
					text += "stoneAt("+ ar[0] +", "+ ar[1] +"). \n";
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
			int tempM = (int)(Math.random()*(mapM-1) + 1);
			int tempN = (int)(Math.random()*(mapN-1) + 1);
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
					text += "obstcalAt("+ ar[0] +", "+ ar[1] +"). \n";
					obstacles.add(ar);
				}
			}
				else{
					i--;
				}
				
		}
		writer.write(text);
		writer.close();
		System.out.println("obstacles done");
		
		
	}
	

}
