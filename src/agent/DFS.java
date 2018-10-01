package agent;

import java.util.Stack;

import models.MiniMap;
import agent.structures.SearchTreeNode;

public class DFS extends Search{
	private static Stack<SearchTreeNode> queue;

	public DFS(SearchTreeNode tre, MiniMap newWorld) {
		queue = new Stack<SearchTreeNode>();
		root = tre;
		queue.push(tre);
		begin(newWorld);
	}
	
	public SearchTreeNode begin(MiniMap world){
		SearchTreeNode current = queue.pop();
		if(isGoal(current))
			return current;
		SearchTreeNode[] expansion = expandNode(world, current);
		
		for(SearchTreeNode temp: expansion)
			try{
				queue.push(temp);
			}catch(NullPointerException e){
			}

		return begin(world);
	}

}
