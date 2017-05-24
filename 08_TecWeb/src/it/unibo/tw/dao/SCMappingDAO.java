package it.unibo.tw.dao;

import java.util.List;

public interface SCMappingDAO {

	public void create(SCMappingDTO scmapping);
	
	public boolean delete(SCMappingDTO scmapping);
	
	public boolean createTable();

	public boolean dropTable();
	
	public List<SCMappingDTO> findCoursesByStudentId(int idStudent);
	
	public List<SCMappingDTO> findStudentByCourseId(int idStudent);

}
