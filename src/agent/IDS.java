package agent;

import java.util.ArrayList;
import java.util.LinkedList;

import agent.structures.SearchTreeNode;
import utilities.utilities;

public class IDS extends Search {
	LinkedList<SearchTreeNode> queue = new LinkedList<SearchTreeNode>();
	public IDS(SearchTreeNode root) {
		this.root = root;
		queue.add(root);
	}

	public SearchTreeNode begin() {
	
		for(int i =0; ; i++){
			LinkedList<SearchTreeNode> Q =depthLimitedSearch(i);
			while(!Q.isEmpty()){
				SearchTreeNode current = Q.removeFirst();
				if(isGoal(current))
					return current;
			}
			
		}
	}
	private LinkedList<SearchTreeNode> depthLimitedSearch(int depth){
		LinkedList<SearchTreeNode> visited = new LinkedList<SearchTreeNode>();
		while(!queue.isEmpty()){
			SearchTreeNode current = queue.removeFirst();
			
			if(current.getDepth() == depth-1){
				visited.addLast(current);
				ArrayList<SearchTreeNode> expansion = expandNode(current);
				
				visited.addAll(expansion);
			}else{
				visited.add(current);
			}
		}
		queue.addAll(visited);
		cumelativeExpansions++;
		return visited;
	}
	
}