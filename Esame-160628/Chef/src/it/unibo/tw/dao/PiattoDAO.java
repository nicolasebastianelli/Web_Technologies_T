package it.unibo.tw.dao;


public interface PiattoDAO {

	// --- CRUD -------------

	public void create(PiattoDTO piatto);

	public PiattoDTO read(String name);

	public boolean update(PiattoDTO paitto);

	public boolean delete(String name);

	
	// ----------------------------------
	
	public boolean createTable();

	public boolean dropTable();

}
