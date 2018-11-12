package agent;

import java.util.ArrayList;
import java.util.LinkedList;

import agent.structures.SearchTreeNode;

public class BFS extends Search{
	private static LinkedList<SearchTreeNode> queue;
	private static boolean GoalReached = false;
	public BFS(SearchTreeNode tre) {
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
			return current;
		}

		ArrayList<SearchTreeNode> expansion = expandNode(current);
		LinkedList<SearchTreeNode> newQ = new LinkedList<SearchTreeNode>();
		for(SearchTreeNode temp: expansion){
			if(temp != null){
				newQ.add(temp);
				cumelativeExpansions++;
			}
		}
		while(!queue.isEmpty()){
			newQ.add(queue.removeFirst());
		}
		queue = newQ;
		return begin();
	}

}
