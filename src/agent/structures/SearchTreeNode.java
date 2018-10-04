package agent.structures;

import models.MiniMap;
import java.util.concurrent.atomic.AtomicLong;

public class SearchTreeNode implements Comparable<SearchTreeNode>{
	public static String lastName;
	protected String myLabel;
	protected State state;
	protected SearchTreeNode parent;
	protected Operator operatorApplied;
	protected int depth;
	protected int pathCost;
	private final MiniMap worldState;
	protected final static AtomicLong seq = new AtomicLong();
	protected long seqnum;

	public SearchTreeNode(MiniMap world, State stat, SearchTreeNode prnt, 
			Operator op, int dpth, int cost) {

		seqnum = seq.getAndIncrement();
		myLabel = "N"+seqnum;
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
	public String getMyLabel(){
		return myLabel;
	}
	@Override
        public int compareTo(SearchTreeNode n) {
        	if(this.pathCost == n.pathCost)
           	 	return this.seqnum > n.seqnum ? 1 : -1;
        	else
            		return this.pathCost > n.pathCost ? 1 : -1;
    	}


	public MiniMap getWorldState() {
		return worldState;
	}
}
