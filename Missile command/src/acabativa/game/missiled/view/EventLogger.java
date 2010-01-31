package acabativa.game.missiled.view;

import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import acabativa.game.missiled.model.GameModel;
import acabativa.game.missiled.util.Observer;

public class EventLogger extends JPanel implements Observer {

	private static final long serialVersionUID = -625318211832276927L;

	public static final int MAX_WIDHT = 400;
	public static final int MAX_HEIGHT = 500;
	private int LINES = 30;
	private int COLUMNS = 30;

	private TextArea events;

	
	public EventLogger(GameModel model) {
		model.addObserver(this);
		
		JFrame frame = new JFrame("Missile Command - Events Logger");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(MAX_WIDHT + 17 , MAX_HEIGHT + 37);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		
		this.events = new TextArea(LINES, COLUMNS);
		JScrollPane scrollPane = new JScrollPane(events);
		this.add(scrollPane);

		frame.setVisible(true);
	}

	@Override
	public void notifyEvent(String event) {
		if(event != null) //!event.equals("Missiles update")
			events.append(event.concat("\n"));
	}
	
}
