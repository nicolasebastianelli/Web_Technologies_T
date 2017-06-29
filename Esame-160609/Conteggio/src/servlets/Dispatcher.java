package servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Conteggi;
import beans.Conteggio;
import beans.FileAccessor;


public class Dispatcher extends HttpServlet{

	/**
	 * 
	 */
	private Conteggi conteggiApplication;
	private static final long serialVersionUID = 1L;
	private String homeURL="start.jsp";
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		conteggiApplication= new Conteggi();
		this.getServletContext().setAttribute("conteggiApplication", conteggiApplication);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("stato", null);
		char car ;
		String file = req.getParameter("file");
		if(file == null || file.isEmpty()){
			req.setAttribute("stato", "Nome file non valido");
			req.getRequestDispatcher(homeURL).forward(req, resp);
		}
		else{
			String carTest = req.getParameter("car");
			if( carTest.length()!=1 || !Character.isAlphabetic(carTest.charAt(0))&&!Character.isDigit(carTest.charAt(0)))
			{
				req.setAttribute("stato", "Carattere non valido");
				req.getRequestDispatcher(homeURL).forward(req, resp);		
			}
			else{
				car= carTest.charAt(0);	
				String pathFile = "/files/"+file;
				URL fileUrl = getServletContext().getResource(pathFile);
				if(fileUrl==null){
					File f = new File(getServletContext().getRealPath("/files/")+file);
					FileOutputStream fout = new FileOutputStream(f);
					PrintStream printer = new PrintStream(fout);
					printer.println("text");
					fout.close();
					req.setAttribute("stato", "File "+file+" inesistente , creato nuovo file");
					req.getRequestDispatcher(homeURL).forward(req, resp);
					return;
				}
				else{
					File f = new File(fileUrl.getFile());
					Conteggio conteggio =new Conteggio();
					Conteggi conteggiSession =(Conteggi) req.getSession().getAttribute("conteggiSession");
					conteggio.setNomeFile(file);
					conteggio.setCarattere(car);
					conteggio.setOraRicerca(LocalDateTime.now());
					long fileSize =f.length();
					long nThread = fileSize/1024;
					if (fileSize%1024!=0)
						nThread++;
					List<FileAccessor> fileAccessorList = new ArrayList<FileAccessor>();
					BufferedReader br = new BufferedReader(new InputStreamReader(getServletContext().getResourceAsStream(pathFile)));
					for(int i=0 ; i< nThread; i++){
						br.skip(i*1024);
						FileAccessor fileAccessor=new FileAccessor(conteggio, br);
						fileAccessorList.add(fileAccessor);
						fileAccessor.run();
					}
					for (FileAccessor thread : fileAccessorList) {
						try{
							thread.join();
						}catch (Exception e) {
							req.setAttribute("stato", "Errore interno");
							req.getRequestDispatcher(homeURL).forward(req, resp);
							return;

						}
					}
					if(conteggiSession.getConteggi().size()==3)
						conteggiSession.getConteggi().remove(0);
					conteggiSession.getConteggi().add(conteggio);
					conteggiApplication.getConteggi().add(conteggio);
					req.setAttribute("stato", "File analizzato correttamente");
					req.getRequestDispatcher(homeURL).forward(req, resp);
				}
			}
		}
	}

}
