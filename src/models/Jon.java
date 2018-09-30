package models;

public class Jon {
	private final int MAX_DRAGON_STONES;
	private int x,y,dragonGlass;

	public Jon(int dg) {
		MAX_DRAGON_STONES = dg;
		dragonGlass = 0;
	}



	public int getMAX_DRAGON_STONES() {
		return MAX_DRAGON_STONES;
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
