package agent;

import models.MiniMap;
import agent.structures.Operator;
import agent.structures.SearchTreeNode;
import agent.structures.State;

public abstract class Search {
	protected SearchTreeNode root;
	protected final int MAX_DPETH = 1000;
	protected int cumelativeCost;
	
	public boolean isGoal(SearchTreeNode node){
		if(node.getState().walkersLeft <= 0)
			return true;
		return false;
	}
	public boolean isLocalGoal(SearchTreeNode node){
			if(node.getState().localGoal)
				if(node.getOperatorApplied() == Operator.KILL)
					return true;
			else
				if(node.getOperatorApplied() == Operator.PICKUP)
					return true;
			
			return false;
	}
	
	/*public static int scoreOfOperator(WorldHandler world, SearchTreeNode node, Operator op){
		switch(op){
		case KILL: 
			int walkerCount = world.ifAttack();
			if(node.getState().walkersLeft == walkerCount)
				return 20;
			return walkerCount*5;
			
		case PICKUP:
			if(world.jon.getDragonGlass() > 0)
				return -6;
			else
				return 5;

		case UP:
			if(node.getOperatorApplied() == Operator.DOWN)
				return -3;
			if(node.getOperatorApplied() == Operator.KILL)
				return -3;
			if(world.ifMoveUp() == null)
				return -10;
			if(world.ifMoveUp() instanceof WalkerCell)
				return -20;
			if(world.ifMoveUp() instanceof ObstacleCell)
				return -10;
			return -1;

		case DOWN:
			if(node.getOperatorApplied() == Operator.UP)
				return -3;
			if(node.getOperatorApplied() == Operator.KILL)
				return -3;
			if(world.ifMoveDown() == null)
				return -10;
			if(world.ifMoveDown() instanceof WalkerCell)
				return -20;
			if(world.ifMoveDown() instanceof ObstacleCell)
				return -10;
			return -1;
		
		case LEFT:
			if(node.getOperatorApplied() == Operator.RIGHT)
				return -3;
			if(node.getOperatorApplied() == Operator.KILL)
				return -3;
			if(world.ifMoveLeft() == null)
				return -10;
			if(world.ifMoveLeft() instanceof WalkerCell)
				return -20;
			if(world.ifMoveLeft() instanceof ObstacleCell)
				return -10;
			return -1;
		
		case RIGHT:
			if(node.getOperatorApplied() == Operator.LEFT)
				return -3;
			if(node.getOperatorApplied() == Operator.KILL)
				return -3;
			if(world.ifMoveRight() == null)
				return -10;
			if(world.ifMoveRight() instanceof WalkerCell)
				return -20;
			if(world.ifMoveRight() instanceof ObstacleCell)
				return -10;
			return -1;
		
		}
		return 0;
	}
	*/

	public static SearchTreeNode[] expandNode(MiniMap world, SearchTreeNode node){
		SearchTreeNode[] result = new SearchTreeNode[6];
		State current = node.getState();
		int walkersLeft = current.walkersLeft;
		boolean localGoal = current.localGoal;
		MiniMap moveAfterKill = world;

		State normalState = new State(walkersLeft, localGoal);
		State killState = new State(walkersLeft--, localGoal);
		State pickupState = new State(walkersLeft, true);

		if(node.getOperatorApplied() != Operator.KILL)
			moveAfterKill.dragonGlass--;
		else
			if(world.dragonGlass > 1)
				killState = new State(walkersLeft--, false);

		if(world.dragonGlass > 1){
			normalState = new State(walkersLeft, false);
			killState = new State(walkersLeft, false);
		}

		if(world.ifMoveUp())
			//UP
			result[0] = new SearchTreeNode(moveAfterKill, normalState, node, 
					Operator.UP, node.getDepth()+1, 
					Operator.costOfOperator(Operator.UP));
		if(world.ifMoveDown())
			//DOWN
			result[1] = new SearchTreeNode(moveAfterKill, normalState, node,
					Operator.DOWN, node.getDepth()+1,
					Operator.costOfOperator(Operator.DOWN));
		if(world.ifMoveLeft())
			//LEFT
			result[2] = new SearchTreeNode(moveAfterKill, normalState, node, 
					Operator.LEFT, node.getDepth()+1, 
					Operator.costOfOperator(Operator.LEFT));
		if(world.ifMoveRight())
			//RIGHT
			result[3] = new SearchTreeNode(moveAfterKill, normalState, node,
					Operator.RIGHT, node.getDepth()+1, 
					Operator.costOfOperator(Operator.RIGHT));
		
		
		if(world.ifAttack())
			//Kill
			result[4] = new SearchTreeNode(world, killState, node, Operator.KILL, 
					node.getDepth()+1, Operator.costOfOperator(Operator.KILL));
		
		if(world.ifPickUp()){
			//Pickup
			world.dragonGlass = world.MAX_DRAGON_STONES;
			result[5] = new SearchTreeNode(world, pickupState, node, Operator.PICKUP, 
				node.getDepth()+1, Operator.costOfOperator(Operator.PICKUP));
		}
		return result;
	}

	public static SearchTreeNode[] backTrack(SearchTreeNode node){
		SearchTreeNode[] result = new SearchTreeNode[node.getDepth()+1];
		SearchTreeNode current = node;
		for(int i=node.getDepth(); i>=0; i--){
			result[i] = current;
			try{
				current = current.getParent();
			}catch(NullPointerException e){
				//Root reached
			}
		}
		return result;
	}
}
