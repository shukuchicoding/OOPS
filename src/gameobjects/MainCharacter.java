package gameobjects;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

import util.Animation;
import util.Resource;

public class MainCharacter {
	public static final int LAND_POSY = 120; // updated tu 130 -> 120
	public static final float GRAVITY = 0.4f;
	
	private static final int NORMAL_RUN = 0;
	private static final int JUMPING = 1;
	private static final int DOWN_RUN = 2;
	private static final int ATTACK = 3;
	private static final int DEATH = 4;
	private static final int START = 5; // update them trang thai start
	
	private float posX;
	private float posY;
	private float speedX;
	private float speedY;
	private Rectangle rectBound;
	
	public int score = 0;
	
	private int state = START;
	
	private BufferedImage startImage;
	private Animation normalRunAnim;
	private BufferedImage jumping;
	private Animation downRunAnim;
	private BufferedImage deathImage;
	private Animation attackAnim;
	
	private AudioClip jumpSound;
	private AudioClip deadSound;
	private AudioClip scoreUpSound;
	
	public MainCharacter() {
		posX = 50;
		posY = LAND_POSY;
		rectBound = new Rectangle();
		startImage = Resource.getResourceImage("data/start_character.png");
		normalRunAnim = new Animation(80);
		normalRunAnim.addFrame(Resource.getResourceImage("data/normal_run_1.png"));
		normalRunAnim.addFrame(Resource.getResourceImage("data/normal_run_2.png"));
		jumping = Resource.getResourceImage("data/jumping.png");

		downRunAnim = new Animation(80);
		downRunAnim.addFrame(Resource.getResourceImage("data/down_run.png"));

		downRunAnim = new Animation(90);
		downRunAnim.addFrame(Resource.getResourceImage("data/down_run.png"));
		//downRunAnim.addFrame(Resource.getResourceImage("data/main-character6.png"));

		deathImage = Resource.getResourceImage("data/death_character.png");
		attackAnim = new Animation(80);
		attackAnim.addFrame(Resource.getResourceImage("data/main-character7.png"));
		attackAnim.addFrame(Resource.getResourceImage("data/main-character8.png"));
		try {
			jumpSound = Applet.newAudioClip(new URL("file","","data/jump.wav"));
			deadSound = Applet.newAudioClip(new URL("file","","data/dead.wav"));
			scoreUpSound = Applet.newAudioClip(new URL("file","","data/scoreup.wav"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}
	
	public void draw(Graphics g) {
		switch(state) {
			case NORMAL_RUN:
				g.drawImage(normalRunAnim.getFrame(), (int)posX, (int)posY, null);
				break;
			case JUMPING:
				g.drawImage(jumping, (int)posX, (int)posY, null);
				break;
			case DOWN_RUN:
				g.drawImage(downRunAnim.getFrame(), (int)posX, (int)(posY + 20), null);
				break;
			case DEATH:
				g.drawImage(deathImage, (int)posX, (int)posY, null);
				break;
			case START: 
				g.drawImage(startImage, (int)posX, (int)posY-5, null);
				break;
			case ATTACK:
				g.drawImage(attackAnim.getFrame(), (int)posX, (int)posY, null);
				break;
		}
		Rectangle bound = getBound();
//		g.setColor(Color.RED);
		g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}
	
	public void update() {
		normalRunAnim.updateFrame();
		downRunAnim.updateFrame();
		if (posY >= LAND_POSY) {
			posY = LAND_POSY;
			if (state != DOWN_RUN) {
				state = NORMAL_RUN;
			}
		}
		else {
			speedY += GRAVITY;
			posY += speedY;
		}
//		System.out.println("posY="+posY);
	}
	
	public void jump () {
		if (posY >= LAND_POSY) {
			if (jumpSound != null) {
				jumpSound.play();
			}
			speedY = -9f;
			posY += speedY;
			state = JUMPING;
		}
	}
	
	public void down(boolean isDown) {
		if (state == JUMPING) {
			return;
		}
		else if (isDown) {
			state = DOWN_RUN;
		}
		else {
			state = NORMAL_RUN;
		}
	}
	
	public void attack(boolean isAttack) {
		if (state == ATTACK) {
			return;
		}
		else if (isAttack) {
			state = ATTACK;
		}
		else {
			state = NORMAL_RUN;
		}
	}
	
	
	public Rectangle getBound() {
		rectBound = new Rectangle();
		if (state == DOWN_RUN) {
			rectBound.x = (int)posX + 5;
			rectBound.y = (int)posY + 20;
			rectBound.width = downRunAnim.getFrame().getWidth() - 10;
			rectBound.height = downRunAnim.getFrame().getHeight();
		}
		else {
			rectBound.x = (int)posX + 5;
			rectBound.y = (int)posY;
			rectBound.width = normalRunAnim.getFrame().getWidth() - 10;
			rectBound.height = normalRunAnim.getFrame().getHeight();
		}
		return rectBound;
	}
	
	public void dead(boolean isDeath) {
		if (isDeath) {
			state = DEATH;
		}
		else {
			state = NORMAL_RUN;
		}
	}
	
	public void reset() {
		posY = LAND_POSY;
		score = 0;
	}
	
	public void playDeadSound() {
		deadSound.play();
	}
	
	public void upScore() {
		score += 20;
		if (score % 100 == 0) {
			scoreUpSound.play();
		}
	}
}
