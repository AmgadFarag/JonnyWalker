package agent.structures;

public class SearchTreeNode {
	protected State state;
	protected SearchTreeNode parent;
	protected Operator operatorApplied;
	protected int depth;
	protected int pathCost;

	protected SearchTreeNode(State stat, SearchTreeNode prnt, 
			Operator op, int dpth, int cost) {
		state = stat;
		parent = prnt;
		operatorApplied = op;
		depth = dpth;
		pathCost = cost;
	}
	
	protected void addNode(SearchTreeNode newNode){
		newNode.parent = this;
	}

}
