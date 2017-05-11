package it.unibo.tw.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import it.unibo.tw.web.beans.FeedDb;
import it.unibo.tw.web.beans.Feed;

public class FeedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String category = request.getParameter("category");
		FeedDb feeds = new FeedDb();
		String result="{ \"Feed\" : [";		
		int nelem = 1;
		for ( Feed feed : feeds.find(category) ) {
			result += "{ \"Titolo\" : \""+feed.getTitle()+"\", ";
			result += "\"Descrizione\" : \""+feed.getDescription()+"\", ";
			result += "\"Autore\" : \""+feed.getAuthor()+"\", ";
			result += "\"Categoria\" : \""+feed.getCategory()+"\", ";
			result += "\"Data\" : \""+feed.getPubDate()+"\", ";
			result += "\"Link\" : \""+feed.getLink()+"\"";
			result += ", \"Immagine\" : \""+feed.getImageUrl()+"\" }";
			if(nelem != feeds.find(category).size() ){
				result += ", ";
			}
			nelem++;
		}
		result += "] }";
		out.write(result);
	}
}
