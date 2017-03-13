
package entity.powerup;

import java.awt.Graphics;
import java.util.Random;

import Main.Game;
import Main.Handler;
import Main.Id;
import block.Block;
import entity.Entity;

public class PowerStar extends Entity {
	
	private Random random;

	public PowerStar(int x, int y, int width, int height, Id id, Handler handler, int originalWidth) {
		super(x, y, width, height, id, handler, originalWidth);
		random = new Random();
		int dir = random.nextInt(2);
		
		switch(dir) {
		case 0:
			setVelX(-4);
			break;
		case 1:
			setVelX(4);
			break;
		}
		
		falling = true;
		gravity = 0.17;

	}

	public void render(Graphics g) {
		g.drawImage(Game.star.getBufferedImage(),getX(),getY(),getWidth(),getHeight(),null);
	}

	public void tick() {
		x+=velX;
		y+=velY;
		for(int i = 0;i<handler.block.size();i++) {
			Block bl = handler.block.get(i);
			
			if(bl.isSolid()) {
				if(getBoundsBottom().intersects(bl.getBounds())) {
					falling = false;
					jumping = true;
					gravity = 8.0;
				}
				
				if(getBoundsLeft().intersects(bl.getBounds())) setVelX(4);
				if(getBoundsRight().intersects(bl.getBounds())) setVelX(-4);
			}
		}
		if(jumping) {
			gravity-=0.17;
			setVelY((int)-gravity);
			if(gravity<=0.5) {
				jumping = false;
				falling = true;
			}
		}
		
		if(falling) {
			gravity+=0.17;
			setVelY((int)gravity);
		}
	}

}
