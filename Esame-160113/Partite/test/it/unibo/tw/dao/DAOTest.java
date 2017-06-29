package it.unibo.tw.dao;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DAOTest {
	
	public static final int DAO = DAOFactory.DB2;
	
	public static void main(String[] args) {
		
		// Student
		
		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		
		StadioDAO stadioDAO = DAOFactory.getDAOFactory(DAO).getStadioDAO();
		stadioDAO.dropTable();
		stadioDAO.createTable();
		
		StadioDTO stadio = new StadioDTO();
		stadio.setCodice(1);;
		stadio.setCitta("c1");
		stadio.setNome("n1");
		stadioDAO.create(stadio);
		
		stadio = new StadioDTO();
		stadio.setCodice(2);;
		stadio.setCitta("c2");
		stadio.setNome("n2");
		stadioDAO.create(stadio);
		
		
		PartitaDAO partitaDAO = daoFactoryInstance.getPartitaDAO();
		partitaDAO.dropTable();
		partitaDAO.createTable();
		
		PartitaDTO p = new PartitaDTO();
		Calendar c = Calendar.getInstance();
		c.set(2017, 1, 24);
		p.setCodice(1);
		p.setCategoria("c1");
		p.setGirone("g1");
		p.setNomecasa("ca1");
		p.setNomeospite("os1");
		p.setCodicestadio(2);
		p.setData(c.getTime());
		partitaDAO.create(p);

		p = new PartitaDTO();
		c.set(2017, 1, 25);
		p.setCodice(2);
		p.setCategoria("c2");
		p.setGirone("g2");
		p.setNomecasa("ca2");
		p.setNomeospite("os2");
		p.setCodicestadio(1);
		p.setData(c.getTime());
		partitaDAO.create(p);
		
		p = new PartitaDTO();
		c.set(2017, 1, 26);
		p.setCodice(3);
		p.setCategoria("c2");
		p.setGirone("g3");
		p.setNomecasa("ca3");
		p.setNomeospite("os3");
		p.setCodicestadio(1);
		p.setData(c.getTime());
		partitaDAO.create(p);
		
		
		for (StadioDTO sc : stadioDAO.readAll()) {
			System.out.println(sc.getNome());
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (PartitaDTO partita : stadioDAO.findPartiteByStadioId(sc.getCodice())) {
				if(map.containsKey(partita.getCategoria())){
					map.put(partita.getCategoria(), map.get(partita.getCategoria())+1);
				}
				else{
					map.put(partita.getCategoria(), 1);
				}
			}
			for (String string : map.keySet()) {
				System.out.println(string+" "+map.get(string)+" partite");
			}
		}
		System.out.println();
		
	}

}
