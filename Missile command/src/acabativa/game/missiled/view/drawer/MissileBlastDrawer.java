package acabativa.game.missiled.view.drawer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import acabativa.game.missiled.model.elements.GameElement;
import acabativa.game.missiled.model.elements.MissileBlast;

public class MissileBlastDrawer implements Drawer{
		
	public void draw(Graphics2D g2d, GameElement element) throws IllegalArgumentException{
		MissileBlast blast = (MissileBlast) element;
		Point point = (blast.getConflictPosition().getLocation());
				
		if(point!=null){
			createAndDrawShape(g2d, point, blast.getRadius());
		}
	}
	
	private void createAndDrawShape(Graphics2D g2d, Point point, int size){
		Shape shape = new Ellipse2D.Double((int)point.getX()-(size/2),(int)point.getY()-(size/2),size,size);
		g2d.draw(shape);
	}
	
	public Shape getShape(GameElement element){
		Point point = (element.getConflictPosition().getLocation());
		return getShape(point);
	}
	
	private Shape getShape(Point point){
		return new Ellipse2D.Double(point.getX(), point.getY(), 1, 1);
	}
	
}
