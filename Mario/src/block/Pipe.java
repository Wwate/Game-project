package block;

import java.awt.Color;
import java.awt.Graphics;

import Main.Handler;
import Main.Id;

public class Pipe extends Block {

	public Pipe(int x, int y, int width, int height, boolean solid, Id id, Handler handler, int facing) {
		super(x, y, width, height, solid, id, handler);
		this.facing = facing;
		
	}

	public void render(Graphics g) {
		g.setColor(new Color(128,128,128));
		g.fillRect(x,y,width,height);
		
	}

	public void tick() {
	
	}

}