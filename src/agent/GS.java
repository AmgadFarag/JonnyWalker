
package agent;

import java.util.ArrayList;
import java.util.PriorityQueue;

import agent.structures.SearchTreeNode;

public class GS extends Search{
	private PriorityQueue<SearchTreeNode> queue;
	private int heuristic;
	
	public GS(int heuristic, SearchTreeNode root) {
		this.heuristic = heuristic;
		this.root = root;
		root.setHeursticCost(heuristic ==1 ? heuristic1(root) :heuristic2(root));
		queue = new PriorityQueue<SearchTreeNode>();
		queue.add(root);
	}

	@Override
	public SearchTreeNode begin() {
		while(true){
			if(queue.isEmpty()){
				System.out.println("No more moves -GS");
				return null;
			}
			SearchTreeNode current = queue.poll();
			//System.out.println(current);
			if (isGoal(current))
					return current;
		    ArrayList<SearchTreeNode> expandednodes = expandNode(current);
			for (int j = expandednodes.size() - 1; j >= 0; j--) {
				SearchTreeNode curr = expandednodes.get(j);
				if(heuristic==1)
				    curr.setHeursticCost(heuristic1(curr));
				if(heuristic == 2)
					curr.setHeursticCost(heuristic2(curr));
			    queue.add(expandednodes.get(j));
				cumelativeExpansions++;
			}
		}
	}

}