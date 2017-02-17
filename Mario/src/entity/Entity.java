package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import Main.Handler;
import Main.Id;

public abstract class Entity {
	
	public int x, y;
	public int width, height;
	public int originalWidth;
	public int facing = 0; // 0 left, 1 right
	
	public boolean goingDownPipe = false;
	public boolean jumping = false;
	public boolean falling = true;
	public int velX, velY;
	public Id id;
	public double gravity = 0.0;
	public Handler handler;
	
	public Entity(int x, int y, int width, int height, Id id, Handler handler, int originalWidth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		this.handler = handler;
		this.originalWidth = originalWidth;
		
		
		
	}
	public abstract void render(Graphics g);
		
	
	public abstract void tick();
	
	//death
	public void die() {
		handler.removeEntity(this);
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Id getId() {
		return id;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}	
	public void setWidth(int modifier) {
		width *= modifier; 
	}
	
	public void setHeigth(int modifier) {
		height *= modifier; 
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getOriginalWidth() {
		return originalWidth;
	}
	
	public int getHeight() {
		return height;
	}
	
	//Entity collision detection
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle(getX()+10,getY(), getWidth()-20, 5);
	}
	
	public Rectangle getBoundsBottom() {
		//original line: return new Rectangle(getX()+10,getY()+height-5, width-20, 5);
		return new Rectangle(getX(),getY()+getHeight()-5, getWidth()-20, 5);	
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle(getX(),getY()+10,5,getHeight()-20);
	}
	
	public Rectangle getBoundsRight() {
			return new Rectangle(getX()+getWidth()-5,getY()+10,5,getHeight()-20);
			//return new Rectangle(getX()+getWidth()-5,getY()+10,5,getHeight()-20);
	}

}
