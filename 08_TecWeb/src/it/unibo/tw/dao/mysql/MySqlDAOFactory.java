package it.unibo.tw.dao.mysql;

import it.unibo.tw.dao.CourseDAO;
import it.unibo.tw.dao.DAOFactory;
import it.unibo.tw.dao.SCMappingDAO;
import it.unibo.tw.dao.StudentDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDAOFactory extends DAOFactory {

	/**
	 * Name of the class that holds the jdbc driver implementation for the MySQL database
	 */
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * URI of the database to connect to
	 */
	public static final String DBURL = "jdbc:mysql://localhost:3306/tw_stud";

	public static final String USERNAME = "root";
	public static final String PASSWORD = "";

	// --------------------------------------------

	static {
		try {
			Class.forName(DRIVER).newInstance();
		} 
		catch (Exception e) {
			System.err.println("MySqlDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	// --------------------------------------------
	
	public static Connection createConnection() {
		try {
			Connection conn = DriverManager.getConnection (DBURL, USERNAME, PASSWORD);
			System.out.println(MySqlDAOFactory.class.getName()+".createConnection(): database connection established");
			return conn;
		} 
		catch (Exception e) {
			System.err.println(MySqlDAOFactory.class.getName()+".createConnection(): failed creating connection\n"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		}
		catch (Exception e) {
			System.err.println(MySqlDAOFactory.class.getName()+".closeConnection(): failed closing connection\n"+e);
			e.printStackTrace();
		}
	}

	// --------------------------------------------
	
	@Override
	public StudentDAO getStudentDAO() {
		return new MySqlStudentDAO();
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
		return new MySqlCourseDAO();
	}
	
	@Override
	public CourseStudentMappingDAO getStudentCourseMappingDAO() {
		return new MySqlStudentCourseMappingDAO();
	}*/
	
}
