package Reversi;

import java.util.ArrayList;

/**
 * Singleton object used to find the tiles that can be clicked by player 1 when it is their turn.
 * @author Raymond Sutandar
 *
 */
public class FindClickableP1Tiles {
	
	int num_clickable_tiles = 0;
	
	private FindClickableP1Tiles() {
		
	}
	
	private static FindClickableP1Tiles clickableP1Tiles = null;
	
	public static FindClickableP1Tiles getInstance() {
		if (clickableP1Tiles == null) {
			clickableP1Tiles = new FindClickableP1Tiles();
		}
		return clickableP1Tiles;
	}
	
	
	/**
	 * Checks for tiles that can be clicked by player 1.
	 * @param tile_board
	 * @param board_length
	 * @param board_width
	 */
	public int clickableP1Tiles(Tile[][] tile_board, int board_length, int board_width) {
		
		num_clickable_tiles = 0;
		
		for (int k = 0; k < board_length; k++) {
			for (int l = 0; l < board_width; l++) {
				if (tile_board[k][l].colour.equals("red")) {
					
					//board_length = max horizontal size
					findP1ClickableUpTiles(tile_board, k, l);
					findP1ClickableDownTiles(tile_board, k, l, board_width);
					findP1ClickableLeftTiles(tile_board, k, l);
					findP1ClickableRightTiles(tile_board, k, l, board_length);
					findP1ClickableUpLeftTiles(tile_board, k, l);
					findP1ClickableUpRightTiles(tile_board, k, l, board_length);
					findP1ClickableDownLeftTiles(tile_board, k, l, board_width);
					findP1ClickableDownRightTiles(tile_board, k, l, board_length, board_width);
					
				}
			}
		}
		
		//System.out.println("Num_clickable_tiles for P1: " + num_clickable_tiles);
		/*
		if (num_clickable_tiles == 0) {
			//System.out.println("Player 1 num-clickable_tiles = 0.");
			SingletonUpdateBoard clickables_updater = SingletonUpdateBoard.getInstance();
			if (PlayerPanel.p1health > PlayerPanel.p2health) {
				clickables_updater.updateGameStatusLabel("player 1 win");
			}
			else if (PlayerPanel.p1health < PlayerPanel.p2health) {
				clickables_updater.updateGameStatusLabel("player 2 win");
			}
			else {
				clickables_updater.updateGameStatusLabel("draw");
			}
		}
		*/
		return num_clickable_tiles;
		
	}
	
	public void findP1ClickableUpTiles(Tile[][] tile_board, int x_pos, int y_pos) {
		boolean checkAvailable = true; //True if tile available in a direction from tile_board[k][l]
		boolean crossedBlue = false; //True if I have checked a blue tile
		int availableXpos = x_pos;
		int availableYpos = y_pos;
		ArrayList<Tile> upCrossedTiles = new ArrayList<Tile>(8); //Store blue tiles I've crossed to find a clickable tile
		
		//Check tiles above
		while (checkAvailable == true) {
			if (availableYpos - 1 >= 0) { //If tile above tile_board[x_pos][y_pos] is on tile_board.
				
				if ( (tile_board[availableXpos][availableYpos - 1].colour.equals("red") || tile_board[availableXpos][availableYpos - 1].colour.equals("black"))) {
					checkAvailable = false;
				}
				
				else if (( ( tile_board[availableXpos][availableYpos - 1].colour.equals("white")) || (tile_board[availableXpos][availableYpos - 1].colour.equals("grey")) ) && (crossedBlue == true)) {
					checkAvailable = false;
					tile_board[availableXpos][availableYpos - 1].colour = "grey";
					tile_board[availableXpos][availableYpos - 1].repaint();
					tile_board[availableXpos][availableYpos - 1].upChangedTiles = upCrossedTiles;
					num_clickable_tiles++;
				}
				
				else if (tile_board[availableXpos][availableYpos - 1].colour.equals("blue")) {
					upCrossedTiles.add(tile_board[availableXpos][availableYpos - 1]);
					availableYpos--;
					
					crossedBlue = true;
				}
				
				else { //Haven't crossed a blue tile, but have checked a white tile
					checkAvailable = false;
				}
			}
			else { //Above tile is not on the board
				checkAvailable = false;
			}
		}
	}
	
