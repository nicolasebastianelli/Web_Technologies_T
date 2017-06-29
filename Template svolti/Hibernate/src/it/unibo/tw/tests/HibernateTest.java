package it.unibo.tw.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.jdbc.util.FormatStyle;
import org.hibernate.jdbc.util.Formatter;

import it.unibo.tw.beans.Accertamento;
import it.unibo.tw.beans.Paziente;
import it.unibo.tw.beans.RichiestaMedica;
import it.unibo.tw.beans.Struttura;
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
			sql="drop table TipoAccertamento_struttura";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="drop table accertamento";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="drop table richiesta_medica";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="drop table struttura";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="drop table paziente";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="drop table tipo_accertamento";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			// Create sql commands
			sql="create table tipo_accertamento (id bigint not null, codice integer not null unique, descrizione varchar(255), primary key (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="create table paziente (id bigint not null, codiceFiscale varchar(255) not null unique, nome varchar(255) not null, cognome varchar(255) not null, sesso char(1) not null, primary key (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="create table struttura (id bigint not null, codice integer not null unique, nome varchar(255) not null, indirizzo varchar(255) not null, primary key (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="create table richiesta_medica (id bigint not null, codicePaziente varchar(255) not null, data date not null, nomeMedico varchar(255) not null, paziente_id bigint, primary key (id), unique (codicePaziente, data), foreign key (paziente_id) references paziente (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="create table accertamento (id bigint not null, codice integer not null unique, esito varchar(255), tipo_accertamento_id bigint, richiesta_medica_id bigint, primary key (id), foreign key (tipo_accertamento_id) references tipo_accertamento (id), foreign key (richiesta_medica_id) references richiesta_medica (id))";
			System.out.println("Executing: "+sql);
			st.executeUpdate(sql);

			sql="create table TipoAccertamento_Struttura (tipo_accertamento_ids bigint not null, struttura_ids bigint not null, primary key (struttura_ids, tipo_accertamento_ids), foreign key (tipo_accertamento_ids) references tipo_accertamento (id), foreign key (struttura_ids) references struttura (id))";
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

			Paziente p1 = new Paziente("paziente1", "nome1", "cognome1", 'M');
			Paziente p2 = new Paziente("paziente2", "nome2", "cognome2", 'F');

			RichiestaMedica rm1 = new RichiestaMedica("paziente1", "medico1",
					new GregorianCalendar(2016, 4, 1).getTime());
			RichiestaMedica rm2 = new RichiestaMedica("paziente1", "medico2",
					new GregorianCalendar(2016, 4, 2).getTime());
			RichiestaMedica rm3 = new RichiestaMedica("paziente2", "medico3",
					new GregorianCalendar(2016, 4, 3).getTime());
			RichiestaMedica rm4 = new RichiestaMedica("paziente2", "medico4",
					new GregorianCalendar(2016, 4, 4).getTime());

			p1.getRichiesteMediche().add(rm1);
			p1.getRichiesteMediche().add(rm2);
			p2.getRichiesteMediche().add(rm3);
			p2.getRichiesteMediche().add(rm4);

			rm1.setPaziente(p1);
			rm2.setPaziente(p1);
			rm3.setPaziente(p2);
			rm4.setPaziente(p2);
			rm1.setCodicePaziente(p1.getCodiceFiscale());
			rm2.setCodicePaziente(p1.getCodiceFiscale());
			rm3.setCodicePaziente(p2.getCodiceFiscale());
			rm4.setCodicePaziente(p2.getCodiceFiscale());

			Accertamento a1 = new Accertamento(111, "esito111");
			Accertamento a2 = new Accertamento(222, "esito222");
			Accertamento a3 = new Accertamento(333, "esito333");
			Accertamento a4 = new Accertamento(444, "esito444");
			Accertamento a5 = new Accertamento(555, "esito555");
			Accertamento a6 = new Accertamento(666, "esito666");

			rm1.getAccertamenti().add(a1);
			rm1.getAccertamenti().add(a2);
			rm2.getAccertamenti().add(a3);
			rm2.getAccertamenti().add(a4);
			rm3.getAccertamenti().add(a5);
			rm4.getAccertamenti().add(a6);

			a1.setRichiestaMedica(rm1);
			a2.setRichiestaMedica(rm1);
			a3.setRichiestaMedica(rm2);
			a4.setRichiestaMedica(rm2);
			a5.setRichiestaMedica(rm3);
			a6.setRichiestaMedica(rm4);

			TipoAccertamento ta1 = new TipoAccertamento(1, "risonanza");
			TipoAccertamento ta2 = new TipoAccertamento(2, "acc2");
			TipoAccertamento ta3 = new TipoAccertamento(3, "acc3");

			a1.setTipoAccertamento(ta1);
			a2.setTipoAccertamento(ta1);
			a3.setTipoAccertamento(ta2);
			a4.setTipoAccertamento(ta2);
			a5.setTipoAccertamento(ta3);
			a6.setTipoAccertamento(ta3);

			ta1.getAccertamentiDiQuestoTipo().add(a1);
			ta1.getAccertamentiDiQuestoTipo().add(a2);
			ta2.getAccertamentiDiQuestoTipo().add(a3);
			ta2.getAccertamentiDiQuestoTipo().add(a4);
			ta3.getAccertamentiDiQuestoTipo().add(a5);
			ta3.getAccertamentiDiQuestoTipo().add(a6);

			Struttura s1 = new Struttura(11, "struttura1", "indirizzo1");
			Struttura s2 = new Struttura(22, "struttura2", "indirizzo2");
			Struttura s3 = new Struttura(33, "struttura3", "indirizzo3");

			ta1.getStruttureCheLoOffrono().add(s1);
			ta1.getStruttureCheLoOffrono().add(s2);
			ta1.getStruttureCheLoOffrono().add(s3);
			ta2.getStruttureCheLoOffrono().add(s1);
			ta2.getStruttureCheLoOffrono().add(s2);
			ta3.getStruttureCheLoOffrono().add(s3);

			s1.getTipiDiAccertamentoOfferti().add(ta1);
			s1.getTipiDiAccertamentoOfferti().add(ta2);
			s2.getTipiDiAccertamentoOfferti().add(ta1);
			s2.getTipiDiAccertamentoOfferti().add(ta2);
			s3.getTipiDiAccertamentoOfferti().add(ta1);
			s3.getTipiDiAccertamentoOfferti().add(ta3);

			session.persist(p1);
			session.persist(p2);

			session.persist(rm1);
			session.persist(rm2);
			session.persist(rm3);
			session.persist(rm4);

			session.persist(a1);
			session.persist(a2);
			session.persist(a3);
			session.persist(a4);
			session.persist(a5);
			session.persist(a6);

			session.persist(ta1);
			session.persist(ta2);
			session.persist(ta3);

			session.persist(s1);
			session.persist(s2);
			session.persist(s3);

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
			String medico = "";
			int countmax=0;
			Map<String, Integer> medici = new HashMap<String, Integer>();
			Query query1 = session.createQuery("from "+Paziente.class.getSimpleName()+" where sesso = ?");
			query1.setCharacter(0, 'F');
			List<Paziente> pazienti = query1.list();
			for (Paziente paziente : pazienti) {
				for (RichiestaMedica richiesta : paziente.getRichiesteMediche()) {
					if (!medici.containsKey(richiesta.getNomeMedico()))
						medici.put(richiesta.getNomeMedico(), 0);
					for (Accertamento accertamento : richiesta.getAccertamenti()) {
						if(accertamento.getTipoAccertamento().getDescrizione().equals("risonanza"));
							medici.put(richiesta.getNomeMedico(), medici.get(richiesta.getNomeMedico())+1);
					}
					for (String key : medici.keySet()) {
						if( medici.get(key)>countmax){
							medico=richiesta.getNomeMedico();
							countmax=medici.get(key);
						}
					}
				}
			}
			System.out.println("Medico: "+medico);
			
			
			File f = new File("Result.txt");
			if(!f.exists())
			{
				f.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter out = new PrintWriter(fos);
			out.println("Query 1: Medico "+medico);
			out.close();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			session.close();
		}
	}


}
