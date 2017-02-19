package mob;

//import java.awt.Color;
import java.awt.Graphics;

import Main.Game;
import Main.Handler;
import Main.Id;
import block.Block;
import entity.Entity;
import states.PlayerState;

public class Player extends Entity {
	
	private PlayerState state;
	
	private int pixelsTravelled = 0;
	private int frame = 0;
	private int frameDelay = 0;
	private boolean animate = false;
	//private boolean animate = false;
	
	//Player constructor
	public Player(int x, int y, int width, int height, Id id, Handler handler, int originalWidth) {
		super(x, y, width, height, id, handler, originalWidth);
		
		state = PlayerState.SMALL;
	}

	//Player image render
	public void render(Graphics g) {
		if(facing == 0) {
			g.drawImage(Game.player[frame+6].getBufferedImage(), x,y, width, height, null);
		} else if(facing == 1) {
			g.drawImage(Game.player[frame].getBufferedImage(), x,y, width, height, null);
		}
		
	}
	//Player collision detection
	public void tick() {
		x+=velX;
		y+=velY;
		
		if(y<=0) y=0;
		//if(y+height>771) y = 771;
		//if(y+height>771) y = 771 - height;
		if(velX!=0) animate = true;
		else animate = false;
		for(int i=0;i<handler.block.size();i++) {
				Block bl  = handler.block.get(i);
				if(bl.isSolid()&&!goingDownPipe) {
					if(getBoundsTop().intersects(bl.getBounds())) {
						setVelY(0);
						if(jumping&&!goingDownPipe) {
					jumping = false;
					gravity = 0.8;
					falling = true;
						}
						
			//Commented the lines below to remove bug where the player teleports when colliding with powerUpBlock
			//if(!bl.isSolid()&&!goingDownPipe) {
			//if(bl.getId()==Id.wall) {	
			
				if(getBoundsTop().intersects(bl.getBounds())) {
					setVelY(0);
					
					
					}
					//collision with powerUpBlock 
					if(bl.getId()==Id.powerUp) {
						if(getBoundsTop().intersects(bl.getBounds())) {
							bl.activated=true;
						}
					}
				}					
			
				
				if(getBoundsBottom().intersects(bl.getBounds())) {
					setVelY(0);
					if(falling) falling = false;
					
					if(!falling&&!jumping) {
						gravity = 0.8;
						falling = true;
					}
				}
				if(getBoundsLeft().intersects(bl.getBounds())) {
					setVelX(0);
					x = bl.getX()+bl.width;
				}
				if(getBoundsRight().intersects(bl.getBounds())) {
					setVelX(0);
					//collision fix by Aleksi
					if (getWidth() > getOriginalWidth()) {
						x = bl.getX()-bl.width * 2;
					}
					
					else {
						x = bl.getX()-bl.width;
					}
					
					
				}
				
			}
		}
		
		for(int i=0;i<handler.entity.size();i++) {
			Entity en = handler.entity.get(i);
			//Player-mushroom interaction
			if(en.getId()==Id.mushroom) {
				if(getBounds().intersects(en.getBounds())) {
					int tpX = getX();
					int tpY = getY();
					width+=(width/3);
					height+=(height/3);
					//width*=2;
					//height*=2;
					//setWidth(2);
					//setHeigth(2);
					//fixed player offset when erecting to bigger size
					setX(tpX-getWidth()-(getWidth() / -2));
					setY(tpY-getHeight()-(getHeight() / -2));
					if(state==PlayerState.SMALL)state = PlayerState.BIG;
					en.die();
				}
				//collision with enemy
			} else if(en.getId()==Id.enemy) {
				if(getBoundsBottom().intersects(en.getBoundsTop())) {
					en.die();
					//When you have powerup activated you turn small if you hit the enemy
				} else if(getBounds().intersects(en.getBounds())) {
					if(state==PlayerState.BIG) {
						state = PlayerState.SMALL;
						width/=3;
						height/=3;
						x+=width;
						y+=height;
				}
					//If you hit the enemy while small you die
				else if(state==PlayerState.SMALL) {
					die();
				}
			}
		}else if(en.getId()==Id.coin) {
			if(getBounds().intersects(en.getBounds())&&en.getId()==Id.coin) {
				Game.coins++;
				en.die();
			}
		}
		}
		//Gravity loop
		if(jumping&&!goingDownPipe) {
			gravity-=0.17;
			setVelY((int)-gravity);
			if(gravity<=0.5) {
				jumping = false;
				falling = true;
			}
		}
		if(falling&&!goingDownPipe) {
			gravity+=0.17;
			setVelY((int)gravity);
		}
		
		if(animate) {
			frameDelay++;
			if(frameDelay>=10) {
				frame++;
				if(frame>=5) {
					frame = 0;
				}
				frameDelay = 0;
		}
	
		}
		//This is for going up or down in pipes
		//Not working at the moment
		if(goingDownPipe) {
			for(int i=0;i<Game.handler.block.size();i++) {
				Block bl = Game.handler.block.get(i);
				if(bl.getId()==Id.pipe) {
					if(getBounds().intersects(bl.getBounds())) {
						switch(bl.facing) {
						case 0:
							setVelY(-5);
							setVelX(0);
							pixelsTravelled+=-velY;
							break;
						case 2:
							setVelY(5);
							setVelX(0);
							pixelsTravelled+=velY;
							break;
						}
						if(pixelsTravelled>=bl.height+height) {
							goingDownPipe=false;
							pixelsTravelled=0;
						}
					}

				}
			}
		}
	}

}


