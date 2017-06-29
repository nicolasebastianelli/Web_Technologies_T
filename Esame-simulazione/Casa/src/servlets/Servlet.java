package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String citta;
	
	public void init() throws ServletException {
		
	}
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		citta= request.getParameter("input");
		String filePath = "/resources/"+citta+".xml";
		PrintWriter pw =response.getWriter();
		URL fileUrl = getServletContext().getResource(filePath);
		if(fileUrl==null){
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(getServletContext().getResourceAsStream(filePath)));
		String line;
		while ((line = br.readLine()) !=null) {
			pw.write(line);
		}
		br.close();	
	}
}
