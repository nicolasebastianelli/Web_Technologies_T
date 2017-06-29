package it.unibo.tw.dao.db2;

import it.unibo.tw.dao.FooDAO;
import it.unibo.tw.dao.DAOFactory;
import it.unibo.tw.dao.FooBarMappingDAO;
import it.unibo.tw.dao.BarDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Db2DAOFactory extends DAOFactory {

	public static final String DRIVER = "com.ibm.db2.jcc.DB2Driver";
	
	public static final String DBURL = "jdbc:db2://diva.deis.unibo.it:50000/tw_stud";

	
	//TODO
	public static final String USERNAME = "00722894";
	public static final String PASSWORD = "Nicola31";

	// --------------------------------------------

	// static initializer block to load db driver class in memory
	static {
		try {
			Class.forName(DRIVER);
		} 
		catch (Exception e) {
			System.err.println("HsqldbDAOFactory: failed to load DB2 JDBC driver" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	// --------------------------------------------

	public static Connection createConnection() {
		try {
			return DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		} 
		catch (Exception e) {
			System.err.println(Db2DAOFactory.class.getName() + ".createConnection(): failed creating connection" + "\n" + e.toString());
			e.printStackTrace();
			System.err.println("Was the database started? Is the database URL right?");
			return null;
		}
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		}
		catch (Exception e) {
			System.err.println(Db2DAOFactory.class.getName() + ".closeConnection(): failed closing connection" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	// --------------------------------------------
	
	@Override
	public BarDAO getBarDAO() {
		return new Db2BarDAO();
	}

	@Override
	public FooDAO getFooDAO() {
		return new Db2FooDAO();
	}
	
	@Override
	public FooBarMappingDAO getFooBarMappingDAO() {
		return new Db2FooBarMappingDAO();
	}
	
}
