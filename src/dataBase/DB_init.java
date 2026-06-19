package dataBase;
import java.io.*;
import java.sql.*;

public class DB_init
{
	
	public static void init()   //called from the mainFrame 
    {
        try (Connection con = DB_Connection.getConnection()) 
        {
        	//important comments
            //runSQLFile(con, "schema.sql");
            //runSQLFile(con, "dat.sql");
            System.out.println("Database initialized successfully");
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void runSQLFile(Connection con, String fileName) throws Exception
    {
        InputStream is = DB_init.class.getClassLoader().getResourceAsStream(fileName); //will load the sql files from the "resources" src folder
        BufferedReader br = new BufferedReader(new InputStreamReader(is));  //read the SQL file like a normal text file

        StringBuilder sql = new StringBuilder();
        String line;

        Statement stmt = con.createStatement();   //to executes SQL

        while ((line = br.readLine()) != null) 
        {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("--")) continue;  //ignore empty and comment lines

            sql.append(line);        //build SQL command
            
            if (line.endsWith(";"))    //execute when ";" is found = which is the end of one SQL command
            {
                stmt.execute(sql.toString().replace(";", ""));    //execute it 
                sql.setLength(0);     //clear buffer
            }
            //move to next statement
        }
        stmt.close();
    }

    
}
