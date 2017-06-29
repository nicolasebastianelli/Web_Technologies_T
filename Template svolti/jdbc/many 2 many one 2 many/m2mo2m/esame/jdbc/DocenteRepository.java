package m2mo2m.esame.jdbc; 
 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
 
public class DocenteRepository 
{ 
	private DataSource dataSource; 
	 
	public DocenteRepository () throws PersistenceException 
	{ 
		dataSource = new DataSource(DataSource.DB2); 
	} 
	 
	// == STATEMENT SQL preconfigurati 
 
	//statement metodi CRUD 
		 
	//INSERT 
	private static final String insert = "INSERT INTO docente (MatricolaDocente,nome,Cognome) VALUES (?,?,?)";
 
	 
	//DELETE 
	private static final String delete = "DELETE FROM docente WHERE MatricolaDocente=?";
 
 
	//UPDATE 
	private static final String update = "UPDATE docente SET nome=?,Cognome=? WHERE MatricolaDocente=?";
 
	 
	//READ 
	private static String read_by_id = "SELECT * FROM docente WHERE MatricolaDocente=?";
 
 
	// implementazione metodi CRUD 
		 
	// create table 
	private static String create = 
"CREATE TABLE docente (MatricolaDocente BIGINT NOT NULL PRIMARY KEY,nome VARCHAR(50) NOT NULL,Cognome VARCHAR(50) NOT NULL)"; 
	 
	//drop table 
	private static String drop = "DROP TABLE docente";
 
	 
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
	 
	public void insert( Docente obj) throws PersistenceException 
	{ 
		Connection connection = null; 
		PreparedStatement statement = null; 
		try 
		{ 
			connection = this.dataSource.getConnection(); 
			statement = connection.prepareStatement(insert); 
			statement.setLong(1,obj.getMatricolaDocente());
			statement.setString(2,obj.getNome());
			statement.setString(3,obj.getCognome());
 
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
	 
	public void update( Docente obj) throws PersistenceException 
	{ 
		Connection connection = null; 
		PreparedStatement statement = null; 
		try 
		{ 
			connection = this.dataSource.getConnection(); 
			statement = connection.prepareStatement(update); 
			System.out.println(update); 
			statement.setString(1,obj.getNome());
			statement.setString(2,obj.getCognome());
			statement.setLong(3,obj.getMatricolaDocente());
 
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
	 
	public Docente read(long id) throws PersistenceException 
	{ 
Docente res = null; 
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
				res = new Docente (); 
				res.setMatricolaDocente(results.getLong("MatricolaDocente"));
				res.setNome(results.getString("nome"));
				res.setCognome(results.getString("Cognome"));
 
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
