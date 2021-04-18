import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class gameSalesAPI {

 	static final String databasePrefix ="cs366-2211_paprockisj04";
    static final String netID ="paprockisj04";
    static final String hostName ="washington.uww.edu";
    static final String databaseURL ="jdbc:mariadb://"+hostName+"/"+databasePrefix;
    static final String password="sp3619";

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
    }
    
    public static void main(String args[]) {

        gameSalesAPI demoObj = new gameSalesAPI();
        demoObj.Connection();
        //String sqlQuery = "";
        //demoObj.simpleQuery(sqlQuery);
    }

}