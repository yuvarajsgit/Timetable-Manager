
package dataBase;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection 
{
	
	// Note the "/" before XEPDB1 instead of ":"
    private static final String URL_DB ="jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String USER = "Yuvaraj";
    private static final String PASS = "Yuvi0702";

   
    public static Connection getConnection() throws Exception 
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL_DB, USER, PASS);
    }
}