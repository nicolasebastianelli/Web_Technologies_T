package it.unibo.tw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.unibo.tw.dao.FooDTO;
import it.unibo.tw.dao.FooBarMappingDAO;
import it.unibo.tw.dao.BarDTO;

public class Db2FooBarMappingDAO implements FooBarMappingDAO {

	// --- Final Strings ---

	public static final String TABLE = "foo_bar";

	public static final String ID_FOO = Db2FooDAO.ID;
	public static final String ID_BAR = Db2BarDAO.ID;

	// --- SQL Statements ---

	static final String insert = 
			"INSERT " +
					"INTO " + TABLE + " ( " + 
					ID_FOO +", "+ID_BAR + " " +
					") " +
					"VALUES (?,?) "
					;

	static String read_all = 
			"SELECT * " +
					"FROM " + TABLE + " ";

	static String delete = 
			"DELETE " +
					"FROM " + TABLE + " " +
					"WHERE " + ID_FOO + " = ? " +
					"AND " + ID_BAR + " = ? "
					;

	static String create =
			"CREATE " +
					"TABLE " + TABLE +" ( " +
					ID_FOO + " INT NOT NULL, " +
					ID_BAR + " INT NOT NULL, " +
					"PRIMARY KEY (" + ID_FOO +","+ ID_BAR + " ), " +
					"FOREIGN KEY ("+ID_FOO+") REFERENCES "+Db2FooDAO.TABLE+"("+ID_FOO+")"+", "+
					"FOREIGN KEY ("+ID_BAR+") REFERENCES "+Db2BarDAO.TABLE+"("+ID_BAR+")"+
					") "
					;
	static String drop = 
			"DROP " +
					"TABLE " + TABLE + " "
					;

	static String fooByBar_query = 
			"SELECT * " +
					"FROM " + TABLE + " M, "+Db2FooDAO.TABLE+" A " +
					"WHERE M."+ID_FOO+" = A."+ID_FOO+" AND " + ID_BAR + " = ? "
					;


	static String barByFoo_query = 
			"SELECT * " +
					"FROM " + TABLE + " M, "+Db2BarDAO.TABLE+" A " +
					"WHERE M."+ID_BAR+" = A."+ID_BAR+" AND " + ID_FOO+ " = ? "
					;

	// --- SQL Statements ---

	@Override
	public void create(int idFoo, int idBar ) {
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, idFoo);
			prep_stmt.setInt(2, idBar);
			prep_stmt.executeUpdate();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("create(): failed to insert entry: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(int idFoo, int idBar) {
		boolean result = false;
		if ( idFoo < 0 || idBar < 0 )  {
			System.out.println("delete(): cannot delete an entry with an invalid id ");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(delete);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, idFoo);
			prep_stmt.setInt(2, idBar);
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		}
		catch (Exception e) {
			//TODO: Change println() argument below.
			System.out.println("delete(): failed to delete entry with idFoo = " + idFoo +" and idBar = " + idBar + ": "+e.getMessage());
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
	public List<FooDTO> getFooByBar(int id) {
		List<FooDTO> result = null;
		if ( id< 0 )  {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		result = new ArrayList<FooDTO>();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(fooByBar_query);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			while(rs.next())
			{
				FooDTO foo = new FooDTO();
				foo.setId(rs.getInt(Db2FooDAO.ID));
				foo.setValue(rs.getString(Db2FooDAO.VALUE));
				//TODO
				result.add(foo);
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public List<BarDTO> getBarByFoo(int id) {
		List<BarDTO> result = null;
		if ( id< 0 )  {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		result = new ArrayList<BarDTO>();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(barByFoo_query);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			ResultSet rs = prep_stmt.executeQuery();
			while(rs.next())
			{
				BarDTO bar = new BarDTO();
				bar.setId(rs.getInt(Db2BarDAO.ID));
				bar.setValue(rs.getString(Db2BarDAO.VALUE));
				//TODO
				result.add(bar);
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

}
