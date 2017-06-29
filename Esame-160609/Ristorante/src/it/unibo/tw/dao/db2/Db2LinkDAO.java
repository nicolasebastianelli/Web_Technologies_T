package it.unibo.tw.dao.db2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import it.unibo.tw.dao.LinkDAO;

public class Db2LinkDAO implements LinkDAO{
	// statement metodi CRUD

		// INSERT
		static final String insert = "INSERT INTO link (id_Ristorante,id_Piatto) VALUES (?,?)";

		// DELETE
		static String delete = "DELETE FROM link WHERE id_Ristorante=? AND id_Piatto=?";
		static String delete_by_id_Ristorante = "DELETE FROM link WHERE id_Ristorante=?";
		static String delete_by_id_Piatto = "DELETE FROM link WHERE id_Piatto=?";

		// READ
		static String read_by_id_Ristorante = "SELECT id_Piatto FROM link WHERE id_Ristorante=?";
		static String read_by_id_Piatto = "SELECT id_Ristorante FROM link WHERE id_Piatto=?";

		// -------------------------------------------------------------------------------------

		// create table
		private static String create = "CREATE TABLE link (id_Ristorante BIGINT NOT NULL REFERENCES ristorante(id),id_Piatto BIGINT NOT NULL REFERENCES piatto(id), PRIMARY KEY(id_Ristorante, id_Piatto))";
		
		// drop table
		static String drop = "DROP TABLE link";

		@Override
		public void create(long id_Ristorante, long id_Piatto) {

			Connection conn = Db2DAOFactory.createConnection();
			try {
				PreparedStatement prep_stmt = conn.prepareStatement(insert);
				prep_stmt.clearParameters();

				prep_stmt.setLong(1, id_Ristorante);
				prep_stmt.setLong(2, id_Piatto);

				prep_stmt.executeUpdate();

				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

		}

		// -------------------------------------------------------------------------------------

		@Override
		public Set<Long> read_by_id_Ristorante(long id_Ristorante) {
			Set<Long> result = new HashSet<>();

			if (id_Ristorante < 0)
				return result;

			Connection conn = Db2DAOFactory.createConnection();

			try {
				PreparedStatement prep_stmt = conn.prepareStatement(read_by_id_Ristorante);
				prep_stmt.clearParameters();
				prep_stmt.setLong(1, id_Ristorante);
				ResultSet rs = prep_stmt.executeQuery();
				while (rs.next()) {
					result.add(rs.getLong("id_Piatto"));
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
		public Set<Long> read_by_id_Piatto(long id_Piatto) {
			Set<Long> result = new HashSet<>();

			if (id_Piatto < 0)
				return result;

			Connection conn = Db2DAOFactory.createConnection();

			try {
				PreparedStatement prep_stmt = conn.prepareStatement(read_by_id_Piatto);
				prep_stmt.clearParameters();
				prep_stmt.setLong(1, id_Piatto);
				ResultSet rs = prep_stmt.executeQuery();
				while (rs.next()) {
					result.add(rs.getLong("id_Ristorante"));
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
		public boolean delete(long id_Ristorante, long id_Piatto) {
			boolean result = false;
			Connection conn = Db2DAOFactory.createConnection();
			try {
				PreparedStatement prep_stmt = conn.prepareStatement(delete);
				prep_stmt.clearParameters();

				prep_stmt.setLong(1, id_Ristorante);
				prep_stmt.setLong(2, id_Piatto);

				prep_stmt.executeUpdate();
				result = true;
				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}
			return result;
		}
		
		@Override
		public boolean delete_by_id_Ristorante(long id_Ristorante) {
			boolean result = false;
			if (id_Ristorante < 0)
				return result;

			Connection conn = Db2DAOFactory.createConnection();
			try {
				PreparedStatement prep_stmt = conn.prepareStatement(delete_by_id_Ristorante);
				prep_stmt.clearParameters();
				prep_stmt.setLong(1, id_Ristorante);
				prep_stmt.executeUpdate();
				result = true;
				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			return result;
		}
		
		@Override
		public boolean delete_by_id_Piatto(long id_Piatto) {
			boolean result = false;
			if (id_Piatto < 0)
				return result;

			Connection conn = Db2DAOFactory.createConnection();
			try {
				PreparedStatement prep_stmt = conn.prepareStatement(delete_by_id_Piatto);
				prep_stmt.clearParameters();
				prep_stmt.setLong(1, id_Piatto);
				prep_stmt.executeUpdate();
				result = true;
				prep_stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				Db2DAOFactory.closeConnection(conn);
			}

			return result;
		}

		// -------------------------------------------------------------------------------------

		@Override
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

			return result;
		}

		// -------------------------------------------------------------------------------------

		@Override
		public boolean dropTable() {
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
}
