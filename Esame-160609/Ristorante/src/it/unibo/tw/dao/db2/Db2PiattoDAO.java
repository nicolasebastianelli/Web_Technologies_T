package it.unibo.tw.dao.db2;

import it.unibo.tw.dao.PiattoDAO;
import it.unibo.tw.dao.PiattoDTO;
import it.unibo.tw.dao.RistoranteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
 * @author pisi79
 *
 */
public class Db2PiattoDAO implements PiattoDAO {

	// statement metodi CRUD

		// INSERT
		static final String insert = "INSERT INTO piatto (id,nomePiatto,tipo) VALUES (?,?,?)";

		// DELETE
		static String delete = "DELETE FROM piatto WHERE id=?";

		// UPDATE
		static String update = "UPDATE piatto SET nomePiatto=?,tipo=? WHERE id=?";

		// READ
		static String read_by_id = "SELECT * FROM piatto WHERE id=?";

		// -------------------------------------------------------------------------------------

		// create table
		private static String create = "CREATE TABLE piatto (id BIGINT NOT NULL PRIMARY KEY,nomePiatto VARCHAR(50) NOT NULL UNIQUE,tipo VARCHAR(50) NOT NULL, CHECK (tipo in ('antipasto','primo','secondo')))";

		// drop table
		static String drop = "DROP TABLE piatto";

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
		public void create(PiattoDTO obj) {

			Connection conn = Db2DAOFactory.createConnection();
			try {
				PreparedStatement prep_stmt = conn.prepareStatement(insert);
				prep_stmt.clearParameters();

				prep_stmt.setLong(1, obj.getId());
				prep_stmt.setString(2, obj.getNomePiatto());
				prep_stmt.setString(3, obj.getTipo());

				prep_stmt.executeUpdate();

				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			if (relationshipOwner && obj.getRistoranti() != null
					&& !obj.getRistoranti().isEmpty()) {
				Db2LinkDAO dao = new Db2LinkDAO();
				for (RistoranteDTO r : obj.getRistoranti()) {
					dao.create(r.getId(), obj.getId());
				}
			}
		}

		// -------------------------------------------------------------------------------------

		@Override
		public PiattoDTO read(long id) {
			PiattoDTO result = null;

			if (id < 0)
				return result;

			Connection conn = Db2DAOFactory.createConnection();

			try {
				PreparedStatement prep_stmt = conn.prepareStatement(read_by_id);
				prep_stmt.clearParameters();
				prep_stmt.setLong(1, id);
				ResultSet rs = prep_stmt.executeQuery();

				if (rs.next()) {
					PiattoDTO res = new PiattoDTO();

					res.setId(rs.getLong("id"));
					res.setNomePiatto(rs.getString("nomePiatto"));
					res.setTipo(rs.getString("tipo"));

					Db2RistoranteDAO db2RistoranteDAO = new Db2RistoranteDAO();
					Set<RistoranteDTO> Ristoranti = new HashSet<>();
					for (long id_Ristorante : new Db2LinkDAO()
							.read_by_id_Piatto(res.getId())) {
						Ristoranti.add(db2RistoranteDAO.readLazy(id_Ristorante));
					}
					res.setRistoranti(Ristoranti);

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

		// NON VISIBILE DALL'INTERFACCIA, usato internamente
		public PiattoDTO readLazy(long id) {
			PiattoDTO result = null;

			if (id < 0)
				return result;

			Connection conn = Db2DAOFactory.createConnection();

			try {
				PreparedStatement prep_stmt = conn.prepareStatement(read_by_id);
				prep_stmt.clearParameters();
				prep_stmt.setLong(1, id);
				ResultSet rs = prep_stmt.executeQuery();

				if (rs.next()) {
					PiattoDTO res = new PiattoDTO();

					res.setId(rs.getLong("id"));
					res.setNomePiatto(rs.getString("nomePiatto"));
					res.setTipo(rs.getString("tipo"));

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

		// -------------------------------------------------------------------------------------

		@Override
		public boolean update(PiattoDTO obj) {
			boolean result = false;
			if (obj == null)
				return result;

			Connection conn = Db2DAOFactory.createConnection();

			try {
				PreparedStatement prep_stmt = conn.prepareStatement(update);
				prep_stmt.clearParameters();

				prep_stmt.setString(1, obj.getNomePiatto());
				prep_stmt.setString(2, obj.getTipo());
				prep_stmt.setLong(3, obj.getId());

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
				if (obj.getRistoranti() == null || obj.getRistoranti().isEmpty()) {
					dao.delete_by_id_Piatto(obj.getId());
				} else {
					Set<Long> olds = dao.read_by_id_Piatto(obj.getId());
					Set<Long> news = obj.getRistoranti().stream()
							.map(p -> p.getId()).collect(Collectors.toSet());

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
				new Db2LinkDAO().delete_by_id_Piatto(id);
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

			// prima faccio drop della tabella che la fk su questa
			if (relationshipOwner) {
				new Db2LinkDAO().dropTable();
			}
			boolean result = false;
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