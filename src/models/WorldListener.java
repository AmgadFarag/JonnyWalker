package models;

public interface WorldListener {

	public void onWalkerKilled();
	public void onHitWalker();
	public void onHitObstacle();
	public void onHitDragonStoneCell();
}
