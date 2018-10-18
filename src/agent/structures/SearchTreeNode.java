package agent.structures;

import models.MiniMap;

import java.util.ArrayList;
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
	

	@Override
    public int compareTo(SearchTreeNode n) {
		switch (searchType) {
		case "UCS":
			if(this.pathCost != n.pathCost)
	      		return (this.pathCost > n.pathCost)? 1 : -1;
	      		
		case "Greedy":
			if(this.heursticCost != n.heursticCost)
	      		return (this.heursticCost > n.heursticCost)? 1 : -1;
	      		
		case "AS":
		    int combineda = heursticCost + pathCost;
		    int combinedb = n.heursticCost + n.pathCost;
			if(combineda != combinedb)
	      		return (combineda > combinedb)? 1 : -1;
	      		
		default: break;
		}
		
		return (this.seqnum > n.seqnum)? 1 : -1;
	} 
		

	public String toString(){
		String s = "Label: "+/*myLabel*/seqnum;
		try{
			int m = getWorldState().mapM;
			int n = getWorldState().mapN;
			ArrayList<int[]> stones = getWorldState().stones;
			ArrayList<int[]> obstcals = getWorldState().obstacles;
			ArrayList<int[]> walkers = getWorldState().walkers;
			int x = getWorldState().x;
			int y = getWorldState().y;
			boolean filled = false;
			s = "Label: "+ seqnum+", Parent: ";
			s+= (parent!=null)? parent.seqnum: "0";
			s+= ", After:";
			s+=(operatorApplied!=null)? operatorApplied.toString():" NOTHING" ;
			s+=" Cost "+pathCost+"\n";
			for(int i =0; i <m; i++){
				for(int j =0; j<n; j++){
					if(x == i && y == j ){
						s+= " [J] ";
						filled = true;
					}
					if(!filled)
					for(int stidx = 0;stidx < stones.size() &&!filled; stidx++){
						int[] stone = stones.get(stidx);
						if(i == stone[0] && j == stone[1] &&!filled){
							s += " [S] ";
							filled =true;
						}
					}
					if(!filled)
					for(int obidx = 0;obidx < obstcals.size()&&!filled; obidx++){
						int[] obstcal = obstcals.get(obidx);
						if(i == obstcal[0] && j == obstcal[1]){
							s += " [O] ";
							filled = true;
						}
					}
					if(!filled)
					for(int walidx = 0;walidx < walkers.size()&&!filled; walidx++){
						int[] walker = walkers.get(walidx);
						if(i == walker[0] && j == walker[1]){
							s += " [W] ";
							filled = true;
						}
					}
					
					
					if(!filled)
						s+= " [E] ";
					filled =false;
				}
				filled =false;
				s+="\n";
			}
//		s = "Label: "+/*myLabel*/seqnum+", Parent: "+parent.seqnum+ "WorldSate: "+ worldState.x +", "+ worldState.y + " After ";
//		s+=(operatorApplied!=null)? operatorApplied.toString():" NOTHING";
//		s+=" Cost "+pathCost;
		}catch(Exception e){
			
		}
		return s;
	}

	public void setSearchType(String string) {
		this.searchType = string;
		
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
}
