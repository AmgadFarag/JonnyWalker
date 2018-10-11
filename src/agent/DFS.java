package agent;

import java.util.ArrayList;
import java.util.Stack;

import agent.structures.SearchTreeNode;

public class DFS extends Search{
	private static Stack<SearchTreeNode> queue;

	public DFS(SearchTreeNode tre) {
		queue = new Stack<SearchTreeNode>();
		root = tre;
		queue.push(tre);
		cumelativeExpansions = 0;
		begin();
	}
	
	public SearchTreeNode begin(){
		if(queue.isEmpty())
			return null;

		SearchTreeNode current = queue.pop();
		if(isGoal(current)){
			System.out.println(current);
			return current;
		}

		ArrayList<SearchTreeNode> expansion = expandNode(current);
		
		for(SearchTreeNode temp : expansion){
			//System.out.println(temp);
			queue.push(temp);
			cumelativeExpansions++;
		}
		
		//System.out.println(queue);
		return begin();
	}

}
