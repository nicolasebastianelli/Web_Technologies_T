package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;


public class WordProcessing extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson;

	public void init() throws ServletException {
		gson = new Gson();
		this.getServletContext().setAttribute("serializer", gson);	
	}
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String result="File "+request.getParameter("num")+" scritto";
		gson = (Gson)this.getServletContext().getAttribute("serializer");
		String output;
		//response.getOutputStream().print(output);
		String fileName="file.txt";
		int num = Integer.parseInt(request.getParameter("num"));
		int input = Integer.parseInt(request.getParameter("input"));
		int linetoread = num* input;
		String filePath = "/resources/"+fileName;
		URL fileUrl = getServletContext().getResource(filePath);
		if(fileUrl==null){
			output =gson.toJson("File non trovato");
			response.getOutputStream().print(output);
			return;
		}
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(getServletContext().getResourceAsStream(filePath)));
			String line;
			int count=0;
			PrintStream printer ;
			FileOutputStream fout;
			String newfileName=String.valueOf(num)+".txt";
			File f = new File(getServletContext().getRealPath("/resources/")+newfileName);
			fout = new FileOutputStream(f);
			printer= new PrintStream(fout);   
			while ((line = br.readLine()) !=null) {
				count++;
				if (count>=linetoread && count<linetoread+input){
					printer.println(line);
				}
			}
			fout.close();
			br.close();
		}catch (Exception e) {
			output =gson.toJson("Errore interno");
			response.getOutputStream().print(output);
			return;
		}
		output =gson.toJson(result);
		response.getOutputStream().print(output);
		return;
	}
}
