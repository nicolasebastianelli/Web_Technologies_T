package it.unibo.tw.dao;

public interface BarDAO {

	// ------------- CRUD -------------

	public void create(BarDTO bar);

	public BarDTO read(int code);

	public boolean update(BarDTO bar);

	public boolean delete(int code);

	
	// ---------- Utility -------------

	// --- Create and Drop Table ------
	
	public boolean createTable();

	public boolean dropTable();

}