	public void findP1ClickableDownTiles(Tile[][] tile_board, int x_pos, int y_pos, int max_height) {
		boolean checkAvailable = true;
		boolean crossedBlue = false;
		int availableXpos = x_pos;
		int availableYpos = y_pos;
		ArrayList<Tile> downCrossedTiles = new ArrayList<Tile>(8); //Store blue tiles I've crossed to find a clickable tile
		
		//Check tiles below
		while (checkAvailable == true) {
			if (availableYpos + 1 < max_height) { //If tile below tile_board[k][l] is on tile_board.
				
				if ((tile_board[availableXpos][availableYpos + 1].colour.equals("red")) || (tile_board[availableXpos][availableYpos + 1].colour.equals("black"))) {
					checkAvailable = false;
				}
				
				else if (( (tile_board[availableXpos][availableYpos + 1].colour.equals("white")) || (tile_board[availableXpos][availableYpos + 1].colour.equals("grey")) ) && (crossedBlue == true)) {
					checkAvailable = false;
					tile_board[availableXpos][availableYpos + 1].colour = "grey";
					tile_board[availableXpos][availableYpos + 1].repaint();
					tile_board[availableXpos][availableYpos + 1].downChangedTiles = downCrossedTiles;
					num_clickable_tiles++;
				}
				
				else if (tile_board[availableXpos][availableYpos + 1].colour.equals("blue")) {
					downCrossedTiles.add(tile_board[availableXpos][availableYpos + 1]);
					availableYpos++;
					
					crossedBlue = true;
				}
				
				else { //Haven't crossed a blue tile, but have checked a white tile
					checkAvailable = false;
				}
			}
			else { //Below tile is not on the board
				checkAvailable = false;
			}
		}
	}
	
	public void findP1ClickableLeftTiles(Tile[][] tile_board, int x_pos, int y_pos) {
		boolean checkAvailable = true;
		boolean crossedBlue = false;
		int availableXpos = x_pos;
		int availableYpos = y_pos;
		ArrayList<Tile> leftCrossedTiles = new ArrayList<Tile>(8); //Store blue tiles I've crossed to find a clickable tile
		
		//Check tiles below
		while (checkAvailable == true) {
			if (availableXpos - 1 >= 0) { //If tile left of tile_board[k][l] is on tile_board.
				
				if ((tile_board[availableXpos - 1][availableYpos].colour.equals("red")) || (tile_board[availableXpos - 1][availableYpos].colour.equals("black"))) {
					checkAvailable = false;
				}
				
				else if (( (tile_board[availableXpos - 1][availableYpos].colour.equals("white")) || (tile_board[availableXpos - 1][availableYpos].colour.equals("grey")) ) && (crossedBlue == true)) {
					checkAvailable = false;
					tile_board[availableXpos - 1][availableYpos].colour = "grey";
					tile_board[availableXpos - 1][availableYpos].repaint();
					tile_board[availableXpos - 1][availableYpos].leftChangedTiles = leftCrossedTiles;
					num_clickable_tiles++;
				}
				
				else if (tile_board[availableXpos - 1][availableYpos].colour.equals("blue")) {
					leftCrossedTiles.add(tile_board[availableXpos - 1][availableYpos]);
					availableXpos--;
					
					crossedBlue = true;
				}
				
				else { //Haven't crossed a blue tile, but have checked a white tile
					checkAvailable = false;
				}
			}
			else { //Below tile is not on the board
				checkAvailable = false;
			}
		}
	}
	
	public void findP1ClickableRightTiles(Tile[][] tile_board, int x_pos, int y_pos, int max_width) {
		boolean checkAvailable = true;
		boolean crossedBlue = false;
		int availableXpos = x_pos;
		int availableYpos = y_pos;
		ArrayList<Tile> rightCrossedTiles = new ArrayList<Tile>(8); //Store blue tiles I've crossed to find a clickable tile
		
		//Check tiles below
		while (checkAvailable == true) {
			if (availableXpos + 1 < max_width) { //If tile right of tile_board[k][l] is on tile_board.
				
				if ((tile_board[availableXpos + 1][availableYpos].colour.equals("red")) || (tile_board[availableXpos + 1][availableYpos].colour.equals("black"))) {
					checkAvailable = false;
				}
				
				else if (( (tile_board[availableXpos + 1][availableYpos].colour.equals("white")) || (tile_board[availableXpos + 1][availableYpos].colour.equals("grey")) ) && (crossedBlue == true)) {
					checkAvailable = false;
					tile_board[availableXpos + 1][availableYpos].colour = "grey";
					tile_board[availableXpos + 1][availableYpos].repaint();
					tile_board[availableXpos + 1][availableYpos].rightChangedTiles = rightCrossedTiles;
					num_clickable_tiles++;
				}
				
				else if (tile_board[availableXpos + 1][availableYpos].colour.equals("blue")) {
					rightCrossedTiles.add(tile_board[availableXpos + 1][availableYpos]);
					availableXpos++;
					
					crossedBlue = true;
				}
				
				else { //Haven't crossed a blue tile, but have checked a white tile
					checkAvailable = false;
				}
			}
			else { //Below tile is not on the board
				checkAvailable = false;
			}
		}
	}
	
