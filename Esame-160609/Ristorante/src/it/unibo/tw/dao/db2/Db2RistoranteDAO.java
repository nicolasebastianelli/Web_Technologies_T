package it.unibo.tw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.unibo.tw.dao.Db2RistoranteProxy;
import it.unibo.tw.dao.PiattoDTO;
import it.unibo.tw.dao.RistoranteDAO;
import it.unibo.tw.dao.RistoranteDTO;

/**
 * Implementazione di query jdbc su una versione hqldb della tabella entry
 * 
 * La scrittura dei metodi DAO con JDBC segue sempre e comunque questo schema:
 * 
	// --- 1. Dichiarazione della variabile per il risultato ---
	// --- 2. Controlli preliminari sui dati in ingresso ---
	// --- 3. Apertura della connessione ---
	// --- 4. Tentativo di accesso al db e impostazione del risultato ---
	// --- 5. Gestione di eventuali eccezioni ---
	// --- 6. Rilascio, SEMPRE E COMUNQUE, la connessione prima di restituire il controllo al chiamante
	// --- 7. Restituzione del risultato (eventualmente di fallimento)
 * 
 * Inoltre, all'interno di uno statement JDBC si segue lo schema generale
 * 
	// --- a. Crea (se senza parametri) o prepara (se con parametri) lo statement
	// --- b. Pulisci e imposta i parametri (se ve ne sono)
	// --- c. Esegui l'azione sul database ed estrai il risultato (se atteso)
	// --- d. Cicla sul risultato (se presente) pe accedere ai valori di ogni sua tupla
	// --- e. Rilascia la struttura dati del risultato
	// --- f. Rilascia la struttura dati dello statement
 * 

 */
public class Db2RistoranteDAO implements RistoranteDAO {

	// statement metodi CRUD

		// INSERT
		static final String insert = "INSERT INTO ristorante (id,nomeRistorante,indirizzo,rating) VALUES (?,?,?,?)";

		// DELETE
		static String delete = "DELETE FROM ristorante WHERE id=?";

		// UPDATE
		static String update = "UPDATE ristorante SET nomeRistorante=?,indirizzo=?,rating=? WHERE id=?";

		// READ
		static String read_by_id = "SELECT * FROM ristorante WHERE id=?";
		static String read_by_citta = "SELECT * FROM ristorante WHERE indirizzo=?";
		static String read_all = "SELECT * FROM ristorante";
		
		// -------------------------------------------------------------------------------------

		// create table
		private static String create = "CREATE TABLE ristorante (id BIGINT NOT NULL PRIMARY KEY,nomeRistorante VARCHAR(50) NOT NULL UNIQUE,indirizzo VARCHAR(50) NOT NULL,rating INT NOT NULL, CHECK (rating in ('1','2','3','4','5')))";;

		// drop table
		static String drop = "DROP TABLE ristorante";

		// -------------------------------------------------------------------------------------
		// should update the link table of the relationship?
		private boolean relationshipOwner = false;

		@Override
		public boolean isRelationshipOwner() {
			return relationshipOwner;
		}

		@Override
		public void setRelationshipOwner(boolean relationshipOwner) {
			this.relationshipOwner = relationshipOwner;
		}

		// -------------------------------------------------------------------------------------

		// implementazione DAO

