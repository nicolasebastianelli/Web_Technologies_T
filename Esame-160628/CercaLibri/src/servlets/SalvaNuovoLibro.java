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

import com.google.gson.Gson;

import beans.Libro;

public class SalvaNuovoLibro extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Set<Libro> libri;
	private Gson gson;
	
	public void init() throws ServletException {
		libri = new HashSet<Libro>();
		gson =new Gson();
		this.getServletContext().setAttribute("libri", libri);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String res = (String) request.getAttribute("res");	
		libri= (Set<Libro>) this.getServletContext().getAttribute("libri");
		Libro libro;
		libro = gson.fromJson(res,Libro.class);
		libri.add(libro);
		request.getRequestDispatcher("login.jsp?inserisci=null").forward(request, response);
	}
}
