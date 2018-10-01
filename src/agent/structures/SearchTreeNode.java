package agent.structures;

import models.MiniMap;

public class SearchTreeNode {
	protected State state;
	protected SearchTreeNode parent;
	protected Operator operatorApplied;
	protected int depth;
	protected int pathCost;
	protected final MiniMap worldState;

	public SearchTreeNode(MiniMap world, State stat, SearchTreeNode prnt, 
			Operator op, int dpth, int cost) {
		worldState = world;
		state = stat;
		parent = prnt;
		operatorApplied = op;
		depth = dpth;
		pathCost = cost;
	}
	

	public State getState() {
		return state;
	}
	public SearchTreeNode getParent() {
		return parent;
	}
	public Operator getOperatorApplied() {
		return operatorApplied;
	}
	public int getDepth() {
		return depth;
	}
	public int getPathCost() {
		return pathCost;
	}

}
