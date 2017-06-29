package it.unibo.tw.dao;


public interface CourseDAO {

	// --- CRUD -------------

	public void create(CourseDTO student);

	public CourseDTO read(int id);

	public boolean update(CourseDTO student);

	public boolean delete(int id);

	
	// ----------------------------------
	
	public boolean createTable();

	public boolean dropTable();

}
