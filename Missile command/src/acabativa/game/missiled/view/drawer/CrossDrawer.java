package acabativa.game.missiled.view.drawer;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import acabativa.game.missiled.model.elements.GameElement;

public class CrossDrawer implements Drawer{
	int size = 11;
	int space = 5;
	
	@Override
	public void draw(Graphics2D g2d, GameElement element)
			throws IllegalArgumentException {
		drawCross(g2d, element);		
	}

	private void drawCross(Graphics2D g2d, GameElement element){
		int positionX = (int) element.getConflictPosition().getX();
		int positionY = (int) element.getConflictPosition().getY();
		g2d.draw(new Line2D.Double(positionX,positionY+space/2,positionX,positionY+space));
		g2d.draw(new Line2D.Double(positionX+space/2,positionY,positionX+space,positionY));
		g2d.draw(new Line2D.Double(positionX,positionY-space/2,positionX,positionY-space));
		g2d.draw(new Line2D.Double(positionX-space/2,positionY,positionX-space,positionY));
	}
	
}
