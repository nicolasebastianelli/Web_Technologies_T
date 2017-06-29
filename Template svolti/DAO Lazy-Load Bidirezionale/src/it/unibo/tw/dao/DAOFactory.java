package it.unibo.tw.dao;

import it.unibo.tw.dao.db2.Db2DAOFactory;

public abstract class DAOFactory {

	// --- List of supported DAO types ---

	public static final int DB2 = 0;
	
	public static final int HSQLDB = 1;

	public static final int MYSQL = 2;
	
	
	// --- static factory method ---
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch ( whichFactory ) {
		case DB2:
			return new Db2DAOFactory();
		default:
			return null;
		}
	}
	
	// --- Abstract DBMS-dependent methods ---
	
	public abstract BarDAO getBarDAO();

	public abstract FooDAO getFooDAO();
	
	public abstract FooBarMappingDAO getFooBarMappingDAO();
	
}
