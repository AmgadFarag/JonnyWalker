package agent;

import java.util.ArrayList;
import java.util.Stack;

import models.MiniMap;
import agent.structures.SearchTreeNode;

public class DFS extends Search{
	private static Stack<SearchTreeNode> queue;

	public DFS(SearchTreeNode tre, MiniMap newWorld) {
		queue = new Stack<SearchTreeNode>();
		root = tre;
		queue.push(tre);
		cumelativeExpansions = 0;
		System.out.println(root);
		begin(newWorld);
	}
	
	public SearchTreeNode begin(MiniMap world){
		if(queue.isEmpty())
			return null;

		SearchTreeNode current = queue.pop();
		if(isGoal(current)){
			System.out.println(current);
			return current;
		}

		ArrayList<SearchTreeNode> expansion = expandNode(world, current);
		
		for(int i=expansion.size()-1; i>=0; i--){
			SearchTreeNode temp = expansion.get(i);
			System.out.println(temp);
			queue.push(temp);
			cumelativeExpansions++;
		}
		

		return begin(world);
	}

}
