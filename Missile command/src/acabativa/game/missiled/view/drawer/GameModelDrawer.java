package acabativa.game.missiled.view.drawer;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import acabativa.game.missiled.model.GameModel;
import acabativa.game.missiled.model.elements.GameElement;
import acabativa.game.missiled.model.elements.Missile;
import acabativa.game.missiled.model.elements.MissileBlast;

public class GameModelDrawer implements Drawer{
	
	CrossDrawer crossDrawer;
	BaseDrawer baseDrawer;
	MissileDrawer missileDrawer;
	MissileBlastDrawer blastDrawer;
	
	public GameModelDrawer(){
		crossDrawer = new CrossDrawer();
		baseDrawer = new BaseDrawer();
		missileDrawer = new MissileDrawer(); 		
		blastDrawer = new MissileBlastDrawer();
	}

	@Override
	public void draw(Graphics2D g2d, GameElement element)
			throws IllegalArgumentException {
		GameModel model = (GameModel) element;
		crossDrawer.draw(g2d, model.getCross());
		baseDrawer.draw(g2d, model.getShooter());
		List<Missile> missiles = new ArrayList<Missile>(); 
		missiles.addAll(model.getEnemyMissiles().keySet());
		missiles.addAll(model.getShooterMissiles().keySet());
		for (Missile missile : missiles) {
			missileDrawer.draw(g2d, missile);
		}		
		List<MissileBlast> blasts = new ArrayList<MissileBlast>(model.getBlasts().keySet());
		for (MissileBlast missileBlast : blasts) {
			blastDrawer.draw(g2d, missileBlast);
		}
	}


	
	
	
}
