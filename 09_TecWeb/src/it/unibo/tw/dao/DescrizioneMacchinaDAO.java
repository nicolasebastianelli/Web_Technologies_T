package it.unibo.tw.dao;


public interface DescrizioneMacchinaDAO {

	// --- CRUD -------------

	public void create(DescrizioneMacchinaDTO student);

	public DescrizioneMacchinaDTO read(String code);

	public boolean update(DescrizioneMacchinaDTO student);

	public boolean delete(int code);

	
	// ----------------------------------

	

	// ----------------------------------

	
	public boolean createTable();

	public boolean dropTable();

}
