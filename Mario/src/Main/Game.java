package Main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import block.Wall;
import entity.Entity;
import graphics.Sprite;
import graphics.SpriteSheet;
import input.KeyInputs;
import mob.Player;

public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH/14*10;
	public static final int SCALE = 4;
	public static final String TITLE = "Mario";
	
	private Thread thread;
	private boolean running = false;
	private BufferedImage image;
	
	public static int coins = 0;
	
	public static Handler handler;
	public static SpriteSheet sheet;
	public static Camera cam;
	//Coin animation Sprite
	//public static Sprite coin[] = new Sprite[3];
	public static Sprite coin;
	public static Sprite grass;
	public static Sprite powerUp;
	public static Sprite usedPowerUp;
	public static Sprite player[] = new Sprite[12];
	public static Sprite mushroom;
	public static Sprite dirt;
	public static Sprite enemy[] = new Sprite[10];
	
	public Game() {
		Dimension Size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
		setPreferredSize(Size);
		setMaximumSize(Size);
		setMinimumSize(Size);
	}
	
	private void init() {
		player = new Sprite[12];
		handler = new Handler();
		sheet = new SpriteSheet("/spritesheet.png");
		cam = new Camera();
		mushroom = new Sprite(sheet,3,1);
		coin = new Sprite(sheet,8,1);
		enemy = new Sprite[10];
		
		addKeyListener(new KeyInputs());
		//Grass graphics from spritesheet
		grass = new Sprite(sheet,5,1);
		powerUp = new Sprite(sheet,6,1);
		usedPowerUp = new Sprite(sheet,7,1);
		//Dirt graphics from spritesheet
		dirt = new Sprite(sheet,4,1);
		//Player graphics from spritesheet
		for(int i=0;i<player.length; i++) {
			player[i] = new Sprite(sheet,i+1,15);
		}
		
		for(int i=0;i<enemy.length; i++) {
			enemy[i] = new Sprite(sheet,i+1,16);
		}
		
		//for(int i=0;i<coin.length;i++) {
			//coin[i] = new Sprite(sheet,i+8,1);
		//}
		try {
			image = ImageIO.read(getClass().getResource("/level.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		handler.createLevel(image);
		
	}
	
	private synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	//Don't touch this method if you're not sure what you're doing
	public void run() {
		init();
		requestFocus();
		long lostTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0/60.0;
		int frames = 0;
		int ticks = 0;
		while(running) {
			long now = System.nanoTime();
			delta+=(now-lostTime)/ns;
			lostTime = now;
			while(delta>=1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				System.out.println(frames + " Frames Per Second " + ticks + " Ticks Per Second");
				frames = 0;
				ticks = 0;
			}
		
		}
		stop();
		
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		//Game background color
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(133, 255, 248));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//Coin counter
		g.drawImage(Game.coin.getBufferedImage(),20,20,75,75,null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier",Font.BOLD,50));
		g.drawString("x" + coins, 135, 95);
		
		g.translate(cam.getX(), cam.getY());
		handler.render(g);
		g.dispose();
		bs.show();
	}
	
	public int getFrameWidth() {
		return WIDTH*SCALE;
	}
	
	public int getFrameHeight() {
		return HEIGHT*SCALE;
	}
	
	public void tick() {
		handler.tick();
		
		for(Entity e:handler.entity) {
			if(e.getId()==Id.player) {
				if(!e.goingDownPipe) cam.tick(e);
			}
		}
	}
	
	public static void main(String[] args) {
	
		JFrame frame = new JFrame(TITLE);
		Game game = new Game();
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
	}
	
}
