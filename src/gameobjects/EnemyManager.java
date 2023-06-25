package gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Resource;

public class EnemyManager {
	private BufferedImage tree;
	private BufferedImage stone;
	private Random rand;
	
	private List<Enemy> enemies;
	private MainCharacter mainCharacter;
	
	public EnemyManager(MainCharacter mainCharacter) {
		rand = new Random();
		tree = Resource.getResourceImage("data/tree.png");
		stone = Resource.getResourceImage("data/stone.png");
		enemies = new ArrayList<>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());
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
		if(enemy.isOutOfScreen()) {
			mainCharacter.upScore();
			enemies.clear();
			enemies.add(createEnemy());
		}
	}
	
	private Enemy createEnemy() {
		int type = rand.nextInt(2);
		if (type == 0) {
			return new TreeAndStone(mainCharacter, 800, tree.getWidth() - 10, tree.getHeight() - 10, tree);
		}
		else {
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
	
	public void reset () {
		enemies.clear();
		enemies.add(createEnemy());
	}
}
