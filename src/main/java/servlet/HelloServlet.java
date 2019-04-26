package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.txnscenario ;
import java.time.LocalDateTime;


@WebServlet(
        name = "MyServlet", 
        urlPatterns = {"/hello"}
    )
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
			
	{

		// out désigne l'écran de la page web, dans lequel on peut écrire
        ServletOutputStream out = resp.getOutputStream();

 		String log = "";
		String the_message = "Hello from heroku : " + LocalDateTime.now() + "\n" ; 
        out.write(the_message.getBytes());
		
		txnscenario the_scenario = new txnscenario() ;
		log = the_scenario.test () ;		
		out.write ( log.getBytes());
		
        out.flush();
        out.close();

    }
    

}
