package entity;

import java.awt.Graphics;

import Main.Game;
import Main.Handler;
import Main.Id;
import block.Block;

public class Coin extends Entity {
	
	public Coin(int x, int y, int width, int height, boolean b, Id coin, Handler handler) {
		super(x, y, width, height, b, coin, handler);
	}

	//private int frame = 0;
	//private int frameDelay = 0;
	//private boolean animate = false;



	public void render(Graphics g) {
		g.drawImage(Game.coin.getBufferedImage(), x,y,width,height,null);
		
		//Coin animation, fix when you have the time
		
		//if(facing == 0) {
			//g.drawImage(Game.coin[frame+3].getBufferedImage(), x,y, width, height, null);
		//} else if(facing == 1) {
			//g.drawImage(Game.player[frame].getBufferedImage(), x,y, width, height, null);
		//}
		
	}
	
	public void tick() {
	
		
	}

}
