package database;

import java.sql.*;

public class DB {
	public static Connection conn=null;
	public static PreparedStatement ps=null;
	public static ResultSet rs;
	
	public static Connection connect() throws SQLException {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/grande_surface","root","");
			//System.out.println("La connexion avec la basse java est avec succès");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Erreur de chargement de pilote");
		}
		
		return conn;
	}


}
