package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Main.Game;
import database.Database;

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
	
	//Pop up window for new player
		public static void execute() {
			JTextField playerName = new JTextField(20);
			JPanel myPanel = new JPanel();
			
			myPanel.add(new JLabel("Player Name: "));
			myPanel.add(playerName);
			
			int result = JOptionPane.showConfirmDialog(null,  myPanel, "Enter a new Player", JOptionPane.OK_CANCEL_OPTION);

				if(result == JOptionPane.OK_OPTION) {
					if(playerName.getText().length()==0) {
						System.out.print("You didn't enter a player name!");
						JOptionPane.showMessageDialog(null,"You didn't enter a player name!", "ERROR",JOptionPane.INFORMATION_MESSAGE);
						
					}else {
						Database db = new Database();
						db.playerQueries();
						db.addPlayer(playerName.getText());
						
						
					}
				}
			
		
		}
	}
