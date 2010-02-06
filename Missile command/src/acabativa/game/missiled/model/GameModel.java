package acabativa.game.missiled.model;

import java.util.Map;

import acabativa.game.missiled.model.elements.GameElement;
import acabativa.game.missiled.model.elements.Marker;
import acabativa.game.missiled.model.elements.Missile;
import acabativa.game.missiled.model.elements.MissileBlast;
import acabativa.game.missiled.model.elements.MissileShooter;
import acabativa.game.missiled.util.Observer;

public interface GameModel extends GameElement, Runnable{

	public abstract Map<MissileBlast, Integer> getBlasts();

	public abstract void addObserver(Observer observer);

	public abstract void up();

	public abstract void down();

	public abstract void right();

	public abstract void left();

	public abstract void fire();

	public abstract void start();

	public abstract void stop();

	public abstract Marker getCross();
	
	public abstract void setCrossPosition(int x, int y) throws IllegalArgumentException;

	public abstract MissileShooter getShooter();
			
	public abstract Map<Missile, Integer> getEnemyMissiles();

	public abstract Map<Missile, Integer> getShooterMissiles();
	
	public int getEnemyPoints();

	public int getDefenderPoints();
	
	public boolean isGameOn();
	
	public int getTimeCounter();

}