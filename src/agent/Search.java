package agent;

import models.WorldHandler;
import models.cells.ObstacleCell;
import models.cells.WalkerCell;
import agent.structures.Operator;
import agent.structures.SearchTreeNode;
import agent.structures.State;

public abstract class Search {
	protected WorldHandler world;
	
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
	
	public int scoreOfOperator(SearchTreeNode node, Operator op){
		switch(op){
		case KILL: 
			int walkerCount = world.ifAttack();
			if(node.getState().walkersLeft == walkerCount)
				return 20;
			return walkerCount*5;
			
		case PICKUP:
			if(node.getState().dragonglassLeft > 0)
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
	
	public int costOfOperator(SearchTreeNode node, Operator op){
		switch(op){
		case KILL: 
			return 5;
			
		case PICKUP:
			return 2;

		case UP:
			return 1;

		case DOWN:
			return 1;
		
		case LEFT:
			return 1;
		
		case RIGHT:
			return 1;
		
		}
		return 0;
	}

	public SearchTreeNode[] expandNode(SearchTreeNode node){
		SearchTreeNode[] result = new SearchTreeNode[6];
		State current = node.getState();
		if(node.getOperatorApplied() != Operator.KILL){
			//UP
			State upState = new State(current.x, current.y+1, 
					current.walkersLeft, current.dragonglassLeft, 
					current.localGoal);
			result[0] = new SearchTreeNode(upState, node, Operator.UP, 
					node.getDepth()+1, costOfOperator(node,Operator.UP));
			//DOWN
			State downState = new State(current.x, current.y-1, 
					current.walkersLeft, current.dragonglassLeft, 
					current.localGoal);
			result[1] = new SearchTreeNode(downState, node, Operator.DOWN, 
					node.getDepth()+1, costOfOperator(node,Operator.DOWN));
			//LEFT
			State leftState = new State(current.x+1, current.y, 
					current.walkersLeft, current.dragonglassLeft, 
					current.localGoal);
			result[2] = new SearchTreeNode(leftState, node, Operator.LEFT, 
					node.getDepth()+1, costOfOperator(node,Operator.LEFT));
			//RIGHT
			State rightState = new State(current.x-1, current.y, 
					current.walkersLeft, current.dragonglassLeft, 
					current.localGoal);
			result[3] = new SearchTreeNode(rightState, node, Operator.RIGHT, 
					node.getDepth()+1, costOfOperator(node,Operator.RIGHT));
		}else{
			//UP
			State upState = new State(current.x, current.y+1, 
					current.walkersLeft, current.dragonglassLeft-1, 
					current.localGoal);
			result[0] = new SearchTreeNode(upState, node, Operator.UP, 
					node.getDepth()+1, costOfOperator(node,Operator.UP));
			//DOWN
			State downState = new State(current.x, current.y-1, 
					current.walkersLeft, current.dragonglassLeft-1, 
					current.localGoal);
			result[1] = new SearchTreeNode(downState, node, Operator.DOWN, 
					node.getDepth()+1, costOfOperator(node,Operator.DOWN));
			//LEFT
			State leftState = new State(current.x+1, current.y, 
					current.walkersLeft, current.dragonglassLeft-1, 
					current.localGoal);
			result[2] = new SearchTreeNode(leftState, node, Operator.LEFT, 
					node.getDepth()+1, costOfOperator(node,Operator.LEFT));
			//RIGHT
			State rightState = new State(current.x-1, current.y, 
					current.walkersLeft, current.dragonglassLeft-1, 
					current.localGoal);
			result[3] = new SearchTreeNode(rightState, node, Operator.RIGHT, 
					node.getDepth()+1, costOfOperator(node,Operator.RIGHT));
		}
		//Kill
		State killState = new State(current.x, current.y, 
				current.walkersLeft-world.ifAttack(), current.dragonglassLeft, 
				current.localGoal);
		result[4] = new SearchTreeNode(killState, node, Operator.KILL, 
				node.getDepth()+1, costOfOperator(node,Operator.KILL));
		//Pickup
		State pickupState = new State(current.x, current.y, 
				current.walkersLeft, world.map.MAX_DRAGON_STONES, 
				current.localGoal);
		result[4] = new SearchTreeNode(pickupState, node, Operator.PICKUP, 
				node.getDepth()+1, costOfOperator(node,Operator.PICKUP));

		return result;
	}

	public SearchTreeNode[] backTrack(SearchTreeNode node){
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
