package models;

import models.cells.MapCell;

public interface MapListener {

	public void onAttack();
	public MapCell ifMoveUp();
	public MapCell ifMoveDown();
	public MapCell ifMoveLeft();
	public MapCell ifMoveRight();
	
	public void onMoveUp();
	public void onMoveDown();
	public void onMoveLeft();
	public void onMoveRight();
}