	public void findP1ClickableUpLeftTiles(Tile[][] tile_board, int x_pos, int y_pos) {
		boolean checkAvailable = true;
		boolean crossedBlue = false;
		int availableXpos = x_pos;
		int availableYpos = y_pos;
		ArrayList<Tile> upLeftCrossedTiles = new ArrayList<Tile>(8); //Store blue tiles I've crossed to find a clickable tile
		
		//Check tiles below
		while (checkAvailable == true) {
			if (availableXpos - 1 >= 0) { //If tile right of tile_board[k][l] is on tile_board.
				if (availableYpos - 1 >= 0) {
					if ((tile_board[availableXpos - 1][availableYpos - 1].colour.equals("red")) || (tile_board[availableXpos - 1][availableYpos - 1].colour.equals("black"))) {
						checkAvailable = false;
					}
					
					else if (( (tile_board[availableXpos - 1][availableYpos - 1].colour.equals("white")) || (tile_board[availableXpos - 1][availableYpos - 1].colour.equals("grey")) ) && (crossedBlue == true)) {
						checkAvailable = false;
						tile_board[availableXpos - 1][availableYpos - 1].colour = "grey";
						tile_board[availableXpos - 1][availableYpos - 1].repaint();
						tile_board[availableXpos - 1][availableYpos - 1].upLeftChangedTiles = upLeftCrossedTiles;
						num_clickable_tiles++;
					}
					
					else if (tile_board[availableXpos - 1][availableYpos - 1].colour.equals("blue")) {
						upLeftCrossedTiles.add(tile_board[availableXpos - 1][availableYpos - 1]);
						availableXpos--;
						availableYpos--;
						
						crossedBlue = true;
					}
					
					else { //Haven't crossed a blue tile, but have checked a white tile
						checkAvailable = false;
					}
				}
				else { //Upper-left tile is not on the board
					checkAvailable = false;
				}
			}
			else { //Upper-left tile is not on the board
				checkAvailable = false;
			}
		}
	}
	
	public void findP1ClickableUpRightTiles(Tile[][] tile_board, int x_pos, int y_pos, int max_width) {
		boolean checkAvailable = true;
		boolean crossedBlue = false;
		int availableXpos = x_pos;
		int availableYpos = y_pos;
		ArrayList<Tile> upRightCrossedTiles = new ArrayList<Tile>(8); //Store blue tiles I've crossed to find a clickable tile
		
		//Check tiles below
		while (checkAvailable == true) {
			if (availableXpos + 1 < max_width) { //If tile right of tile_board[k][l] is on tile_board.
				if (availableYpos - 1 >= 0) {
					if ((tile_board[availableXpos + 1][availableYpos - 1].colour.equals("red")) || (tile_board[availableXpos + 1][availableYpos - 1].colour.equals("black"))) {
						checkAvailable = false;
					}
					
					else if (( (tile_board[availableXpos + 1][availableYpos - 1].colour.equals("white")) || (tile_board[availableXpos + 1][availableYpos - 1].colour.equals("grey")) ) && (crossedBlue == true)) {
						checkAvailable = false;
						tile_board[availableXpos + 1][availableYpos - 1].colour = "grey";
						tile_board[availableXpos + 1][availableYpos - 1].repaint();
						tile_board[availableXpos + 1][availableYpos - 1].upRightChangedTiles = upRightCrossedTiles;
						num_clickable_tiles++;
					}
					
					else if (tile_board[availableXpos + 1][availableYpos - 1].colour.equals("blue")) {
						upRightCrossedTiles.add(tile_board[availableXpos + 1][availableYpos - 1]);
						availableXpos++;
						availableYpos--;
						
						crossedBlue = true;
					}
					
					else { //Haven't crossed a blue tile, but have checked a white tile
						checkAvailable = false;
					}
				}
				else { //Upper-right tile is not on the board
					checkAvailable = false;
				}
			}
			else { //Upper-right tile is not on the board
				checkAvailable = false;
			}
		}
	}
	
