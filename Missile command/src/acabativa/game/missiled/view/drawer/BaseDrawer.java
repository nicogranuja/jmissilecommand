package acabativa.game.missiled.view.drawer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import acabativa.game.missiled.model.elements.GameElement;

public class BaseDrawer implements Drawer{
	int size = 11;
	int space = 5;
	private static final int SHAPE_SIZE = 2;
		
	public BaseDrawer() {
		super();
	}

	@Override
	public void draw(Graphics2D g2d, GameElement element) {
		drawCross(g2d, element);		
	}
	
	private void drawCross(Graphics2D g2d, GameElement element){
		Point point = (element.getConflictPosition().getLocation());
		createAndDrawShape(g2d, point);
	}
		
	private void createAndDrawShape(Graphics2D g2d, Point point){
		Shape shape = new Ellipse2D.Double((int)point.getX()-(SHAPE_SIZE/2),(int)point.getY()-(SHAPE_SIZE/2),SHAPE_SIZE,SHAPE_SIZE);
		g2d.fill(shape);
	}

}
