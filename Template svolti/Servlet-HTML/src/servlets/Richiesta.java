package servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Libro;

public class Richiesta extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Hashtable<String, Set<Libro>> table=new Hashtable<String, Set<Libro>>();
		this.getServletContext().setAttribute("table", table);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean ok;
		String request = req.getParameter("req");
		if(request.equals("Richiedi")){
			Hashtable<String, Set<Libro>> tempTable = (Hashtable<String, Set<Libro>>)req.getSession().getAttribute("tempTable");
			if (tempTable == null){
				tempTable = new Hashtable<String, Set<Libro>>();
				req.getSession().setAttribute("tempTable", tempTable);
			}
			String codiceFiscale= req.getParameter("codiceFiscale");
			req.getSession().setAttribute("codiceFiscale", codiceFiscale);
			String titolo=req.getParameter("titolo");
			String autore=req.getParameter("autore");
			int mesePrelievo=Integer.parseInt(req.getParameter("mesePrelievo"));
			int meseConsegna=Integer.parseInt(req.getParameter("meseConsegna"));
			int giornoPrelievo=Integer.parseInt(req.getParameter("giornoPrelievo"));
			int giornoConsegna=Integer.parseInt(req.getParameter("giornoConsegna"));
			Libro newLibro = new Libro(titolo, autore, giornoPrelievo, giornoConsegna, mesePrelievo, meseConsegna);
			if(tempTable.containsKey(codiceFiscale)){
				ok=true;
				for (Libro libro : tempTable.get(codiceFiscale)) {
					if(libro.getTitolo().equals(titolo)){
						System.out.println("Libro "+titolo+" gia presente");
						ok=false;
					}
				}
				if(ok){
					tempTable.get(codiceFiscale).add(newLibro);
					System.out.println("ok: aggiunto libro in sessione");
					System.out.println("db = "+tempTable);
				}
			}
			else{
				tempTable.put(codiceFiscale, new HashSet<Libro>()) ;
				tempTable.get(codiceFiscale).add(newLibro);
				System.out.println("ok: aggiunto libro in sessione");
				System.out.println("db = "+tempTable);
			}
		}
		else if(request.equals("Finalizza") ){
			Hashtable<String, Set<Libro>> tempTable = (Hashtable<String, Set<Libro>>)req.getSession().getAttribute("tempTable");
			if (tempTable == null){
				System.out.println("nessun libro in sessione");
				req.getRequestDispatcher("richiestaLibri.html").forward(req, resp);
			}
			Hashtable<String, Set<Libro>> table = (Hashtable<String, Set<Libro>>) this.getServletContext().getAttribute("table");
			for (String codice : tempTable.keySet()) {
				if(table.containsKey(codice)){
					for (Libro libro : tempTable.get(codice)) {
						ok=true;
						for (Libro librodb : table.get(codice)) {
							if(libro.getTitolo().equals(librodb.getTitolo())){
								ok=false;
								System.out.println("Libro "+libro.getTitolo()+" gia presente");
							}
						}
						if(ok){
							table.get(codice).add(libro);
							System.out.println("Libro "+libro.getTitolo()+" aggiunto");
						}
					}
				}
				else{
					table.put(codice, new HashSet<Libro>());
					for (Libro libro : tempTable.get(codice)) {
						table.get(codice).add(libro);
						System.out.println("Libro "+libro.getTitolo()+" aggiunto");
					}					
				}
				
			}
			System.out.println("db = "+table);
		}
		req.getRequestDispatcher("richiestaLibri.html").forward(req, resp);
	}

}
