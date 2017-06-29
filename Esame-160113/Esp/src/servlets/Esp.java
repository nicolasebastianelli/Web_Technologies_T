package servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Esp extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	public void init() throws ServletException {
		gson = new Gson();
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int y = Integer.parseInt(request.getParameter("y"));
		float x = Float.parseFloat(request.getParameter("x"));
		double res = Math.pow(x, y);
		gson.toJson(res);
		response.getWriter().print(res);
	}
}
