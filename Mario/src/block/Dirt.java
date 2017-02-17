package block;

import java.awt.Graphics;

import Main.Game;
import Main.Handler;
import Main.Id;

public class Dirt extends Block {
	
	public Dirt(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}

	public void render(Graphics g) {
		g.drawImage(Game.dirt.getBufferedImage(), x,y, width, height, null);
	}
	
	public void tick() {
	
	}

}
