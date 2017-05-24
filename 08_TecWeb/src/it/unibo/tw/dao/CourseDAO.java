package it.unibo.tw.dao;

public interface CourseDAO {

	
	// --- CRUD -------------

		public void create(CourseDTO course);

		public CourseDTO read(int code);

		public boolean update(CourseDTO student);

		public boolean delete(int code);
		
		
		public boolean createTable();

		public boolean dropTable();
}
