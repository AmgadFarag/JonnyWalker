package agent;

import java.util.Stack;

import models.WorldHandler;
import agent.structures.SearchTreeNode;

public class DFS extends Search{
	private SearchTreeNode root;
	private Stack<SearchTreeNode> queue;

	public DFS(SearchTreeNode tre, WorldHandler newWorld) {
		world = newWorld;
		root = tre; 
		queue = new Stack<SearchTreeNode>();
	}
	
	public SearchTreeNode begin(){
		SearchTreeNode current = queue.pop();
		if(isGoal(current))
			return current;
		SearchTreeNode[] expansion = expandNode(current);
		
		for(SearchTreeNode temp: expansion)
			try{
				queue.push(temp);
			}catch(NullPointerException e){
			}

		return begin();
	}

}