	public void findP1ClickableDownLeftTiles(Tile[][] tile_board, int x_pos, int y_pos, int max_height) {
		boolean checkAvailable = true;
		boolean crossedBlue = false;
		int availableXpos = x_pos;
		int availableYpos = y_pos;
		ArrayList<Tile> downLeftCrossedTiles = new ArrayList<Tile>(8); //Store blue tiles I've crossed to find a clickable tile
		
		//Check tiles below
		while (checkAvailable == true) {
			if (availableXpos - 1 >= 0) { //If tile right of tile_board[k][l] is on tile_board.
				if (availableYpos + 1 < max_height) {
					if ((tile_board[availableXpos - 1][availableYpos + 1].colour.equals("red")) || (tile_board[availableXpos - 1][availableYpos + 1].colour.equals("black"))) {
						checkAvailable = false;
					}
					
					else if (( (tile_board[availableXpos - 1][availableYpos + 1].colour.equals("white")) || (tile_board[availableXpos - 1][availableYpos + 1].colour.equals("grey"))) && (crossedBlue == true)) {
						checkAvailable = false;
						tile_board[availableXpos - 1][availableYpos + 1].colour = "grey";
						tile_board[availableXpos - 1][availableYpos + 1].repaint();
						tile_board[availableXpos - 1][availableYpos + 1].downLeftChangedTiles = downLeftCrossedTiles;
						num_clickable_tiles++;
					}
					
					else if (tile_board[availableXpos - 1][availableYpos + 1].colour.equals("blue")) {
						downLeftCrossedTiles.add(tile_board[availableXpos - 1][availableYpos + 1]);
						availableXpos--;
						availableYpos++;
						
						crossedBlue = true;
					}
					
					else { //Haven't crossed a blue tile, but have checked a white tile
						checkAvailable = false;
					}
				}
				else { //Down and to the left tile is not on the board
					checkAvailable = false;
				}
			}
			else { //Down and to the left tile is not on the board
				checkAvailable = false;
			}
		}
	}
	
	public void findP1ClickableDownRightTiles(Tile[][] tile_board, int x_pos, int y_pos, int max_width, int max_height) {
		boolean checkAvailable = true;
		boolean crossedBlue = false;
		int availableXpos = x_pos;
		int availableYpos = y_pos;
		ArrayList<Tile> downRightCrossedTiles = new ArrayList<Tile>(8); //Store blue tiles I've crossed to find a clickable tile
		
		//Check tiles below
		while (checkAvailable == true) {
			if (availableXpos + 1 < max_width) { //If tile tile_board[availableXpos][availableYpos] is on tile_board.
				if (availableYpos + 1 < max_height) {
					if ((tile_board[availableXpos + 1][availableYpos + 1].colour.equals("red")) || (tile_board[availableXpos + 1][availableYpos + 1].colour.equals("black"))) {
						checkAvailable = false;
					}
					
					else if (( (tile_board[availableXpos + 1][availableYpos + 1].colour.equals("white")) || (tile_board[availableXpos + 1][availableYpos + 1].colour.equals("grey"))) && (crossedBlue == true)) {
						checkAvailable = false;
						tile_board[availableXpos + 1][availableYpos + 1].colour = "grey";
						tile_board[availableXpos + 1][availableYpos + 1].repaint();
						tile_board[availableXpos + 1][availableYpos + 1].downRightChangedTiles = downRightCrossedTiles;
						num_clickable_tiles++;
					}
					
					else if (tile_board[availableXpos + 1][availableYpos + 1].colour.equals("blue")) {
						downRightCrossedTiles.add(tile_board[availableXpos + 1][availableYpos + 1]);
						availableXpos++;
						availableYpos++;
						
						crossedBlue = true;
					}
					
					else { //Haven't crossed a blue tile, but have checked a white tile
						checkAvailable = false;
					}
				}
				else { //Upper-left tile is not on the board
					checkAvailable = false;
				}
			}
			else { //Upper-left tile is not on the board
				checkAvailable = false;
			}
		}
	}
	
}
