package acabativa.game.missiled.model.elements;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractComplexElement implements GameElement{

	List<AbstractSimpleElement> simpleElements = new ArrayList<AbstractSimpleElement>();
	int maxWidth = 0;
	int maxHeight = 0;
	

	public AbstractComplexElement(int maxWidth, int maxHeight) {
		super();
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
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
		for (AbstractSimpleElement simpleElement : simpleElements) {
			return simpleElement.getConflictPosition();
		}
		return null;
	}
		
}
