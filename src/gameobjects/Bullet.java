package gameobjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Resource;

public class Bullet {
    float x, y, speed;
    BufferedImage image;

    boolean visible;
    static int countCol = 0;
    
    private Rectangle bulletBound;
    private MaBu maBu;
    
    public Rectangle getBound() {
    	bulletBound = new Rectangle();
    	bulletBound.x = (int)x + 5 ;
    	bulletBound.y = (int)y + 5;
    	bulletBound.width = image.getWidth() - 10;
    	bulletBound.height = image.getHeight() - 10;
    	return bulletBound;
    }
    
    public Bullet(float startX, float startY){
        x = startX;
        y = startY;
        speed = 10.0f;
        image = Resource.getResourceImage("data/bullet_Goku.png");
        
        visible = true;
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
