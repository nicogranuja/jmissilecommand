package acabativa.game.missiled.model.elements;

import java.awt.Point;

public interface GameElement {
	
	public boolean containsThisElement(GameElement element);
	
	public boolean conflictsWithThisElement(GameElement element);

	public Point getConflictPosition();

	public void setPosition(Point ... positions);
	
}
