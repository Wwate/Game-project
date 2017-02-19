package mob;

import java.awt.Graphics;

import Main.Handler;
import Main.Id;
import entity.Entity;
import states.BossState;

public class TowerBoss extends Entity {
	
	public int jumpTime = 0;
	
	public boolean addJumpTime = false;

	public TowerBoss(int x, int y, int width, int height, Id id, Handler handler, int hp) {
		super(x, y, width, height, id, handler, hp);
		this.hp = hp;
		
		bossState = BossState.IDLE;
	
	}

	public void render(Graphics g) {
		
	}

	public void tick() {
		
	}

}
