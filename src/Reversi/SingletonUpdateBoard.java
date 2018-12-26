package Reversi;

import java.util.Observable;

/**
 * Singleton object used to update BoardPanel. Can be accessed by all Tile objects.
 * @author Raymond Sutandar
 *
 */
public class SingletonUpdateBoard extends Observable {
	
	private SingletonUpdateBoard() {
		//Make BoardPanel observe this object for updates
		this.addObserver(GameController.getInstance());
	}
	
	private static SingletonUpdateBoard single_updater = null;
	
	public static SingletonUpdateBoard getInstance() {
		if (single_updater == null) {
			single_updater = new SingletonUpdateBoard();
		}
		return single_updater;
	}
	
	public void setGameStatusLabel(GameStatusLabel temp_game_label) {
		this.addObserver(temp_game_label);
	}
	
	public void updateGameController(int damage) {
		this.setChanged();
		this.notifyObservers(damage);
	}
	
	public void updateGameStatusLabel(String player_colour) {
		this.setChanged();
		this.notifyObservers(player_colour);
	}
	
	
}
