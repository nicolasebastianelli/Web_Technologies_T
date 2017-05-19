package it.unibo.tw.db;

public class InitStudentRepository {
	
	public static void main(String[] args) throws PersistenceException {
		
		// utile per eliminare eventuali dati in tabella e crearne una nuova
		StudentRepository sp = new StudentRepository(DataSource.DB2);
		sp.dropAndCreateTable();
			
	}

}
