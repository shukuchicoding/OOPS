package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gameinterface.Enemy;

public class MaBu extends Enemy {

	private float posX = 650.f;
	private float posY = 20.f;
	private float speedY = 0.2f;
	private int directionY = 1;

	private BufferedImage image;
	private Rectangle rectBound;

	public MaBu(BufferedImage image, float posX, float posY, float speedY) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		this.speedY = speedY;
	}

	@Override
	public void update() {
		if (posY <= 18 || posY >= 40) {
			directionY *= -1;
		}
		posY += directionY * speedY;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, (int) posX, (int) posY, null);
	}

	@Override
	public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = (int) posX + 5;
		rectBound.y = (int) posY + 5;
		rectBound.width = image.getWidth() - 10;
		rectBound.height = image.getHeight() - 10;
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		// TODO Auto-generated method stub
		return false;
	}

	public void dead (boolean isDeath) {
		
	}
	
	public void revive (boolean isRevive) {
		
	}
}
