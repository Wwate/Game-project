package block;

import java.awt.Graphics;

import Main.Game;
import Main.Handler;
import Main.Id;

public class Flag extends Block {

	public Flag(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}

	public void render(Graphics g) {
		g.drawImage(Game.flag[1].getBufferedImage(), getX(), getY(),width,64,null);
		
		g.drawImage(Game.flag[2].getBufferedImage(),getX(),getY()+64,width,64,null);
		g.drawImage(Game.flag[2].getBufferedImage(),getX(),getY()+128,width,64,null);
		//g.drawImage(Game.flag[2].getBufferedImage(),getX(),getY()+192,width,64,null);
		
		g.drawImage(Game.flag[0].getBufferedImage(),getX(), getY() +height-128,width,64,null);
	}
	public void tick() {

	}

}
