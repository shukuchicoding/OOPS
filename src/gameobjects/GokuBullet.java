package gameobjects;

import util.Resource;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class GokuBullet {
    float x, y, speed;
    BufferedImage image;

    private Rectangle rectBound;
    
    boolean visible;

    public GokuBullet(int startX, int startY){
        x = startX;
        y = startY;
        speed = 10;
        image = Resource.getResourceImage("data/bullet_Goku.png");
        visible = true;
    }

    public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = (int) x + 5;
		rectBound.y = (int) y + 5;
		rectBound.width = image.getWidth() - 10;
		rectBound.height = image.getHeight() - 10;
		return rectBound;
	}
    
   
    
    public void move(){
        x += speed;
        if ( x > 700 ){
            visible = false;
        }
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public boolean getVisible() {
        return visible;
    }

    public BufferedImage getImage(){
        return image;
    }
}