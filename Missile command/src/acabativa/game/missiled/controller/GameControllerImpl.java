package acabativa.game.missiled.controller;

import acabativa.game.missiled.model.GameModel;
import acabativa.game.missiled.view.EventLogger;
import acabativa.game.missiled.view.GameView;

public class GameControllerImpl implements GameController{
	
	GameModel model;
	GameView view;
	EventLogger logger;
	Thread game;
	
	public GameControllerImpl(GameModel model) {
		super();
		this.model = model;
		this.view = new GameView(this, model);
		this.logger = new EventLogger(model);
		start();	
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
		model.start();
		game = new Thread(model);
		game.start();	
	}

	@Override
	public void stop() {
		model.stop();
		game = null;
	}

	@Override
	public void up() {
		model.up();	
	}
	
	
	
	
}
