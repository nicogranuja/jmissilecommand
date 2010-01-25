package acabativa.game.missiled.controller;

import acabativa.game.missiled.model.GameModel;
import acabativa.game.missiled.view.GameView;

public class GameControllerImpl implements GameController{
	
	GameModel model;
	GameView view;
	
	public GameControllerImpl(GameModel model) {
		super();
		this.model = model;
		this.view = new GameView(this, model);
		Thread t = new Thread(model);
		t.start();		
	}	

	@Override
	public void down() {
		model.down();	
	}

	@Override
	public void fire() {
		model.fire();		
	}

	@Override
	public void left() {
		model.left();		
	}

	@Override
	public void right() {
		model.right();		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void up() {
		model.up();	
	}
	
	
	
	
}
