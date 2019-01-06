package Reversi;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.Random;


/**
 * Singleton game board divided into a grid. Each part of the grid contains a tile a user can click
 * on to play the game.
 * @author Raymond Sutandar
 *
 */
public class BoardPanel extends JPanel {
	//Singleton design pattern for BoardPanel
	private static BoardPanel singletonBoardPanel = null;
	
	public static BoardPanel getInstance() {
		if (singletonBoardPanel == null) {
			singletonBoardPanel = new BoardPanel();
		}
		
		return singletonBoardPanel;
	}
	
	
	private BoardPanel() {
	
	}
	
	/**
	 * Places obstacle tiles, empty tiles, and starting player tiles on the given tile_board in a BoardPanel.
	 * @param tile_board - The given game board.
	 * @param board_length - tile_board's length (height).
	 * @param board_width - tile_board's width.
	 * @return Returns a tile_board that is set up, that is with the default player tiles, obstacle tiles, and free tiles placed.
	 */
	public Tile[][] setupBoardPanel(Tile[][] tile_board, int board_length, int board_width) {
		int tile_counter = 0; //Num of tile
		
		this.setPreferredSize(new Dimension(48 * board_length, 48 * board_width));
		this.setLayout(new GridLayout(board_length, board_width));	
		this.setVisible(true);
		
		//Initialize tiles
		for (int i = 0; i < board_length; i++) {
			for (int j = 0; j < board_width; j++) {
				tile_board[i][j] = new Tile();
				tile_board[i][j].loadTileImages();
				tile_board[i][j].colour = "white";
				tile_board[i][j].repaint();
				tile_board[i][j].appendMouseListener();
				tile_board[i][j].tile_num = tile_counter;
				
				//Initialize player starting tiles (assumes board is 8x8, middle tiles)
				if ((tile_board[i][j].tile_num == 27) || (tile_board[i][j].tile_num == 36)) {
					tile_board[i][j].colour = "red";
					tile_board[i][j].repaint();
				}
				if ((tile_board[i][j].tile_num == 28) || (tile_board[i][j].tile_num == 35)) {
					tile_board[i][j].colour = "blue";
					tile_board[i][j].repaint();
				}
				
				
				//Add tiles to board grid layout.
				this.add(tile_board[i][j]);
				
				
				tile_counter++;
			}
		}
		
		//Randomly pick 5 tiles to be obstacle tiles that players cannot place their tiles on.
		generateObstacleTiles(tile_board, board_length, board_width);
		
		//Set tiles that can be clicked on by P1
		FindClickableP1Tiles possibleP1tiles = FindClickableP1Tiles.getInstance();
		possibleP1tiles.clickableP1Tiles(tile_board, board_length, board_width);
		
		return tile_board;

		
	}
	
	/**
	 * Chooses an obstacle set to determine which tiles on the game board will be obstacles.
	 * @param tile_board - The given game board.
	 * @param board_length - tile_board's length (height).
	 * @param board_width - tile_board's width.
	 */
	public void generateObstacleTiles(Tile[][] tile_board, int board_length, int board_width) {
		Random random_generator = new Random();
		int obstacle_array_size = 7;
		int random_index = random_generator.nextInt(obstacle_array_size);
		
		int[][] obstacle_tile_array = new int[obstacle_array_size][5];
		// Choose random tiles among these.
		int[] obstacles1 = {0, 7, 8, 63, 40};
		int[] obstacles2 = {3, 6, 49, 60, 41};
		int[] obstacles3 = {8, 48, 56, 15, 39};
		int[] obstacles4 = {9, 11, 18, 46, 58};
		int[] obstacles5 = {17, 21, 23, 48, 54};
		int[] obstacles6 = {1, 5, 22, 39, 37};
		int[] obstacles7 = {6, 7, 31, 45, 51};
		obstacle_tile_array[0] = obstacles1;
		obstacle_tile_array[1] = obstacles2;
		obstacle_tile_array[2] = obstacles3;
		obstacle_tile_array[3] = obstacles4;
		obstacle_tile_array[4] = obstacles5;
		obstacle_tile_array[5] = obstacles6;
		obstacle_tile_array[6] = obstacles7;
		
		for (int i = 0; i < board_length; i++) {
			for (int j = 0; j < board_width; j++) {
				for (int k = 0; k < obstacle_tile_array[random_index].length; k++) {
					if (tile_board[i][j].tile_num == obstacle_tile_array[random_index][k]) {
						tile_board[i][j].colour = "black";
						tile_board[i][j].repaint();
					}
				}
			}
		}
	}

	
	
}
