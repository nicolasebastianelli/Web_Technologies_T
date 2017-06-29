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

import beans.Bean;
import beans.Beans;



public class Servlet extends HttpServlet{

	/**
	 * 
	 */
	private Beans beansApplication;
	private static final long serialVersionUID = 1L;
	private String homeURL="index.jsp";
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		beansApplication= new Beans();
		this.getServletContext().setAttribute("beansApplication", beansApplication);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				req.setAttribute("stato", null);
		
			}
		
	}

}
