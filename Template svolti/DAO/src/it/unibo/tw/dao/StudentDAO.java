package it.unibo.tw.dao;


import java.util.List;

public interface StudentDAO {

	// --- CRUD -------------

	public void create(StudentDTO student);

	public StudentDTO read(int code);

	public boolean update(StudentDTO student);

	public boolean delete(int code);

	
	// ----------------------------------

	public List<StudentDTO> findStudentByLastName(String lastName);

	// ----------------------------------

	
	public boolean createTable();

	public boolean dropTable();

}
