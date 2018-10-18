package agent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import agent.structures.SearchTreeNode;

public class DFS extends Search{
	private static LinkedList<SearchTreeNode> queue;
	private static boolean GoalReached = false;
	public DFS(SearchTreeNode tre) {
		queue = new LinkedList<SearchTreeNode>();
		root = tre;
		queue.add(tre);
		cumelativeExpansions = 0;
	}
	
	public SearchTreeNode begin(){
		if(GoalReached)
			return null;
		if(queue.isEmpty())
			return null;

		SearchTreeNode current = queue.removeFirst();
		if(isGoal(current)){
			GoalReached = true;
			//System.out.println(current);
			return current;
		}

		ArrayList<SearchTreeNode> expansion = expandNode(current);
		System.out.println();
		for(SearchTreeNode temp : expansion){
			System.out.print(", "+temp);
			queue.addLast(temp);
			cumelativeExpansions++;
		}
		
		//System.out.println(queue);
		return begin();
	}

}
