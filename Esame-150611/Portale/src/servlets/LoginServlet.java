package servlets;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LoginInfo;

public class LoginServlet extends HttpServlet {
	public static final String LOGGED_USERS = "loggedUsers";
	public static final String LOGIN_ERROR_MESSAGE = "loginError";
	private static final long serialVersionUID = 1L;
	private String firstPageAfterLogin;
	private AtomicInteger loggedUsers;
	private Integer loginLimit;

	@Override
	public void init() throws ServletException {
		super.init();
		firstPageAfterLogin = this.getServletContext().getInitParameter("firstPageAfterLogin");

		loginLimit = Integer.valueOf(this.getInitParameter("loginLimit"));

		if (this.getServletContext().getAttribute(LOGGED_USERS) == null) {
			this.getServletContext().setAttribute(LOGGED_USERS, new AtomicInteger(0));
		}
		this.loggedUsers = (AtomicInteger) this.getServletContext().getAttribute(LOGGED_USERS);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute(LoginInfo.LOGIN_INFO) == null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (checkCredentialis(username, password)) {
				if (loginLimit < 0 || loggedUsers.get() < loginLimit) {
					// Object used as placeholder
					request.getSession().setAttribute(LoginInfo.LOGIN_INFO, new LoginInfo(username));
					loggedUsers.incrementAndGet();
					response.sendRedirect(request.getContextPath()+firstPageAfterLogin);
				} else {
					getServletContext().getRequestDispatcher(firstPageAfterLogin).forward(request, response);
				}
			} else {
				request.setAttribute(LOGIN_ERROR_MESSAGE, "invalid credentialis");
				getServletContext().getRequestDispatcher("home.jsp").forward(request, response);
			}
		} else {
			// do nothing and let the request go back to the forwarding servlet
			// if any
			;
		}
	}

	private boolean checkCredentialis(String username, String password) {
		if (username == null || password == null)
			return false;		
		return true;
	}
}
