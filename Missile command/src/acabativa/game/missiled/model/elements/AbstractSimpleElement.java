package acabativa.game.missiled.model.elements;

import java.awt.Point;


public abstract class AbstractSimpleElement implements GameElement{

	Point position = null;
	int maxWidth = 0;
	int maxHeight = 0;
	
	public AbstractSimpleElement(Point position, int maxWidth, int maxHeight) {
		super();
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.setPosition(position);
	}
	
	public boolean conflictsWithThisElement(GameElement element) {
		if (element.getConflictPosition().equals(position)){
			return true;
		}
		else{
			return false;
		}
	}

	public Point getConflictPosition() {
		return position;
	}

	public void setPosition(Point ... positions) throws IllegalArgumentException{
		if(positions==null || positions.length!=1){
			throw new IllegalArgumentException("Invalid Number of points for simple Element");
		}
		Point position = positions[0];
		if(validateX(position.getX()) && validateY(position.getY())){
			this.position = position;
		}
		else{
			throw new IllegalArgumentException("Illegal position, out of bounds");
		}
	}
	
	private boolean validateY(double positionY){
		if(positionY<0 || positionY>this.maxHeight){
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean containsThisElement(GameElement element) {
		return element.getConflictPosition().equals(this.position);
	}
	
	private boolean validateX(double positionX){
		if(positionX<0 || positionX>this.maxWidth){
			return false;
		}
		else{
			return true;
		}
	}
	
}