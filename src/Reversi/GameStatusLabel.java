package Reversi;

import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Singleton label that displays the game's status (ie. who's turn it is, or if someone has won).
 * @author Raymond Sutandar
 *
 */
public class GameStatusLabel extends JLabel implements Observer {
	
	public GameStatusLabel() {
		this.setText("Player 1's turn.");
		this.setFont(new Font("SansSerif", Font.BOLD, 20));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setForeground(Color.red);
	}
	

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			//arg is of type Object. Can't use it as a string until I cast it with (String).
			String status_string = (String)arg;
			if (status_string.equals("player 1 turn")) {
				this.setText("Player 1's turn.");
				this.setForeground(Color.red);
			}
			else if (status_string.equals("player 2 turn")) {
				this.setText("Player 2's turn.");
				this.setForeground(Color.blue);
			}
			else if (status_string.equals("player 1 win")) {
				this.setText("Player 1 wins!");
				this.setForeground(Color.red);
			}
			else if (status_string.equals("player 2 win")) {
				this.setText("Player 2 wins!");
				this.setForeground(Color.blue);
			}
			else if (status_string.equals("draw")) {
				this.setText("Draw!");
				this.setForeground(Color.green);
			}
			else if (status_string.equals("player 1 turn skipped")) {
				this.setText("Player 2's turn again. Player 1's turn is skipped due to no placeable tiles.");
				this.setForeground(Color.blue);
			}
			else if (status_string.equals("player 2 turn skipped")) {
				this.setText("Player 1's turn again. Player 2's turn is skipped due to no placeable tiles.");
				this.setForeground(Color.red);
			}
		}
		
	}

	
}
