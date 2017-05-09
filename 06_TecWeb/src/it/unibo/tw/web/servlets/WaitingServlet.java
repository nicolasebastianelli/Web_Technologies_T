package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class WaitingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		int wait = Integer.parseInt(request.getParameter("wait"));
		PrintWriter out = response.getWriter();
		out.write("Ecco il risultato");
		for ( int i = 0 ; i < wait ; i++ ) { 
			out.append("!"); 
			try {
				Thread.sleep(1000); 
			}	
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.write(" (attesa = " + wait + " secondi)");
	}

}
