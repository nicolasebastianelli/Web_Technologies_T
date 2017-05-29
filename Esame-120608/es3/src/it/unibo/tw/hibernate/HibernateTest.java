package it.unibo.tw.hibernate;

import it.unibo.tw.beans.Party;
import it.unibo.tw.beans.Partecipante;

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

			String username = "xxx";
			String password = "xxx";

			Connection conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();

			String sql;
			
			//Dropping previously created tables
			try{
				//Insert table name
				sql="drop table party_partecipanti";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);
				
				sql="drop table partecipanti";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);
				
				sql="drop table party";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);
			}
			catch(Exception e)
			{
				//Table doesn't exist
			}
			//Operation must be repeated for each table in order to drop them
			
			
			//Creation sql command
			sql = "CREATE TABLE party " +
					"( " +
						"id BIGINT NOT NULL PRIMARY KEY," +
						"titolo VARCHAR(50) NOT NULL, " +
						"luogo VARCHAR(50) NOT NULL, " +
						"data DATE NOT NULL" +
					") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);
			
			sql = "CREATE TABLE partecipanti " +
			"( " +
				"id BIGINT NOT NULL PRIMARY KEY," +
				"nickname VARCHAR(10) NOT NULL UNIQUE, " +
				"nome VARCHAR(50) NOT NULL, " +
				"cognome VARCHAR(50) NOT NULL, " +
				"eta INT NOT NULL" +
			") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);
			
			sql = "CREATE TABLE party_partecipanti " +
			"( " +
				"idparty BIGINT NOT NULL REFERENCES party, " +
				"idpartecipante BIGINT NOT NULL REFERENCES partecipanti," +
				"PRIMARY KEY(idparty, idpartecipante) " +
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
			Party p1 = new Party();
			p1.setLuogo("Bologna");
			p1.setTitolo("Silence party");
			p1.setData(Calendar.getInstance().getTime());
			session.saveOrUpdate(p1);
			
			Party p2 = new Party();
			p2.setLuogo("Roma");
			p2.setTitolo("Techo party");
			p2.setData(Calendar.getInstance().getTime());
			session.saveOrUpdate(p2);
			//The above lines must be repeated for each entry

			
			Partecipante pa1 = new Partecipante();
			pa1.setNickname("nick1");
			pa1.setNome("nome1");
			pa1.setCognome("cognome1");
			pa1.setEta(1);
			session.saveOrUpdate(pa1);
			
			Partecipante pa2 = new Partecipante();
			pa2.setNickname("nick2");
			pa2.setNome("nome2");
			pa2.setCognome("cognome2");
			pa2.setEta(2);
			session.saveOrUpdate(pa2);
			
			Partecipante pa3 = new Partecipante();
			pa3.setNickname("nick3");
			pa3.setNome("nome3");
			pa3.setCognome("cognome3");
			pa3.setEta(3);
			session.saveOrUpdate(pa3);
			
			Partecipante pa4 = new Partecipante();
			pa4.setNickname("nick4");
			pa4.setNome("nome4");
			pa4.setCognome("cognome4");
			pa4.setEta(4);
			session.saveOrUpdate(pa4);
		
			//Updates on entries after having created them all
			
			Set<Partecipante> partecipanti = new HashSet<Partecipante>();
			partecipanti.add(pa1);
			partecipanti.add(pa2);
			partecipanti.add(pa3);
			p1.setPartecipanti(partecipanti);
			session.saveOrUpdate(p1);
			
			partecipanti = new HashSet<Partecipante>();
			partecipanti.add(pa2);
			partecipanti.add(pa3);
			partecipanti.add(pa4);
			p2.setPartecipanti(partecipanti);
			session.saveOrUpdate(p2);
			
			Set<Party> parties_p1 = new HashSet<Party>();
			pa1.setParty(parties_p1);
			pa1.getParty().add(p1);
			
			Set<Party> parties_p2 = new HashSet<Party>();
			pa2.setParty(parties_p2);
			pa2.getParty().add(p1);
			pa2.getParty().add(p2);
			
			Set<Party> parties_p3 = new HashSet<Party>();
			pa3.setParty(parties_p3);
			pa3.getParty().add(p1);
			pa3.getParty().add(p2);
			
			Set<Party> parties_p4 = new HashSet<Party>();
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
			Query query1 = session.createQuery("from "+Partecipante.class.getSimpleName()+" where nickname= ?");
			String nick = "nick2";
			query1.setString(0, nick);
			System.out.println("Cercando party del partecipante "+nick+"...");
			Partecipante p = (Partecipante)query1.uniqueResult();
			if(p != null)
			{
				Set<Party> searchedParty = p.getParty();
				for (Party party : searchedParty) {
					System.out.println(nick+" Partecipa alla festa "+party.getTitolo());
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
			Query query2 = session.createQuery("from "+Party.class.getSimpleName()+" where titolo= ?");
			String titolo = "Silence party";
			query2.setString(0, titolo);
			System.out.println("Cercando partecipanti al party "+titolo+"...");
			Party p = (Party)query2.uniqueResult();
			if(p != null)
			{
				Set<Partecipante> searchedPartecipante = p.getPartecipanti();
				for (Partecipante partecipante : searchedPartecipante) {
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
