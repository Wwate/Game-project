package block;

import java.awt.Color;
import java.awt.Graphics;

import Main.Handler;
import Main.Id;
import mob.Plant;

public class Pipe extends Block {

	public Pipe(int x, int y, int width, int height, boolean solid, Id id, Handler handler, int facing, boolean plant) {
		super(x, y, width, height, solid, id, handler);
		this.facing = facing;
		
		if(plant)handler.addEntity(new Plant(getX(),getY()-64,getWidth(),64,Id.plant,handler,64));
		
	}

	public void render(Graphics g) {
		g.setColor(new Color(0,128,0));
		g.fillRect(x,y,width,height);
		
	}

	public void tick() {
	
	}

}
