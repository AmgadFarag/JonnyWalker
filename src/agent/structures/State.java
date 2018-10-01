package agent.structures;

public class State {
	public int walkersLeft;//TODO Ask bout this
	public boolean localGoal;//kill or pickup
	
	public State(int walker, boolean locgoal){
		walkersLeft = walker;
		localGoal = locgoal;
	}

}
