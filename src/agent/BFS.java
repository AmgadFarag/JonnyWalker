package agent;

import java.util.ArrayList;
import java.util.LinkedList;

import agent.structures.SearchTreeNode;


public class BFS extends Search{
	private static LinkedList<SearchTreeNode> queue;

	public BFS(SearchTreeNode tre) {
		queue = new LinkedList<SearchTreeNode>();
		root = tre;
		queue.add(tre);
		cumelativeExpansions = 0;
		begin();
	}
	
	public SearchTreeNode begin(){
		if(queue.isEmpty())
			return null;
		
		SearchTreeNode current = queue.removeFirst();
		if(isGoal(current)){
			System.out.println(current);
			return current;
		}

		ArrayList<SearchTreeNode> expansion = expandNode(current);
		
		for(SearchTreeNode temp: expansion)
			if(temp != null){
				//System.out.println(temp);
				queue.addLast(temp);
				cumelativeExpansions++;
			}

		System.out.println(queue);		
		return begin();
	}

}
