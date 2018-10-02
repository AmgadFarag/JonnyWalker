package agent.structures;

public class SearchTreeNode {
	public static String lastName;
	protected String myLabel;
	protected State state;
	protected SearchTreeNode parent;
	protected Operator operatorApplied;
	protected int depth;
	protected int pathCost;

	public SearchTreeNode(State stat, SearchTreeNode prnt, 
			Operator op, int dpth, int cost) {
		if(lastName.equals("") || lastName.equals(null)){
			myLabel = "N0";
			lastName = "N0";
		}else{
			int lastCount = Integer.parseInt(lastName.substring(1));
			lastCount++;
			myLabel = "N"+lastCount;
			lastName = "N"+lastCount;
		}
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
}
