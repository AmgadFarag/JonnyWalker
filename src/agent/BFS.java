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
		begin();
	}
	
	public SearchTreeNode begin(){
		if(GoalReached)
			return null;
		if(queue.isEmpty())
			return null;
		
		SearchTreeNode current = queue.removeFirst();
//		System.out.println(current.toString());
		if(isGoal(current)){
			GoalReached = true;
			return current;
		}

		ArrayList<SearchTreeNode> expansion = expandNode(current);
		System.out.println();
		for(SearchTreeNode temp: expansion){
			System.out.print(" , " + temp.toString());
			if(temp != null){
				//System.out.println(temp);
				queue.addLast(temp);
				cumelativeExpansions++;
			}
		}
		//System.out.println(queue);		
		return begin();
	}

}
