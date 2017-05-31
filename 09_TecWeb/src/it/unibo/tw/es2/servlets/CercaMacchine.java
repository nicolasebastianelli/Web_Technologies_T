package it.unibo.tw.es2.servlets;
import it.unibo.tw.dao.DAOFactory;
import it.unibo.tw.dao.DescrizioneMacchinaDAO;
import it.unibo.tw.dao.DescrizioneMacchinaDTO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import com.google.gson.Gson;

public class CercaMacchine extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final int DAO = DAOFactory.DB2;
	private DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
	private DescrizioneMacchinaDAO macchinaDAO = daoFactoryInstance.getMacchinaDAO();
	// MODIFICA GSON
		private Gson gson;
	// FINE MODIFICA GSON
	
	@Override
	public void init() throws ServletException {
		
		try{
		macchinaDAO.dropTable();
		}
		catch(Exception e){
			
		}
		macchinaDAO.createTable();
		
		DescrizioneMacchinaDTO m =new DescrizioneMacchinaDTO();
		m.setTarga("1111111");
		m.setModello("Mazma");
		m.setColore("Giallo");
		macchinaDAO.create(m);

		m =new DescrizioneMacchinaDTO();
		m.setTarga("2222222");
		m.setModello("Ferrari");
		m.setColore("Rosso");
		macchinaDAO.create(m);
		
		m =new DescrizioneMacchinaDTO();
		m.setTarga("3333333");
		m.setModello("Lamborghini");
		m.setColore("Arancione");
		macchinaDAO.create(m);
		
		m =new DescrizioneMacchinaDTO();
		m.setTarga("4444444");
		m.setModello("Twingo");
		m.setColore("Verde");
		macchinaDAO.create(m);
		
//      realizzare persistenza con DB2 al posto di HashMap
		
		//MODIFICA GSON
				gson = new Gson();
		
//		JSONSerializer serializer = new JSONSerializer();
//		try {
//			// inizializza i tipi serializzatori forniti di default 
//			serializer.registerDefaultSerializers(); 
//		} 
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		this.getServletContext().setAttribute("serializer", serializer);
				this.getServletContext().setAttribute("serializer", gson);		
		//FINE MODIFICA GSON
				
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String targa = req.getParameter("targa");
		if( targa != null ){
			
			DescrizioneMacchinaDTO macchina = macchinaDAO.read(targa);
			if( macchina == null ){
				macchina = new DescrizioneMacchinaDTO("errore","errore","errore");
			}
			// MODIFICA GSON
			gson = (Gson)this.getServletContext().getAttribute("serializer");
//			JSONSerializer serializer = (JSONSerializer)this.getServletContext().getAttribute("serializer");
//			try {
//				String output = serializer.toJSON(macchina);
//				resp.getOutputStream().print(output);
//			} 
//			catch (MarshallException e) {
//				e.printStackTrace();
//			}
			String output =gson.toJson(macchina);
			resp.getOutputStream().print(output);
			//FINE MODIFICA GSON
		}
		
	}
	
}
