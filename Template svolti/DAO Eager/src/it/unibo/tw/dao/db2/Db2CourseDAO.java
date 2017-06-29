package it.unibo.tw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unibo.tw.dao.CourseDAO;
import it.unibo.tw.dao.CourseDTO;

public class Db2CourseDAO implements CourseDAO {
	
	// === Costanti letterali per non sbagliarsi a scrivere !!! ============================
	
	static final String TABLE = "courses";

	// -------------------------------------------------------------------------------------

	static final String ID = "id";
	static final String NAME = "name";
	
	
	// == STATEMENT SQL ====================================================================

	// INSERT INTO table ( name,description, ...) VALUES ( ?,?, ... );
	static final String insert = 
		"INSERT " +
			"INTO " + TABLE + " ( " + 
				ID +", "+NAME + " " +
			") " +
			"VALUES (?,?) "
		;
	
	// SELECT * FROM table WHERE idcolumn = ?;
	static String read_by_id = 
		"SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? "
		;

	// SELECT * FROM table;
	static String read_all = 
		"SELECT * " +
		"FROM " + TABLE + " ";
	
	// DELETE FROM table WHERE idcolumn = ?;
	static String delete = 
		"DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? "
		;

	// UPDATE table SET xxxcolumn = ?, ... WHERE idcolumn = ?;
	static String update = 
		"UPDATE " + TABLE + " " +
			"SET " + 
				NAME + " = ?, " +
			"WHERE " + ID + " = ? "
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
				ID + " INT NOT NULL PRIMARY KEY, " +
				NAME + " VARCHAR(50) " +
			") "
		;
	static String drop = 
		"DROP " +
			"TABLE " + TABLE + " "
		;
	
	
	// === METODI DAO =========================================================================
	
	@Override
	public void create(CourseDTO course) {
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, course.getId());
			prep_stmt.setString(2, course.getName());
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
	public CourseDTO read(int id) {
		CourseDTO result = null;
		if ( id < 0 )  {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read_by_id);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			if ( rs.next() ) {
				CourseDTO entry = new CourseDTO();
				entry.setId(rs.getInt(ID));
				entry.setName(rs.getString(NAME));
				result = entry;
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to retrieve entry with id = " + id+": "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}
	
	@Override
	public List<CourseDTO> readAll() {
		List<CourseDTO> result = new ArrayList<CourseDTO>();
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read_all);
			prep_stmt.clearParameters();
			ResultSet rs = prep_stmt.executeQuery();
			while ( rs.next() ) {
				CourseDTO entry = new CourseDTO();
				entry.setId(rs.getInt(ID));
				entry.setName(rs.getString(NAME));
				result.add(entry);
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to retrieve entry: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean update(CourseDTO course) {
		boolean result = false;
		if ( course == null )  {
			System.out.println( "update(): failed to update a null entry");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(Db2CourseDAO.update);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, course.getId());
			prep_stmt.setString(2, course.getName());
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
	public boolean delete(int id) {
		boolean result = false;
		if ( id < 0 )  {
			System.out.println("delete(): cannot delete an entry with an invalid id ");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(Db2CourseDAO.delete);
			prep_stmt.clearParameters();
			prep_stmt.setLong(1, id);
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("delete(): failed to delete entry with id = " + id+": "+e.getMessage());
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
			stmt.execute(Db2CourseDAO.create);
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
