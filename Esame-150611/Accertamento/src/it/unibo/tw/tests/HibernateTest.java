package it.unibo.tw.tests;

import it.unibo.tw.beans.Struttura;
import it.unibo.tw.beans.TipoAccertamento;
import it.unibo.tw.beans.Accertamento;
import it.unibo.tw.beans.Paziente;
import it.unibo.tw.beans.RichiestaMedica;
import it.unibo.tw.beans.SqlUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class HibernateTest {

	public static void main(String[] args) 
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		//TABLE CREATION
		try{
			//Unibo Intranet DB2 tw_stud connection
			//Class.forName("COM.ibm.db2.jdbc.app.DB2Driver").newInstance();
			//String url = "jdbc:db2:tw_stud";

			//Remote DB2 tw_stud connection
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			String url = "jdbc:db2://diva.deis.unibo.it:50000/tw_stud";

			String username = "00722894";
			String password = "Nicola31";

			Connection conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();

			String sql;

			//Dropping previously created tables
			try{
				//Insert table name
				sql="drop table pazienti";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);

				sql="drop table richiestemediche";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);

				sql="drop table accertamenti";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);

				sql="drop table tipiaccertamento";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);

				sql="drop table tipi_strutture";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);

				sql="drop table struttura";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);
			}
			catch(Exception e)
			{
				//Table doesn't exist
			}
			//Operation must be repeated for each table in order to drop them


			//Creation sql command
			sql = "CREATE TABLE pazienti " +
					"( " +
					"id BIGINT NOT NULL PRIMARY KEY," +
					"cd VARCHAR(50) NOT NULL UNIQUE, " +
					"nome VARCHAR(50) NOT NULL, " +
					"cognome VARCHAR(50) NOT NULL, " +
					"sesso CHAR NOT NULL" +
					") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql = "CREATE TABLE richiestemediche " +
					"( " +
					"id BIGINT NOT NULL PRIMARY KEY," +
					"codicepaziente VARCHAR(10) NOT NULL UNIQUE, " +
					"data DATE NOT NULL UNIQUE, " +
					"nomemedico VARCHAR(50) NOT NULL, " +
					") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql = "ALTER TABLE richiestemediche " +
					"ADD CONSTRAINT richiestemediche UNIQUE(codicepaziente, data) ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql = "CREATE TABLE accertamenti " +
					"( " +
					"idaccertamento BIGINT NOT NULL PRIMARY KEY," +
					"codice VARCHAR(10) NOT NULL UNIQUE, " +
					"esito VARCHAR(50) NOT NULL, " +
					") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql = "CREATE TABLE tipiaccertamento " +
					"( " +
					"id BIGINT NOT NULL PRIMARY KEY," +
					"codice VARCHAR(10) NOT NULL UNIQUE, " +
					"descrizione VARCHAR(50) NOT NULL, " +
					") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);
			
			sql = "CREATE TABLE strutture " +
					"( " +
					"id BIGINT NOT NULL PRIMARY KEY," +
					"codice VARCHAR(10) NOT NULL UNIQUE, " +
					"nome VARCHAR(50) NOT NULL, " +
					"indirizzo VARCHAR(50) NOT NULL, " +
					") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql = "CREATE TABLE tipi_strutture " +
					"( " +
					"idstruttura BIGINT NOT NULL REFERENCES party, " +
					"idtipoaccertamento BIGINT NOT NULL REFERENCES partecipanti," +
					"PRIMARY KEY(idstruttura, idtipoaccertamento) " +
					") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);
			System.out.println("Done");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}

		//INSERT ENTRIES
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			//Object creation process
			Paziente p1 = new Paziente();
			p1.setCf("cf1");
			p1.setNome("p1");
			p1.setCognome("p1");
			p1.setSesso('F');
			session.saveOrUpdate(p1);

			Paziente p2 = new Paziente();
			p2.setCf("cf2");
			p2.setNome("p2");
			p2.setCognome("p2");
			p2.setSesso('F');
			session.saveOrUpdate(p1);
			//The above lines must be repeated for each entry


			RichiestaMedica r1 = new RichiestaMedica();
			r1.setIdpaziente("r1");
			r1.setData(SqlUtils.randomSqlDate());
			r1.setNomemedico("Mario Rossi");
			session.saveOrUpdate(r1);

			RichiestaMedica r2 = new RichiestaMedica();
			r2.setIdpaziente("r2");
			r2.setData(SqlUtils.randomSqlDate());
			r2.setNomemedico("Mario Rossi");
			session.saveOrUpdate(r2);

			Accertamento a1 = new Accertamento();
			a1.setCodice("a1");
			a1.setEsito("e1");;
			session.saveOrUpdate(a1);

			Accertamento a2 = new Accertamento();
			a2.setCodice("a2");
			a2.setEsito("e1");;
			session.saveOrUpdate(a2);

			
			TipoAccertamento t1 = new TipoAccertamento();
			t1.setCodice("t1");
			t1.setDescrizione("risonanza");
			session.saveOrUpdate(t1);

			TipoAccertamento t2 = new TipoAccertamento();
			t2.setCodice("t2");
			t2.setDescrizione("risonanza");
			session.saveOrUpdate(t1);
			
			Struttura s1 = new Struttura();
			s1.setCodice("s1");
			s1.setIndirizzo("i1");
			s1.setNome("n1");
			session.saveOrUpdate(s1);
			
			Struttura s2 = new Struttura();
			s1.setCodice("s2");
			s1.setIndirizzo("i2");
			s1.setNome("n2");
			session.saveOrUpdate(s1);
			//Updates on entries after having created them all

			Set<Accertamento> accertamenti =new HashSet<Accertamento>();
			accertamenti.add(a1);
			r1.setAccertamenti(accertamenti);
			session.saveOrUpdate(r1);
			
			accertamenti =new HashSet<Accertamento>();
			accertamenti.add(a2);
			r2.setAccertamenti(accertamenti);
			session.saveOrUpdate(r2);
			
			Set<RichiestaMedica> richieste = new HashSet<RichiestaMedica>();
			richieste.add(r1);
			p1.setRichiesteMediche(richieste);
			session.saveOrUpdate(p1);

			richieste = new HashSet<RichiestaMedica>();
			richieste.add(r2);
			p1.setRichiesteMediche(richieste);
			session.saveOrUpdate(p2);
			
			partecipanti = new HashSet<Paziente>();
			partecipanti.add(pa2);
			partecipanti.add(pa3);
			partecipanti.add(pa4);
			p2.setPartecipanti(partecipanti);
			session.saveOrUpdate(p2);

			Set<Struttura> parties_p1 = new HashSet<Struttura>();
			pa1.setParty(parties_p1);
			pa1.getParty().add(p1);

			Set<Struttura> parties_p2 = new HashSet<Struttura>();
			pa2.setParty(parties_p2);
			pa2.getParty().add(p1);
			pa2.getParty().add(p2);

			Set<Struttura> parties_p3 = new HashSet<Struttura>();
			pa3.setParty(parties_p3);
			pa3.getParty().add(p1);
			pa3.getParty().add(p2);

			Set<Struttura> parties_p4 = new HashSet<Struttura>();
			pa4.setParty(parties_p4);
			pa4.getParty().add(p2);

			session.saveOrUpdate(pa1);
			session.saveOrUpdate(pa2);
			session.saveOrUpdate(pa3);
			session.saveOrUpdate(pa4);



			tx.commit();
		}
		catch (Exception e1) {
			if (tx != null){
				try{
					tx.rollback();
				}
				catch(Exception e2){
					e2.printStackTrace();
				}
			}
			e1.printStackTrace();
		}
		finally {
			session.close();
		}

		//QUERIES
		try{
			session = sessionFactory.openSession();

			//HQL Query
			Query query1 = session.createQuery("from "+Paziente.class.getSimpleName()+" where sesso= F");
			Query query2 = session.createQuery("from "+TipoAccertamento.class.getSimpleName()+" where descrizione='risonanza'");
			System.out.println("Cercando Medico per pazienti sesso femminile per  accertamento risonanza");
			Paziente p = (Paziente)query1.uniqueResult();
			if(p != null)
			{
				Set<RichiestaMedica> richieste = p.getRichiesteMediche();
				for (RichiestaMedica richiesta : richieste) {
					if(richiesta.getNomemedico().equals("Mario Rossi")){
						Set<Accertamento> accertamenti = richiesta.getAccertamenti();
						for (Accertamento accertamento : accertamenti) {
								TipoAccertamento t =(TipoAccertamento)query2.uniqueResult();
							}
								
						}
					
					}
				}
			}

			System.out.println();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		finally {
			session.close();
		}

		try{
			session = sessionFactory.openSession();

			//HQL Query
			Query query2 = session.createQuery("from "+Struttura.class.getSimpleName()+" where titolo= ?");
			String titolo = "Silence party";
			query2.setString(0, titolo);
			System.out.println("Cercando partecipanti al party "+titolo+"...");
			Struttura p = (Struttura)query2.uniqueResult();
			if(p != null)
			{
				Set<Paziente> searchedPartecipante = p.getPartecipanti();
				for (Paziente partecipante : searchedPartecipante) {
					System.out.println(titolo+" Partecipa alla festa "+partecipante.getNickname());
				}
			}

			System.out.println();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		finally {
			session.close();
		}
	}

}
