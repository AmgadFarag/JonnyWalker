package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import view.View;
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
	public View view;
	
	public GenGrid GenGrid(){
		grid = new GenGrid();
		return grid;
	}
	
	public Object[] Search(GenGrid grid, String strategy, boolean visualize){
		Object [] returns= new Object[3];//[0] sequence - [1] cost - [2] number nodes
		
		State initial = new State(grid.walkers.size(), false);
		
		MiniMap miniWorld = new MiniMap(grid.mapM, grid.mapN, grid.MAX_DRAGON_GLASS,
				grid.stones, grid.walkers, grid.obstacles, grid.mapM, grid.mapN, 0);
		

		SearchTreeNode root = new SearchTreeNode(miniWorld, initial,null,null,0,0,"");
		
		Search search;
		switch(strategy){
		case "BF" : search = new BFS(root); break;
		case "DF" : search = new DFS(root); break;
		case "ID" : search = new IDS(root); break;
		case "UC" : search = new UCS(root); root.setSearchType("UCS");break;
		case "GR1": search = new GS(1,root); root.setSearchType("Greedy"); break;
		case "GR2": search = new GS(2,root); root.setSearchType("Greedy"); break;
		case "AS1": search = new AS(1,root); root.setSearchType("AS"); break;
		case "AS2": search = new AS(2,root); root.setSearchType("AS"); break;
		default : search = new BFS(root); break;
		}
		
		SearchTreeNode goal = search.begin();
		if(goal == null){
			returns[0] = null;
			returns[1] = 1000;
			returns[2] = search.getCumelativeExpansions();
		}else{
		
			returns[0] = (ArrayList<SearchTreeNode>)Search.backTrack(goal);
			returns[1] = (int)goal.getPathCost();
			returns[2] = (int)search.getCumelativeExpansions();
		
			if(visualize)
				visual((ArrayList<SearchTreeNode>) returns[0]);
		}
		return returns;
	}

	public void visual(ArrayList<SearchTreeNode> trace){
		world = new WorldHandler(this, grid);
		view = new View(world, trace);

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
		Scanner scan = new Scanner(System.in);
		GenGrid grid = null;
		Object[] ret = new Object[3];
		ret[0] = new ArrayList<SearchTreeNode>();
		ret[1] = 0;
		ret[2] = 0;
		boolean compute = true;
		System.out.println("Soft Start Complete, Welcome");
		while(compute){
			System.out.println("Options: -- 'GenGrid' to generate the grid"+"\n"+
					" -- 'ViewGrid' to preview the grid"+"\n"+
					" -- 'Search(ALGORITHM, VISUAL)' to start searching for a solution:"+"\n"+
					"where ALGORITHM is BF, DF, ID, UC, GR1, GR2, AS1, AS2"+"\n"+
					"and VISUAL is True to visualise, False to print solution only"+"\n"+
					" -- 'Exit' to quit");
			String input = scan.nextLine();

			if(input.contains("Exit") || input.contains("exit"))
				compute = false;
			
			if(input.contains("Gen") || input.contains("gen")){
				grid = ct.GenGrid();
				System.out.println("Grid Generated");
			}
			
			if(input.contains("View") || input.contains("view"))
				ct.visual(null);

			if(input.contains("Search") || input.contains("search")){
				if(grid == null)
					System.out.println("You can't search in an empty space, Please use GenGrid first");
				else{
					boolean visual = input.contains("true") || input.contains("True");
				
					if(input.contains("BF")){
						try{
							ret = ct.Search(grid, "BF", visual);
							System.out.println("Search Complete");
						}catch(Exception e){
							System.out.println("Search Fail");
							System.out.println(e.getMessage());
						}
					}
					if(input.contains("DF")){
						try{
							ret = ct.Search(grid, "DF", visual);
							System.out.println("Search Complete");
						}catch(Exception e){
							System.out.println("Search Fail");
							System.out.println(e.getMessage());
						}
					}
					if(input.contains("UC")){
						try{
							ret = ct.Search(grid, "UC", visual);
							System.out.println("Search Complete");
						}catch(Exception e){
							System.out.println("Search Fail");
							System.out.println(e.getMessage());
						}
					}
					if(input.contains("ID")){
						try{
							ret = ct.Search(grid, "ID", visual);
							System.out.println("Search Complete");
						}catch(Exception e){
							System.out.println("Search Fail");
							System.out.println(e.getMessage());
						}
					}
					if(input.contains("GR1")){
						try{
							ret = ct.Search(grid, "GR1", visual);
							System.out.println("Search Complete");
						}catch(Exception e){
							System.out.println("Search Fail");
							System.out.println(e.getMessage());
						}
					}
					if(input.contains("GR2")){
						try{
							ret = ct.Search(grid, "GR2", visual);
							System.out.println("Search Complete");
						}catch(Exception e){
							System.out.println("Search Fail");
							System.out.println(e.getMessage());
						}
					}
					if(input.contains("AS1")){
						try{
							ret = ct.Search(grid, "AS1", visual);
							System.out.println("Search Complete");
						}catch(Exception e){
							System.out.println("Search Fail");
							System.out.println(e.getMessage());
						}
					}
					if(input.contains("AS2")){
						try{
							ret = ct.Search(grid, "AS2", visual);
							System.out.println("Search Complete");
						}catch(Exception e){
							System.out.println("Search Fail");
							System.out.println(e.getMessage());
						}
					}
					try{
						for(int i=0; i<((ArrayList<SearchTreeNode>)ret[0]).size(); i++)
							System.out.println(((ArrayList<SearchTreeNode>)ret[0]).get(i).toString());
					}catch(Exception e){
						System.out.println("Search Fail");
						System.out.println(e.getMessage());
					}
				}
			}
		}
		scan.close();
	}

}
