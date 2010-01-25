package acabativa.game.missiled.view.drawer;

import java.awt.Graphics2D;

import acabativa.game.missiled.model.elements.GameElement;

public interface Drawer {
	
	public void draw(Graphics2D g2d, GameElement element) throws IllegalArgumentException;
	
}
