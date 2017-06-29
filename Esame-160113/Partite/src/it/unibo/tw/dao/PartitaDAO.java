package it.unibo.tw.dao;

public interface PartitaDAO {

	// --- CRUD -------------

	public void create(PartitaDTO partita);

	public PartitaDTO read(int code);

	public boolean update(PartitaDTO partita);

	public boolean delete(int code);


	// ----------------------------------

	
	public boolean createTable();

	public boolean dropTable();

}
