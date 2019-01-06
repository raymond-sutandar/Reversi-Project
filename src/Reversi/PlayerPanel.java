package Reversi;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A PlayerPanel displays a player's title (Player 1 (P1Title.png) or Player 2 (P2Title.png)), image (Player1Rep.png or Player2Rep.png), 
 * and health.
 * @author Raymond Sutandar
 *
 */
public class PlayerPanel extends JPanel {
	BufferedImage player_title = null;
	BufferedImage player_rep = null;
	BufferedImage health_bg = null;
	static JLabel health_P1_label;
	static JLabel health_P2_label;	
	public static int p1health = 10000;
	public static int p2health = 10000;
	JLabel player_title_pic;
	JLabel player_rep_pic;
	JLabel health_bg_pic;
	
	GridBagConstraints grid_constraint = new GridBagConstraints();
	
	/**
	 * Sets up a GridBagLayout to be used for a PlayerPanel.
	 */
	public PlayerPanel() {
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
		this.setPreferredSize(new Dimension(192, 384));
	}
	
	/**
	 * Sets up the display of Player 1's PlayerPanel with their player title, image, and health.
	 */
	public void setupP1PlayerPanel() {
		try {
			player_title = ImageIO.read(getClass().getResource("P1Title.png"));
		}
		catch (IOException e) {
			
		}
		
		try {
			player_rep = ImageIO.read(getClass().getResource("Player1Rep.png"));
		}
		catch (IOException e) {
			
		}
		
		player_title_pic = new JLabel(new ImageIcon(player_title));
		player_rep_pic = new JLabel(new ImageIcon(player_rep));
		
		health_P1_label = new JLabel("Health: 10000");
		health_P1_label.setFont(new Font("SansSerif", Font.BOLD, 26));
		health_P1_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		grid_constraint.gridx = 0;
		grid_constraint.gridy = 0;
		this.add(player_title_pic, grid_constraint);
		
		grid_constraint.gridx = 0;
		grid_constraint.gridy = 1;
		this.add(health_P1_label, grid_constraint);
		
		grid_constraint.gridx = 0;
		grid_constraint.gridy = 2;
		this.add(player_rep_pic, grid_constraint);
	}
	
	/**
	 * Sets up the display of Player 2's PlayerPanel with their player title, image, and health.
	 */
	public void setupP2PlayerPanel() {
		try {
			player_title = ImageIO.read(getClass().getResource("P2Title.png"));
		}
		catch (IOException e) {
			
		}
		
		try {
			player_rep = ImageIO.read(getClass().getResource("Player2Rep.png"));
		}
		catch (IOException e) {
			
		}
		
		player_title_pic = new JLabel(new ImageIcon(player_title));
		player_rep_pic = new JLabel(new ImageIcon(player_rep));
		
		health_P2_label = new JLabel("Health: 10000");
		health_P2_label.setFont(new Font("SansSerif", Font.BOLD, 26));
		health_P2_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		grid_constraint.gridx = 0;
		grid_constraint.gridy = 0;
		this.add(player_title_pic, grid_constraint);
		
		grid_constraint.gridx = 0;
		grid_constraint.gridy = 1;
		this.add(health_P2_label, grid_constraint);
		
		grid_constraint.gridx = 0;
		grid_constraint.gridy = 2;
		this.add(player_rep_pic, grid_constraint);
	}
	
	/**
	 * Reduces Player 1's health (p1health) by damage, and updates Player 1's health display to the new value of p1health.
	 * @param damage - The reduction in health to be applied to a player's health.
	 */
	public static void damageP1Health(int damage) {
		p1health -= damage;
		health_P1_label.setText("Health: " + String.valueOf(p1health));
	}
	public static int getP1Health() {
		return p1health;
	}
	public static void setP1Health(int new_P1_health) {
		p1health = new_P1_health;
	}
	
	/**
	 * Reduces Player 2's health (p2health) by damage, and updates Player 2's health display to the new value of p2health.
	 * @param damage - The reduction in health to be applied to a player's health.
	 */
	public static void damageP2Health(int damage) {
		p2health -= damage;
		health_P2_label.setText("Health: " + String.valueOf(p2health));
	}
	public static int getP2Health() {
		return p2health;
	}
	public static void setP2Health(int new_P2_health) {
		p2health = new_P2_health;
	}
	
}
