package m2mo2m.esame.jdbc; 
 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
 
public class CorsoRepository 
{ 
	private DataSource dataSource; 
	 
	public CorsoRepository () throws PersistenceException 
	{ 
		dataSource = new DataSource(DataSource.DB2); 
	} 
	 
	// == STATEMENT SQL preconfigurati 
 
	//statement metodi CRUD 
		 
	//INSERT 
	private static final String insert = "INSERT INTO corso (CodiceCorso,nome,creditiFormativi) VALUES (?,?,?)";
 
	 
	//DELETE 
	private static final String delete = "DELETE FROM corso WHERE CodiceCorso=?";
 
 
	//UPDATE 
	private static final String update = "UPDATE corso SET nome=?,creditiFormativi=? WHERE CodiceCorso=?";
 
	 
	//READ 
	private static String read_by_id = "SELECT * FROM corso WHERE CodiceCorso=?";
 
 
	// implementazione metodi CRUD 
		 
	// create table 
	private static String create = 
"CREATE TABLE corso (CodiceCorso BIGINT NOT NULL PRIMARY KEY,nome VARCHAR(50) NOT NULL,creditiFormativi INT NOT NULL)"; 
	 
	//drop table 
	private static String drop = "DROP TABLE corso";
 
	 
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
			throw new PersistenceException(e.getMessage()); 
			// the table might be referenced by a FK 
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
	 
	public void insert( Corso obj) throws PersistenceException 
	{ 
		Connection connection = null; 
		PreparedStatement statement = null; 
		try 
		{ 
			connection = this.dataSource.getConnection(); 
			statement = connection.prepareStatement(insert); 
			statement.setLong(1,obj.getCodiceCorso());
			statement.setString(2,obj.getNome());
			statement.setInt(3,obj.getCreditiFormativi());
 
			System.out.println(statement); 
			statement.executeUpdate(); 
		} 
		catch (SQLException e) 
		{ 
			throw new PersistenceException(e.getMessage()); 
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
	 
	public void delete(long id) throws PersistenceException 
	{ 
		Connection connection = null; 
		PreparedStatement statement = null; 
		try 
		{ 
			connection = this.dataSource.getConnection(); 
			System.out.println(delete); 
			statement = connection.prepareStatement(delete); 
			statement.setLong(1, id); 
			System.out.println(statement); 
			statement.executeUpdate(); 
		} 
		catch (SQLException e) 
		{ 
			throw new PersistenceException(e.getMessage()); 
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
	 
	public void update( Corso obj) throws PersistenceException 
	{ 
		Connection connection = null; 
		PreparedStatement statement = null; 
		try 
		{ 
			connection = this.dataSource.getConnection(); 
			statement = connection.prepareStatement(update); 
			System.out.println(update); 
			statement.setString(1,obj.getNome());
			statement.setInt(2,obj.getCreditiFormativi());
			statement.setLong(3,obj.getCodiceCorso());
 
			System.out.println(statement); 
			statement.executeUpdate(); 
		} 
		catch (SQLException e) 
		{ 
			throw new PersistenceException(e.getMessage()); 
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
	 
	public Corso read(long id) throws PersistenceException 
	{ 
Corso res = null; 
		Connection connection = null; 
		PreparedStatement statement = null; 
		try { 
			connection = this.dataSource.getConnection(); 
			statement = connection.prepareStatement(read_by_id); 
			statement.setLong(1, id); 
			System.out.println(statement); 
			ResultSet results = statement.executeQuery(); 
			while(results.next()) 
			{ 
				res = new Corso (); 
				res.setCodiceCorso(results.getLong("CodiceCorso"));
				res.setNome(results.getString("nome"));
				res.setCreditiFormativi(results.getInt("creditiFormativi"));
 
			} 
		} 
		catch (SQLException e) 
		{ 
			throw new PersistenceException(e.getMessage()); 
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
		return res; 
	} 
	 
} 
