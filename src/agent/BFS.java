package agent;

import java.util.ArrayList;
import java.util.LinkedList;

import models.MiniMap;
import agent.structures.SearchTreeNode;


public class BFS extends Search{
	private static LinkedList<SearchTreeNode> queue;

	public BFS(SearchTreeNode tre, MiniMap newWorld) {
		queue = new LinkedList<SearchTreeNode>();
		root = tre;
		queue.add(tre);
		begin(newWorld);
	}
	
	public SearchTreeNode begin(MiniMap world){
		SearchTreeNode current = queue.getFirst();
		if(isGoal(current))
			return current;

		ArrayList<SearchTreeNode> expansion = expandNode(world, current);
		
		for(SearchTreeNode temp: expansion)
			if(temp != null){
				System.out.println(temp);
				queue.addLast(temp);
			}

		return begin(world);
	}

}
