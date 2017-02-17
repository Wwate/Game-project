package Main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

import block.Block;
import block.Coin;
import block.Dirt;
import block.Pipe;
import block.PowerUpBlock;
import block.Wall;
import entity.Entity;
import entity.powerup.Mushroom;
import mob.Enemy;
import mob.Player;

public class Handler {
	//Fixed the bug where you get error when player touches the enemy
	public CopyOnWriteArrayList<Entity> entity = new CopyOnWriteArrayList<Entity>();
	//public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Block> block = new LinkedList<Block>();
	//did not help was long shot anyway...
	//public LinkedList<Wall> wall = new LinkedList<Wall>();
	
	public Handler() {
	}
	
	public void render(Graphics g) {
		for(Entity en:entity) {
			en.render(g);
		}
		for(Block bl:block) {
			bl.render(g);
		}
	}
	
	public void tick() {
		for(Entity en:entity) {
			en.tick();
		}
		for(Block bl:block) {
			bl.tick();
		}
		
	}
	
	public void addEntity(Entity en) {
		entity.add(en);
	}
	
	public void removeEntity(Entity en) {
		entity.remove(en);
	}
	
	public void addBlock(Block bl) {
		block.add(bl);
	}
	
	public void removeBlock(Block bl) {
		block.remove(bl);
	}
	//level creation from an image
	public void createLevel(BufferedImage level) {
		int width = level.getWidth();
		int height = level.getHeight();
		
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				int pixel = level.getRGB(x,y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				//Defines the rgb values and what it creates on the map of everything you draw on level.png
				if(red==0&&green==0&&blue==0) addBlock(new Wall(x*64,y*64,64,64,true,Id.wall,this));
				if(red==0&&green==0&&blue==255) addEntity(new Player(x*64,y*64,64,64,Id.player,this,64));
				if(red==255&&green==0&&blue==0) addEntity(new Mushroom(x*64,y*64,64,64,Id.mushroom,this,64));
				if(red==51&&green==25&&blue==0) addBlock(new Dirt(x*64,y*64,64,64,true,Id.dirt,this));
				if(red==0&&green==255&&blue==0) addEntity(new Enemy(x*64,y*64,64,64,Id.enemy,this,64));
				if(red==255&&green==255&&blue==0) addBlock(new PowerUpBlock(x*64,y*64,64,64,true,Id.powerUp,this,Game.mushroom));
				if(red==0&&(green>123&&green<129)&&blue==0) addBlock(new Pipe(x*64,y*64,64,15,true,Id.pipe,this,128-green));
				if(red==255&&green==255&&blue==133) addBlock(new Coin(x*64,y*64,64,64,true,Id.coin,this));
			}
		}
	}


}
