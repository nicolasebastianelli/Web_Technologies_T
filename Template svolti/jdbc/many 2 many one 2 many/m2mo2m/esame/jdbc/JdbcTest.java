package m2mo2m.esame.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcTest {
	private static CorsoRepository cr;
	private static DocenteRepository dr;
	private static DidatticaRepository dd;

	public static void main(String[] args) throws PersistenceException {
		tableCreation();
		enterEntries();
		queries();
	}

	private static void tableCreation() throws PersistenceException {
		cr = new CorsoRepository();
		dr = new DocenteRepository();
		dd = new DidatticaRepository();

		dd.dropTable();
		cr.dropAndCreateTable();
		dr.dropAndCreateTable();
		dd.createTable();
	}

	private static void enterEntries() throws PersistenceException {
		Corso c1 = new Corso(1, "c1", 6);
		Corso c2 = new Corso(2, "c2", 9);
		Corso c3 = new Corso(3, "c3", 6);
		Corso c4 = new Corso(4, "c4", 12);

		Docente d1 = new Docente(1, "d1", "d1");
		Docente d2 = new Docente(2, "d2", "d2");
		Docente d3 = new Docente(3, "d3", "d3");
		Docente d4 = new Docente(4, "d4", "d4");

		Didattica dd1 = new Didattica(1, 1);
		Didattica dd2 = new Didattica(1, 2);
		Didattica dd3 = new Didattica(2, 3);
		Didattica dd4 = new Didattica(2, 4);
		Didattica dd5 = new Didattica(3, 1);
		Didattica dd6 = new Didattica(3, 3);
		Didattica dd7 = new Didattica(4, 2);
		Didattica dd8 = new Didattica(4, 4);

		cr.insert(c1);
		cr.insert(c2);
		cr.insert(c3);
		cr.insert(c4);

		dr.insert(d1);
		dr.insert(d2);
		dr.insert(d3);
		dr.insert(d4);

		dd.insert(dd1);
		dd.insert(dd2);
		dd.insert(dd3);
		dd.insert(dd4);
		dd.insert(dd5);
		dd.insert(dd6);
		dd.insert(dd7);
		dd.insert(dd8);

		try {
			dd.insert(new Didattica(99, 110));
		} catch (Exception e) {
			// expected
		}
	}

	private static void queries() {
		String result = "";
		String innerSql = "select D.MatricolaDocente as matricola, count(*) as count from docente D, corso C, didattica DC where D.MatricolaDocente=DC.Docente and C.CodiceCorso=DC.Corso and C.creditiformativi=6 group by D.MatricolaDocente";
		String outerSql = "select D.nome, D.cognome, Dcount.count from (" + innerSql
				+ ") Dcount, docente D where D.matricoladocente=Dcount.matricola";

		try {
			Statement statement = new DataSource(DataSource.DB2).getConnection().createStatement();
			ResultSet results = statement.executeQuery(outerSql);
			String nome = "";
			String cognome = "";
			int max = 0;
			int count = 0;
			while (results.next()) {
				count = results.getInt("count");
				if (count > max) {
					max = count;
					nome = results.getString("nome");
					cognome = results.getString("cognome");
				}
			}
			System.out.println(nome + " " + cognome + " " + count);

		} catch (Exception e) {
			throw new javax.persistence.PersistenceException(e);
		}

		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream("didattica.txt", true));
			pw.println(result);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
