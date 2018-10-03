package agent.structures;

public enum Operator {
	UP,DOWN,LEFT,RIGHT,KILL,PICKUP;
	
	public static int costOfOperator(Operator op){
		switch(op){
		case KILL: 
			return 1;
			
		case PICKUP:
			return 2;

		case UP:
			return 3;

		case DOWN:
			return 3;
		
		case LEFT:
			return 3;
		
		case RIGHT:
			return 3;
		
		}
		return 0;
	}
}
