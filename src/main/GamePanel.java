package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{

		//SCREEN SETTINGS
		final int originalTileSize = 32; //16x16 size (default size)
		final int scale = 1;
		
		public final int tileSize = originalTileSize * scale;
		final int maxScreenCol = 26;
		final int maxScreenRow = 14;
		final int screenWidth = tileSize * maxScreenCol; //832 pixel
		final int screenHeight = tileSize * maxScreenRow; // 448 pixels

		//FPS
		int FPS = 120;
		
		Image bg;
		
		Keyhandler Key = new Keyhandler();
		Thread gameThread; //it keeps the program running
		Player player = new Player(this, Key);
		GamePanel gamePanel;
		
		//Set player's default position
		int playerX = 350;
		int playerY = 500;
		int playerSpeed = 5;
		int nx2;
		
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));	
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(Key);
		this.setFocusable(true);
		this.requestFocus();
	
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override	
	
	public void run () {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		double lastTime = System.nanoTime();
		double currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while (gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			
			if (delta >= 1) {
				update();
				repaint();
				delta --;
				drawCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS: "+ drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	public void update() {
		
		
			player.update();
		
	}
	public void paintComponent(Graphics g) { //default to draw in java
		
		super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g;
	    ImageIcon i = new ImageIcon("C:/Users/Acer/Desktop/eclipse-workspace/Trygame/res/player/bg.png");
	    bg = i.getImage();

	    // Draw the background image
	    if ((player.getZ() - 75) % 2400 == 0) 
	    	player.nx = 0; 
	    if ((player.getZ() - 873) % 2400 == 0)
	    	player.nx2 = 0;
//	    if ((player.getZ() - 1685) % 2400 == 0)
//	    	player.nx3 = 0;
	    g.drawImage(bg, 800-player.nx2, 0, screenWidth, screenHeight, null);
	    if (player.getZ() >= 75) 
	    	g.drawImage(bg, 800 - player.nx, 0, screenWidth, screenHeight, null);
	    
	    
	    // Draw other components/entities
	    player.draw(g2);

	    g2.dispose();
	    
	}
}
