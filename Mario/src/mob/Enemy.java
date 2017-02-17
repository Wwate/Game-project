package mob;

import java.awt.Graphics;
import java.util.Random;

import Main.Game;
import Main.Handler;
import Main.Id;
import block.Block;
import entity.Entity;

public class Enemy extends Entity {
	
	private Random random = new Random();
	
	private int frame = 0;
	private int frameDelay = 0;
	private boolean animate = true;

	public Enemy(int x, int y, int width, int height, Id id, Handler handler, int originalWidth) {
		super(x, y, width, height, id, handler, originalWidth);
		
		int dir = random.nextInt(2);
		
		switch(dir) {
		case 0:
			setVelX(-1);
			facing = 0;
			break;
		case 1:
			setVelX(1);
			facing = 1;
			break;
		}
		
	}
	
	public void render(Graphics g) {
		if(facing == 0) {
			g.drawImage(Game.enemy[frame+5].getBufferedImage(), x,y, width, height, null);
		} else if(facing == 1) {
			g.drawImage(Game.enemy[frame].getBufferedImage(), x,y, width, height, null);
		}
		
	}
	
	
	public void tick() {
		x+=velX;
		y+=velY;
		
		for(Block bl:handler.block) {
			if(!bl.solid)break;
			if(bl.getId()==Id.wall) {
			
				}
				if(getBoundsBottom().intersects(bl.getBounds())) {
					setVelY(0);
					if(falling) falling = false;
					//minor fix did not break nor fix
					if(!falling) {
						gravity = 0.8;
						falling = true;
					}
				}
				//if(getBoundsBottom().intersects(bl.getBounds())) {
					//setVelY(0);
					//if(falling) falling = false;
				//} else {
					//if(!falling) {
						//gravity = 0.8;
						//falling = true;
					//}
				//}
				if(getBoundsLeft().intersects(bl.getBounds())) {
					setVelX(1);
					facing = 1;
				}
				if(getBoundsRight().intersects(bl.getBounds())) {
					setVelX(-1);
					facing = 0;
				}
			}
		
		if(falling) {
			gravity+=0.1;
			setVelY((int)gravity);
		}
		if(animate) {
			frameDelay++;
			if(frameDelay>=3) {
				frame++;
				if(frame>=5) {
					frame = 0;
				}
				frameDelay = 0;
		}
	
		}
		}
	
	}


