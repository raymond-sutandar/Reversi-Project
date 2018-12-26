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
	
	public PlayerPanel() {
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
		this.setPreferredSize(new Dimension(192, 384));
		//sthis.setBackground(Color.lightGray);
		
		//Border label_border = BorderFactory.createLineBorder(Color.black, 2);
		
		//health_P1_label.setPreferredSize(new Dimension(192, 64));
		//health_P1_label.setFont(new Font("SansSerif", Font.BOLD, 26));
		//health_P1_label.setHorizontalAlignment(SwingConstants.CENTER);
		//health_P1_label.setBorder(label_border);
		
		//health_P2_label.setPreferredSize(new Dimension(192, 64));
		//health_P2_label.setFont(new Font("SansSerif", Font.BOLD, 26));
		//health_P2_label.setHorizontalAlignment(SwingConstants.CENTER);
		//health_P2_label.setBorder(label_border);
		
		//player_title_pic.setPreferredSize(new Dimension(192, 128));
		
		//player_rep_pic.setPreferredSize(new Dimension(192, 224));
	}
	
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
		//health_P1_label.setAlignmentX(CENTER_ALIGNMENT);
		grid_constraint.gridx = 0;
		grid_constraint.gridy = 2;
		this.add(player_rep_pic, grid_constraint);
	}
	
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
		//health_P2_label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		grid_constraint.gridx = 0;
		grid_constraint.gridy = 2;
		this.add(player_rep_pic, grid_constraint);
	}
	
	
	
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
