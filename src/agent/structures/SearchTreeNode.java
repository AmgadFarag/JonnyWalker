package agent.structures;

import models.MiniMap;
import java.util.concurrent.atomic.AtomicLong;

public class SearchTreeNode implements Comparable<SearchTreeNode>{
	public static String lastName;
	//protected String myLabel;
	protected State state;
	protected SearchTreeNode parent;
	protected Operator operatorApplied;
	protected int depth;
	protected int pathCost;
	private final MiniMap worldState;
	protected final static AtomicLong seq = new AtomicLong();
	protected long seqnum;
	protected String searchType;
	protected int heursticCost;

	public String getSearchType() {
		return searchType;
	}

	public SearchTreeNode(MiniMap world, State stat, SearchTreeNode prnt, 
			Operator op, int dpth, int cost,String searchType) {

		seqnum = seq.getAndIncrement();
		worldState = world;
		state = stat;
		parent = prnt;
		operatorApplied = op;
		depth = dpth;
		pathCost = cost;
		this.searchType = searchType;
		
	}
	
	public int getHeursticCost() {
		return heursticCost;
	}

	public void setHeursticCost(int heursticCost) {
		this.heursticCost = heursticCost;
	}

	public MiniMap getWorldState() {
		return worldState;
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

	@Override
    public int compareTo(SearchTreeNode n) {
		switch (searchType) {
		case "UCS":
			if(this.pathCost == n.pathCost)
	       	 	return this.seqnum > n.seqnum ? 1 : -1;
	       	else
	      		return this.pathCost > n.pathCost ? 1 : -1;
		case "Greedy":
			if(this.heursticCost == n.heursticCost)
	       	 	return this.seqnum > n.seqnum ? 1 : -1;
	       	else
	      		return this.heursticCost > n.heursticCost ? 1 : -1;
		case "AS":
			break;
		case "":
			break;
		}
			
		return 0;

		} 
			
       

	public String toString(){
		String s = "Label: "+/*myLabel*/seqnum+" After ";
		s+=(operatorApplied!=null)? operatorApplied.toString():" NOTHING";
		s+=" Costing "+pathCost;
		return s;
	}

	public void setSearchType(String string) {
		this.searchType = string;
		
	}
	
}
