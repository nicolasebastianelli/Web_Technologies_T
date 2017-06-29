package it.unibo.tw.dao;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOTest {

	public static final int DAO = DAOFactory.DB2;

	public static void main(String[] args) {

		// Student

		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		ChefDAO chefDAO = daoFactoryInstance.getChefDAO();
		chefDAO.dropTable();
		chefDAO.createTable();

		ChefDTO s = new ChefDTO();
		s.setNomeChef("chef1");
		s.setNomeRistorante("r1");
		s.setNumeroStelle(2);
		chefDAO.create(s);

		s = new ChefDTO();
		s.setNomeChef("chef2");
		s.setNomeRistorante("r2");
		s.setNumeroStelle(3);
		chefDAO.create(s);

		s = new ChefDTO();
		s.setNomeChef("chef3");
		s.setNomeRistorante("r3");
		s.setNumeroStelle(1);
		chefDAO.create(s);


		// Courses

		PiattoDAO piattoDAO = DAOFactory.getDAOFactory(DAO).getPiattoDAO();
		piattoDAO.dropTable();
		piattoDAO.createTable();

		PiattoDTO p = new PiattoDTO();
		p.setNomePiatto("p1");
		p.setTempoPreparazione(30);
		p.setCalorie(10.2);
		p.setNomeChef("chef1");
		piattoDAO.create(p);

		p = new PiattoDTO();
		p.setNomePiatto("p2");
		p.setTempoPreparazione(10);
		p.setCalorie(10.2);
		p.setNomeChef("chef1");
		piattoDAO.create(p);

		p = new PiattoDTO();
		p.setNomePiatto("p3");
		p.setTempoPreparazione(20);
		p.setCalorie(10.2);
		p.setNomeChef("chef2");
		piattoDAO.create(p);

		List<PiattoDTO> piatti;
		PiattoDTO piattomin=null;
		Map<String,PiattoDTO> result = new HashMap<String,PiattoDTO>();
		for (ChefDTO chef: chefDAO.readAll()) {
			if(chef.getNumeroStelle()>=2){
				piatti = chefDAO.findPiattiByIdChef(chef.getNomeChef());
				piattomin=null;
				for (PiattoDTO piatto : piatti) {
					if(piattomin==null)
						piattomin=piatto;
					else
						if(piatto.getTempoPreparazione()<piattomin.getTempoPreparazione()){
							piattomin=piatto;
						}
				}
				result.put(chef.getNomeChef(), piattomin);
			}
		}
		try {
			File f = new File("Chef.txt");
			if(!f.exists())
			{
				f.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter out = new PrintWriter(fos);
			System.out.println("Piatti chef stellati:\n");
			out.println("Piatti chef stellati:");
			out.println();
			for(String chef : result.keySet()){
				System.out.println("Chef: "+chef);
				out.println("Chef: "+chef);
				System.out.println("Piatto: "+result.get(chef).getNomePiatto());
				out.println("Piatto: "+result.get(chef).getNomePiatto());
				System.out.println();
				out.println();
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();


	}

}
