package acabativa.game.missiled.view.drawer;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import acabativa.game.missiled.model.GameModel;
import acabativa.game.missiled.model.elements.GameElement;

public class SceneryDrawer implements Drawer{
	int maxWidht = 0;
	int maxHeight = 0;
		
	public SceneryDrawer(int maxWidht, int maxHeight) {
		super();
		this.maxWidht = maxWidht;
		this.maxHeight = maxHeight;
	}

	public void draw(Graphics2D g2d, GameElement element){
		GameModel model = (GameModel) element;
		Font font = new Font("Dialog", Font.PLAIN, 20);
		g2d.setFont(font);
		g2d.drawString("Pontos Base: " + model.getDefenderPoints(), 20, 20 );
		g2d.drawString("Pontos Alien: " + model.getEnemyPoints(), 300, 20 );
		
		g2d.draw(new Line2D.Double(0,maxWidht,maxHeight,maxHeight));
		g2d.draw(new Line2D.Double(maxWidht,0,maxHeight,maxHeight));
		g2d.draw(new Line2D.Double(0,0,maxHeight,0));
		g2d.draw(new Line2D.Double(0,0,0,maxHeight));
		g2d.draw(new Rectangle(maxWidht/2,maxHeight/2,1,1));
	}

}
