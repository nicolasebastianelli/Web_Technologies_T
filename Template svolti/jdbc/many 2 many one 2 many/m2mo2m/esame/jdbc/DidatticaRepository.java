package m2mo2m.esame.jdbc; 
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
 
public class DidatticaRepository 
{ 
	private DataSource dataSource; 
	 
	public DidatticaRepository () throws PersistenceException 
	{ 
		dataSource = new DataSource(DataSource.DB2); 
	} 
	 
	// == STATEMENT SQL preconfigurati 
 
	//statement metodi CRUD 
		 
	//INSERT 
	private static final String insert = "INSERT INTO didattica (Docente,Corso) VALUES (?,?)";
 
	 
	//DELETE 
	private static final String delete = "DELETE FROM didattica WHERE Docente=?";
 
 
	//UPDATE 
	private static final String update = "UPDATE didattica SET Corso=? WHERE Docente=?";
 
	 
	//READ 
	private static String read_by_id = "SELECT * FROM didattica WHERE Docente=?";
 
 
	// implementazione metodi CRUD 
		 
	// create table 
	private static String create = 
"CREATE TABLE didattica (Docente BIGINT NOT NULL,Corso BIGINT NOT NULL, PRIMARY KEY (Docente,Corso), FOREIGN KEY (Docente) REFERENCES docente(MatricolaDocente),FOREIGN KEY (Corso) REFERENCES corso(CodiceCorso))"; 
	 
	//drop table 
	private static String drop = "DROP TABLE didattica";
 
	 
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
	 
	public void insert( Didattica obj) throws PersistenceException 
	{ 
		if (new DocenteRepository().read(obj.getDocente())==null||new CorsoRepository().read(obj.getCorso())==null) {
			try {
				PrintWriter pw = new PrintWriter(new FileOutputStream("didattica.txt",true));
				pw.println("FK violation "+obj.getCorso() + " " + obj.getDocente());
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			throw new PersistenceException("FK violation");
		}
		Connection connection = null; 
		PreparedStatement statement = null; 
		try 
		{ 
			connection = this.dataSource.getConnection(); 			
			statement = connection.prepareStatement(insert); 
			statement.setLong(1,obj.getDocente());
			statement.setLong(2,obj.getCorso());
 
			System.out.println(statement.toString()); 
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
	 
	public void update( Didattica obj) throws PersistenceException 
	{ 
		Connection connection = null; 
		PreparedStatement statement = null; 
		try 
		{ 
			connection = this.dataSource.getConnection(); 
			statement = connection.prepareStatement(update); 
			System.out.println(update); 
			statement.setLong(1,obj.getCorso());
			statement.setLong(2,obj.getDocente());
 
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
	 
	public Didattica read(long id) throws PersistenceException 
	{ 
Didattica res = null; 
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
				res = new Didattica (); 
				res.setDocente(results.getLong("Docente"));
				res.setCorso(results.getLong("Corso"));
 
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
