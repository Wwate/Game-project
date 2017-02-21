package input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Main.Game;
import Main.Id;
import block.Block;
import entity.Entity;

public class KeyInputs implements KeyListener {

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(Entity en:Game.handler.entity) {
			if(en.getId()==Id.player) {
				if(en.goingDownPipe) return;
				switch(key) {
				case KeyEvent.VK_W:
					for(int i=0;i<Game.handler.block.size();i++) {
						Block bl = Game.handler.block.get(i);
						if(bl.getId()==Id.pipe) {
							if(en.getBoundsTop().intersects(bl.getBounds())) {
								if(!en.goingDownPipe) en.goingDownPipe=true;
							}
						}
					}
					if(!en.jumping) {
						en.jumping = true;
						en.gravity = 10.0;
					}
					break;
					//Press S on top of a pipe to go down
				case KeyEvent.VK_S:
					for(int q=0;q<Game.handler.block.size();q++) {
						Block bl = Game.handler.block.get(q);
						if(bl.getId()==Id.pipe) {
							if(en.getBoundsBottom().intersects(bl.getBounds())) {
								if(!en.goingDownPipe) en.goingDownPipe=true;
							}
						}
					}
				break;
				case KeyEvent.VK_A:
					en.setVelX(-5);
					en.facing=0;
					break;
				case KeyEvent.VK_D:
					en.setVelX(5);
					en.facing = 1;
					break;
				}
			}

		}
			}


	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(Entity en:Game.handler.entity) {
			if(en.getId()==Id.player) {
			switch(key) {
			case KeyEvent.VK_W:
				en.setVelY(0);
				break;
			case KeyEvent.VK_S:
				en.setVelY(0);
				break;
			case KeyEvent.VK_A:
				en.setVelX(0);
				break;
			case KeyEvent.VK_D:
				en.setVelX(0);
				break;
			}
		}

	}
	}

	public void keyTyped(KeyEvent arg0) {

	}

}
