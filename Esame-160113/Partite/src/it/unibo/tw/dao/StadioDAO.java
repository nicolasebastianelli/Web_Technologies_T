package it.unibo.tw.dao;


import java.util.List;

public interface StadioDAO {

	// --- CRUD -------------

	public void create(StadioDTO stadio);

	public StadioDTO read(int codice);

	//public boolean update(CourseDTO student);

	public boolean delete(int codice);
	public boolean update(StadioDTO stadio);

	
	// ----------------------------------
	public List<PartitaDTO> findPartiteByStadioId(int codice);

	// ----------------------------------

	
	public boolean createTable();

	public boolean dropTable();

	public List<StadioDTO> readAll();

}
