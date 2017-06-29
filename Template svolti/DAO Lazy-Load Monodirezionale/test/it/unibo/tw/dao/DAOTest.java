package it.unibo.tw.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class DAOTest {

	public static final int DAO = DAOFactory.DB2;
	private static DAOFactory daoFactoryInstance;
	private static RistoranteDAO ristoranteDao;
	private static PiattoDAO piattoDao;

	public static void main(String[] args) {
		daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		ristoranteDao = daoFactoryInstance.getRistoranteDAO();
		piattoDao = daoFactoryInstance.getPiattoDAO();

		ristoranteDao.setRelationshipOwner(true);
		piattoDao.setRelationshipOwner(false);

		tableCreation();
		enterEntries();
		queries();
	}

	private static void tableCreation() {
		System.out
				.println("\n\n----------------- DDL STATEMENTS ------------------------------------");
		// ristorante è l'owner della relazione: deve creare per ultimo la
		// tabella
		ristoranteDao.dropTable();
		piattoDao.dropTable();
		piattoDao.createTable();
		ristoranteDao.createTable();
	}

	private static void enterEntries() {
		System.out
				.println("\n\n----------------- ENTERING ENTRIES -------------------------------");
		RistoranteDTO r1 = new RistoranteDTO(1, "r1", "Bologna", 4);
		RistoranteDTO r2 = new RistoranteDTO(2, "r2", "Bologna", 2);
		RistoranteDTO r3 = new RistoranteDTO(3, "r3", "Bologna", 4);
		RistoranteDTO r4 = new RistoranteDTO(4, "r4", "Bologna", 5);

		PiattoDTO p1 = new PiattoDTO(1, "seppie con i piselli", "primo");
		PiattoDTO p2 = new PiattoDTO(2, "calamari", "secondo");
		PiattoDTO p3 = new PiattoDTO(3, "primo1", "primo");
		PiattoDTO p4 = new PiattoDTO(4, "secondo1", "secondo");

		p1.getRistoranti().add(r1);
		p1.getRistoranti().add(r2);
		p1.getRistoranti().add(r3);
		p1.getRistoranti().add(r4);
		p2.getRistoranti().add(r2);
		p2.getRistoranti().add(r3);
		p2.getRistoranti().add(r4);
		p3.getRistoranti().add(r3);
		p3.getRistoranti().add(r4);
		p4.getRistoranti().add(r4);

		r4.getPiatti().add(p4);
		r4.getPiatti().add(p3);
		r4.getPiatti().add(p2);
		r4.getPiatti().add(p1);
		r3.getPiatti().add(p3);
		r3.getPiatti().add(p2);
		r3.getPiatti().add(p1);
		r2.getPiatti().add(p2);
		r2.getPiatti().add(p1);
		r1.getPiatti().add(p1);

		// ristorante è l'owner della relazione: deve creare per ultimo le entry
		piattoDao.create(p1);
		piattoDao.create(p2);
		piattoDao.create(p3);
		piattoDao.create(p4);

		ristoranteDao.create(r1);
		ristoranteDao.create(r2);
		ristoranteDao.create(r3);
		ristoranteDao.create(r4);
	}

	private static void queries() {
		System.out
				.println("\n\n----------------- QUERIES ----------------------------------------");

		Set<RistoranteDTO> ristoBolo = ristoranteDao.readLazyByCitta("Bologna");

		// per ogni ristorante sito in Bologna, si richiede la lista dei piatti
		// di tipo “primo” offerti nel
		// rispettivo menù
		for (RistoranteDTO ristoranteDTO : ristoBolo) {
			System.out.print(ristoranteDTO.getNomeRistorante()+" : ");
			for (PiattoDTO piattoDTO : ristoranteDTO.getPiatti()) {
				System.out.print(piattoDTO.getNomePiatto()+", ");
				
			}
			System.out.println();
		}

		Set<RistoranteDTO> allRisto = ristoranteDao.readLazyAll();
		int count=0;
		boolean ok=false;
		for (RistoranteDTO ristoranteDTO : allRisto) {
			for (PiattoDTO piattoDTO : ristoranteDTO.getPiatti()) {
				if(ristoranteDTO.getRating()>3
						&&piattoDTO.getNomePiatto().toLowerCase().equals("seppie con i piselli")
						&&piattoDTO.getTipo().equals("primo"))
					ok=true;
			}
			if(ok)
				count++;
			ok=false;
		}
		System.out.println("Ristoranti richiesti: " + count);
        
        File f = new File("Result.txt");
        try {
        if(!f.exists())
        {
				f.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(f);
        
        PrintWriter out = new PrintWriter(fos);
        out.println();      
        out.close();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
        
}
