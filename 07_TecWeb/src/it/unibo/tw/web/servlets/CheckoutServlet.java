package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unibo.tw.db.DataSource;
import it.unibo.tw.db.PersistenceException;
import it.unibo.tw.db.ShopRepository;
import it.unibo.tw.web.beans.Cart;

public class CheckoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Cart carrello;
	private List<Cart> carrelli;
	private ShopRepository sr; 
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		sr = new ShopRepository(DataSource.DB2);
		try {
			sr.dropAndCreateTable();
		} catch (PersistenceException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// tempo di attesa, qui inserito artificiosamente
		String confirm = req.getParameter("confirm");
		String mail = req.getParameter("mail");
		carrelli=null;
		req.getSession().setAttribute("listacarrello", carrelli);
		if(confirm.equals("Confirm order")){
			try {
				if(mail.equals("")||mail== null){
					throw new IOException("MAIL VUOTA");
				}
				carrello = (Cart) req.getSession().getAttribute("cart");
				carrello.setMail(mail);
				sr.persistCart(carrello);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(confirm.equals("Show order")){
			try {
				if(mail.equals("")||mail== null){
					throw new IOException("MAIL VUOTA");
				}				
				carrello=sr.findCartByEmail(mail);
				req.getSession().setAttribute("cart", carrello);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(confirm.equals("Show all")){
			try {	
				carrelli=sr.findAll();
				req.getSession().setAttribute("listacarrello", carrelli);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		// un altro forward eseguito lato servlet
				
				resp.sendRedirect("pages/checkout.jsp");
	}
}
