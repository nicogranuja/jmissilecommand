package acabativa.game.missiled.main;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import acabativa.game.missiled.model.GameModel;
import acabativa.game.missiled.model.elements.Missile;

public class Player implements Runnable {

	Logger logger = Logger.getLogger(Player.class);

	GameModel model;
	Missile handleThis;
	int ticker;
	long sleepTime = 5;
	Random r = new Random();
	int shooterMissileDefaultSpeed = 20;
	int enemyMissileDefaultSpeed = 100;

	public Player(GameModel model) {
		this.model = model;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (model.isGameOn()) {
			int counter = model.getTimeCounter();
			if (counter > ticker) {
				play();
			}
			ticker = counter;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Missile getOldestEnemyMissiles(Map<Missile, Integer> enemyMissiles) {
		Set<Entry<Missile, Integer>> entrys = enemyMissiles.entrySet();
		Iterator<Entry<Missile, Integer>> iterator = entrys.iterator();

		int smallest = Integer.MAX_VALUE;
		Missile ret = null;

		while (iterator.hasNext()) {
			Entry<Missile, Integer> entry = iterator.next();
			int value = entry.getValue();
			if (value < smallest) {
				smallest = value;
				ret = entry.getKey();
			}
		}
		return ret;
	}

	private void play() {
		Map<Missile, Integer> enemyMissiles = model.getEnemyMissiles();
		synchronized (enemyMissiles) {
			if (handleThis != null && enemyMissiles.containsKey(handleThis)) {
				handleMissile(handleThis);
			} else {
				if (enemyMissiles != null && enemyMissiles.size() > 0) {
					handleThis = getOldestEnemyMissiles(enemyMissiles);
				} else {
					goCenter();
				}

			}
		}
	}

	private void handleMissile(Missile missile) {
		try{
			model.setCrossPosition(getNextX(missile), getNextY(missile));
		}
		catch (Exception e) {
//			e.printStackTrace();
			model.setCrossPosition(
					(int)missile.getConflictPosition().getX(),
					(int)missile.getConflictPosition().getY());
		}
		model.fire();
		handleThis = null;
	}

	private int getNextX(Missile missile) {
		double startX = missile.getStartPosition().getConflictPosition().getX();
		double endX = missile.getEndPosition().getConflictPosition().getX();
		double current = missile.getCurrentPosition().getConflictPosition().getX();
		double coef = (endX-startX)/500;
		double value = coef*enemyMissileDefaultSpeed;
		return (int) (current+value);
	}

	private int getNextY(Missile missile) {
		double startY = missile.getStartPosition().getConflictPosition().getY();
		double endY = missile.getEndPosition().getConflictPosition().getY();
		double currentY = missile.getConflictPosition().getY();
		double coef = (endY-startY)/500;
		double value = coef*enemyMissileDefaultSpeed;
		return (int) (currentY + value);
	}

	private void goCenter() {
		model.setCrossPosition(250, 250);
	}

}
