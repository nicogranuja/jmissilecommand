package acabativa.game.missiled.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import acabativa.game.missiled.model.elements.CrossMarker;
import acabativa.game.missiled.model.elements.GameElement;
import acabativa.game.missiled.model.elements.Marker;
import acabativa.game.missiled.model.elements.Missile;
import acabativa.game.missiled.model.elements.MissileBlast;
import acabativa.game.missiled.model.elements.MissileShooter;
import acabativa.game.missiled.util.Observable;
import acabativa.game.missiled.util.Observer;

public class GameModelImpl implements Observable, Runnable, GameModel{
	List<Observer> observers = new ArrayList<Observer>();
	
	int maxHeight = 0;
	int maxWidth = 0;
	Random randomGenerator = new Random();
	
	boolean gameOn = true;
	
	int defenderPoints = 0;
	int enemyPoints = 0;
	
	Marker cross;
	MissileShooter shooter;
	Map<Missile, Integer> enemyMissiles;
	Map<Missile, Integer> shooterMissiles;
	Map<MissileBlast, Integer> blasts;
	
	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#getBlasts()
	 */
	public Map<MissileBlast, Integer> getBlasts() {
		return blasts;
	}

	public void setBlasts(Map<MissileBlast, Integer> blasts) {
		this.blasts = blasts;
	}

	int timeCounter = 0;
	int crossPass = 10;
	int timeToSleep = 50;
	
	public GameModelImpl(int maxWidth, int maxHeight){
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		cross = new CrossMarker(getCrossStartingPosition(), maxWidth, maxHeight);
		shooter = new MissileShooter(getShooterStartingPosition(), maxWidth, maxHeight);
		enemyMissiles = new HashMap<Missile, Integer>();
		shooterMissiles = new HashMap<Missile, Integer>();
		blasts = new HashMap<MissileBlast, Integer>();
	}
	
	public int getEnemyPoints() {
		return enemyPoints;
	}

	public int getDefenderPoints() {
		return defenderPoints;
	}

	
	private Point getCrossStartingPosition(){
		return new Point(this.maxWidth/2, this.maxHeight/2);
	}
	
	private Point getShooterStartingPosition(){
		return new Point(this.maxWidth/2, this.maxHeight);
	}	
	
	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#addObserver(acabativa.game.missiled.util.Observer)
	 */
	@Override
	public synchronized void addObserver(Observer observer) {
		observers.add(observer);		
	}

	@Override
	public synchronized void removeObserver(Observer observer) {
		observers.remove(observer);		
	}
	
	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#up()
	 */
	public void up(){
		cursorUp();
		notifyAll("up");
	}
	
	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#down()
	 */
	public void down(){
		cursorDown();
		notifyAll("Down");
	}
	
	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#right()
	 */
	public void right(){
		cursorRight();
		notifyAll("Right");
	}
	
	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#left()
	 */
	public void left(){
		cursorLeft();
		notifyAll("Left");
	}
	
	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#fire()
	 */
	public synchronized void fire(){
		addNewFriendlyMissile(timeCounter);
		notifyAll("New friendly missile");
	}
	
	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#start()
	 */
	public void start(){
		gameOn = true;
	}
	
	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#stop()
	 */
	public void stop(){
		gameOn = false;
	}
	
	private synchronized void notifyAll(String event){
		for (Observer observer : observers) {
			observer.notifyEvent(event);
		}
	}
	
	private void addNewFriendlyMissile(int counter){
		Missile missile = createFriendlyMissile();
		shooterMissiles.put(missile, new Integer(counter));
	}
	
	private void addNewEnemyMissile(int counter){
		Missile missile = createEnemyMissile();
		enemyMissiles.put(missile, new Integer(counter));
	}
	
	private void cursorLeft(){
		Point point = new Point(this.cross.getConflictPosition());
		point.setLocation(point.getX()-crossPass, point.getY());
		if(isValidPoint(point)){
			this.cross.setPosition(point);
		}
	}
	
	private void cursorRight(){
		Point point = new Point(this.cross.getConflictPosition());
		point.setLocation(point.getX()+crossPass, point.getY());
		if(isValidPoint(point)){
			this.cross.setPosition(point);
		}
	}
	
	private void cursorUp(){
		Point point = new Point(this.cross.getConflictPosition());
		point.setLocation(point.getX(), point.getY()-crossPass);
		if(isValidPoint(point)){
			this.cross.setPosition(point);
		}
	}
	
	private void cursorDown(){
		Point point = new Point(this.cross.getConflictPosition());
		point.setLocation(point.getX(), point.getY()+crossPass);
		if(isValidPoint(point)){
			this.cross.setPosition(point);
		}
	}
	
	private boolean isValidPoint(Point point){
		if(
			point.getX()>=0 &&
			point.getX()<=maxWidth &&
			point.getY()>=0 &&
			point.getY()<=maxHeight
			
		){
			return true;
		}
		else{
			return false;
		}
	}
	
	private Missile createEnemyMissile(){
		Random r = new Random();
		int xPointStart = r.nextInt(maxWidth);
		int xPointEnd = r.nextInt(maxWidth);
		
		Point starPoint = new Point(xPointStart, 0);
		Point endPoint = new Point(xPointEnd, maxHeight);
		Point currentPoint = new Point(starPoint);
		Missile missile = new Missile(maxWidth, maxHeight, starPoint, currentPoint, endPoint);
		return missile;
	}
	
