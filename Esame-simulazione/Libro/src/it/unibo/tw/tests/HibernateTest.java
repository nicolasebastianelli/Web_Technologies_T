package it.unibo.tw.tests;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.jdbc.util.FormatStyle;
import org.hibernate.jdbc.util.Formatter;

import it.unibo.tw.beans.Libro;
import it.unibo.tw.beans.Autore;


public class HibernateTest {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();

		System.out.println("\n\n----------------- DDL STATEMENTS ------------------------------------");
		{
			Dialect dialect = Dialect.getDialect(configuration.getProperties());
			String[] createSQL = configuration.generateSchemaCreationScript(dialect);
			String[] dropSQL = configuration.generateDropSchemaScript(dialect);

			Formatter formatter = FormatStyle.DDL.getFormatter();
			try (PrintWriter writer = new PrintWriter(new FileOutputStream("ddl.sql"))) {
				for (String string : dropSQL) {
					System.out.println(formatter.format(string) + ";");
					writer.println(string + ";");
				}
				System.out.println("\n\n\n");
				writer.println("\n\n\n");
				for (String string : createSQL) {
					System.out.println(formatter.format(string) + ";");
					writer.println(string + ";");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		Connection conn = null;
		Session session = null;
		Transaction tx = null;
		try {
			// Local mysql tw_stud connection
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://diva.deis.unibo.it:50000/tw_stud";

			String username = "00722894";
			String password = "Nicola31";

			conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
			String sql;


			System.out.println("\n\n----------------- TABLE CREATION ------------------------------------");
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// Drop sql commands
			sql="drop table libri";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="drop table autori";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);


			// Create sql commands

			sql="create table autori (id bigint not null, codiceFiscale varchar(255) not null unique, nome varchar(255) not null, cognome varchar(255) not null, primary key (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="create table libri (id bigint not null, isbn varchar(255) not null unique, titolo varchar(255) not null, idautore bigint not null, primary key (id), foreign key (idautore) references autori (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);


			// executing

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Done");
			session.close();
		}

		// INSERT ENTRIES
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Autore p1 = new Autore("cf1", "n1", "c1");
			Autore p2 = new Autore("cf2", "n2", "c2");
			Autore p3 = new Autore("cf3", "n3", "c3");

			Libro a1 = new Libro("i1", "t1" , p1);
			Libro a2 = new Libro("i2", "t2" , p2);
			Libro a3 = new Libro("i3", "t3" , p2);
			Libro a4 = new Libro("i4", "t4" , p3);
			Libro a5 = new Libro("i5", "t5" , p3);
			Libro a6 = new Libro("i6", "t6" , p3);

			p1.getListalibri().add(a1);
			p2.getListalibri().add(a2);
			p2.getListalibri().add(a3);
			p3.getListalibri().add(a4);
			p3.getListalibri().add(a5);
			p3.getListalibri().add(a6);

			session.persist(p1);
			session.persist(p2);
			session.persist(p3);

			session.persist(a1);
			session.persist(a2);
			session.persist(a3);
			session.persist(a4);
			session.persist(a5);
			session.persist(a6);

			tx.commit();
		} catch (Exception e1) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			e1.printStackTrace();
		} finally {
			session.close();
		}

		// QUERIES
		try {
			session = sessionFactory.openSession();
			Query query1 = session.createQuery("from "+Autore.class.getSimpleName()+" where codicefiscale = ?");
			query1.setString(0, "cf3");
			Autore autore = (Autore) query1.uniqueResult();
			System.out.println("Libri di "+ autore.getNome()+" "+autore.getCognome());
			for (Libro libro : autore.getListalibri()) {
				System.out.println(libro.getTitolo());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
