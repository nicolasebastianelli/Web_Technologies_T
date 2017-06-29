package it.unibo.tw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import it.unibo.tw.dao.PiattoDAO;
import it.unibo.tw.dao.PiattoDTO;

public class Db2PiattoDAO implements PiattoDAO {
	
	// === Costanti letterali per non sbagliarsi a scrivere !!! ============================
	
	static final String TABLE = "piatti";

	// -------------------------------------------------------------------------------------

	static final String NOMEPIATTO = "nomePiatto";
	static final String TEMPODIPREPARAZIONE = "tempoPreparazione";
	static final String CALORIE = "calorie";
	static final String CHEFNAME = "nomeChef";
	
	// == STATEMENT SQL ====================================================================

	// INSERT INTO table ( name,description, ...) VALUES ( ?,?, ... );
	static final String insert = 
		"INSERT " +
			"INTO " + TABLE + " ( " + 
			NOMEPIATTO +", "+ 
			TEMPODIPREPARAZIONE+ ", " +
			CALORIE+ ", " +
			CHEFNAME+ " " +
			") " +
			"VALUES (?,?,?,?) "
		;
	
	// SELECT * FROM table WHERE idcolumn = ?;
	static String read_by_id = 
		"SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + NOMEPIATTO  + " = ? "
		;

	// SELECT * FROM table;
	/*static String read_all = 
		"SELECT * " +
		"FROM " + TABLE + " ";*/
	
	// DELETE FROM table WHERE idcolumn = ?;
	static String delete = 
		"DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + NOMEPIATTO + " = ? "
		;

	// UPDATE table SET xxxcolumn = ?, ... WHERE idcolumn = ?;
	static String update = 
		"UPDATE " + TABLE + " " +
			"SET " + 
			TEMPODIPREPARAZIONE + " = ?, " +
			CALORIE + " = ?, " +
			CHEFNAME + " = ? " +
			"WHERE " + NOMEPIATTO + " = ? "
		;

	// SELECT * FROM table;
	static String query = 
		"SELECT * " +
			"FROM " + TABLE + " "
		;

	// -------------------------------------------------------------------------------------

	// CREATE entrytable ( code INT NOT NULL PRIMARY KEY, ... );
	static String create = 
		"CREATE " +
			"TABLE " + TABLE +" ( " +
				NOMEPIATTO + " VARCHAR(50) NOT NULL PRIMARY KEY, " +
				TEMPODIPREPARAZIONE + " INT, " +
				CALORIE + " FLOAT, " +
				CHEFNAME + " VARCHAR(50) REFERENCES CHEF " +
			") "
		;
	static String drop = 
		"DROP " +
			"TABLE " + TABLE + " "
		;
	
	
	// === METODI DAO =========================================================================
	
	@Override
	public void create(PiattoDTO piatto) {
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(insert);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, piatto.getNomePiatto());
			prep_stmt.setInt(2, piatto.getTempoPreparazione());
			prep_stmt.setDouble(3, piatto.getCalorie());
			prep_stmt.setString(4, piatto.getNomeChef());
			prep_stmt.executeUpdate();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("create(): failed to insert entry: " + e.getMessage());
			e.printStackTrace();
			//result = new Long(-2);
		}
	}

	@Override
	public PiattoDTO read(String nome) {
		PiattoDTO result = null;
		if ( nome.equals("") || nome==null )  {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read_by_id);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, nome);
			ResultSet rs = prep_stmt.executeQuery();
			if ( rs.next() ) {
				PiattoDTO entry = new PiattoDTO();
				entry.setNomePiatto(rs.getString(NOMEPIATTO));
				entry.setTempoPreparazione(rs.getInt(TEMPODIPREPARAZIONE));
				entry.setCalorie(rs.getDouble(CALORIE));
				entry.setNomeChef(rs.getString(CHEFNAME));
				result = entry;
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to retrieve entry with nomePiatto = " +nome +": "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean update(PiattoDTO course) {
		boolean result = false;
		if ( course == null )  {
			System.out.println( "update(): failed to update a null entry");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(Db2PiattoDAO.update);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, course.getNomePiatto());
			prep_stmt.setInt(2, course.getTempoPreparazione());
			prep_stmt.setDouble(3, course.getCalorie());
			prep_stmt.setString(4, course.getNomeChef());
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("insert(): failed to update entry: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean delete(String nome) {
		boolean result = false;
		if ( nome == null || nome.equals(""))  {
			System.out.println("delete(): cannot delete an entry with an invalid id ");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(Db2PiattoDAO.delete);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, nome);
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("delete(): failed to delete entry with id = " + nome+": "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}
	
	@Override
	public boolean createTable() {
		boolean result = false;
		Connection conn = Db2DAOFactory.createConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(Db2PiattoDAO.create);
			result = true;
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("createTable(): failed to create table '"+TABLE+"': "+e.getMessage());
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean dropTable() {
		boolean result = false;
		Connection conn = Db2DAOFactory.createConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(drop);
			result = true;
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("dropTable(): failed to drop table '"+TABLE+"': "+e.getMessage());
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

}
