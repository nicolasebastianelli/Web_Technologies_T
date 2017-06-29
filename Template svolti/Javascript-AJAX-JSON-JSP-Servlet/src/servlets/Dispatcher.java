package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Libro;

public class Dispatcher extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Set<Libro> libriTrovati;
	
	public void init() throws ServletException {
		
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Set<Libro> libri =(Set<Libro>) this.getServletContext().getAttribute("libri");
		Set<String> autori= new HashSet<String>();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		autori.add(request.getParameter("input1"));
		autori.add(request.getParameter("input2"));
		autori.add(request.getParameter("input3"));
		Set<DispatcherSon> sons = new HashSet<DispatcherSon>();
		DispatcherSon son = null;
		libriTrovati = new HashSet<Libro>();
		if (libri==null){
			gson.toJson(libriTrovati);
			return;
		}
		int n = libri.size() / 10 ;
		if (libri.size()%10!=0)
			n++;
		for(int i =0; i <n ;i++){
			son = new DispatcherSon(libri, i,libriTrovati,autori);
			sons.add(son);
			son.run();
		}
		try {
			for (DispatcherSon son1 : sons) {
				son1.join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		out.println(gson.toJson(libriTrovati));
		gson.toJson(libriTrovati);
	}
}
