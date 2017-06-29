package m2m.esame.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SquadraRepository {
	private DataSource dataSource;

	public SquadraRepository() throws PersistenceException {
		dataSource = new DataSource(DataSource.DB2);
	}

	// == STATEMENT SQL preconfigurati

	// statement metodi CRUD

	// INSERT
	private static final String insert = "INSERT INTO squadra (codsquadra,nome,categoria,girone) VALUES (?,?,?,?)";

	// DELETE
	private static final String delete = "DELETE FROM squadra WHERE codsquadra=?";

	// UPDATE
	private static final String update = "UPDATE squadra SET nome=?,categoria=?,girone=? WHERE codsquadra=?";

	// READ
	private static String read_by_codsquadra = "SELECT * FROM squadra WHERE codsquadra=?";

	// implementazione metodi CRUD

	// create table
	private static String create = "CREATE TABLE squadra (" + "codsquadra BIGINT NOT NULL PRIMARY KEY,"
			+ "nome VARCHAR(50) NOT NULL," + "categoria VARCHAR(50) NOT NULL," + "girone VARCHAR(50) NOT NULL)";

	// drop table
	private static String drop = "DROP TABLE IF EXISTS squadra";

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

	public void insert(Squadra obj) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(insert);
			statement.setLong(1, obj.getCodSquadra());
			statement.setString(2, obj.getNome());
			statement.setString(3, obj.getCategoria());
			statement.setString(4, obj.getGirone());
			System.out.println(statement);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public void delete(long codsquadra) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			System.out.println(delete);
			statement = connection.prepareStatement(delete);
			statement.setLong(1, codsquadra);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public void update(Squadra obj) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(update);
			System.out.println(update);
			statement.setString(1, obj.getNome());
			statement.setString(2, obj.getCategoria());
			statement.setString(3, obj.getGirone());
			statement.setLong(4, obj.getCodSquadra());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public Squadra read(long codsquadra) throws PersistenceException {
		Squadra res = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(read_by_codsquadra);
			statement.setLong(1, codsquadra);
			System.out.println(statement);

			ResultSet results = statement.executeQuery();
			while (results.next()) {
				res = new Squadra();
				res.setId(results.getLong("codsquadra"));
				res.setNome(results.getString("nome"));
				res.setCategoria(results.getString("categoria"));
				res.setGirone(results.getString("girone"));

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return res;
	}

}
