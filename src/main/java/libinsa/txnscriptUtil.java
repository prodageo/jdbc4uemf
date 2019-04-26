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

// pour manage log of the exception
import java.io.StringWriter;
import java.io.PrintWriter;

// For more information about this class, PLEASE DO VISIT :
// http://prodageo.insa-rouen.fr/wiki/pmwiki.php?n=ProdageoLib.TxnscriptUtil

public class txnscriptUtil {

	// static String log = "" ;
	static Connection connection = null ;
	static Statement stmt = null ;

	
	
	
    public static String test ()
	{
		String log = "" ;
		

		String log1 = txnscriptUtil.initConnection () ;		
		
		String log2 = txnscriptUtil.selectExample () ;		

		String log3 = txnscriptUtil.insertExample () ;		
		
		log = log1 + log2 + log3 ;

		return log ;
	}	
	
	// initialisation de la connection et d'un statement
	// avant utilisation des fonctions comme selectExample ou insertExample
    public static String initConnection ()
	{
		String log = "" ;
		

	// URISyntaxException, SQLException 
	String dbUrl ;
	String dbUrl4output = "vide" ;
            
		try {
			
			// URI dbUri = new URI(System.getenv("DATABASE_URL"));
	    	dbUrl = System.getenv("JDBC_DATABASE_URL");
			dbUrl4output = dbUrl ;
    		connection = DriverManager.getConnection(dbUrl);
			stmt = connection.createStatement();
			log = log + "JDBC_DATABASE_URL :\n" + dbUrl4output + "\n" ;
			
			/*
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
			connection = DriverManager.getConnection(dbUrl, username, password);
			*/	

		} catch (Exception e) {
				log = log + exceptionNiceDisplay(e) ;
		}     
		return log ;
	}			

	// procedure stockee qui renvoie une liste d'enregistrements (SELECT)			
    public static String selectExample ()
	{
		String log = "" ;
		
		if ( stmt == null ) { initConnection(); } ;
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM getLabel() ;");
			while (rs.next()) {
				// System.log.println("Read from DB: " + rs.getTimestamp("query_time"));
				log = log + "\n" + "Read from DB: " + rs.getString("query_label") ;
		}
				
		} catch (Exception e) {
				log = log + exceptionNiceDisplay(e) ;
		} 
		return log ;			
	}

	// procedure stockee qui créer un enregistrement (INSERT)	
    public static String insertExample ()
	{
		String log = "" ;
		
		if ( stmt == null ) { initConnection() ; } ;
		
		try {
			int nbRecordInserted = stmt.executeUpdate("INSERT INTO test10 (label10) VALUES ('Minnie') ;", Statement.RETURN_GENERATED_KEYS);
			log = log + "\n" + "Nb record inserted by previous statement = " + nbRecordInserted + "\n" ;
			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
  				int id_inserted = rs.getInt(1); // Get automatically generated key value
  				log = log + "\n" + "Automatically generated key value = " + id_inserted  + "\n" ;
			}
		} catch (Exception e) {
				log = log + exceptionNiceDisplay(e) ;
		}           
			
		return log ;

    }

	// pour manage log of the exception
    public static String exceptionNiceDisplay (Exception e)
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String the_stack = sw.toString() ;
		return "\n" + the_stack + "\n" ;
	}
	
	
}
