package servlet;

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

@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
			
	{

	// URISyntaxException, SQLException 
	StringWriter sw = new StringWriter();
            
        ServletOutputStream out = resp.getOutputStream();
	String dbUrl ;
	String dbUrl4output = "vide" ;
            
		try {
			
			// URI dbUri = new URI(System.getenv("DATABASE_URL"));
	    		dbUrl = System.getenv("JDBC_DATABASE_URL");
			dbUrl4output = dbUrl ;
    			Connection connection = DriverManager.getConnection(dbUrl);

			/*
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
			Connection connection = DriverManager.getConnection(dbUrl, username, password);
			*/	
			
			Statement stmt = connection.createStatement();
			// stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
			ResultSet rs = stmt.executeQuery("SELECT * FROM getMissionnaires()");
			while (rs.next()) {
				System.out.println("Read from DB: " + rs.getTimestamp("query_nom"));
			}
				
			} catch (Exception e) {
			        out.write("JDBC_DATABASE_URL :\n".getBytes());
				out.write(dbUrl4output.getBytes());
				out.write("\n".getBytes());
			
				e.printStackTrace(new PrintWriter(sw));
				String the_stack = sw.toString() ;
				out.write(the_stack.getBytes());
				out.write("\n".getBytes());
			}           

	String the_message = "hello heroku" ; // + now() ; 
        out.write("hello heroku".getBytes());
        out.flush();
        out.close();

    }
    

}
