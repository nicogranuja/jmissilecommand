package acabativa.game.missiled.model.elements;

import java.awt.Point;

import acabativa.game.missiled.model.util.LineWalker;

public class MissileBlast extends AbstractSimpleElement{
	
	int blastPass = 3;
	int radius = 0;
	int maxRadius = 50;

	public MissileBlast(Point position, int maxWidth, int maxHeight) {
		super(position, maxWidth, maxHeight);
	}
	
	public boolean conflictsWithThisElement(GameElement element) {
		double hipotenusa = LineWalker.getHipotenusa(element.getConflictPosition(), this.position);
		if(hipotenusa<radius){
			return true;
		}
		else{
			return false;
		}		
	}	
	
	public boolean setCurrentRadius(int iteration){
		int testRadius = iteration*blastPass;
		if(testRadius>maxRadius){
			return false;
		}
		else{
			radius = testRadius;
			return true;
		}
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	

}
