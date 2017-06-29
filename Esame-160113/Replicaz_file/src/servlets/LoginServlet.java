package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ListaOp;
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
		ListaOp listop = new ListaOp();
		this.getServletContext().setAttribute("listaop", listop);
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
			String fileName = "password.txt";
			String filePath = "/files/"+fileName;
			Integer[] couple =new Integer[1];
			URL fileUrl = getServletContext().getResource(filePath);
			if (checkCredentialis(username, password,fileUrl ,filePath, couple)) {
				if (loginLimit < 0 || loggedUsers.get() < loginLimit) {
					// Object used as placeholder
					request.getSession().setAttribute(LoginInfo.LOGIN_INFO, new LoginInfo(username,couple[0]));
					loggedUsers.incrementAndGet();
					response.sendRedirect(request.getContextPath()+firstPageAfterLogin);
				} else {
					request.setAttribute(LOGIN_ERROR_MESSAGE, "too many logins");
					getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute(LOGIN_ERROR_MESSAGE, "invalid credentialis");
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} else {
			// do nothing and let the request go back to the forwarding servlet
			// if any
			;
		}
	}

	private boolean checkCredentialis(String username, String password , URL fileUrl, String filePath, Integer[] couple) {
		if (username == null || password == null)
			return false;

		if(fileUrl==null){
			return false; 
		}
		int count = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(getServletContext().getResourceAsStream(filePath)));
		String read;
		try {
			while ((read = br.readLine()) != null) {
				if(read.split(" ")[0].equals(username)&& read.split(" ")[1].equals(password)){
					couple[0]=count/2;
					return true;
				}
				count++;
			}
			br.close();
		} catch (IOException e) {
			return false;
		}
		return false;
	}
}
