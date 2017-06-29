package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import com.google.gson.Gson;

import beans.Libri;
import beans.Libro;

public class SalvaNuovoLibro extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Libri libri;
	
	public void init() throws ServletException {
		libri = new Libri();
		this.getServletContext().setAttribute("libri", libri);
		libri=(Libri) this.getServletContext().getAttribute("libri");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String autore="";
		String isbn="";
		String titolo="";
		Libro libroSession = (Libro) request.getSession().getAttribute("libroSession");
		if(request.getParameter("save")!= null){
			if(request.getParameter("autore")!= null)
				autore=request.getParameter("autore");
			if(request.getParameter("titolo")!= null)
				titolo=request.getParameter("titolo");
			if(request.getParameter("isbn")!= null)
				isbn=request.getParameter("isbn");
			libroSession.setAutore(autore);
			libroSession.setIsbn(isbn);
			libroSession.setTitolo(titolo);
		}
		else if(request.getParameter("finalizza")!=null){
			libroSession.setAutore("");
			libroSession.setIsbn("");
			libroSession.setTitolo("");
			if(request.getParameter("autore")!= null && request.getParameter("titolo")!= null 
					&&request.getParameter("isbn")!= null){
				Libro libro =new Libro(request.getParameter("autore"), request.getParameter("titolo"), request.getParameter("isbn"));
			libri.getLibri().add(libro);
			}
		}
		request.getRequestDispatcher("nuovoLibro.jsp").forward(request, response);
	}
}
