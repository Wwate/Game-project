package mob;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Main.Handler;
import Main.Id;
import block.Block;
import entity.Entity;
import states.BossState;

public class TowerBoss extends Entity {
	
	public int jumpTime = 0;
	
	public boolean addJumpTime = false;
	
	private Random random;

	public TowerBoss(int x, int y, int width, int height, Id id, Handler handler, int hp) {
		super(x, y, width, height, id, handler, hp);
		this.hp = hp;
		
		bossState = BossState.IDLE;
		
		random = new Random();
	
	}
	//Tower boss graphics for different states
	public void render(Graphics g) {
		if(bossState==BossState.IDLE||bossState==BossState.SPINNING) g.setColor(Color.GRAY);
		else if(bossState==BossState.RECOVERING) g.setColor(Color.RED);
		else g.setColor(Color.ORANGE);
		
		g.fillRect(x, y, width, height);
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		if(hp<=0) die();
		
		phaseTime++;
		//Different states for the boss
		if((phaseTime>=180&&bossState==BossState.IDLE)||(phaseTime>=600&&bossState!=BossState.SPINNING))chooseState();
		
		if(bossState==BossState.RECOVERING&&phaseTime>=180) {
			bossState = BossState.SPINNING;
			phaseTime = 0;
		}
		
		if(phaseTime>=360&&bossState==BossState.SPINNING) {
			phaseTime = 0;
			bossState = BossState.IDLE;
		}
		
		if(bossState==BossState.IDLE||bossState==BossState.RECOVERING) {
			setVelX(0);
			setVelY(0);
		}
		
		if(bossState==BossState.JUMPING||bossState==BossState.RUNNING) attackable = true;
		else attackable = false;
		
		if(bossState!=BossState.JUMPING) {
			addJumpTime = false;
			jumpTime = 0;
		}
		
		if(addJumpTime) {
			jumpTime++;
			if(jumpTime>=30) {
				addJumpTime = false;
				jumpTime = 0;
			}
			
			if(!jumping&&!falling) {
				jumping = true;
				gravity = 8.0;
			}
		}
		//Boss collision
		for(int i=0;i<handler.block.size();i++) {
			Block bl  = handler.block.get(i);
			if(bl.isSolid()) {
				if(getBoundsTop().intersects(bl.getBounds())) {
					setVelY(0);
					if(jumping) {
				jumping = false;
				gravity = 0.8;
				falling = true;
					}
		
			if(getBoundsTop().intersects(bl.getBounds())) {
				setVelY(0);
				
				}
			}					
			
			if(getBoundsBottom().intersects(bl.getBounds())) {
				setVelY(0);
				if(falling) {
					falling = false;
					addJumpTime = true;
				}
				
			}
			if(getBoundsLeft().intersects(bl.getBounds())) {
				setVelX(0);
				if(bossState==BossState.RUNNING) setVelX(4);
				x = bl.getX()+bl.width;
			}
			if(getBoundsRight().intersects(bl.getBounds())) {
				setVelX(0);
				if(bossState==BossState.RUNNING) setVelX(-4);
				x = bl.getX()-bl.width;
				}
			}
		}
		//Boss tracking mechanism while jumping
		for(int i=0;i<handler.entity.size();i++) {
			Entity en = handler.entity.get(i);
			if(en.getId()==Id.player) {
				if(bossState==BossState.JUMPING) {
					if(jumping||falling) {
						if(getX()>=en.getX()-4&&getX()<=en.getX()+4) setVelX(0);
						else if(en.getX()<getX()) setVelX(-3);
						else if(en.getX()>getX()) setVelX(3);
					} else setVelX(0);
				}else if(bossState==BossState.SPINNING) {
					if(en.getX()<getX()) setVelX(-3);
					else if(en.getX()>getX()) setVelX(3);
				}
			}
		}
		
		if(jumping) {
			gravity-=0.17;
			setVelY((int)-gravity);
			if(gravity<=0.5) {
				jumping = false;
				falling = true;
			}
		}
		if(falling) {
			gravity+=0.17;
			setVelY((int)gravity);
		}

	}
	//Randomly decides if the boss starts either jumping or walking
	public void chooseState() {
		int nextPhase = random.nextInt(2);
		if(nextPhase==0) {
			bossState = BossState.RUNNING;
			int dir = random.nextInt(2);
			if(dir==0) setVelX(-4);
			else setVelX(4);
		}else if(nextPhase==1) {
			bossState = BossState.JUMPING;
			
			jumping = true;
			gravity = 8.0;
		}
		
		phaseTime = 0;
	}

}
