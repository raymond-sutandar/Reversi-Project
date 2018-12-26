package Reversi;

import javax.imageio.*;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;

public class TitlePanel extends JPanel {
	BufferedImage title_image = null;
	
	public void loadTitleImage() {
		try {
			title_image = ImageIO.read(getClass().getResource("/Reversi/Title.png"));
			this.setPreferredSize(new Dimension(768, 160));
		}
		catch (IOException e) {
			
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(title_image, 0, 0, null);
	}
}
