package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gameinterface.GameObject;
import gamemanager.GameManager;
import gamemanager.GameSettings;
import util.Resource;

public class MaBu implements GameObject {

	public float posX;
	public float posY;
	public float speedY;

	public BufferedImage image;
	public Rectangle rectBound;
	public GameSettings settings;

	public MaBu(GameManager gameManager) {
		image = Resource.getResourceImage("data/Buu_0.png");
		settings = gameManager.settings;
		posX = settings.mabuPosX;
		posY = settings.mabuPosY;
	}

	@Override
	public void update() {
		if (posY <= 18 || posY >= 40) {
			settings.mabuDirectionY *= -1;
		}
		posY += settings.mabuDirectionY * settings.mabuSpeedY;
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
