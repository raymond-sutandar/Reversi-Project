package Reversi;

import java.util.Observable;
import java.util.Observer;

/**
 * This class sets up the game grid for BoardPanel and handles the process of turn
 * changing as an Observer to game changes.
 * @author Raymond Sutandar
 *
 */
public class GameController implements Observer {
	
	Tile[][] tile_board = new Tile[8][8];
	static boolean p1Turn = true;
	int board_length = 8;
	int board_width = 8;
	
	//Singleton GameController, since I want only one GameController
	private GameController() {
		tile_board = BoardPanel.getInstance().setupBoardPanel(tile_board, board_length, board_width);
	}
	
	private static GameController controller_instance = null;
	
	public static GameController getInstance() {
		if (controller_instance == null) {
			controller_instance = new GameController();
		}
		return controller_instance;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		//Damage other player's health based on Object arg, which represents damage
		if (arg instanceof Integer) {
			int damage = (int)arg;
			
			if (p1Turn) {
				PlayerPanel.damageP2Health(damage);
				if (PlayerPanel.getP2Health() <= 0) {
					PlayerPanel.setP2Health(0);
					endGame();
				}
			}
			else {
				PlayerPanel.damageP1Health(damage);
				if (PlayerPanel.getP1Health() <= 0) {
					PlayerPanel.setP1Health(0);
					endGame();
				}
			}
		
		
			//Set to other player's turn (after one player's mouse-click on grey tile)
			if (p1Turn == true) {
				p1Turn = false;	
			}
			else {
				p1Turn = true;
			}
			
			//Repaint board after this player's turn, change all grey tiles to white ones in anticipation for other player's turn
			//Also, reset grey tiles' directional tile lists
			for (int i = 0; i < board_length; i++) {
				for (int j = 0; j < board_width; j++) {
					if (tile_board[i][j].colour.equals("grey")) {
						tile_board[i][j].colour = "white";
						tile_board[i][j].repaint();
							
						tile_board[i][j].upChangedTiles.clear();
						tile_board[i][j].downChangedTiles.clear();
						tile_board[i][j].leftChangedTiles.clear();
						tile_board[i][j].rightChangedTiles.clear();
						tile_board[i][j].upLeftChangedTiles.clear();
						tile_board[i][j].upRightChangedTiles.clear();
						tile_board[i][j].downLeftChangedTiles.clear();
						tile_board[i][j].downRightChangedTiles.clear();
					}
				}
			}
			
			//Check if the end of the game has been reached using num_clickable_tiles
			//SingletonUpdateBoard updater = SingletonUpdateBoard.getInstance();
			
			//On next player's turn, find their clickable tiles.
			FindClickableP1Tiles possibleP1tiles = FindClickableP1Tiles.getInstance();
			FindClickableP2Tiles possibleP2tiles = FindClickableP2Tiles.getInstance();
			if (p1Turn) {
				SingletonUpdateBoard.getInstance().updateGameStatusLabel("player 1 turn");
				
				if (possibleP1tiles.clickableP1Tiles(tile_board, board_length, board_width) == 0) {
					SingletonUpdateBoard.getInstance().updateGameStatusLabel("player 1 turn skipped");
					p1Turn = false;
					
					//Neither player 1 or 2 have any clickable tiles
					if (possibleP2tiles.clickableP2Tiles(tile_board, board_length, board_width) == 0) {
						endGame();
					}
				}
			}
			else {
				SingletonUpdateBoard.getInstance().updateGameStatusLabel("player 2 turn");
				
				if (possibleP2tiles.clickableP2Tiles(tile_board, board_length, board_width) == 0) {
					SingletonUpdateBoard.getInstance().updateGameStatusLabel("player 2 turn skipped");
					p1Turn = true;
					
					//Neither player 1 or 2 have any clickable tiles
					if (possibleP1tiles.clickableP1Tiles(tile_board, board_length, board_width) == 0) {
						endGame();
					}
				}
			}
			
		}
		
	}
	
	public void endGame() {
		if (PlayerPanel.p1health > PlayerPanel.p2health) {
			SingletonUpdateBoard.getInstance().updateGameStatusLabel("player 1 win");
		}
		else if (PlayerPanel.p1health < PlayerPanel.p2health) {
			SingletonUpdateBoard.getInstance().updateGameStatusLabel("player 2 win");
		}
		else {
			SingletonUpdateBoard.getInstance().updateGameStatusLabel("draw");
		}
	}
}
