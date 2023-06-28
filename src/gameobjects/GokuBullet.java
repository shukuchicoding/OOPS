package gameobjects;

import util.Resource;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gameinterface.GameObject;
import gamemanager.GameManager;
import gamemanager.GameSettings;

public class GokuBullet implements GameObject {
    private float posX;
    private float posY;

    BufferedImage image;
    private Rectangle rectBound;
    public GameSettings settings;

    public GokuBullet(GameManager gameManager) {
        image = Resource.getResourceImage("data/bullet_Goku.png");
        settings = gameManager.settings;
        posX = gameManager.mainCharacter.posX + (float) gameManager.mainCharacter.getBound().getWidth();
        posY = gameManager.mainCharacter.posY + (float) gameManager.mainCharacter.getBound().getHeight() / 2
                - image.getHeight() / 2;
    }

    @Override
    public void update() {
        posX += settings.gokuBulletSpeed;
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
        if (posX > settings.SCREEN_WIDTH) {
            return true;
        }
        return false;
    }
}
