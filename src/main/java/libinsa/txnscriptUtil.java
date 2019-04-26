package libinsa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

// pour gerer les traces 
import java.io.StringWriter;
import java.io.PrintWriter;

public class txnscriptUtil {

	static String log ;

    public static String initConnection ()
	{
	// URISyntaxException, SQLException 
	String dbUrl ;
	String dbUrl4output = "vide" ;
	Connection connection = null ;
	Statement stmt = null ;
            
		try {
			
			// URI dbUri = new URI(System.getenv("DATABASE_URL"));
	    	dbUrl = System.getenv("JDBC_DATABASE_URL");
			dbUrl4output = dbUrl ;
    		connection = DriverManager.getConnection(dbUrl);
			stmt = connection.createStatement();
			
			/*
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
			connection = DriverManager.getConnection(dbUrl, username, password);
			*/	

			} catch (Exception e) {
			
				StringWriter sw = new StringWriter();
			    log = log + "JDBC_DATABASE_URL :\n" + dbUrl4output + "\n" ;
				e.printStackTrace(new PrintWriter(sw));
				String the_stack = sw.toString() ;
				log = log + "\n" + the_stack ;
			}           

			
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM getLabel() ");
			while (rs.next()) {
				// System.log.println("Read from DB: " + rs.getTimestamp("query_time"));
				log = log + "\n" + "Read from DB: " + rs.getString("query_label") ;
			}
				
			} catch (Exception e) {
			
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				String the_stack = sw.toString() ;
				log = log + "\n" + the_stack ;
			}           


		try {
			// stmt.executeUpdate("INSERT INTO ticks VALUES (now())");			
			ResultSet rs = stmt.executeQuery("INSERT INTO ticks VALUES (now())");
			/*
			while (rs.next()) {
				// System.log.println("Returned from insert: " + rs.getTimestamp("query_time"));
				log = log + "\n" + "Returned from insert " ; // + rs.getString("query_label");
			}
			*/
				
			} catch (Exception e) {
			
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				String the_stack = sw.toString() ;
				log = log + "\n" + the_stack ;
			}           
			
		return log ;

    }

	
}
