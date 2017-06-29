package it.unibo.tw.dao;

import java.util.List;

public interface CourseDAO {

	// --- CRUD -------------

	public void create(CourseDTO student);

	public CourseDTO read(int id);
	
	public List<CourseDTO> readAll();

	public boolean update(CourseDTO student);

	public boolean delete(int id);

	
	// ----------------------------------
	
	public boolean createTable();

	public boolean dropTable();

}
