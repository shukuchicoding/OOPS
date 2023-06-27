package gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gameinterface.Enemy;
import util.Resource;

public class EnemyManager {
	private BufferedImage tree;
	private BufferedImage stone;
	private BufferedImage mabu;
	private Random rand;

	private List<Enemy> enemies;
	private MainCharacter mainCharacter;

	public EnemyManager(MainCharacter mainCharacter) {
		rand = new Random();
		tree = Resource.getResourceImage("data/tree.png");
		stone = Resource.getResourceImage("data/stone.png");
		mabu = Resource.getResourceImage("data/Buu_0.png");
		enemies = new ArrayList<>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());
		enemies.add(new MaBu(mabu, 650.f, 20.f, 0.2f));
	}

	public void update() {
		for (Enemy e : enemies) {
			e.update();
		}
	}

	public void draw(Graphics g) {
		for (Enemy e : enemies) {
			e.draw(g);
		}
		Enemy enemy = enemies.get(0);
		if (enemy.isOutOfScreen()) {
			mainCharacter.upScore();
			enemies.remove(0);
			enemies.add(0, createEnemy());
//			System.out.println(enemies.size());
		}
	}

	private Enemy createEnemy() {
		int type = rand.nextInt(2);
		if (type == 0) {
			return new TreeAndStone(mainCharacter, 800, tree.getWidth() - 10, tree.getHeight() - 10, tree);
		} else {
			return new TreeAndStone(mainCharacter, 800, stone.getWidth() - 10, stone.getHeight() - 10, stone);
		}
	}

	public boolean isCollision() {
		for (Enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}

	public void reset() {
		enemies.clear();
		enemies.add(createEnemy());
		enemies.add(new MaBu(mabu, 650.f, 20.f, 0.2f));
	}
}
