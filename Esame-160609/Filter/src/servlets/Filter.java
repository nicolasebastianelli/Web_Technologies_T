package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import beans.Righe;

public class Filter extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson;
	
	public void init() throws ServletException {
		gson = new Gson();
		this.getServletContext().setAttribute("serializer", gson);	
	}
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		Righe righe = new Righe();
		gson = (Gson)this.getServletContext().getAttribute("serializer");
		String output;
		//response.getOutputStream().print(output);
		String fileName="testo.txt";
		String filePath = "/files/"+fileName;
		String parola= request.getParameter("input");
		URL fileUrl = getServletContext().getResource(filePath);
		if(fileUrl==null){
			output =gson.toJson(righe);
			response.getOutputStream().print(output);
			return;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(getServletContext().getResourceAsStream(filePath)));
		String line;
		while ((line = br.readLine()) !=null) {
			if (line.contains(parola+" "))
				righe.getRighe().add(line);
		}
		br.close();
		output =gson.toJson(righe);
		response.getOutputStream().print(output);
		return;
	}
}
