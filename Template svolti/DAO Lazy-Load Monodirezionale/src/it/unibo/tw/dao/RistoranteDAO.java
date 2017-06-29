package it.unibo.tw.dao;
import java.util.Set;

public interface RistoranteDAO {

	// --- CRUD -------------

	public void create(RistoranteDTO obj);
	public RistoranteDTO readLazy(long id);
	public boolean update(RistoranteDTO obj);
	public boolean delete(long id);

	public boolean createTable();
	public boolean dropTable();
	public boolean isRelationshipOwner();
	public void setRelationshipOwner(boolean relationshipOwner);
	public Set<RistoranteDTO> readLazyByCitta(String citta);
	public Set<RistoranteDTO> readLazyAll();

}
