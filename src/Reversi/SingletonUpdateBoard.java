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
	
	/**
	 * Makes temp_game_label an observer (that is, makes the GameStatusLabel an observer).
	 * @param temp_game_label - A given GameStatusLabel.
	 */
	public void setGameStatusLabel(GameStatusLabel temp_game_label) {
		this.addObserver(temp_game_label);
	}
	
	/**
	 * Tells the GameController observer that damage to a player has been done.
	 * @param damage - The amount of damage one player did to the other in their turn.
	 */
	public void updateGameController(int damage) {
		this.setChanged();
		this.notifyObservers(damage);
	}
	
	/**
	 * Tells the GameStatusLabel observer what to say depending on a given player_colour.
	 * @param player_colour - The colour assigned to a player (red = Player 1, blue = Player 2).
	 */
	public void updateGameStatusLabel(String player_colour) {
		this.setChanged();
		this.notifyObservers(player_colour);
	}
	
	
}