	private Missile createFriendlyMissile(){
		Point endPoint = new Point(this.cross.getConflictPosition());
		Point starPoint = new Point(this.shooter.getConflictPosition());
		Point currentPoint = new Point(this.shooter.getConflictPosition());
		Missile missile = new Missile(maxWidth, maxHeight, starPoint, currentPoint, endPoint);
		missile.setDefaultSpeed(20);
		return missile;
	}
	
	private void updateEnemyMissiles(int counter){
		Set<Entry<Missile, Integer>> entrys = enemyMissiles.entrySet();
		Iterator<Entry<Missile, Integer>> iterator = entrys.iterator(); 
		
		while (iterator.hasNext()) {
			Entry<Missile, Integer> entry = iterator.next();
			int counterMissile = entry.getValue();
			int iteration = counter-counterMissile;
			Missile missile = entry.getKey();
			if(missile.setCurrentWithIteration(iteration)){
				if(conflictWithAnyBlast(missile)){
					iterator.remove();
					defenderPoints = defenderPoints + 1;
				}
				continue;
			}
			else{
				enemyPoints = enemyPoints + 1;
				iterator.remove();
			}
		}
	}
	
	private boolean conflictWithAnyBlast(Missile missile){
		Set<MissileBlast> mBlasts = blasts.keySet();
		 
		for (MissileBlast missileBlast : mBlasts) {
			if(missileBlast.conflictsWithThisElement(missile)){
				return true;
			}
		}
		return false;
	}
	
	private void updateFriendlyMissiles(int counter){
		Set<Entry<Missile, Integer>> entrys = shooterMissiles.entrySet();
		Iterator<Entry<Missile, Integer>> iterator = entrys.iterator(); 
		
		while (iterator.hasNext()) {
			Entry<Missile, Integer> entry = iterator.next();
			int counterMissile = entry.getValue();
			int iteration = counter-counterMissile;
			Missile missile = entry.getKey();
			if(missile.setCurrentWithIteration(iteration)){
				continue;
			}
			else{
				blasts.put(new MissileBlast(
						missile.getEndPosition().getConflictPosition(), 
						this.maxWidth, this.maxHeight), counter);
				iterator.remove();
			}
		}
	}
	
	private void updateBlasts(int counter){
		Set<Entry<MissileBlast, Integer>> entrys = blasts.entrySet();
		Iterator<Entry<MissileBlast, Integer>> iterator = entrys.iterator(); 
		
		while (iterator.hasNext()) {
			Entry<MissileBlast, Integer> entry = iterator.next();
			int counterMissile = entry.getValue();
			int iteration = counter-counterMissile;
			MissileBlast blast = entry.getKey();
			if(blast.setCurrentRadius(iteration)){
				continue;
			}
			else{
				iterator.remove();
			}
		}
	}
	
	public void setCounterModel(int counter){
		this.timeCounter = counter;
		updateModel();
	}
	
	private synchronized void updateModel(){
		if(timeCounter%10==0){
			if(randomGenerator.nextInt(100)>75){
				addNewEnemyMissile(timeCounter);
			}
		}
		updateEnemyMissiles(timeCounter);
		updateFriendlyMissiles(timeCounter);
		updateBlasts(timeCounter);
		notifyAll("Missiles update");		
	}

	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#getCross()
	 */
	public Marker getCross() {
		return cross;
	}

	public void setCross(Marker cross) {
		this.cross = cross;
	}

	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#getShooter()
	 */
	public MissileShooter getShooter() {
		return shooter;
	}

	public void setShooter(MissileShooter shooter) {
		this.shooter = shooter;
	}

	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#getEnemyMissiles()
	 */
	public synchronized Map<Missile, Integer> getEnemyMissiles() {
		return enemyMissiles;
	}

	public void setEnemyMissiles(Map<Missile, Integer> enemyMissiles) {
		this.enemyMissiles = enemyMissiles;
	}

	/* (non-Javadoc)
	 * @see acabativa.game.missiled.model.GameModel#getShooterMissiles()
	 */
	public synchronized Map<Missile, Integer> getShooterMissiles() {
		return shooterMissiles;
	}

	public void setShooterMissiles(Map<Missile, Integer> shooterMissiles) {
		this.shooterMissiles = shooterMissiles;
	}
	
	public List<Missile> getShooterMissilesAsList() {
		return new ArrayList<Missile>(shooterMissiles.keySet());
	}
	
	public List<Missile> getEnemyMissilesAsList() {
		return new ArrayList<Missile>(enemyMissiles.keySet());
	}

	@Override
	public boolean conflictsWithThisElement(GameElement element) {
		return true;
	}

	@Override
	public boolean containsThisElement(GameElement element) {
		return true;
	}

	@Override
	public Point getConflictPosition() {
		return new Point(0,0);
	}

	@Override
	public void setPosition(Point... positions) {
		
	}

	@Override
	public void run() {
		while(gameOn){
			try{
				updateModel();
				timeCounter++;
				Thread.sleep(timeToSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
