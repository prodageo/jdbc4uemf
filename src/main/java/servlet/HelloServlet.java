package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import libinsa.txnscriptUtil ;
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

		String log = "" ;
		txnscriptUtil txnUtil = new txnscriptUtil() ;
	
		log = txnscriptUtil.test () ;		
		out.write ( log.getBytes());
			
		String the_message = "hello heroku : " + LocalDateTime.now() + "\n" ; 
        out.write("hello heroku".getBytes());

        out.flush();
        out.close();

    }
    

}
