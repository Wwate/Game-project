package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import Main.Game;
import database.Database;

public class Button {
	
	public int x,y;
	public int width,height;
	
	public String label;

	public Button(int x, int y, int width, int height, String label) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
	}
	//Button font and color
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Century Gothic",Font.ITALIC,40));
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		
		//Aligns text in the center of the button 
		FontMetrics fm = g.getFontMetrics();
		int stringX = (getWidth() - fm.stringWidth(getLabel())) /2;
		int stringY = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(getLabel(),getX() + stringX, getY() + stringY);
	}
	//Trigger event for different buttons
	public void triggerEvent() {
		if(getLabel().toLowerCase().contains("new")) Game.playing = true;
		else if(getLabel().toLowerCase().contains("exit")) System.exit(0);
		else if(getLabel().toLowerCase().contains("player")) Launcher.execute();
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
