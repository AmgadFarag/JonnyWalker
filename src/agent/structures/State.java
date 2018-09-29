package agent.structures;

public class State {
	public int x,y;
	public int walkersLeft;//TODO Ask bout this
	public int dragonglassLeft;
	public boolean localGoal;//kill or pickup
	
	public State(int x, int y, int walker, int glass, boolean locgoal){
		this.x = x;
		this.y = y;
		walkersLeft = walker;
		dragonglassLeft = glass;
		localGoal = locgoal;
	}

}
