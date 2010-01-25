package acabativa.game.missiled.view.drawer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import acabativa.game.missiled.model.elements.GameElement;
import acabativa.game.missiled.model.elements.Missile;

public class MissileDrawer implements Drawer{
	
	private static final int SHAPE_SIZE = 6;
		
	public void draw(Graphics2D g2d, GameElement element) throws IllegalArgumentException{
		Missile missile = (Missile) element;
		Point point = (missile.getConflictPosition().getLocation());
		Point start = (missile.getStartPosition().getConflictPosition().getLocation());
		
		if(point!=null){
			g2d.draw(new Line2D.Double(
					new Point2D.Double(start.getX(), start.getY()),
					new Point2D.Double(point.getX(), point.getY())
			));		
			createAndDrawShape(g2d, start);
			createAndDrawShape(g2d, point);
		}
	}
	
	private void createAndDrawShape(Graphics2D g2d, Point point){
		Shape shape = new Ellipse2D.Double((int)point.getX()-(SHAPE_SIZE/2),(int)point.getY()-(SHAPE_SIZE/2),SHAPE_SIZE,SHAPE_SIZE);
		g2d.fill(shape);
	}
	
	public Shape getShape(GameElement element){
		Point point = (element.getConflictPosition().getLocation());
		return getShape(point);
	}
	
	private Shape getShape(Point point){
		return new Ellipse2D.Double(point.getX(), point.getY(), 1, 1);
	}
	
}
