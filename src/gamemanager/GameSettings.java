package gamemanager;

/** A class to store all settings for game */
public class GameSettings {

	public final int SCREEN_WIDTH = 800;
	public final int SCREEN_HEIGHT = 235;

	// Goku stats
	public float gokuSpeedX = 4.f;

	// MaBu stats
	public float mabuPosX = 650.f;
	public float mabuPosY = 20.f;
	public float mabuSpeedY = 0.2f;
	public int mabuDirectionY = 1;
	public int mabuHP = 3;

	// Goku bullet stats
	public float gokuBulletSpeed = 10.f;

	// MaBu bullet stats
	public int mabuShootingPeriod = 3000; // milisecond
	public float mabuBulletSpeed = 5.f;

	// How the game difficult increase
	public float scale = 1.1f;

	public GameSettings() {

	}

	/** Increase speed settings */
	public void increaseSpeed() {
		gokuSpeedX *= scale;
		mabuBulletSpeed *= scale;
	}
}
