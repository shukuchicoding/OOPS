package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gameinterface.Enemy;

public class BulletBu extends Enemy {
	private float posX;
	private float posY;
	private float speedX;

	private BufferedImage image;
	private Rectangle rectBound;

	public BulletBu(float posX, float posY, float speedX, BufferedImage image) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		this.speedX = speedX;
	}

	@Override
	public void update() {
		posX += speedX;
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
		return null;
	}

	@Override
	public boolean isOutOfScreen() {
		if (posX < -image.getWidth()) {
			return true;
		}
		return false;
	}

}
