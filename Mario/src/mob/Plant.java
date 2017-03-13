package mob;

import java.awt.Color;
import java.awt.Graphics;

import Main.Handler;
import Main.Id;
import entity.Entity;

public class Plant extends Entity {
	
	private int wait;
	private int pixelsTravelled;
	
	private boolean moving;
	private boolean insidePipe;

	public Plant(int x, int y, int width, int height, Id id, Handler handler, int originalWidth) {
		super(x, y, width, height, id, handler, originalWidth);
		
		moving = false;
		insidePipe = true;

	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getX(),getY(),getWidth(),getHeight());

	}

	public void tick() {
		y+=velY;
		
		if(!moving)wait++;
		
		if(wait>=180) {
			if(insidePipe) insidePipe = false;
			else insidePipe = true;
			
			moving = true;
			wait = 0;
		}
		if(moving) {
			if(insidePipe) setVelY(-3);
			else setVelY(3);
			
			pixelsTravelled+=velY;
			
			if(pixelsTravelled>=getHeight()||pixelsTravelled<=-getHeight()) {
				pixelsTravelled = 0;
				moving = false;
				
				setVelY(0);
			}
		}
	}

}
