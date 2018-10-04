package controllers;

import agent.*;
import agent.structures.SearchTreeNode;
import agent.structures.State;
import models.GenGrid;
import models.WorldHandler;
import models.WorldListener;

public class MainController implements WorldListener{
	public WorldHandler world;
	public GenGrid grid;
	
	public void GenGrid(){
		grid = new GenGrid();
	}
	
	public Object[] Search(GenGrid grid, String strategy, boolean visualize){
		Object [] returns= new Object[3];//[0] sequence - [1] cost - [2] number nodes
		
		/*world = new WorldHandler(this, grid);
		State initial = new State(world.map.mapM, false);
		SearchTreeNode root = new SearchTreeNode(null, initial,null,null,0,0);
		
		Search search;
		switch(strategy){
		case "BF" : search = new BFS(); break;
		case "DF" : search = new DFS(root, world); break;
		case "ID" : search = new IDS(root, world); break;
		case "UC" : search = new UCS(); break;
		case "GR1": search = new GS(1); break;
		case "GR2": search = new GS(2); break;
		case "AS1": search = new AS(1); break;
		case "AS2": search = new AS(2); break;
		}*/
		
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
