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

import libinsa.insaLogger ;

public class txnscriptUtil {

	// static String log = "" ;
	static Connection connection = null ;
	static Statement stmt = null ;
	private static insaLogger logger = insaLogger.getLogger(txnscriptUtil.class);
	
	
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
			
			// Don't use DATABASE_URL environment variable (because it is not the format expected by getConnection)
			// URI dbUri = new URI(System.getenv("DATABASE_URL"));
			// DATABASE_URL : postgres://abcdef:xyz@machine.amazonaws.com:5432/pqrsdatabase

			// DO USE JDBC_DATABASE_URL instead
			// JDBC_DATABASE_URL format : jdbc:postgresql://machine.compute.amazonaws.com:5432/pqrsdatabase?user=abcdef&password=xyz&sslmode=require
	    	        dbUrl = System.getenv("JDBC_DATABASE_URL");
			dbUrl4output = dbUrl ;
    		        connection = DriverManager.getConnection(dbUrl);
			
			stmt = connection.createStatement();
			// https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java#using-the-jdbc_database_url
			// JDBC_DATABASE_URL is CREATED by Heroku and it is not listed in the https://dashboard.heroku.com/apps/jdbc4uemf/settings
			// where only DATABASE_URL is liste
						
			log = log + "JDBC_DATABASE_URL :\n" + dbUrl4output + "\n" ;
			log = log + getFormattedAdminerParameters ( dbUrl4output ) + "\n" ;
			
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

    public static String getFormattedAdminerParameters ( String jdbcUrl )
    {
	    // JDBC_DATABASE_URL format : jdbc:postgresql://machine.compute.amazonaws.com:5432/pqrsdatabase?user=abcdef&password=xyz&sslmode=require
	    String dbServer = "machine.compute.amazonaws.com" ;
	    String dbUser = "abcdef" ;
	    String dbPass = "xyz" ;
	    String dbName = "pqrsdatabase" ;
	    String formattedParameters = "vide" ;
	    
	    
	    
	    // DO USE JDBC_DATABASE_URL instead
	    // JDBC_DATABASE_URL format : jdbc:postgresql://machine.compute.amazonaws.com:5432/pqrsdatabase?user=abcdef&password=xyz&sslmode=require
	    logger.debug ( "getFormattedAdminerParameters - jdbcUrl : " + jdbcUrl ) ;
	    
	    String[] dbUrlParts = jdbcUrl.split("/");
    
	    // String dbServerWithPort = dbUrlParts[1] ; chaine vide entre les deux // !
	    String dbServerWithPort = dbUrlParts[2] ;
	    logger.debug ( "getFormattedAdminerParameters - dbServerWithPort : " + dbServerWithPort ) ;
	    
	    dbServer = dbServerWithPort.split(":")[0] ;
	    logger.debug ( "getFormattedAdminerParameters - dbServer : " + dbServer ) ;
	    
	    String lastDbUrlPart = dbUrlParts[dbUrlParts.length-1] ;
	    // lastDbUrlPart = pqrsdatabase?user=abcdef&password=xyz&sslmode=require
	    logger.debug ( "getFormattedAdminerParameters - lastDbUrlPart : " + lastDbUrlPart ) ;
	    
	    // https://coderanch.com/t/479464/java/java-util-regex-PatternSyntaxException-Dangling : ? est un meta character en regexp
	    dbName = lastDbUrlPart.split("\\?")[0] ;
	    logger.debug ( "getFormattedAdminerParameters - dbName : " + dbName ) ;
	    

	    
	    String[] dbUrlPartsOfParts = lastDbUrlPart.split("&");
	    // dbUrlPartsOfParts = [ pqrsdatabase?user=abcdef , password=xyz , sslmode=require ]


	    String dbUserBulk = dbUrlPartsOfParts[0] ;
	    dbUser = dbUserBulk.split("\\=")[1] ;	    
	    logger.debug ( "getFormattedAdminerParameters - dbUserBulk : " + dbUserBulk ) ;
	    
	    String passwordBulk = dbUrlPartsOfParts[1] ;
	    // passwordBulk = "password=xyz"
	    logger.debug ( "getFormattedAdminerParameters - passwordBulk : " + passwordBulk ) ;
	    
	    String[] passwordParts = passwordBulk.split("=");
	    // passwordParts = [ password , xyz ]
	    dbPass = passwordParts[1] ;
	    

	     
	    
	    formattedParameters =
	    "Système         : PostgreSQL\n" +
	    "Serveur         : " + dbServer + "\n" +
	    "Utilisateur     : " + dbUser + "\n" +
	    "Mot de passe    : " + dbPass + "\n" +
	    "Base de données : " + dbName + "\n" ;
	    
	    return formattedParameters ;
	    
    }
	
    public static String getPassword ( String jdbcUrl )
    {
	    String dbPass ;
		    
	    // DO USE JDBC_DATABASE_URL instead
	    // JDBC_DATABASE_URL format : jdbc:postgresql://machine.compute.amazonaws.com:5432/pqrsdatabase?user=abcdef&password=xyz&sslmode=require

	    String[] dbUrlParts = jdbcUrl.split("/");
	    String lastDbUrlPart = dbUrlParts[dbUrlParts.length-1] ;
	    // lastDbUrlPart = pqrsdatabase?user=abcdef&password=xyz&sslmode=require

	    
	    String[] dbUrlPartsOfParts = lastDbUrlPart.split("&");
	    // dbUrlPartsOfParts = [ pqrsdatabase?user=abcdef , password=xyz , sslmode=require ]
	    String passwordBulk = dbUrlPartsOfParts[1] ;
	    // passwordBulk = "password=xyz"
	    
	    String[] passwordParts = passwordBulk.split("=");
	    // passwordParts = [ password , xyz ]
	    dbPass = passwordParts[1] ;
	    
	    return dbPass ;
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
