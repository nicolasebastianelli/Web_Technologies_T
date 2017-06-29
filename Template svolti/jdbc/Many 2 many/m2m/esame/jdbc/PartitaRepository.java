package m2m.esame.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PartitaRepository {
	private DataSource dataSource;

	public PartitaRepository() throws PersistenceException {
		dataSource = new DataSource(DataSource.DB2);
	}

	// == STATEMENT SQL preconfigurati

	// statement metodi CRUD

	// INSERT
	private static final String insert = "INSERT INTO partita (squadraCasa,squadraOspite,stadio,data) VALUES (?,?,?,?)";

	// DELETE
	private static final String delete = "DELETE FROM partita WHERE squadraCasa=? and squadraOspite=?";

	// UPDATE
	private static final String update = "UPDATE partita SET stadio=?,data=? WHERE squadraCasa=? and squadraOspite=?";

	// READ
	private static String read_by_squadraCasa_squadraOspite = "SELECT * FROM partita WHERE squadraCasa=? and squadraOspite=?";

	// implementazione metodi CRUD

	// create table
	private static String create = "CREATE TABLE partita (" + "squadraCasa BIGINT NOT NULL PRIMARY KEY,"
			+ "squadraOspite BIGINT NOT NULL," + "stadio BIGINT NOT NULL," + "data DATE NOT NULL,"
			+ "FOREIGN KEY (stadio) REFERENCES stadio(codStadio),"
			+ "FOREIGN KEY (SquadraCasa) REFERENCES squadra(codSquadra),"
			+ "FOREIGN KEY (SquadraOspite) REFERENCES squadra(codSquadra))";

	// drop table
	private static String drop = "DROP TABLE IF EXISTS partita";

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

	public void insert(Partita obj) throws PersistenceException {
		if (obj.getSquadraCasa() == obj.getSquadraOspite())
			throw new PersistenceException("vincolo squadre violato");
		Squadra casa = new SquadraRepository().read(obj.getSquadraCasa());
		Squadra ospite = new SquadraRepository().read(obj.getSquadraOspite());
		if (casa == null || ospite == null)
			throw new PersistenceException("vincolo FK violato");
		if (!casa.getCategoria().equals(ospite.getCategoria()) || !casa.getGirone().equals(ospite.getGirone()))
			throw new PersistenceException("vincolo categoria-girone violato");

		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(insert);
			statement.setLong(1, obj.getSquadraCasa());
			statement.setLong(2, obj.getSquadraOspite());
			statement.setLong(3, obj.getStadio());
			statement.setDate(4, obj.getData());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public void delete(long squadraCasa, long squadraOspite) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			System.out.println(delete);
			statement = connection.prepareStatement(delete);
			statement.setLong(1, squadraCasa);
			statement.setLong(2, squadraOspite);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public void update(Partita obj) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(update);
			System.out.println(update);
			statement.setLong(1, obj.getStadio());
			statement.setDate(2, obj.getData());
			statement.setLong(3, obj.getSquadraCasa());
			statement.setLong(4, obj.getSquadraOspite());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	public Partita read(long squadraCasa, long squadraOspite) throws PersistenceException {
		Partita res = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(read_by_squadraCasa_squadraOspite);
			statement.setLong(1, squadraCasa);
			statement.setLong(2, squadraOspite);
			System.out.println(statement);

			ResultSet results = statement.executeQuery();
			while (results.next()) {
				res = new Partita();
				res.setSquadraCasa(results.getLong("squadraCasa"));
				res.setSquadraOspite(results.getLong("squadraOspite"));
				res.setStadio(results.getLong("stadio"));
				res.setData(results.getDate("data"));

			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return res;
	}

}
