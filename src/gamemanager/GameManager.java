package gamemanager;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameinterface.GameObject;
import gameobjects.GokuBullet;
import gameobjects.MaBu;
import gameobjects.MabuBullet;
import gameobjects.MainCharacter;
import gameobjects.TreeAndStone;
import util.Resource;

public class GameManager {
	public MainCharacter mainCharacter;
	public MaBu mabu;

	public GameSettings settings;

	private List<GameObject> obstacles;
	private ArrayList<MabuBullet> mabuBullets;
	public ArrayList<GokuBullet> gokuBullets;

	private BufferedImage tree;
	private BufferedImage stone;
	private Random rand;

	private long mabuPreviousShoot; // milisecond

	public GameManager(MainCharacter mainCharacter) {
		rand = new Random();
		tree = Resource.getResourceImage("data/tree.png");
		stone = Resource.getResourceImage("data/stone.png");
		settings = new GameSettings();

		this.mainCharacter = mainCharacter;
		mabu = new MaBu(this);

		obstacles = new ArrayList<>();
		obstacles.add(createObstacle());

		mabuBullets = new ArrayList<>();
		gokuBullets = new ArrayList<>();

		mabuPreviousShoot = System.currentTimeMillis();
	}

	public void update() {
		mabu.update();
		bulletUpdate(new ArrayList<>(mabuBullets));
		bulletUpdate(new ArrayList<>(gokuBullets));

		for (GameObject o : obstacles) {
			o.update();
		}

		if ((System.currentTimeMillis() - mabuPreviousShoot) >= settings.mabuShootingPeriod) {
			mabuFire();
			mabuPreviousShoot = System.currentTimeMillis();
		}
	}

	public void bulletUpdate(ArrayList<GameObject> bullets) {
		for (GameObject bullet : bullets) {
			bullet.update();
		}

		if (bullets.size() > 0) {
			GameObject bullet = bullets.get(0);
			if (bullet.isOutOfScreen()) {
				bullets.remove(0);
			}
		}
	}

	public void mabuFire() {
		mabuBullets.add(new MabuBullet(this));
	}

	public void gokuFire() {
		gokuBullets.add(new GokuBullet(this));
	}

	public void draw(Graphics g) {
		mabu.draw(g);

		for (GameObject o : obstacles) {
			o.draw(g);
		}

		for (MabuBullet bullet : mabuBullets) {
			bullet.draw(g);
		}
		for (GokuBullet bullet : gokuBullets) {
			bullet.draw(g);
		}

		GameObject obstacle = obstacles.get(0);
		if (obstacle.isOutOfScreen()) {
			mainCharacter.upScore();
			obstacles.remove(0);
			obstacles.add(createObstacle());
		}
	}

	private GameObject createObstacle() {
		int type = rand.nextInt(2);
		if (type == 0) {
			return new TreeAndStone(mainCharacter, 800, tree.getWidth() - 10, tree.getHeight() - 10, tree);
		} else {
			return new TreeAndStone(mainCharacter, 800, stone.getWidth() - 10, stone.getHeight() - 10, stone);
		}
	}

	public boolean isCollision() {
		for (GameObject o : obstacles) {
			if (mainCharacter.getBound().intersects(o.getBound())) {
				return true;
			}
		}
		for (MabuBullet bullet : mabuBullets) {
			if (mainCharacter.getBound().intersects(bullet.getBound())) {
				return true;
			}
		}
		return false;
	}

	public void reset() {
		obstacles.clear();
		obstacles.add(createObstacle());
		mabuBullets.clear();
		gokuBullets.clear();
	}
}
