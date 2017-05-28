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