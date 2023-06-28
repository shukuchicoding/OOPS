package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gameinterface.GameObject;
import gamemanager.GameManager;
import gamemanager.GameSettings;
import util.Resource;

public class MabuBullet implements GameObject {
	private float posX;
	private float posY;

	private BufferedImage image;
	private Rectangle rectBound;
	public GameSettings settings;

	public MabuBullet(GameManager gameManager) {
		image = Resource.getResourceImage("data/bullet_Buu.png");
		settings = gameManager.settings;
		posX = gameManager.mabu.posX;
		posY = gameManager.mabu.posY;
	}

	@Override
	public void update() {
		posX -= settings.mabuBulletSpeed;
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
		if (posX < -image.getWidth()) {
			return true;
		}
		return false;
	}

}
