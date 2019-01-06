package Reversi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;

/**
 * A Tile is a JPanel that is placed on top of a BoardPanel. Tiles can either be:
 * white = Empty tiles not claimed by players (free tile);
 * grey = Tiles that can be clicked on by a player to make a move;
 * black = Obstacle tiles that players cannot click and whose colour cannot change;
 * red = Tiles belonging to Player 1;
 * blue = Tiles belonging to Player 2;
 * 
 * @author Raymond Sutandar
 *
 */
public class Tile extends JPanel implements MouseListener {
	int tile_num; // Used for identifying vertex on board. First vertex (top left) is vertex_num 0
	String colour; //Used for identifying owner of tile; white = unowned, red = Player 1, blue = Player 2, grey = clickable tile
	int tiles_changed = 0; //Number of tiles changed upon clicking a grey tile (not including the grey tile itself)
	
	//Array lists treated as stacks to hold tiles that are changed if a grey tile is clicked.
	ArrayList<Tile> upChangedTiles = new ArrayList<Tile>(8);
	ArrayList<Tile> downChangedTiles = new ArrayList<Tile>(8);
	ArrayList<Tile> leftChangedTiles = new ArrayList<Tile>(8);
	ArrayList<Tile> rightChangedTiles = new ArrayList<Tile>(8);
	ArrayList<Tile> upLeftChangedTiles = new ArrayList<Tile>(8);
	ArrayList<Tile> upRightChangedTiles = new ArrayList<Tile>(8);
	ArrayList<Tile> downLeftChangedTiles = new ArrayList<Tile>(8);
	ArrayList<Tile> downRightChangedTiles = new ArrayList<Tile>(8);
	
	//Initialize the tile images to be displayed.
	BufferedImage white_tile = null;
	BufferedImage red_tile = null;
	BufferedImage blue_tile = null;
	BufferedImage grey_tile = null;
	BufferedImage black_tile = null;
	
	/**
	 * Load white, blue, red, and grey tile pictures to a tile so each tile can represent a state.
	 * <ul>
	 * <li>white = Free tile;</li>
	 * <li>red = Tile belongs to Player 1;</li>
	 * <li>blue = Tile belongs to Player 2;</li>
	 * <li>grey = Tile can be clicked on by a player.</li>
	 * </ul>
	 */
	public void loadTileImages() {
		try {
			white_tile = ImageIO.read(getClass().getResource("/Reversi/WhiteTile.png"));
		}
		catch (IOException e) {
			
		}
		
		try {
			blue_tile = ImageIO.read(getClass().getResource("/Reversi/BlueTile.png"));
		}
		catch (IOException e) {
			
		}
		
		try {
			red_tile = ImageIO.read(getClass().getResource("/Reversi/RedTile.png"));
		}
		catch (IOException e) {
			
		}
		
		try {
			grey_tile = ImageIO.read(getClass().getResource("/Reversi/GreyTile.png"));
		}
		catch (IOException e) {
			
		}
		
		try {
			black_tile = ImageIO.read(getClass().getResource("/Reversi/ObstacleTile.png"));
		}
		catch (IOException e) {
			
		}
	}
	
	/**
	 * Add the mouse listener from this tile to itself.
	 */
	public void appendMouseListener() {
		addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if (this.colour.equals("grey")) {
			//Changes a clicked grey tile's colour to red if it is Player 1's turn, or blue if it is Player 2's turn.
			if (GameController.p1Turn) {
				this.colour = "red";
			}
			else {
				this.colour = "blue";
			}
			this.repaint();
			boolean changedTilesEmpty = false;
			
			//Empty all ChangedTiles array in preparation for the next player's turn.
			//If not all ChangedTiles array lists are empty, repeatedly remove their last elements.
			while (!changedTilesEmpty) {
				changeTiles(upChangedTiles, this.colour);
				changeTiles(downChangedTiles, this.colour);
				changeTiles(leftChangedTiles, this.colour);
				changeTiles(rightChangedTiles, this.colour);
				changeTiles(upLeftChangedTiles, this.colour);
				changeTiles(upRightChangedTiles, this.colour);
				changeTiles(downLeftChangedTiles, this.colour);
				changeTiles(downRightChangedTiles, this.colour);
				
				if (upChangedTiles.isEmpty() && downChangedTiles.isEmpty() && leftChangedTiles.isEmpty() && rightChangedTiles.isEmpty() && upLeftChangedTiles.isEmpty() && upRightChangedTiles.isEmpty() && downLeftChangedTiles.isEmpty() && downRightChangedTiles.isEmpty()) {
					changedTilesEmpty = true;
				}
				
				//OPTIONAL: Repaint tile with delay for animation
				
			}
			
			//Calculate health damage based on tiles_changed. Note that tiles_changed >= 2 is checked since the clicked grey tile
			//changing does not give extra points.
			int damage = (100 * tiles_changed);
			if (tiles_changed >= 2) {
				damage += (tiles_changed - 1) * 50;
			}
			
			//Reset all remaining grey, clickable tiles to white ones for next player's turn (updates BoardPanel)
			SingletonUpdateBoard boardUpdater = SingletonUpdateBoard.getInstance();
			boardUpdater.updateGameController(damage);
			
			
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Changes the colour of the last tile in tile_list to playerColour. Returns the number of tiles changed.
	 * @param tile_list
	 * @param playerColour
	 */
	public void changeTiles(ArrayList<Tile> tile_list, String playerColour) {		
			if (!tile_list.isEmpty()) {
				Tile retTile = tile_list.get(tile_list.size() - 1);
				retTile.colour = playerColour;
				retTile.repaint();
				tile_list.remove(tile_list.size() - 1);
				
				tiles_changed++;
			}
				
	}
	
	public void paint(Graphics g) {
		if (this.colour.equals("white")) {
			g.drawImage(white_tile, 0, 0, null);
		}
		else if (this.colour.equals("red")) {
			g.drawImage(red_tile, 0, 0, null);
		}
		else if (this.colour.equals("blue")) {
			g.drawImage(blue_tile, 0, 0, null);
		}
		else if (this.colour.equals("grey")) {
			g.drawImage(grey_tile, 0, 0, null);
		}
		else if (this.colour.equals("black")) {
			g.drawImage(black_tile, 0, 0, null);
		}
	}
	
	
}
