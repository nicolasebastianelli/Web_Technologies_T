package it.unibo.tw.hibernate;

import it.unibo.tw.beans.Corso;
import it.unibo.tw.beans.Studente;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;


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
				sql="drop table corsi_studenti";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);
				
				sql="drop table studenti";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);
				
				sql="drop table corsi";
				System.out.println("Executing: "+sql);
				st.executeUpdate(sql);
			}
			catch(Exception e)
			{
				//Table doesn't exist
			}
			//Operation must be repeated for each table in order to drop them
			
			
			//Creation sql command
			sql = "CREATE TABLE corsi " +
					"( " +
						"id BIGINT NOT NULL PRIMARY KEY," +
						"codice INT NOT NULL UNIQUE, " +
						"titolo VARCHAR(50) NOT NULL, " +
						"crediti_formativi INT NOT NULL, " +
						"nome_docente VARCHAR(50) NOT NULL" +
					") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);
			
			sql = "CREATE TABLE studenti " +
			"( " +
				"id BIGINT NOT NULL PRIMARY KEY," +
				"matricola VARCHAR(10) NOT NULL UNIQUE, " +
				"nome VARCHAR(50) NOT NULL, " +
				"cognome VARCHAR(50) NOT NULL, " +
				"sesso CHAR(1) NOT NULL, " +
				"data_nascita DATE NOT NULL" +
			") ";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);
			
			sql = "CREATE TABLE corsi_studenti " +
			"( " +
				"idcorso BIGINT NOT NULL REFERENCES corsi, " +
				"idstudente BIGINT NOT NULL REFERENCES studenti," +
				"PRIMARY KEY(idstudente, idcorso) " +
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
			Corso c1 = new Corso();
			c1.setCodice(25);
			c1.setTitolo("corso1");
			c1.setCrediti_formativi(6);
			c1.setNome_docente("docente1");
			session.saveOrUpdate(c1);
			
			Corso c2 = new Corso();
			c2.setCodice(26);
			c2.setTitolo("corso2");
			c2.setCrediti_formativi(12);
			c2.setNome_docente("docente2");
			session.saveOrUpdate(c2);
			//The above lines must be repeated for each entry

			
			Studente s1 = new Studente();
			s1.setMatricola("0000111111");
			s1.setNome("nome1");
			s1.setCognome("cognome1");
			s1.setSesso("M");
			s1.setData_nascita(Calendar.getInstance().getTime());
			session.saveOrUpdate(s1);
			
			Studente s2 = new Studente();
			s2.setMatricola("0000222222");
			s2.setNome("nome2");
			s2.setCognome("cognome2");
			s2.setSesso("M");
			s2.setData_nascita(Calendar.getInstance().getTime());
			session.saveOrUpdate(s2);
			
			Studente s3 = new Studente();
			s3.setMatricola("0000333333");
			s3.setNome("nome3");
			s3.setCognome("cognome3");
			s3.setSesso("F");
			s3.setData_nascita(Calendar.getInstance().getTime());
			session.saveOrUpdate(s3);
			
			Studente s4 = new Studente();
			s4.setMatricola("0000444444");
			s4.setNome("nome4");
			s4.setCognome("cognome4");
			s4.setSesso("M");
			s4.setData_nascita(Calendar.getInstance().getTime());
			session.saveOrUpdate(s4);
			
			Studente s5 = new Studente();
			s5.setMatricola("0000555555");
			s5.setNome("nome5");
			s5.setCognome("cognome5");
			s5.setSesso("F");
			s5.setData_nascita(Calendar.getInstance().getTime());
			session.saveOrUpdate(s5);
			
			Studente s6 = new Studente();
			s6.setMatricola("0000666666");
			s6.setNome("nome6");
			s6.setCognome("cognome6");
			s6.setSesso("F");
			s6.setData_nascita(Calendar.getInstance().getTime());
			session.saveOrUpdate(s6);
			//Updates on entries after having created them all
			
			Set<Studente> studenti = new HashSet<Studente>();
			studenti.add(s1);
			studenti.add(s2);
			studenti.add(s3);
			c1.setStudenti(studenti);
			session.saveOrUpdate(c1);
			
			studenti = new HashSet<Studente>();
			studenti.add(s2);
			studenti.add(s3);
			studenti.add(s4);
			studenti.add(s5);
			studenti.add(s6);
			c2.setStudenti(studenti);
			session.saveOrUpdate(c2);
			
			session.saveOrUpdate(s1);
			session.saveOrUpdate(s2);
			session.saveOrUpdate(s3);
			session.saveOrUpdate(s4);
			session.saveOrUpdate(s5);
			session.saveOrUpdate(s6);

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
			Query query1 = session.createQuery("from "+Corso.class.getSimpleName()+" where codice= ?");
			int codice = 26;
			query1.setInteger(0, codice);
			System.out.println("Retrieving number of women attending course "+codice+"...");
			Corso c = (Corso)query1.uniqueResult();
			if(c != null)
			{
				int res = 0;
				for(Studente s : c.getStudenti())
				{
					if(s.getSesso().equals("F"))
					{
						res++;
					}
				}
				if(res == 0)
				{
					System.out.println("No women are attending course "+codice);
				}
				else
				{
					System.out.println(res+" women are attending course "+codice);
					
					System.out.println("Printing on Studentesse.txt the details of the students just found...");
					File f = new File("Studentesse.txt");
					if(!f.exists())
					{
						f.createNewFile();
					}
					FileOutputStream fos = new FileOutputStream(f);
					PrintWriter out = new PrintWriter(fos);
					for(Studente s : c.getStudenti())
					{
						if(s.getSesso().equals("F"))
						{
							System.out.println(s.getNome()+" "+s.getCognome()+", Nata il "+s.getData_nascita().toString());
							out.println(s.getNome()+" "+s.getCognome()+", Nata il "+s.getData_nascita().toString());
						}
					}
					out.close();
					System.out.println("Written to file!");
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
