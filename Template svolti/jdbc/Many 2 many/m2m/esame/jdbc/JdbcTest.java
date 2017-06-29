package m2m.esame.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {
	public static void main(String[] args) throws PersistenceException, IOException {
		
		PartitaRepository partite = new PartitaRepository();
		SquadraRepository squadre = new SquadraRepository();
		StadioRepository stadi = new StadioRepository();
		
		partite.dropTable();
		squadre.dropAndCreateTable();
		stadi.dropAndCreateTable();
		partite.createTable();

		Squadra s1 = new Squadra(1,"s1","c1","g1");
		Squadra s2 = new Squadra(2,"s2","c1","g1");
		Squadra s3 = new Squadra(3,"s3","c1","g2");
		Squadra s4 = new Squadra(4,"s3","c2","g1");
		
		Stadio st1 = new Stadio(1, "st1", "citt1");
		Stadio st2 = new Stadio(2, "st2", "citt2");
		Stadio st3 = new Stadio(3, "st3", "citt3");
		
		squadre.insert(s1);
		squadre.insert(s2);
		squadre.insert(s3);
		squadre.insert(s4);
		
		stadi.insert(st1);
		stadi.insert(st2);
		stadi.insert(st3);
		
		List<Partita> part = new ArrayList<>();
		part.add(new Partita(1, 2, 1, DataSource.randomSqlDate()));
		part.add(new Partita(1, 1, 2, DataSource.randomSqlDate())); // stessa squadra
		part.add(new Partita(1, 2, 3, DataSource.randomSqlDate())); // PK violation
		part.add(new Partita(1, 3, 3, DataSource.randomSqlDate())); // girone diverso
		part.add(new Partita(1, 4, 2, DataSource.randomSqlDate())); // categoria diversa diverso
		
		PrintWriter out = new PrintWriter(new FileWriter("Partite.txt", true));
		for (Partita p : part) {
			try {
				partite.insert(p);
			} catch (PersistenceException e) {
				e.printStackTrace();
				out.println(e.getMessage());
			}
		}
		out.close();
	}
}
