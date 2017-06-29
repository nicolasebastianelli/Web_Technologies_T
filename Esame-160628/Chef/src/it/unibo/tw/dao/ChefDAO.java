package it.unibo.tw.dao;


import java.util.List;

public interface ChefDAO {

	// --- CRUD -------------

	public void create(ChefDTO chef);

	public ChefDTO read(String chef);
	
	public List<ChefDTO> readAll();

	public boolean update(ChefDTO chef);

	public boolean delete(String chef);

	
	// ----------------------------------

	public List<PiattoDTO> findPiattiByIdChef(String nomeChef);

	// ----------------------------------

	
	public boolean createTable();

	public boolean dropTable();

}
