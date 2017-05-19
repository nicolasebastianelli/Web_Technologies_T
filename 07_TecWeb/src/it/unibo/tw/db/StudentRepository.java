package it.unibo.tw.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import it.unibo.tw.model.Student;

public class StudentRepository {
    private DataSource dataSource;
    
    public StudentRepository(int databaseType) {
        dataSource = new DataSource(databaseType);
    }
    
    public void dropAndCreateTable() throws PersistenceException{
	    Connection connection = this.dataSource.getConnection();
	    Statement statement = null;
	    try {
	        statement = connection.createStatement ();
	        try{
	            statement.executeUpdate ("DROP TABLE students");
	        }
	        catch (SQLException e) {
	            // the table does not exist
	        }
	        statement.executeUpdate (
	            "CREATE TABLE students ("
	                + "code INT NOT NULL PRIMARY KEY,"
	                + "firstName CHAR(40), " 
	                + "lastName CHAR(40), "
	                + "birthDate DATE " 
	            + ") "
	        );
	        statement.close ();
	    }
	    catch (SQLException e) {
	        throw new PersistenceException(e.getMessage());
	    }
	    finally {
	        try {
	            if (statement != null) 
	                statement.close();
	            if (connection!= null)
	                connection.close();
	        }
	        catch (SQLException e) {
	            throw new PersistenceException(e.getMessage());
	        }
	    }
	}

    
    public void persist(Student student) throws PersistenceException{
        Connection connection = this.dataSource.getConnection();

        if (findByPrimaryKey(student.getCode())!=null) 
            throw new PersistenceException("Student exists");
                
        PreparedStatement statement = null; 
        String insert = "insert into students(code, firstname, lastname, birthDate) values (?,?,?,?)";
        try {
            statement = connection.prepareStatement(insert);
            statement.setInt(1, student.getCode());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setDate(4, new java.sql.Date(student.getBirthDate().getTime()));
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        finally {
            try {
                if (statement != null) 
                    statement.close();
                if (connection!= null)
                    connection.close();
            }
            catch (SQLException e) {
                throw new PersistenceException(e.getMessage());
            }
        }
    }

    public void delete(Student student) throws PersistenceException{
        Connection connection = this.dataSource.getConnection();

        PreparedStatement statement = null;
        String insert = "delete from students where code = ?";
        try {
            statement = connection.prepareStatement(insert);
            statement.setInt(1, student.getCode());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        finally {
            try {
                if (statement != null) 
                    statement.close();
                if (connection!= null)
                    connection.close();
            }
            catch (SQLException e) {
                throw new PersistenceException(e.getMessage());
            }
        }
    }

    public Student findByPrimaryKey(int code) throws PersistenceException {
        Student student = null;
        
        Connection connection = this.dataSource.getConnection();
        PreparedStatement statement = null;
        String query = "select * from students where code=?";
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, code);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                long secs;
                java.util.Date birthDate; 
                student = new Student();
                student.setCode(result.getInt("code"));
                student.setFirstName(result.getString("firstname"));
                student.setLastName(result.getString("lastname"));
                secs = result.getDate("birthdate").getTime();
                birthDate = new java.util.Date(secs);
                student.setBirthDate(birthDate);
                }
        }
        catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        finally {
            try {
                if (statement != null) 
                    statement.close();
                if (connection!= null)
                    connection.close();
            } catch (SQLException e) {
                throw new PersistenceException(e.getMessage());
            }
        }
        return student;
    }   
    
    public List<Student> findAll() throws PersistenceException {
        List<Student> students = null;
        Student student = null;
        Connection connection = this.dataSource.getConnection();
        PreparedStatement statement = null;
        String query = "select * from students";
        try {
            statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                students = new LinkedList<Student>();
                student = new Student();
                student.setCode(result.getInt("code"));
                student.setFirstName(result.getString("firstname"));
                student.setLastName(result.getString("lastname"));
                student.setBirthDate(new java.util.Date(result.getDate("birthdate").getTime()));
                students.add(student);
            }
            while(result.next()) {
                student = new Student();
                student.setCode(result.getInt("code"));
                student.setFirstName(result.getString("firstname"));
                student.setLastName(result.getString("lastname"));
                student.setBirthDate(new java.util.Date(result.getDate("birthdate").getTime()));
                students.add(student);
            }
        }
        catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        finally {
            try {
                if (statement != null) 
                    statement.close();
                if (connection!= null)
                    connection.close();
            } catch (SQLException e) {
                throw new PersistenceException(e.getMessage());
            }
        }
        return students;
    }   

    public List<Student> findStudentsByBirthDate(java.util.Date birthDate) throws PersistenceException {
        List<Student> students = null;
        Student student = null;
        Connection connection = this.dataSource.getConnection();
        PreparedStatement statement = null;
        String query = "select * from students where birthdate=?";
        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(birthDate.getTime()));
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                students = new LinkedList<Student>();
                student = new Student();
                student.setCode(result.getInt("code"));
                student.setFirstName(result.getString("firstname"));
                student.setLastName(result.getString("lastname"));
                student.setBirthDate(birthDate);
                students.add(student);
            }
            while(result.next()) {
                student = new Student();
                student.setCode(result.getInt("code"));
                student.setFirstName(result.getString("firstname"));
                student.setLastName(result.getString("lastname"));
                student.setBirthDate(birthDate);
                students.add(student);
                }
        }
        catch (SQLException e) {
            throw new PersistenceException(e.getMessage());
        }
        finally {
            try {
                if (statement != null) 
                    statement.close();
                if (connection!= null)
                    connection.close();
            }
            catch (SQLException e) {
                throw new PersistenceException(e.getMessage());
            }
        }
        return students;
    }
    
}
