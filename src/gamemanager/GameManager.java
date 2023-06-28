package gamemanager;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameinterface.Object;
import gameobjects.*;
import gameinterface.GameSettings;
import util.Resource;

public class GameManager extends GameSettings{
	private MainCharacter mainCharacter;
	private MaBu mabu;
	
	private BufferedImage tree;
	private BufferedImage stone;
	
	private List<Object> obstacles;
	private List<MabuBullet> mabuBullet;
	
	private Random rand;
	
	private long mabuPreviousShoot; // milisecond


	public GameManager(MainCharacter mainCharacter) {
		rand = new Random();
		tree = Resource.getResourceImage("data/tree.png");
		stone = Resource.getResourceImage("data/stone.png");
		
		this.mainCharacter = mainCharacter;
		mabu = new MaBu(mabuPosX, mabuPosY, mabuSpeedY, mabuDirectionY);
		
		obstacles = new ArrayList<>();
		obstacles.add(createObstacle());
		
		mabuBullet = new ArrayList<>();
		mabuPreviousShoot = System.currentTimeMillis();
	}

	public void update() {
		mabu.update();
		mabuBulletUpdate();
		for (Object o : obstacles) {
			o.update();
		}
		
		if ((System.currentTimeMillis() - mabuPreviousShoot) >= mabuShootingPeriod) {
			mabuFire();
			mabuPreviousShoot = System.currentTimeMillis();
		}
	}
	
	public void mabuBulletUpdate() {
		for (MabuBullet bullet: mabuBullet) {
			bullet.update();
		}
		
		if (mabuBullet.size() > 0) {
			MabuBullet bullet = mabuBullet.get(0);
			if (bullet.isOutOfScreen()) {
				mabuBullet.remove(0);
			}
		}
	}
	
	public void mabuFire() {
		Rectangle mabuBound = mabu.getBound();
		mabuBullet.add(new MabuBullet(mabuBound.x, mabuBound.y, mabuBulletSpeed));
	}

	public void draw(Graphics g) {
		mabu.draw(g);
		
		for (Object o : obstacles) {
			o.draw(g);
		}
		
		for (MabuBullet bullet : mabuBullet) {
			bullet.draw(g);
		}
		
		Object obstacle = obstacles.get(0);
		if (obstacle.isOutOfScreen()) {
			mainCharacter.upScore();
			obstacles.remove(0);
			obstacles.add(createObstacle());
		}
	}

	private Object createObstacle() {
		int type = rand.nextInt(2);
		if (type == 0) {
			return new TreeAndStone(mainCharacter, 800, tree.getWidth() - 10, tree.getHeight() - 10, tree);
		} else {
			return new TreeAndStone(mainCharacter, 800, stone.getWidth() - 10, stone.getHeight() - 10, stone);
		}
	}

	public boolean isCollision() {
		for (Object o : obstacles) {
			if (mainCharacter.getBound().intersects(o.getBound())) {
				return true;
			}
		}
		for (MabuBullet bullet: mabuBullet) {
			if (mainCharacter.getBound().intersects(bullet.getBound())) {
				return true;
			}
		}
		return false;
	}

	public void reset() {
		obstacles.clear();
		obstacles.add(createObstacle());
		mabuBullet.clear();
	}
}
