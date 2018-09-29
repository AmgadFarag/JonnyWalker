package models;

import models.cells.DragonStoneCell;
import models.cells.EmptyCell;
import models.cells.MapCell;
import models.cells.ObstacleCell;
import models.cells.WalkerCell;

public class WorldHandler implements MapListener{
	private Map map;
	private Jon jon;
	private WorldListener listener;

	public WorldHandler(WorldListener listen, Jon jon) {
		listener = listen;
		/*map = new Map((int)(Math.random()*10+1),(int)(Math.random()*10+1),
				(int)(Math.random()*5+1),(int)(Math.random()*5+1),
				(int)(Math.random()*3+1));*/
		this.jon = jon;
		/*jon.setX(map.getM());
		jon.setY(map.getN());*/
	}

	public int ifAttack() {
		int count = 0;
		if(jon.getDragonGlass()>0){
			int tempX = jon.getX();
			int tempY = jon.getY();
			
			MapCell temp = map.getMap()[tempX][tempY+1];
			if (temp instanceof WalkerCell)
				count++;
			temp = map.getMap()[tempX][tempY-1];
			if (temp instanceof WalkerCell)
				count++;
			temp = map.getMap()[tempX+1][tempY];
			if (temp instanceof WalkerCell)
				count++;
			temp = map.getMap()[tempX-1][tempY];
			if (temp instanceof WalkerCell)
				count++;
		}
		return count;
	}
	
	@Override
	public void onAttack() {
		if(jon.getDragonGlass()>0){
			int tempX = jon.getX();
			int tempY = jon.getY();
			
			MapCell temp = map.getMap()[tempX][tempY+1];
			if (temp instanceof WalkerCell){
				map.getMap()[tempX][tempY+1] = new EmptyCell();
				listener.onWalkerKilled();
			}
			temp = map.getMap()[tempX][tempY-1];
			if (temp instanceof WalkerCell){
				map.getMap()[tempX][tempY-1] = new EmptyCell();
				listener.onWalkerKilled();
			}
			temp = map.getMap()[tempX+1][tempY];
			if (temp instanceof WalkerCell){
				map.getMap()[tempX+1][tempY] = new EmptyCell();
				listener.onWalkerKilled();
			}
			temp = map.getMap()[tempX-1][tempY];
			if (temp instanceof WalkerCell){
				map.getMap()[tempX-1][tempY] = new EmptyCell();
				listener.onWalkerKilled();
			}
		}
	}

	@Override
	public MapCell ifMoveUp() {
		MapCell cell;
		if((map.getMap().length < jon.getX()) ||
		   (map.getMap().length > jon.getX()) ||
				map.getMap()[jon.getX()].length < (jon.getY()+1) ||
				map.getMap()[jon.getX()].length > (jon.getY()+1))
			cell = null;
		cell = map.getMap()[jon.getX()][jon.getY()+1];
		return cell;
	}
	@Override
	public MapCell ifMoveDown() {
		MapCell cell;
		if((map.getMap().length < jon.getX()) ||
		   (map.getMap().length > jon.getX()) ||
				map.getMap()[jon.getX()].length < (jon.getY()-1) ||
				map.getMap()[jon.getX()].length > (jon.getY()-1))
			cell = null;
		else
			cell = map.getMap()[jon.getX()][jon.getY()-1];
		return cell;
	}
	@Override
	public MapCell ifMoveLeft() {
		MapCell cell;
		if((map.getMap().length < jon.getX()-1) ||
		   (map.getMap().length > jon.getX()-1) ||
				map.getMap()[jon.getX()].length < jon.getY() ||
				map.getMap()[jon.getX()].length > jon.getY())
			cell = null;
		cell = map.getMap()[jon.getX()-1][jon.getY()];
		return cell;
	}
	@Override
	public MapCell ifMoveRight() {
		MapCell cell;
		if((map.getMap().length < jon.getX()+1) ||
		   (map.getMap().length > jon.getX()+1) ||
				map.getMap()[jon.getX()].length < jon.getY() ||
				map.getMap()[jon.getX()].length > jon.getY())
			cell = null;
		cell = map.getMap()[jon.getX()+1][jon.getY()];
		return cell;
	}

	
	@Override
	public void onMoveUp(){
		int tempX = jon.getX();
		int tempY = jon.getY();
		MapCell temp = map.getMap()[tempX][tempY+1];
		if(temp instanceof WalkerCell)
			listener.onHitWalker();
		if(temp instanceof ObstacleCell)
			listener.onHitObstacle();
		if(temp instanceof EmptyCell)
			jon.setY(jon.getY()+1);
		if(temp instanceof DragonStoneCell){
			jon.setY(jon.getY()+1);
			listener.onHitDragonStoneCell();
		}
	}
	@Override
	public void onMoveDown(){
		int tempX = jon.getX();
		int tempY = jon.getY();
		MapCell temp = map.getMap()[tempX][tempY-1];
		if(temp instanceof WalkerCell)
			listener.onHitWalker();
		if(temp instanceof ObstacleCell)
			listener.onHitObstacle();
		if(temp instanceof EmptyCell)
			jon.setY(jon.getY()-1);
		if(temp instanceof DragonStoneCell){
			jon.setY(jon.getY()-1);
			listener.onHitDragonStoneCell();
		}
	}
	@Override
	public void onMoveLeft() {
		int tempX = jon.getX();
		int tempY = jon.getY();
		MapCell temp = map.getMap()[tempX-1][tempY];
		if(temp instanceof WalkerCell)
			listener.onHitWalker();
		if(temp instanceof ObstacleCell)
			listener.onHitObstacle();
		if(temp instanceof EmptyCell)
			jon.setX(jon.getX()-1);
		if(temp instanceof DragonStoneCell){
			jon.setX(jon.getX()-1);
			listener.onHitDragonStoneCell();
		}
	}
	@Override
	public void onMoveRight() {
		int tempX = jon.getX();
		int tempY = jon.getY();
		MapCell temp = map.getMap()[tempX+1][tempY];
		if(temp instanceof WalkerCell)
			listener.onHitWalker();
		if(temp instanceof ObstacleCell)
			listener.onHitObstacle();
		if(temp instanceof EmptyCell)
			jon.setX(jon.getX()+1);
		if(temp instanceof DragonStoneCell){
			jon.setX(jon.getX()+1);
			listener.onHitDragonStoneCell();
		}
	}
	
}
