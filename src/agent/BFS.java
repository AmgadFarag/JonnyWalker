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
//			System.out.println(current.toString());
			GoalReached = true;
			return current;
		}

		ArrayList<SearchTreeNode> expansion = expandNode(current);
		//System.out.println();
		LinkedList<SearchTreeNode> newQ = new LinkedList<SearchTreeNode>();
		for(SearchTreeNode temp: expansion){
			//System.out.print(" , " + temp.toString());
			if(temp != null){
				//System.out.println(temp);
				newQ.add(temp);
				cumelativeExpansions++;
			}
		}
		while(!queue.isEmpty()){
			newQ.add(queue.removeFirst());
		}
		queue = newQ;
		//System.out.println(queue);		
		return begin();
	}

}
