package gui;

import java.awt.Color;
import java.awt.Graphics;

import Main.Game;

public class Launcher {
	
	public Button[] buttons; 
	
	public Launcher() {
		buttons = new Button[2];
		
		buttons[0] = new Button(100,100,250,100,"New Game");
		buttons[1] = new Button(200,200,250,100,"Exit Game");
	}
	
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0,0,Game.getFrameWidth()+50,Game.getFrameHeight()+50);
		
		for(int i=0;i<buttons.length;i++) {
			buttons[i].render(g);
		}
	}
	
}
