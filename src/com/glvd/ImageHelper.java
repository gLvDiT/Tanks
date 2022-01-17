package com.glvd;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageHelper {

	private static BufferedImage image;
	private static BufferedImage [] frames = new BufferedImage[121];

	public static void loadTexture(String fileName) {
		
		File f = new File(fileName);
		
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Не найдена текстура "+fileName);
			System.exit(1);
		}
	
	}
	
	public static void cropImage(int row, int col,  int code) {
		
		int size = Map.BLOCK_SIZE;
		
		BufferedImage frame =  image.getSubimage(col * size, row * size, size, size);
		
		frames[code] = frame;
	}
	
	
	public static void paint (Graphics g, int code, int screenX, int screenY ) {
		g.drawImage(frames[code], screenX, screenY, null);
	}
	
	public static void paint (Graphics g, double angle, int code, int screenX, int screenY ) {
	
			Graphics2D g2 = (Graphics2D)g;	
			
			AffineTransform transform = new AffineTransform();
			transform.translate(screenX, screenY);
			transform.rotate(angle, Map.BLOCK_SIZE / 2, Map.BLOCK_SIZE / 2);
			
			
			g2.drawImage(frames[code], transform, null);
		
	}
	
	
}
