package it.unibo.tw.dao;

public interface PiattoDAO {

	
	// --- CRUD -------------

		public void create(PiattoDTO piatto);

		public PiattoDTO read(long id);

		public boolean update(PiattoDTO piatto);

		public boolean delete(long id);
		
		public boolean isRelationshipOwner();
		public void setRelationshipOwner(boolean relationshipOwner);

		public boolean createTable();

		public boolean dropTable();
}
