package acabativa.game.missiled.model.elements;

import java.awt.Point;

import acabativa.game.missiled.model.util.LineWalker;

public class Missile extends AbstractComplexElement{

	GameElement currentPosition;
	GameElement startPosition;
	GameElement endPosition;
	double defaultSpeed = 5;
	
	public Missile(int maxWidth, int maxHeight) throws IllegalArgumentException {
		super(maxWidth, maxHeight);
	}
	
	public Missile(int maxWidth, int maxHeight, Point ... position) throws IllegalArgumentException {
		super(maxWidth, maxHeight);
		setPosition(position);
	}
	
	@Override
	public boolean conflictsWithThisElement(GameElement element) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setPosition(Point... positions) throws IllegalArgumentException {
		if(positions==null || positions.length!=3){
			throw new IllegalArgumentException("Invalid Number of points for missile Element");
		}
		this.startPosition = new Marker(positions[0], maxWidth, maxHeight);
		this.currentPosition = new Marker(positions[1], maxWidth, maxHeight);
		this.endPosition = new Marker(positions[2], maxWidth, maxHeight);
	}
	
	@Override
	public boolean containsThisElement(GameElement element) {
		for (AbstractSimpleElement simpleElement : simpleElements) {
			if(simpleElement.conflictsWithThisElement(element)){
				return true;
			}
		}
		return false;		
	}

	@Override
	public Point getConflictPosition() {
		if (currentPosition!=null){
			return currentPosition.getConflictPosition();
		}
		return null;
	}
	
	public Point getConflictPosition(int iteration) {
		LineWalker lineWalker = new LineWalker(defaultSpeed);
		if(lineWalker.hasIteration(
				startPosition.getConflictPosition(),
				endPosition.getConflictPosition(),
				iteration)){
			Point newPoint = lineWalker.getPoint(
					startPosition.getConflictPosition(), 
					endPosition.getConflictPosition(), 
					iteration);
			return newPoint;
		}
		else{
			return null;
		}
	}
	
	public boolean setCurrentWithIteration(int iteration){
		LineWalker lineWalker = new LineWalker(defaultSpeed);
		if(lineWalker.hasIteration(
				startPosition.getConflictPosition(),
				endPosition.getConflictPosition(),
				iteration)){
			Point newPoint = lineWalker.getPoint(
					startPosition.getConflictPosition(), 
					endPosition.getConflictPosition(), 
					iteration);
			this.currentPosition = new Marker(newPoint, maxWidth, maxHeight);
			return true;
		}
		else{
			return false;
		}
	}

	public GameElement getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(GameElement currentPosition) {
		this.currentPosition = currentPosition;
	}

	public GameElement getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(GameElement startPosition) {
		this.startPosition = startPosition;
	}

	public GameElement getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(GameElement endPosition) {
		this.endPosition = endPosition;
	}

	public double getDefaultSpeed() {
		return defaultSpeed;
	}

	public void setDefaultSpeed(double defaultSpeed) {
		this.defaultSpeed = defaultSpeed;
	}
	
	
	
	
	
	
}
