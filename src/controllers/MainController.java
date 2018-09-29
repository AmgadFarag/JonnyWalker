package controllers;

import agent.*;
import models.Jon;
import models.WorldHandler;
import models.WorldListener;

public class MainController implements WorldListener{
	public WorldHandler world;
	public Jon jon;
	
	public void GenGrid(){
		jon = new Jon();
		world = new WorldHandler(this, jon);
	}
	
	public Object[] Search(/*grid,*/ String strategy, boolean visualize){
		Object [] returns= new Object[3];//[0] sequence - [1] cost - [2] number nodes
		
		Search search;
		switch(strategy){
		case "BF" : search = new BFS(); break;
		case "DF" : search = new DFS(); break;
		case "ID" : search = new IDS(); break;
		case "UC" : search = new UCS(); break;
		case "GR1": search = new GS(1); break;
		case "GR2": search = new GS(2); break;
		case "AS1": search = new AS(1); break;
		case "AS2": search = new AS(2); break;
		}
		
		return returns;
	}

	@Override
	public void onWalkerKilled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHitWalker() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHitObstacle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHitDragonStoneCell() {
		// TODO Auto-generated method stub
		
	}

}
