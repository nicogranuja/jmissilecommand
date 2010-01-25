package acabativa.game.missiled.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import acabativa.game.missiled.controller.GameController;
import acabativa.game.missiled.model.GameModel;
import acabativa.game.missiled.util.Observer;
import acabativa.game.missiled.view.drawer.Drawer;
import acabativa.game.missiled.view.drawer.GameModelDrawer;
import acabativa.game.missiled.view.drawer.GameSceneryDrawer;

public class GameView extends JPanel implements ActionListener, KeyListener,
		Observer {

	private static final long serialVersionUID = 1L;
	public static final int MAX_WIDHT = 500;
	public static final int MAX_HEIGHT = 500;
	Timer timer;
	int ticker = 0;
	GameController controller;
	GameModel model;
	
	Drawer scenarieDrawer = new GameSceneryDrawer(MAX_WIDHT, MAX_HEIGHT);
	Drawer modelDrawer = new GameModelDrawer();
	
	
	public GameView(GameController controller, GameModel model) {
		this.controller = controller;
		this.model = model;
		model.addObserver(this);
		JFrame frame = new JFrame("Missile Defender");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(MAX_WIDHT + 17 , MAX_HEIGHT + 37);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setVisible(true);
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			scenarieDrawer.draw(g2d, model);
			modelDrawer.draw(g2d, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		ticker += 1;
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == ('w')){
			controller.up();
		}
		if(e.getKeyChar() == ('s')){
			controller.down();
		}
		if(e.getKeyChar() == ('a')){
			controller.left();
		}
		if(e.getKeyChar() == ('d')){
			controller.right();
		}
		if(e.getKeyChar() == (' ')){
			controller.fire();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyEvent(String event) {
		repaint();
	}

}
