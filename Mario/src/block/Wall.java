package block;

import java.awt.Color;
import java.awt.Graphics;

import Main.Game;
import Main.Handler;
import Main.Id;

public class Wall extends Block {

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}

	public void render(Graphics g) {
		g.drawImage(Game.grass.getBufferedImage(), x,y, width, height, null);
	}
	
	public void tick() {
	
	}

}
