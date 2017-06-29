package it.unibo.tw.dao;


import java.util.List;

public interface CourseStudentMappingDAO {

	// --- CRUD -------------

	public void create(CourseStudentMappingDTO mapping);

	public CourseStudentMappingDTO read(int idCourse, int idStudent);

	//public boolean update(CourseDTO student);

	public boolean delete(int idCourse, int idStudent);

	
	// ----------------------------------

	public List<CourseStudentMappingDTO> findCoursesByStudentId(int idStudent);
	public List<CourseStudentMappingDTO> findStudentsByCourseId(int idCourse);

	// ----------------------------------

	
	public boolean createTable();

	public boolean dropTable();

}
