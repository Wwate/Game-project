package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Database {
	
	public static void execute() {
		JTextField playerName = new JTextField(20);
		JPanel myPanel = new JPanel();
		
		myPanel.add(new JLabel("Player Name: "));
		myPanel.add(playerName);
		
		int result = JOptionPane.showConfirmDialog(null,  myPanel, "Enter a new Player", JOptionPane.OK_CANCEL_OPTION);
		
	}
	// DB connection details
	private static final String URL = "jdbc:mysql://localhost:3306/javadatabase";
	private Connection connection = null;
	private PreparedStatement insertPlayer = null;
	
	public void PlayerQueries()
	{
		try
		{
			connection = DriverManager.getConnection(URL); // Starts a connection to the database
			connection.prepareStatement("SELECT * FROM player"); 
			
			insertPlayer = connection.prepareStatement("INSERT INTO player VALUES (?)");
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.exit(1);
		}
	}

	public void addPlayer(String player)
	{
		try
		{
			
			insertPlayer.setString(1, player); 
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}	
	}
	
}