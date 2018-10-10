package agent;

import java.util.ArrayList;
import java.util.PriorityQueue;

import agent.structures.Operator;
import agent.structures.SearchTreeNode;
import agent.structures.State;
import models.MiniMap;

public class UCS extends Search{
    private SearchTreeNode root;
	private PriorityQueue<SearchTreeNode> queue;
	
	public UCS(SearchTreeNode root) {
		// TODO Auto-generated constructor stub
		this.root = root;
		queue = new PriorityQueue<SearchTreeNode>();
		queue.add(root);
		begin();
	}
	
	public SearchTreeNode begin() {
		while(true){
			if(queue.isEmpty()){
				System.out.println("no more moves -ucs");
				return null;
			}
			SearchTreeNode current = queue.poll();
			System.out.println(current);
			if (isGoal(current))
					return current;
		    ArrayList<SearchTreeNode> expandednodes = expandNode(current);
			for (int j = expandednodes.size() - 1; j >= 0; j--) {
						queue.add(expandednodes.get(j));
			}
		}
	}
}

