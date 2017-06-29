package it.unibo.tw.dao;

import java.util.List;

public interface FooBarMappingDAO {

	// ------------- CRUD -------------
	// Read and Update are not needed in Mapping DAOs!
	public void create(int idFoo, int idBar);

	public boolean delete(int idFoo, int idBar);

	// ---------- Utility -------------
	
	public List<FooDTO> getFooByBar(int id);

	public List<BarDTO> getBarByFoo(int id);

	// --- Create and Drop Table ------

	public boolean createTable();

	public boolean dropTable();

}