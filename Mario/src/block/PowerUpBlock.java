package block;

import java.awt.Graphics;

import Main.Game;
import Main.Handler;
import Main.Id;
import entity.powerup.Mushroom;
import graphics.Sprite;

public class PowerUpBlock extends Block {
	
	private Sprite powerUp;
	private boolean poppedUp = false;
	private int spriteY = getY();
	//PowerupBLockconstructor
	public PowerUpBlock(int x, int y, int width, int height, boolean solid, Id id, Handler handler,Sprite powerUp) {
		super(x, y, width, height, solid, id, handler);
		this.powerUp = powerUp;
	}
	//PowerUpBlock image render
	public void render(Graphics g) {
		if(!poppedUp) g.drawImage(powerUp.getBufferedImage(),x,spriteY,width,height,null);
		if(!activated) g.drawImage(Game.powerUp.getBufferedImage(),x,y,width,height,null);
		else g.drawImage(Game.usedPowerUp.getBufferedImage(),x,y,width,height,null);
	}
		//"Mushroom" comes up if you jump to the powerUpBlock
	public void tick() {
		if(activated&&!poppedUp) {
			spriteY--;
			if(spriteY<=y-height) {
				handler.addEntity(new Mushroom(x,spriteY,width,height,Id.mushroom,handler,64));
				poppedUp = true;
			}
		}
		
	}
		
	}

