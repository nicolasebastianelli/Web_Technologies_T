package it.unibo.tw.dao.hsqldb;

import it.unibo.tw.dao.CourseDAO;
import it.unibo.tw.dao.DAOFactory;
import it.unibo.tw.dao.SCMappingDAO;
import it.unibo.tw.dao.StudentDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class HsqldbDAOFactory extends DAOFactory {

	/**
	 * Name of the class that holds the jdbc driver implementation for the hsqldb database
	 */
	public static final String DRIVER = "org.hsqldb.jdbcDriver";
	
	/**
	 * URI of the database to connect to
	 */
	public static final String DBURL = "jdbc:hsqldb:hsql://localhost/tw_stud";

	public static final String USERNAME = "sa";
	public static final String PASSWORD = "";

	// --------------------------------------------

	// static initializer block to load db driver class in memory
	static {
		try {
			Class.forName(DRIVER);
		} 
		catch (Exception e) {
			System.err.println("HsqldbDAOFactory: failed to load HSQLDB JDBC driver" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	// --------------------------------------------

	/**
	 * For the sake of simplicity, here we create a brand new connection every time we are asked to.
	 * 
	 * Anyway, this is how things should go in the real world:
	 * 1. 	DB offers a limited number of connections (typically 100) so we can't really afford to 
	 * 		rely on factory invokers to always terminate the connection we have opened
	 * 2. 	At initialization, factory creates a well-defined number of connections (say 20) and put them
	 * 		in a pool
	 * 3.	create and close connection methods actually do not create or close brand new connections, but
	 * 		pick available connections from the pool, each time, and mark them as 'busy'/'free'
	 * 4.	factory does monitoring on the connection pool and force termination of connection in use
	 * 		for a too long time (invoker prob'ly forgot to close it... maybe due to exceptions it didn't handle well)
	 * 5.	terminated connections are replaced by brand new ones
	 */
	public static Connection createConnection() {
		try {
			return DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
		} 
		catch (Exception e) {
			System.err.println(HsqldbDAOFactory.class.getName() + ".createConnection(): failed creating connection" + "\n" + e.toString());
			e.printStackTrace();
			System.err.println("Was the database started? Is the database URL right?");
			return null;
		}
	}
	
	/**
	 * For the sake of simplicity, here we actually close the connection we are told to.
	 */
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		}
		catch (Exception e) {
			System.err.println(HsqldbDAOFactory.class.getName() + ".closeConnection(): failed closing connection" + "\n" + e.toString());
			e.printStackTrace();
		}
	}

	// --------------------------------------------
	
	@Override
	public StudentDAO getStudentDAO() {
		return new HsqldbStudentDAO();
	}

	@Override
	public CourseDAO getCourseDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SCMappingDAO getStudentCourseMappingDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Override
	public CourseDAO getCourseDAO() {
		return new HsqldbCourseDAO();
	}
	
	@Override
	public CourseStudentMappingDAO getStudentCourseMappingDAO() {
		return new HsqldbStudentCourseMappingDAO();
	}*/
	
}
