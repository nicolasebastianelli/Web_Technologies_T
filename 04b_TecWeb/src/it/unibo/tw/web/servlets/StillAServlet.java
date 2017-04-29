package it.unibo.tw.web.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class StillAServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String homeURL = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.homeURL = config.getInitParameter("homeURL");
	}

	@Override
	public void service(ServletRequest req, ServletResponse resp)
	throws ServletException, IOException {
		// tempo di attesa, qui inserito artificiosamente
		try {
			Thread.sleep(5000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		// un altro forward eseguito lato servlet
		req.getRequestDispatcher(homeURL).forward(req, resp);
	}
	
}
