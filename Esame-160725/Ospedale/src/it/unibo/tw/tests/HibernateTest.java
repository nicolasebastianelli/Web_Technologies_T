package it.unibo.tw.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.jdbc.util.FormatStyle;
import org.hibernate.jdbc.util.Formatter;

import it.unibo.tw.beans.Accertamento;
import it.unibo.tw.beans.Ospedale;
import it.unibo.tw.beans.TipoAccertamento;

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
			sql="drop table TipoAccertamento_Ospedale";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="drop table accertamenti";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="drop table ospedali";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="drop table tipo_accertamento";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			// Create sql commands
			sql="create table tipo_accertamento (id bigint not null, codice integer not null unique, descrizione varchar(255), primary key (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="create table ospedali (id bigint not null, codice integer not null unique, nome varchar(255) not null, indirizzo varchar(255) not null,citta varchar(255) not null, primary key (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="create table accertamenti (id bigint not null, codice integer not null unique,nome varchar(255), descrizione varchar(255), tipo_accertamento_id bigint, primary key (id), foreign key (tipo_accertamento_id) references tipo_accertamento (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="create table TipoAccertamento_Ospedale (idtipiaccertamento bigint not null, idospedale bigint not null, primary key (idospedale, idtipiaccertamento), foreign key (idtipiaccertamento) references tipo_accertamento (id), foreign key (idospedale) references ospedali (id))";
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

			TipoAccertamento ta1 = new TipoAccertamento(1, "Analisi di Laboratorio");
			TipoAccertamento ta2 = new TipoAccertamento(2, "acc2");
			TipoAccertamento ta3 = new TipoAccertamento(3, "acc3");
			
			Accertamento a1 = new Accertamento(111, "n1","d1", ta1);
			Accertamento a2 = new Accertamento(222, "n2","d2",ta1);
			Accertamento a3 = new Accertamento(333, "n3","d3",ta1);
			Accertamento a4 = new Accertamento(444, "n4","d4",ta2);
			Accertamento a5 = new Accertamento(555, "n5","d5",ta2);
			Accertamento a6 = new Accertamento(666, "n6","d6",ta3);

			ta1.getAccertamenti().add(a1);
			ta1.getAccertamenti().add(a2);
			ta1.getAccertamenti().add(a3);
			ta2.getAccertamenti().add(a4);
			ta2.getAccertamenti().add(a5);
			ta3.getAccertamenti().add(a6);

			Ospedale s1 = new Ospedale(11, "Policlinico S.Orsola-Malpighi","bologna", "indirizzo1");
			Ospedale s2 = new Ospedale(22, "struttura2","bologna", "indirizzo2");
			Ospedale s3 = new Ospedale(33, "struttura3","bologna", "indirizzo3");

			ta1.getOspedali().add(s1);
			ta1.getOspedali().add(s2);
			ta1.getOspedali().add(s3);
			ta2.getOspedali().add(s1);
			ta2.getOspedali().add(s2);
			ta3.getOspedali().add(s3);

			s1.getTipiaccertamento().add(ta1);
			s1.getTipiaccertamento().add(ta2);
			s2.getTipiaccertamento().add(ta1);
			s2.getTipiaccertamento().add(ta2);
			s3.getTipiaccertamento().add(ta1);
			s3.getTipiaccertamento().add(ta3);
			
			session.saveOrUpdate(a1);
			session.saveOrUpdate(a2);
			session.saveOrUpdate(a3);
			session.saveOrUpdate(a4);
			session.saveOrUpdate(a5);
			session.saveOrUpdate(a6);

			session.saveOrUpdate(ta1);
			session.saveOrUpdate(ta2);
			session.saveOrUpdate(ta3);
			
			session.saveOrUpdate(s1);
			session.saveOrUpdate(s2);
			session.saveOrUpdate(s3);

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
			Query query1 = session.createQuery("from "+Ospedale.class.getSimpleName()+" where nome = ? and citta =?");
			Set<Accertamento> res1 = new HashSet<Accertamento>();
			query1.setString(0, "Policlinico S.Orsola-Malpighi");
			query1.setString(1, "bologna");
			Ospedale ospedale = (Ospedale) query1.uniqueResult(); 
			for (TipoAccertamento tipoacc : ospedale.getTipiaccertamento()) {
				if(tipoacc.getDescrizione().equals("Analisi di Laboratorio"))
					res1 = tipoacc.getAccertamenti();
			}
			System.out.println("Nome accertamenti: ");
			for (Accertamento accertamento : res1) {
				System.out.println(accertamento.getNome());
			}
			Query query2 = session.createQuery("from "+Ospedale.class.getSimpleName());
			List<Ospedale> ospedali =query2.list();
			System.out.println("Ospedali: ");
			for (Ospedale ospedale2 : ospedali) {
				Set<TipoAccertamento> tipiacc =ospedale2.getTipiaccertamento();
				System.out.println(ospedale2.getNome()+", "+ospedale2.getCitta()+", "+ospedale2.getIndirizzo()+", "+tipiacc.size());
				System.out.println();
			}
			File f = new File("Ospedale.txt");
			if(!f.exists())
			{
				f.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter out = new PrintWriter(fos);
			out.println("Query 1: Nome accertamenti: ");
			for (Accertamento accertamento : res1) {
				out.println(accertamento.getNome());
			}
			out.println();
			out.println("Query 2: Ospedali: ");
			for (Ospedale ospedale2 : ospedali) {
				Set<TipoAccertamento> tipiacc =ospedale2.getTipiaccertamento();
				out.println(ospedale2.getNome()+", "+ospedale2.getCitta()+", "+ospedale2.getIndirizzo()+", "+tipiacc.size());
				out.println();
			}
			out.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			session.close();
		}
	}


}
