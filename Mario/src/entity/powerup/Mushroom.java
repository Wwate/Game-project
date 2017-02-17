package entity.powerup;

import java.awt.Graphics;
import java.util.Random;

import Main.Game;
import Main.Handler;
import Main.Id;
import block.Block;
import entity.Entity;

public class Mushroom extends Entity {
	
	private Random random = new Random();
	//Mushroom constructor
	public Mushroom(int x, int y, int width, int height, Id id, Handler handler, int originalWidth) {
		super(x, y, width, height, id, handler, originalWidth);
		
		int dir = random.nextInt(2);
		
		switch(dir) {
		case 0:
			setVelX(-1);
			break;
		case 1:
			setVelX(1);
			break;
		}
		
	}
	//Mushroom image render
	public void render(Graphics g) {
		g.drawImage(Game.mushroom.getBufferedImage(),x,y,width,height,null);
	}
	public void tick() {
	x+=velX;
	y+=velY;
	//Mushroom and wall interaction
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
			}
			if(getBoundsRight().intersects(bl.getBounds())) {
				setVelX(-1);
			}
		}
	//Mushroom gravity
	if(falling) {
		gravity+=0.1;
		setVelY((int)gravity);
	}
	}
}
