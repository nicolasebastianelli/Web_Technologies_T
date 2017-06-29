package it.unibo.tw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unibo.tw.dao.CourseStudentMappingDAO;
import it.unibo.tw.dao.CourseStudentMappingDTO;

public class Db2StudentCourseMappingDAO implements CourseStudentMappingDAO {

	// === Costanti letterali per non sbagliarsi a scrivere !!! ============================
	
	static final String TABLE = "courses_students";

	// -------------------------------------------------------------------------------------

	static final String ID_C = "idCourse";
	static final String ID_S = "idStudent";
	
	
	// == STATEMENT SQL ====================================================================

	// INSERT INTO table ( idCourse, idStudent ) VALUES ( ?,? );
	static final String insert = 
		"INSERT " +
			"INTO " + TABLE + " ( " + 
				ID_C +", "+ID_S + " " +
			") " +
			"VALUES (?,?) "
		;
	
	// SELECT * FROM table WHERE idcolumns = ?;
	static String read_by_ids = 
		"SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID_C + " = ? " +
			"AND " + ID_S + " = ? "
		;

	// SELECT * FROM table WHERE idcolumns = ?;
	static String read_by_id_course = 
		"SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID_C + " = ? "
		;

	// SELECT * FROM table WHERE idcolumns = ?;
	static String read_by_id_student = 
		"SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID_S + " = ? "
		;
	
	// SELECT * FROM table WHERE stringcolumn = ?;
	static String read_all = 
		"SELECT * " +
		"FROM " + TABLE + " ";
	
	// DELETE FROM table WHERE idcolumn = ?;
	static String delete = 
		"DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + ID_C + " = ? " +
			"AND " + ID_S + " = ? "
		;

	// UPDATE table SET xxxcolumn = ?, ... WHERE idcolumn = ?;
	/*static String update = 
		"UPDATE " + TABLE + " " +
			"SET " + 
				NAME + " = ?, " +
			"WHERE " + ID + " = ? "
		;*/

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
				ID_C + " INT NOT NULL, " +
				ID_S + " INT NOT NULL, " +
				"PRIMARY KEY (" + ID_C +","+ ID_S + " ) " +
			") "
		;
	static String drop = 
		"DROP " +
			"TABLE " + TABLE + " "
		;
	
	
	// === METODI DAO =========================================================================
	
	@Override
	public void create(CourseStudentMappingDTO mapping) {
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, mapping.getIdCourse());
			prep_stmt.setInt(2, mapping.getIdStudent());
			prep_stmt.executeUpdate();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("create(): failed to insert entry: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public CourseStudentMappingDTO read(int idCourse, int idStudent) {
		CourseStudentMappingDTO result = null;
		if ( idCourse < 0 || idStudent < 0 )  {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read_by_ids);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, idCourse);
			prep_stmt.setInt(2, idStudent);
			ResultSet rs = prep_stmt.executeQuery();
			if ( rs.next() ) {
				CourseStudentMappingDTO entry = new CourseStudentMappingDTO();
				entry.setIdCourse(rs.getInt(ID_C));
				entry.setIdStudent(rs.getInt(ID_S));
				result = entry;
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to retrieve entry with idCourse = " + idCourse+" and idStudent = " + idStudent+": "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	/*@Override
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
	}*/

	@Override
	public boolean delete(int idCourse, int idStudent) {
		boolean result = false;
		if ( idCourse < 0 || idStudent < 0 )  {
			System.out.println("delete(): cannot delete an entry with an invalid id ");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(delete);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, idCourse);
			prep_stmt.setInt(2, idStudent);
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("delete(): failed to delete entry with idCourse = " + idCourse +" and idStudent = " + idStudent + ": "+e.getMessage());
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
			stmt.execute(create);
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
	
	@Override
	public List<CourseStudentMappingDTO> findCoursesByStudentId(int idStudent) {
		List<CourseStudentMappingDTO> result = null;
		if ( idStudent < 0 )  {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			result = new ArrayList<CourseStudentMappingDTO>();
			PreparedStatement prep_stmt = conn.prepareStatement(read_by_id_student);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, idStudent);
			ResultSet rs = prep_stmt.executeQuery();
			while ( rs.next() ) {
				CourseStudentMappingDTO entry = new CourseStudentMappingDTO();
				entry.setIdCourse(rs.getInt(ID_C));
				entry.setIdStudent(rs.getInt(ID_S));
				result.add(entry);
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to retrieve entry with idStudent = " + idStudent+": "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public List<CourseStudentMappingDTO> findStudentsByCourseId(int idCourse) {
		List<CourseStudentMappingDTO> result = null;
		if ( idCourse < 0 )  {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			result = new ArrayList<CourseStudentMappingDTO>();
			PreparedStatement prep_stmt = conn.prepareStatement(read_by_id_course);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, idCourse);
			ResultSet rs = prep_stmt.executeQuery();
			while ( rs.next() ) {
				CourseStudentMappingDTO entry = new CourseStudentMappingDTO();
				entry.setIdCourse(rs.getInt(ID_C));
				entry.setIdStudent(rs.getInt(ID_S));
				result.add(entry);
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to retrieve entry with idCourse = " + idCourse+": "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

}
