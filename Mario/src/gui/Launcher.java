package gui;

import java.awt.Color;
import java.awt.Graphics;

import Main.Game;

public class Launcher {
	
	public Button[] buttons; 
	//Buttons and their positioning on the launcher
	public Launcher() {
		buttons = new Button[3];
		
		buttons[0] = new Button(100,100,250,100,"New Game");
		buttons[1] = new Button(200,200,250,100,"Exit Game");
		buttons[2] = new Button(300,300,250,100, "Add Player");
	}
	//LAuncher graphics
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0,0,Game.getFrameWidth()+50,Game.getFrameHeight()+50);
		
		for(int i=0;i<buttons.length;i++) {
			buttons[i].render(g);
		}
	}
	
}
