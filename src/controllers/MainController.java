package controllers;

import java.util.ArrayList;

import agent.*;
import agent.structures.SearchTreeNode;
import agent.structures.State;
import models.GenGrid;
import models.MiniMap;
import models.WorldHandler;
import models.WorldListener;

public class MainController implements WorldListener{
	public WorldHandler world;
	public GenGrid grid;
	
	public GenGrid GenGrid(){
		grid = new GenGrid();
		return grid;
	}
	
	public Object[] Search(GenGrid grid, String strategy, boolean visualize){
		Object [] returns= new Object[3];//[0] sequence - [1] cost - [2] number nodes
		
		State initial = new State(grid.walkers.length, false);
		SearchTreeNode root = new SearchTreeNode(null, initial,null,null,0,0);
		
		MiniMap miniWorld = new MiniMap(grid.mapM, grid.mapN, grid.MAX_DRAGON_GLASS,
				grid.stones, grid.walkers, grid.obstacle, grid.mapM, grid.mapN, 0);
		
		Search search;
		switch(strategy){
		case "BF" : search = new BFS(root, miniWorld); break;
		case "DF" : search = new DFS(root, miniWorld); break;
		case "ID" : search = new IDS(root, miniWorld); break;
		case "UC" : search = new UCS(root, miniWorld); break;
		case "GR1": search = new GS(1); break;
		case "GR2": search = new GS(2); break;
		case "AS1": search = new AS(1); break;
		case "AS2": search = new AS(2); break;
		default : search = new AS(1); break;
		}
		
		SearchTreeNode goal = search.begin(miniWorld);
		if(goal == null){
			returns[0] = null;
			returns[1] = 1000;
			returns[2] = search.getCumelativeExpansions();
		}else{
		
			returns[0] = (ArrayList<SearchTreeNode>)Search.backTrack(goal);
			returns[1] = (int)goal.getPathCost();
			returns[2] = (int)search.getCumelativeExpansions();
		
			if(visualize)
				visual((SearchTreeNode[]) returns[0]);
		}
		return returns;
	}

	public void visual(SearchTreeNode[] trace){
		//world = new WorldHandler(this, grid);
		return;
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
	
	
	public static void main(String[] args) {
		MainController ct = new MainController();
		System.out.println("Soft Start");
		GenGrid grid = ct.GenGrid();
		System.out.println("Grid Generated, starting search ......");
		Object[] ret = ct.Search(grid, "DF", false);
		System.out.println("Search Complete");
		
		for(int i=0; i<((ArrayList<SearchTreeNode>)ret[0]).size(); i++)
			System.out.println(((ArrayList<SearchTreeNode>)ret[0]).get(i).toString());
	}

}
