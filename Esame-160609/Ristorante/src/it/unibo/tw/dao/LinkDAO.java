package it.unibo.tw.dao;

import java.util.Set;

public interface LinkDAO {

	void create(long id_Ristorante, long id_Piatto);

	Set<Long> read_by_id_Ristorante(long id_Ristorante);

	Set<Long> read_by_id_Piatto(long id_Piatto);

	boolean delete(long id_Ristorante, long id_Piatto);

	boolean delete_by_id_Ristorante(long id_Ristorante);

	boolean delete_by_id_Piatto(long id_Piatto);

	boolean createTable();

	boolean dropTable();

}

