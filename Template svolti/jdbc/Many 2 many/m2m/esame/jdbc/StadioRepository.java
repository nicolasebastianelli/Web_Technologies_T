package m2m.esame.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StadioRepository {
	private DataSource dataSource;

	public StadioRepository() throws PersistenceException {
		dataSource = new DataSource(DataSource.DB2);
	}

	// == STATEMENT SQL preconfigurati

	// statement metodi CRUD

	// INSERT
	private static final String insert = "INSERT INTO stadio (codstadio,nome,citta) VALUES (?,?,?)";

	// DELETE
	private static final String delete = "DELETE FROM stadio WHERE codstadio=?";

	// UPDATE
	private static final String update = "UPDATE stadio SET nome=?,citta=? WHERE codstadio=?";

	// READ
	private static String read_by_codstadio = "SELECT * FROM stadio WHERE codstadio=?";

	// implementazione metodi CRUD

	// create table
	private static String create = "CREATE TABLE stadio (codstadio BIGINT NOT NULL PRIMARY KEY,"
			+ "nome VARCHAR(50) NOT NULL," + "citta VARCHAR(50) NOT NULL)";

	// drop table
	private static String drop = "DROP TABLE IF EXISTS stadio";

	// init
	public void dropAndCreateTable() throws PersistenceException {
		dropTable();
		createTable();
	}

	public void dropTable() throws PersistenceException {
		Connection connection = this.dataSource.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			System.out.println(drop);
			statement.executeUpdate(drop);
		} catch (SQLException e) {
			e.printStackTrace();
			// the table does not exist or is referenced by a FK
			// that prevents the drop
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void createTable() throws PersistenceException {
		Connection connection = this.dataSource.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			System.out.println(create);
			statement.executeUpdate(create);
		} catch (SQLException e) {
			e.printStackTrace();
			// the table does not exist or is referenced by a FK
			// that prevents the drop
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void insert(Stadio obj) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(insert);
			statement.setLong(1, obj.getCodStadio());
			statement.setString(2, obj.getNome());
			statement.setString(3, obj.getCitta());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public void delete(long codstadio) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			System.out.println(delete);
			statement = connection.prepareStatement(delete);
			statement.setLong(1, codstadio);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public void update(Stadio obj) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(update);
			System.out.println(update);
			statement.setString(1, obj.getNome());
			statement.setString(2, obj.getCitta());
			statement.setLong(3, obj.getCodStadio());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public Stadio read(long codstadio) throws PersistenceException {
		Stadio res = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(read_by_codstadio);
			statement.setLong(1, codstadio);
			System.out.println(statement);

			ResultSet results = statement.executeQuery();
			while (results.next()) {
				res = new Stadio();
				res.setCodStadio(results.getLong("codstadio"));
				res.setNome(results.getString("nome"));
				res.setCitta(results.getString("citta"));

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return res;
	}

}
