package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;

import beans.Documenti;
import beans.Documento;

public class DownloadPage extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson;

	public void init() throws ServletException {
		gson = new Gson();
		this.getServletContext().setAttribute("serializer", gson);	
	}
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		Documenti documenti =new Documenti();
		gson = (Gson)this.getServletContext().getAttribute("serializer");
		Documento doc;
		String output;
		String doccont="";
		InputStream is = null; 
		URL fileUrl = new URL(request.getParameter("url1"));
		is = fileUrl.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		while ((line = br.readLine()) != null) {
			doccont+=line;
		}
		doc = new Documento(fileUrl.toString(),doccont);
		documenti.addDocumento(doc);
		doccont="";
		is = null; 
		fileUrl = new URL(request.getParameter("url2"));
		is = fileUrl.openStream();
		br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			doccont+=line;
		}
		doc = new Documento(fileUrl.toString(),doccont);
		documenti.addDocumento(doc);
		doccont="";
		is = null; 
		fileUrl = new URL(request.getParameter("url3"));
		is = fileUrl.openStream();
		br = new BufferedReader(new InputStreamReader(is));

		while ((line = br.readLine()) != null) {
			doccont+=line;
		}
		doc = new Documento(fileUrl.toString(),doccont);
		documenti.addDocumento(doc);

		output =gson.toJson(documenti);
		response.getOutputStream().print(output);
		return;
	}
}
