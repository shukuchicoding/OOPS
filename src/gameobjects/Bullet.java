package gameobjects;

import util.Resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Bullet {
    int x, y, speed;
    BufferedImage image;

    boolean visible;

    public Bullet(int startX, int startY){
        x = startX;
        y = startY;
        speed = 10;
        image = Resource.getResourceImage("data/bullet_Goku.png");
        visible = true;
    }

    public void move(){
        x += speed;
        if ( x > 700 ){
            visible = false;
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean getVisible() {
        return visible;
    }

    public BufferedImage getImage(){
        return image;
    }
}
