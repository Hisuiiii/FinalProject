package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.Keyhandler;

public class Player extends Entity{

	GamePanel gp;
	Keyhandler Key;
	
	public Player (GamePanel gp, Keyhandler Key) {
		
		this.gp = gp;
		this.Key= Key;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		z = 75;
		speed = 2;
		map_speed = 4;
		nx2 = 800;
		nx = 0;
		direction = "right1"; //lalagyan natin ng apoy sa buntot ng spaceship
	}
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_ship_default.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_ship_default.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_ship_default.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_ship_default.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_ship_default.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_ship_default.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_ship_default.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/sprite_ship_default.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (Key.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		if (Key.downPressed == true) {
			direction = "down";
			y += speed;
		}
		if (Key.rightPressed == true) {
			direction = "right";
			System.out.println(z);
			nx2 += speed;
			nx += speed;
			z += speed;
			//x += speed;
		}
		if (Key.leftPressed == true) {
			direction = "left";
			//x -= speed;
		}

		
	}
	
	public static int getZ() {
		return z;
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			image = up1;
			break;
		case "down":
			image = down1;
			break;
		case "left":
			image = left1;
			break;
		case "right":
			image = right1;
			break;
		}
		
		g2.drawImage(image, x, y, 64, 40, null);
		
		
	}
	
	
}
