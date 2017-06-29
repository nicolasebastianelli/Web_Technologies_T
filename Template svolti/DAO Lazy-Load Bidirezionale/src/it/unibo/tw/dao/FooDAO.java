package it.unibo.tw.dao;

public interface FooDAO {

	// ------------- CRUD -------------

	public void create(FooDTO foo);

	public FooDTO read(int id);

	public boolean update(FooDTO foo);

	public boolean delete(int id);

	// ---------- Utility -------------

	// --- Create and Drop Table ------
	
	public boolean createTable();

	public boolean dropTable();

}
