import java.sql.*;

public class DBHandler {

	//Write the required business logic as expected in the question description
	public Connection establishConnection() {

		//fill the code
		
		String filePath = "db.properties";
	    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    String DB_URL = "mysql://localhost:3306/rainfallreport";
	    //  Database credentials
       String USER =
       String PASS = 
       Connection conn = null;
       Statement stmt = null;
       try{
          //STEP 2: Register JDBC driver
          Class.forName(JDBC_DRIVER);
    
          //STEP 3: Open a connection
          conn = DriverManager.getConnection(DB_URL, USER, PASS);
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
        return conn;

	}
}
