package mob;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Main.Handler;
import Main.Id;
import block.Block;
import entity.Entity;
import states.KoopaState;

public class Koopa extends Entity {
	private Random random;
	
	private int shellCount;
	private int frame = 0;
	private int frameDelay = 0;
	private boolean animate = true;

	public Koopa(int x, int y, int width, int height, Id id, Handler handler, int originalWidth) {
		super(x, y, width, height, id, handler, originalWidth);
		
		random = new Random();
		
		int dir = random.nextInt(2);
		
		switch(dir) {
		case 0:
			setVelX(-2);
			facing = 0;
			break;
		case 1:
			setVelX(2);
			facing = 1;
			break;
		}
		
		koopaState = KoopaState.WALKING;
	}

	public void render(Graphics g) {
		if(koopaState==KoopaState.WALKING)g.setColor(Color.GREEN);
		else g.setColor(new Color(0,128,0));
		g.fillRect(getX(),getY(),width,height);
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		if(koopaState==KoopaState.SHELL) {
			setVelX(0);
			
			shellCount++;
			
			if(shellCount>=300) {
				shellCount = 0;
				
				koopaState = KoopaState.WALKING;
			}
			if(koopaState==KoopaState.WALKING||koopaState==KoopaState.SPINNING) {
				shellCount = 0;
				if(velX==0) {
					int dir = random.nextInt(2);
					switch(dir) {
					case 0:
						setVelX(-2);
						facing = 0;
						break;
					case 1:
						setVelX(2);
						facing = 1;
						break;
					}
				}
			}
		}
		
		for(Block bl:handler.block) {
			if(!bl.solid)break;
			if(bl.getId()==Id.wall) {
			
				}
				if(getBoundsBottom().intersects(bl.getBounds())) {
					setVelY(0);
					if(falling) falling = false;

					if(!falling) {
						gravity = 0.8;
						falling = true;
					}
				}

				if(getBoundsLeft().intersects(bl.getBounds())) {
					if(koopaState==KoopaState.SPINNING)setVelX(10);
					else setVelX(2);
					facing = 1;
				}
				if(getBoundsRight().intersects(bl.getBounds())) {
					if(koopaState==KoopaState.SPINNING)setVelX(-10);
					else setVelX(-2);
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
