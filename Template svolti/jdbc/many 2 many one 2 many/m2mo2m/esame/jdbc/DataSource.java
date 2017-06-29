package m2mo2m.esame.jdbc;

import java.sql.*;

public class DataSource {
    // nome del database

    public final static int DB2 = 0;
    public final static int HSQLDB = 1;
    public final static int MYSQL = 2;

	private String dbUri;
	private String dbName = "tw_stud";
	private String userName;
	private String password;

    public DataSource(int databaseType) throws PersistenceException{
    	// tipo di DBMS utilizzato
        String driver;
        switch ( databaseType ) {
            case DB2:
                driver = "com.ibm.db2.jcc.DB2Driver";
                dbUri = "jdbc:db2://diva.deis.unibo.it:50000/"+dbName;
                userName = "00000000";
                password = "s0000000000";
                break;
            case HSQLDB:
                driver = "org.hsqldb.jdbcDriver";
                // tre modalita' (vedi http://hsqldb.org/doc/guide/ch01.html):
                //  1) hsql --> Hsqldb as a Server
                //  2) mem --> Memory-Only Databases
                //  3) file --> In-Process (Standalone) Mode
                dbUri = "jdbc:hsqldb:hsql://localhost/"+dbName;
                userName = "sa";
                password = "";
                break;
            case MYSQL:
                driver = "com.mysql.jdbc.Driver";
                dbUri = "jdbc:mysql://localhost:3306/"+dbName;
                userName = "root";
                password = "";
                break;
            default:
                throw new IllegalArgumentException("Unknown db");
        }
        try {
//        	System.out.println("DataSource.getConnection() driver = "+driver);
//        	System.out.println("DataSource.getConnection() dbUri = "+dbUri);
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		}
    }

    public Connection getConnection() throws PersistenceException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUri, userName, password);
        } catch(SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        return connection;
    }
}
