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
            
        ServletOutputStream out = resp.getOutputStream();
            
		try {
			
			// URI dbUri = new URI(System.getenv("DATABASE_URL"));
	    		String dbUrl = System.getenv("JDBC_DATABASE_URL");
    			Connection connection = DriverManager.getConnection(dbUrl);

			/*
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
			Connection connection = DriverManager.getConnection(dbUrl, username, password);
			*/	
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
			stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
			stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
			ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
			while (rs.next()) {
				// System.out.println("Read from DB: " + rs.getTimestamp("tick"));
			}
				
			} catch (Exception e) {
				// e.printStackTrace();
			        out.write("erreur en SQL".getBytes());
			}            
        out.write("hello heroku".getBytes());
        out.flush();
        out.close();

    }
    

}
