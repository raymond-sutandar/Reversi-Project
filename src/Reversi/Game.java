package Reversi;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game {
	private static JPanel buildGamePanel(JLabel game_status) {
		GridBagConstraints game_constraint = new GridBagConstraints();
		JPanel main_game_panel = new JPanel();
		//main_game_panel.setPreferredSize(new Dimension(768, 384));
		main_game_panel.setLayout(new GridBagLayout());
		
		PlayerPanel p1_panel = new PlayerPanel();
		p1_panel.setupP1PlayerPanel();
		game_constraint.gridx = 0;
		game_constraint.gridy = 0;
		main_game_panel.add(p1_panel);
		
		BoardPanel board_panel = BoardPanel.getInstance();
		game_constraint.gridx = 1;
		game_constraint.gridy = 0;
		main_game_panel.add(board_panel);
		
		PlayerPanel p2_panel = new PlayerPanel();
		p2_panel.setupP2PlayerPanel();
		game_constraint.gridx = 2;
		game_constraint.gridy = 0;
		main_game_panel.add(p2_panel);
		
		
		return main_game_panel;
	}
	
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GridBagConstraints main_constraint = new GridBagConstraints();
				JFrame game_frame = new JFrame("Reversi");
				game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//game_frame.setPreferredSize(new Dimension(768, 544));
				game_frame.setLayout(new GridBagLayout());
				
				main_constraint.insets = new Insets(0, 0, 0, 0);
				
				TitlePanel title = new TitlePanel();
				title.loadTitleImage();
				title.repaint();
				main_constraint.gridx = 0;
				main_constraint.gridy = 0;
				game_frame.add(title, main_constraint);
				
				GameStatusLabel game_status = new GameStatusLabel();
				main_constraint.gridx = 0;
				main_constraint.gridy = 1;
				game_frame.add(game_status, main_constraint);
				
				//To let game_status update every turn
				SingletonUpdateBoard singleton_updater = SingletonUpdateBoard.getInstance();
				singleton_updater.setGameStatusLabel(game_status);
				
				//Setup GameController
				GameController controller = GameController.getInstance();
				
				//Build player and board panels
				main_constraint.gridx = 0;
				main_constraint.gridy = 2;
				game_frame.add(buildGamePanel(game_status), main_constraint);
				
							
				//game_frame.setLocationRelativeTo(null);
				game_frame.pack();
				game_frame.setVisible(true);
			}
		
		} );
	}

}
