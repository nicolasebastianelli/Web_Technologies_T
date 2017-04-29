
package it.unibo.tw.es1.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.es1.beans.Articolo;
import it.unibo.tw.es1.beans.InsiemeDiArticoli;


public class StatisticheServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		float guadagno = 0;
		int lastday;
		int firstday;
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/statistiche.jsp");
		try{
			firstday =  Integer.parseInt(req.getParameter("firstDay"));
			lastday =	Integer.parseInt( req.getParameter("lastDay"));
		}
		catch (Exception e) {
			req.setAttribute("guadagnoRichiestaAttuale", null );
			dispatcher.forward(req, resp);
			return;
		}
		InsiemeDiArticoli articoli =(InsiemeDiArticoli) context.getAttribute("merceVenduta");
		if(id.equals("")){
			for ( Articolo articolo : articoli.getMerce()) {
				if(articolo.getDay()<=lastday &&articolo.getDay()>=firstday)
					guadagno=guadagno+(articolo.getPrice()*articolo.getAmount());
			}
		}
		else{
			for ( Articolo articolo : articoli.getMerce()) {
				if(articolo.getDay()<=lastday &&articolo.getDay()>=firstday && id.equals(articolo.getId()))
					guadagno=guadagno+(articolo.getPrice()*articolo.getAmount());
			}
		}
		//PrintWriter out = resp.getWriter();
		//out.println("<i>"+ guadagno + "</i>");
		Cookie userID = new Cookie("id", id);
		Cookie userFirstDay = new Cookie("firstDay", firstday+"");
		Cookie userLastDay = new Cookie("lastDay", lastday+"");
		Cookie userGuadagno = new Cookie("guadagno", guadagno+"");
		resp.addCookie(userID);
		resp.addCookie(userFirstDay);
		resp.addCookie(userLastDay);
		resp.addCookie(userGuadagno);
		req.setAttribute("guadagnoRichiestaAttuale", (float) guadagno );
		dispatcher.forward(req, resp);
	}

}
