package models;

import java.util.ArrayList;

import agent.structures.Operator;
import utilities.utilities;

public class MiniMap {
	public final int mapM, mapN;
	public ArrayList<int[]> stones;
	public ArrayList<int[]> walkers;
	public ArrayList<int[]> obstacles;
	public final int MAX_DRAGON_GLASS;
	public int x,y,dragonGlass;

	public MiniMap(int mapM, int mapN, int max, 
			ArrayList<int[]> stones, ArrayList<int[]> walkers, 
			ArrayList<int[]> obstacles, 
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
	public MiniMap(MiniMap old){
		this.mapM = old.mapM;
		this.mapN = old.mapN;
		MAX_DRAGON_GLASS = old.MAX_DRAGON_GLASS;
		this.stones = utilities.copyArray(old.stones);
		this.walkers = utilities.copyArray(old.walkers);
		this.obstacles = utilities.copyArray(old.obstacles);
		this.x = old.x;
		this.y = old.y;
		this.dragonGlass = old.dragonGlass;
	}
	
	public void kill(){//Actual Killing surrounding Walkers
		for(int i=0; i<walkers.size(); i++){
			int[] wal = walkers.get(i);
			if((wal[0] == x && wal[1]+1 == y) ||
					(wal[0] == x && wal[1]-1 == y) ||
					(wal[0]+1 == x && wal[1] == y) ||
					(wal[0]-1 == x && wal[1] == y) ){
				walkers.remove(i);
				
			}
		}
	}
	
	public boolean ifAttack() {
		for(int[] wal : walkers){
			if((wal[0] == x && wal[1]+1 == y) ||
					(wal[0] == x && wal[1]-1 == y) ||
					(wal[0]+1 == x && wal[1] == y) ||
					(wal[0]-1 == x && wal[1] == y) )
				return true;
		}
		return false;
	}

	public boolean ifPickUp() {
		if(dragonGlass<=0)
			for(int[] st : stones){
//				if((st[0] == x && st[1]+1 == y) ||
//						(st[0] == x && st[1]-1 == y) ||
//						(st[0]+1 == x && st[1] == y) ||
//						(st[0]-1 == x && st[1] == y) )
//					return true;
				if((st[0] == x && st[1] == y) )
					return true;
			}
		return false;
	}
	
	public boolean ifMoveUp() {
		if(0 > x-1)
			return false;
		for(int[] temp : obstacles)
			if(temp[0] == x-1 && temp[1] == y)
				return false;
		for(int[] temp : walkers)
			if(temp[0] == x-1 && temp[1] == y)
				return false;
		return true;
	}
	public boolean ifMoveDown() {
		if(mapM <= x+1)
			return false;
		for(int[] temp : obstacles)
			if(temp[0] == x+1 && temp[1] == y)
				return false;
		for(int[] temp : walkers)
			if(temp[0] == x+1 && temp[1] == y)
				return false;
		return true;
	}
	public boolean ifMoveRight() {
		if(mapN <= y+1)
			return false;
		for(int[] temp : obstacles)
			if(temp[0] == x && temp[1] == y+1)
				return false;
		for(int[] temp : walkers)
			if(temp[0] == x && temp[1] == y+1)
				return false;
		return true;
	}
	public boolean ifMoveLeft() {
		if(0 > y-1)
			return false;
		for(int[] temp : obstacles)
			if(temp[0] == x && temp[1] == y-1)
				return false;
		for(int[] temp : walkers)
			if(temp[0] == x && temp[1] == y-1)
				return false;
		return true;
	}
	public boolean containStone(Operator op){
		switch (op) {
		case RIGHT:
			for(int[] temp : stones)
				if(temp[0] == x && temp[1] == y+1)
					return true;
			return false;
		case LEFT:
			for(int[] temp : stones)
				if(temp[0] == x && temp[1] == y-1)
					return true;
			return false;
		case UP:
			for(int[] temp : stones)
				if(temp[0] == x-1 && temp[1] == y)
					return true;
			return false;
		case DOWN:
			for(int[] temp : stones)
				if(temp[0] == x+1 && temp[1] == y)
					return true;
			return false;

		default:
			return false;
		} 
	}
}