		@Override
		public void create(RistoranteDTO obj) {

			Connection conn = Db2DAOFactory.createConnection();
			try {
				PreparedStatement prep_stmt = conn.prepareStatement(insert);
				prep_stmt.clearParameters();

				prep_stmt.setLong(1, obj.getId());
				prep_stmt.setString(2, obj.getNomeRistorante());
				prep_stmt.setString(3, obj.getIndirizzo());
				prep_stmt.setInt(4, obj.getRating());

				prep_stmt.executeUpdate();

				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			if (relationshipOwner && obj.getPiatti() != null
					&& !obj.getPiatti().isEmpty()) {
				Db2LinkDAO dao = new Db2LinkDAO();
				for (PiattoDTO p : obj.getPiatti()) {
					dao.create(obj.getId(), p.getId());
				}
			}

		}

		// -------------------------------------------------------------------------------------

		@Override
		public RistoranteDTO readLazy(long id) {
			RistoranteDTO result = null;

			if (id < 0)
				return result;

			Connection conn = Db2DAOFactory.createConnection();

			try {
				PreparedStatement prep_stmt = conn.prepareStatement(read_by_id);
				prep_stmt.clearParameters();
				prep_stmt.setLong(1, id);
				ResultSet rs = prep_stmt.executeQuery();

				if (rs.next()) {
					RistoranteDTO res = new Db2RistoranteProxy();

					res.setId(rs.getLong("id"));
					res.setNomeRistorante(rs.getString("nomeRistorante"));
					res.setIndirizzo(rs.getString("indirizzo"));
					res.setRating(rs.getInt("rating"));

					result = res;
				}

				rs.close();
				prep_stmt.close();
			}

			catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			return result;
		}
		
		@Override
		public Set<RistoranteDTO> readLazyByCitta(String citta) {
			Set<RistoranteDTO> result = new HashSet<RistoranteDTO>();

			if (citta==null||citta.isEmpty())
				return result;

			Connection conn = Db2DAOFactory.createConnection();

			try {
				PreparedStatement prep_stmt = conn.prepareStatement(read_by_citta);
				prep_stmt.clearParameters();
				prep_stmt.setString(1, citta);
				ResultSet rs = prep_stmt.executeQuery();

				while (rs.next()) {
					RistoranteDTO res = new Db2RistoranteProxy();

					res.setId(rs.getLong("id"));
					res.setNomeRistorante(rs.getString("nomeRistorante"));
					res.setIndirizzo(rs.getString("indirizzo"));
					res.setRating(rs.getInt("rating"));

					result.add(res);
				}

				rs.close();
				prep_stmt.close();
			}

			catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			return result;
		}
		
		@Override
		public Set<RistoranteDTO> readLazyAll() {
			Set<RistoranteDTO> result = new HashSet<RistoranteDTO>();

			Connection conn = Db2DAOFactory.createConnection();

			try {
				PreparedStatement prep_stmt = conn.prepareStatement(read_all);
				ResultSet rs = prep_stmt.executeQuery();

				while (rs.next()) {
					RistoranteDTO res = new Db2RistoranteProxy();

					res.setId(rs.getLong("id"));
					res.setNomeRistorante(rs.getString("nomeRistorante"));
					res.setIndirizzo(rs.getString("indirizzo"));
					res.setRating(rs.getInt("rating"));

					result.add(res);
				}

				rs.close();
				prep_stmt.close();
			}

			catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			return result;
		}


		// -------------------------------------------------------------------------------------

		@Override
		public boolean update(RistoranteDTO obj) {
			boolean result = false;
			if (obj == null)
				return result;

			Connection conn = Db2DAOFactory.createConnection();

			try {
				PreparedStatement prep_stmt = conn.prepareStatement(update);
				prep_stmt.clearParameters();

				prep_stmt.setString(1, obj.getNomeRistorante());
				prep_stmt.setString(2, obj.getIndirizzo());
				prep_stmt.setInt(3, obj.getRating());
				prep_stmt.setLong(4, obj.getId());

				prep_stmt.executeUpdate();
				result = true;
				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			if (relationshipOwner) {
				Db2LinkDAO dao = new Db2LinkDAO();
				if (obj.getPiatti() == null || obj.getPiatti().isEmpty()) {
					dao.delete_by_id_Piatto(obj.getId());
				} else {
					Set<Long> olds = dao.read_by_id_Ristorante(obj.getId());
					Set<Long> news = obj.getPiatti().stream().map(p -> p.getId())
							.collect(Collectors.toSet());
					for (long oldId : olds) {
						if (!news.contains(oldId))
							dao.delete(obj.getId(), oldId);
					}
					for (long newId : news) {
						if (!olds.contains(newId))
							dao.create(obj.getId(), newId);
					}
				}
			}

			return result;
		}

		// -------------------------------------------------------------------------------------

		@Override
		public boolean delete(long id) {
			boolean result = false;
			if (id < 0)
				return result;

			Connection conn = Db2DAOFactory.createConnection();
			try {
				PreparedStatement prep_stmt = conn.prepareStatement(delete);
				prep_stmt.clearParameters();
				prep_stmt.setLong(1, id);
				prep_stmt.executeUpdate();
				result = true;
				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			if (relationshipOwner) {
				new Db2LinkDAO().delete_by_id_Ristorante(id);
			}

			return result;
		}

		// -------------------------------------------------------------------------------------

		public boolean createTable() {
			boolean result = false;
			Connection conn = Db2DAOFactory.createConnection();
			try {
				Statement prep_stmt = conn.createStatement();
				prep_stmt.execute(create);
				result = true;
				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			if (relationshipOwner) {
				new Db2LinkDAO().createTable();
			}

			return result;
		}

		// -------------------------------------------------------------------------------------

		public boolean dropTable() {
			boolean result = false;

			// prima faccio drop della tabella che la fk su questa
			if (relationshipOwner) {
				new Db2LinkDAO().dropTable();
			}

			Connection conn = Db2DAOFactory.createConnection();
			try {
				Statement prep_stmt = conn.createStatement();
				prep_stmt.execute(drop);
				result = true;
				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			finally {
				Db2DAOFactory.closeConnection(conn);
			}

			return result;
		}

		// Metodi Aggiuntivi
	
}