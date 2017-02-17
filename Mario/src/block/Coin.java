package block;

import java.awt.Graphics;

import Main.Game;
import Main.Handler;
import Main.Id;

public class Coin extends Block {
	
	//private int frame = 0;
	//private int frameDelay = 0;
	//private boolean animate = false;

	public Coin(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	
	}

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
