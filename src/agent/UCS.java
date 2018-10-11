package agent;

import java.util.ArrayList;
import java.util.PriorityQueue;

import agent.structures.SearchTreeNode;

public class UCS extends Search{
	private PriorityQueue<SearchTreeNode> queue;
	
	public UCS(SearchTreeNode root) {
		queue = new PriorityQueue<SearchTreeNode>();
		this.root = root;
		queue.add(root);
		begin();
	}
	
	public SearchTreeNode begin() {
		if(queue.isEmpty()){
			System.out.println("No more moves -ucs");
			return null;
		}
			
		SearchTreeNode current = queue.poll();
		System.out.println(current);
		if (isGoal(current))
			return current;
		
		ArrayList<SearchTreeNode> expandednodes = expandNode(current);
		for (SearchTreeNode temp: expandednodes)
			if(temp != null){
				queue.add(temp);
				cumelativeExpansions++;
			}
		
		//System.out.println(queue);
		return begin();
	}
}
