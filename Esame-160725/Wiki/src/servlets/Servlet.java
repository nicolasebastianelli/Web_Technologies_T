package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Map<Integer, String> libro;
	private LocalDateTime time ;
	private Gson gson;
	private int count;

	public void init() throws ServletException {
		libro = new HashMap<Integer, String>();
		gson =new Gson();
		time = LocalDateTime.now(); 
		libro.put(1, "lorem ipsum");
		libro.put(2, "lorem ipsum");
		libro.put(3, "lorem ipsum");
		libro.put(4, "lorem ipsum");
		libro.put(5, "lorem ipsum");
		libro.put(6, "lorem ipsum");
		libro.put(7, "lorem ipsum");
		libro.put(8, "lorem ipsum");
		libro.put(9, "lorem ipsum");
		libro.put(10, "lorem ipsum");

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int capitolo = Integer.parseInt(request.getParameter("capitolo"));
		if(time.isAfter(LocalDateTime.now().minusMinutes(10))){
			count=0;
			for (int i : libro.keySet()) {
				if(libro.get(i).contains("aaaaaaaa")){
					count++;
				}
			}
			if(count>10)
				libro = new HashMap<Integer, String>();
		}
		if(request.getParameter("cerca")!=null){	
			request.setAttribute("libro",gson.toJson(libro.get(capitolo)));
		}
		else if(request.getParameter("inserisci")!=null && request.getParameter("original")!= null){
			response.getWriter().write(request.getParameter("original"));
			response.getWriter().write(libro.get(capitolo));
			if(request.getParameter("original").equals(libro.get(capitolo))){
				libro.put(capitolo, request.getParameter("contenuto"));
			}
			request.setAttribute("libro",gson.toJson(libro.get(capitolo)));
		}
		request.getRequestDispatcher("home.jsp?cap="+capitolo).forward(request, response);
	}
}
