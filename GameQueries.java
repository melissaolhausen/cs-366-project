package MySQLDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class GameQueries {

	static final String databasePrefix ="cs366-2211_olhausenmm13";
    static final String netID ="olhausenmm13"; // Please enter your netId
    static final String hostName ="washington.uww.edu"; //140.146.23.39 or washington.uww.edu
    static final String databaseURL ="jdbc:mariadb://"+hostName+"/"+databasePrefix;
    static final String password="mo0643"; // please enter your own password
	  
	    private Connection connection = null;
        private Statement statement = null;
        private ResultSet resultSet = null;
        
	    public void Connection(){
	  
	      try {
	    	    Class.forName("org.mariadb.jdbc.Driver");
	    	  	System.out.println("databaseURL"+ databaseURL);
	            connection = DriverManager.getConnection(databaseURL, netID, password);
	            System.out.println("Successfully connected to the database");
	         }
	        catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } // end of Connection
	    
	    public void simpleQuery(String sqlQuery) {
	    
	    	try {
	    		statement = connection.createStatement();
	    		resultSet = statement.executeQuery(sqlQuery);

	    		ResultSetMetaData metaData = resultSet.getMetaData();
	    		int columns = metaData.getColumnCount();

	    		for (int i=1; i<= columns; i++) {
	    			System.out.print(metaData.getColumnName(i)+"\t");
	    		}

	    		System.out.println();

	    		while (resultSet.next()) {
	       
	    			for (int i=1; i<= columns; i++) {
	    				System.out.print(resultSet.getObject(i)+"\t\t");
	    			}
	    			System.out.println();
	    		}
	    	}
	    	catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    } // end of simpleQuery method
	    
	    public static void main(String args[]) {
	
	    	GameQueries gameObj = new GameQueries();
	    	gameObj.Connection();
	    	String sqlQuery ="select g.name, p.name, d.name, g.year, g.genre, g.platform, g.critic_score, g.user_score, g.age_rating FROM game AS g, publisher2 as p, developer2 as d WHERE g.name='Fallout' and p.name='Xbox'"; //search for a game
	    	gameObj.simpleQuery(sqlQuery);
			sqlQuery="UPDATE game SET game_ID = Breath of the Wild WHERE critic_score = (critic_count * critic_score + (Java input new critic score))/++critic_count;";   //add critic review
			gameObj.simpleQuery(sqlQuery);
			sqlQuery="UPDATE game SET game_ID = Breath of the Wild WHERE user_score = (user_count * user_score + (Java input new user score))/++user_count;";  //add user review
			gameObj.simpleQuery(sqlQuery);
			sqlQuery="INSERT INTO game(game_ID, age_rating, year, platform, name, genre, pub_ID, critic_score, critic_count, user_score, user_count, dev_ID) VALUES (10000, E10+, 2017, 'Switch', 'Breath of the Wild', 'Adventure', 0014, 97, 109, 8.7, 16687, 0014) ;";  //add a new game
			gameObj.simpleQuery(sqlQuery);
	    }
	    
	
	
}
