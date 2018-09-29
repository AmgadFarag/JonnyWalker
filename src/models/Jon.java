package models;

public class Jon {
	private final int MAX_DRAGON_GLASS = (int) Math.random()*20 + 1;
	
	private int x,y,dragonGlass;

	public Jon() {
		dragonGlass = 0;
	}

	public int getDragonGlass() {
		return dragonGlass;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getMAX_DRAGON_GLASS() {
		return MAX_DRAGON_GLASS;
	}
	
	public void setDragonGlass(int dragonGlass) {
		this.dragonGlass = dragonGlass;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

}
