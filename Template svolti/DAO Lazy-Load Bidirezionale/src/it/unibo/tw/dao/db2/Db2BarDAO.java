package it.unibo.tw.dao.db2;

import it.unibo.tw.dao.BarDAO;
import it.unibo.tw.dao.BarDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Db2BarDAO implements BarDAO {

	// --- Final Strings ---

	public static final String TABLE = "bar";

	public static final String ID = "id_bar";
	public static final String VALUE = "value";
	//TODO 

	// --- SQL Statements ---

	static final String insert = 
			"INSERT " +
					"INTO " + TABLE + " ( " + 
					ID +", "+VALUE+" "+ //TODO
					") " +
					"VALUES (?,?) "
					;

	static String read_by_id = 
			"SELECT * " +
					"FROM " + TABLE + " " +
					"WHERE " + ID + " = ? "
					;

	static String read_all = 
			"SELECT * " +
					"FROM " + TABLE + " ";

	static String delete = 
			"DELETE " +
					"FROM " + TABLE + " " +
					"WHERE " + ID + " = ? "
					;

	static String update = 
			"UPDATE " + TABLE + " " +
					"SET " + 
					VALUE + " = ?, " + //TODO
					"WHERE " + ID + " = ? "
					;

	static String query = 
			"SELECT * " +
					"FROM " + TABLE + " "
					;

	static String create = 
			"CREATE " +
					"TABLE " + TABLE +" ( " +
					ID + " INT NOT NULL PRIMARY KEY, " +
					VALUE + " VARCHAR(50) " + //TODO
					") "
					;

	static String drop = 
			"DROP " +
					"TABLE " + TABLE + " "
					;

	// --- DAO Methods ---

	@Override
	public void create(BarDTO bar) {
		Connection conn = Db2DAOFactory.createConnection();
		try {

			PreparedStatement prep_stmt = conn.prepareStatement(Db2BarDAO.insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, bar.getId());
			prep_stmt.setString(2, bar.getValue());
			//TODO
			prep_stmt.executeUpdate();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("create(): failed to insert entry: " + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public BarDTO read(int id) {
		BarDTO result = null;
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
				BarDTO entry = new Db2BarProxyDTO();
				entry.setId(rs.getInt(ID));
				entry.setValue(rs.getString(VALUE));
				//TODO
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
	public boolean update(BarDTO bar) {
		boolean result = false;
		if ( bar == null )  {
			System.out.println("update(): failed to update a null entry");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(Db2BarDAO.update);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, bar.getValue());
			//TODO
			prep_stmt.setInt(2, bar.getId()); //Note: edit the value TODO
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
			PreparedStatement prep_stmt = conn.prepareStatement(Db2BarDAO.delete);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
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

}