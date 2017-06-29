package servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Libro;

public class Cambiamenti extends HttpServlet{
	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String cf = (String)req.getSession().getAttribute("codiceFiscale");
		if( cf == null ){
			cf = req.getParameter("codiceFiscale");
		}
			String key =req.getParameter("codiceFiscale");
			String titolo =req.getParameter("titolo");
			int mesePrelievo = Integer.parseInt(req.getParameter("mesePrelievo"));
			int meseConsegna = Integer.parseInt(req.getParameter("meseConsegna"));
			int giornoPrelievo = Integer.parseInt(req.getParameter("giornoPrelievo"));
			int giornoConsegna = Integer.parseInt(req.getParameter("giornoConsegna"));
			Hashtable<String, Set<Libro>> table = (Hashtable<String, Set<Libro>>) this.getServletContext().getAttribute("table");
			if(table.containsKey(key)){
				for (Libro libro : table.get(key)) {
					if(libro.getTitolo().equals(titolo)){
						if(calcola90(mesePrelievo,meseConsegna,giornoConsegna,giornoPrelievo)){
							libro.setGiornoConsegna(giornoConsegna);
							libro.setGiornoPrelievo(giornoPrelievo);
							libro.setMeseConsegna(meseConsegna);
							libro.setMesePrelievo(mesePrelievo);
							System.out.println("ok: cambiate date in db");
							System.out.println("db = "+table);
						}
						else
							System.out.println("error 1: giorni >= 90");
					}
				};

			}
		req.getRequestDispatcher("cambiaDate.html").forward(req, resp);
	}


	private boolean calcola90(int mesePrelievo, int meseConsegna, int giornoConsegna, int giornoPrelievo) {
		int prelievo = mesePrelievo*30+giornoPrelievo;
		int consegna = meseConsegna*30+giornoConsegna;
		if((consegna - prelievo )<90)
			return true;
		else
			return false;
	}

}
