package it.unibo.tw.dao.db2;

import it.unibo.tw.dao.StudentDAO;
import it.unibo.tw.dao.StudentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementazione di query jdbc su una versione hqldb della tabella entry
 * 
 * La scrittura dei metodi DAO con JDBC segue sempre e comunque questo schema:
 * 
	// --- 1. Dichiarazione della variabile per il risultato ---
	// --- 2. Controlli preliminari sui dati in ingresso ---
	// --- 3. Apertura della connessione ---
	// --- 4. Tentativo di accesso al db e impostazione del risultato ---
	// --- 5. Gestione di eventuali eccezioni ---
	// --- 6. Rilascio, SEMPRE E COMUNQUE, la connessione prima di restituire il controllo al chiamante
	// --- 7. Restituzione del risultato (eventualmente di fallimento)
 * 
 * Inoltre, all'interno di uno statement JDBC si segue lo schema generale
 * 
	// --- a. Crea (se senza parametri) o prepara (se con parametri) lo statement
	// --- b. Pulisci e imposta i parametri (se ve ne sono)
	// --- c. Esegui l'azione sul database ed estrai il risultato (se atteso)
	// --- d. Cicla sul risultato (se presente) pe accedere ai valori di ogni sua tupla
	// --- e. Rilascia la struttura dati del risultato
	// --- f. Rilascia la struttura dati dello statement
 * 
 * @author pisi79
 *
 */
public class Db2StudentDAO implements StudentDAO {

	Logger logger = Logger.getLogger( getClass().getCanonicalName() );

	// === Costanti letterali per non sbagliarsi a scrivere !!! ============================
	
	static final String TABLE = "students";

	// -------------------------------------------------------------------------------------

	static final String ID = "id";
	static final String FIRSTNAME = "firstName";
	static final String LASTNAME = "lastName";
	static final String BIRTHDATE = "birthdate";
	
	// == STATEMENT SQL ====================================================================

	// INSERT INTO table ( id, name, description, ...) VALUES ( ?,?, ... );
	static final String insert = 
		"INSERT " +
			"INTO " + TABLE + " ( " + 
				ID +", "+FIRSTNAME+", "+LASTNAME+", "+BIRTHDATE+" " +
			") " +
			"VALUES (?,?,?,?) "
		;
	
	// SELECT * FROM table WHERE idcolumn = ?;
	static String read_by_id = 
		"SELECT * " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? "
		;

	// SELECT * FROM table WHERE stringcolumn = ?;
	static String read_all = 
		"SELECT * " +
		"FROM " + TABLE + " ";
	
	static String find_student_by_lastname = 
		read_all +
			"WHERE " + LASTNAME + " = ? ";
	
	// DELETE FROM table WHERE idcolumn = ?;
	static String delete = 
		"DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + ID + " = ? "
		;

	// UPDATE table SET xxxcolumn = ?, ... WHERE idcolumn = ?;
	static String update = 
		"UPDATE " + TABLE + " " +
			"SET " + 
				FIRSTNAME + " = ?, " +
				LASTNAME + " = ?, " +
				BIRTHDATE + " = ?, " +
			"WHERE " + ID + " = ? "
		;

	// SELECT * FROM table;
	static String query = 
		"SELECT * " +
			"FROM " + TABLE + " "
		;

	// -------------------------------------------------------------------------------------

	// CREATE entrytable ( code INT NOT NULL PRIMARY KEY, ... );
	static String create = 
		"CREATE " +
			"TABLE " + TABLE +" ( " +
				ID + " INT NOT NULL PRIMARY KEY, " +
				FIRSTNAME + " VARCHAR(50), " +
				LASTNAME + " VARCHAR(50), " +
				BIRTHDATE + " DATE " +
			") "
		;
	
	static String drop = 
		"DROP " +
			"TABLE " + TABLE + " "
		;
	
	// === METODI DAO =========================================================================
	
	/**
	 * C
	 */
	public void create(StudentDTO t) {
		// --- 1. Dichiarazione della variabile per il risultato ---
		//Long result = new Long(-1);
		// --- 2. Controlli preliminari sui dati in ingresso ---
		/*if ( t == null )  {
			logger.warning("create(): failed to insert a null entry");
			return result;
		}*/
		// --- 3. Apertura della connessione ---
		Connection conn = Db2DAOFactory.createConnection();
		// --- 4. Tentativo di accesso al db e impostazione del risultato ---
		try {
			// --- a. Crea (se senza parametri) o prepara (se con parametri) lo statement
			PreparedStatement prep_stmt = conn.prepareStatement(Db2StudentDAO.insert);
			// --- b. Pulisci e imposta i parametri (se ve ne sono)
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, t.getId());
			prep_stmt.setString(2, t.getFirstName());
			prep_stmt.setString(3, t.getLastName());
			prep_stmt.setDate(4, new java.sql.Date(t.getBirthDate().getTime()));
			// --- c. Esegui l'azione sul database ed estrai il risultato (se atteso)
			prep_stmt.executeUpdate();
			// --- d. Cicla sul risultato (se presente) pe accedere ai valori di ogni sua tupla
			// n.d.
			// --- e. Rilascia la struttura dati del risultato
			// n.d.
			// --- f. Rilascia la struttura dati dello statement
			prep_stmt.close();
		}
		// --- 5. Gestione di eventuali eccezioni ---
		catch (Exception e) {
			logger.warning("create(): failed to insert entry: " + e.getMessage());
			e.printStackTrace();
			//result = new Long(-2);
		}

		// Nel caso della creazione di una nuova tupla eseguo un secondo accesso per sapere che code le e' stato assegnato
		// Chiaramente e' inutile farlo se gia'  il primo accesso ha prodotto errori
		// --- 6./7. Chiusura della connessione in caso di errori e restituizione prematura di un risultato di fallimento
		/*if ( result == -2 ) {
			Db2DAOFactory.closeConnection(conn);
			return result;
		}
		
		// --- 1. Dichiarazione della variabile per il risultato ---
		// riutilizziamo quella di prima

		// --- 2. Controlli preliminari sui dati in ingresso ---
		// gia'  fatti

		// --- 3. Apertura della connessione ---
		// ce n'e' una gia'  aperta, se siamo qui

		// --- 4. Tentativo di accesso al db e impostazione del risultato ---
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Db2StudentDAO.lastInsert);
			if ( rs.next() ) {
				result = rs.getLong(1);
			}		
			rs.close();
			stmt.close();
		}
		// --- 5. Gestione di eventuali eccezioni ---
		catch (Exception e) {
			logger.warning("create(): failed to retrieve id of last inserted entry: "+e.getMessage());
			e.printStackTrace();
		}
		// --- 6. Rilascio, SEMPRE E COMUNQUE, la connessione prima di restituire il controllo al chiamante
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		// --- 7. Restituzione del risultato (eventualmente di fallimento)
		return result;*/
	}

	// -------------------------------------------------------------------------------------

	/**
	 * R
	 */
	public StudentDTO read(int id) {
		// --- 1. Dichiarazione della variabile per il risultato ---
		StudentDTO result = null;
		// --- 2. Controlli preliminari sui dati in ingresso ---
		if ( id < 0 )  {
			logger.warning("read(): cannot read an entry with a negative id");
			return result;
		}
		// --- 3. Apertura della connessione ---
		Connection conn = Db2DAOFactory.createConnection();
		// --- 4. Tentativo di accesso al db e impostazione del risultato ---
		try {
			// --- a. Crea (se senza parametri) o prepara (se con parametri) lo statement
			PreparedStatement prep_stmt = conn.prepareStatement(read_by_id);
			// --- b. Pulisci e imposta i parametri (se ve ne sono)
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			// --- c. Esegui l'azione sul database ed estrai il risultato (se atteso)
			ResultSet rs = prep_stmt.executeQuery();
			// --- d. Cicla sul risultato (se presente) pe accedere ai valori di ogni sua tupla
			if ( rs.next() ) {
				StudentDTO entry = new StudentDTO();
				entry.setId(rs.getInt(ID));
				entry.setFirstName(rs.getString(FIRSTNAME));
				entry.setLastName(rs.getString(LASTNAME));
				long secs = rs.getDate(BIRTHDATE).getTime();
				java.util.Date birthDate = new java.util.Date(secs);
				entry.setBirthDate(birthDate);
				result = entry;
			}
			// --- e. Rilascia la struttura dati del risultato
			rs.close();
			// --- f. Rilascia la struttura dati dello statement
			prep_stmt.close();
		}
		// --- 5. Gestione di eventuali eccezioni ---
		catch (Exception e) {
			logger.warning("read(): failed to retrieve entry with id = " + id+": "+e.getMessage());
			e.printStackTrace();
		}
		// --- 6. Rilascio, SEMPRE E COMUNQUE, la connessione prima di restituire il controllo al chiamante
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		// --- 7. Restituzione del risultato (eventualmente di fallimento)
		return result;
	}

	// -------------------------------------------------------------------------------------

	/**
	 * U
	 */
	public boolean update(StudentDTO t) {
		// --- 1. Dichiarazione della variabile per il risultato ---
		boolean result = false;
		// --- 2. Controlli preliminari sui dati in ingresso ---
		if ( t == null )  {
			logger.warning( "update(): failed to update a null entry");
			return result;
		}
		// --- 3. Apertura della connessione ---
		Connection conn = Db2DAOFactory.createConnection();
		// --- 4. Tentativo di accesso al db e impostazione del risultato ---
		try {
			// --- a. Crea (se senza parametri) o prepara (se con parametri) lo statement
			PreparedStatement prep_stmt = conn.prepareStatement(Db2StudentDAO.update);
			// --- b. Pulisci e imposta i parametri (se ve ne sono)
			prep_stmt.clearParameters();
			prep_stmt.setString(1, t.getFirstName());
			prep_stmt.setString(2, t.getLastName());
			prep_stmt.setDate(3, new java.sql.Date(t.getBirthDate().getTime()));
			prep_stmt.setInt(4, t.getId());
			// --- c. Esegui l'azione sul database ed estrai il risultato (se atteso)
			prep_stmt.executeUpdate();
			// --- d. Cicla sul risultato (se presente) pe accedere ai valori di ogni sua tupla
			// n.d. qui devo solo dire al chiamante che e' andato tutto liscio
			result = true;
			// --- e. Rilascia la struttura dati del risultato
			// n.d.
			// --- f. Rilascia la struttura dati dello statement
			prep_stmt.close();
		}
		// --- 5. Gestione di eventuali eccezioni ---
		catch (Exception e) {
			logger.warning("insert(): failed to update entry: "+e.getMessage());
			e.printStackTrace();
		}
		// --- 6. Rilascio, SEMPRE E COMUNQUE, la connessione prima di restituire il controllo al chiamante
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		// --- 7. Restituzione del risultato (eventualmente di fallimento)
		return result;
	}

	// -------------------------------------------------------------------------------------

	/**
	 * D
	 */
	public boolean delete(int id) {
		// --- 1. Dichiarazione della variabile per il risultato ---
		boolean result = false;
		// --- 2. Controlli preliminari sui dati in ingresso ---
		//if ( code == null || code < 0 )  {
		if ( id < 0 )  {
			logger.warning("delete(): cannot delete an entry with an invalid id ");
			return result;
		}
		// --- 3. Apertura della connessione ---
		Connection conn = Db2DAOFactory.createConnection();
		// --- 4. Tentativo di accesso al db e impostazione del risultato ---
		try {
			// --- a. Crea (se senza parametri) o prepara (se con parametri) lo statement
			PreparedStatement prep_stmt = conn.prepareStatement(Db2StudentDAO.delete);
			// --- b. Pulisci e imposta i parametri (se ve ne sono)
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, id);
			// --- c. Esegui l'azione sul database ed estrai il risultato (se atteso)
			prep_stmt.executeUpdate();
			// --- d. Cicla sul risultato (se presente) pe accedere ai valori di ogni sua tupla
			// n.d. Qui devo solo dire al chiamante che e' andato tutto liscio
			result = true;
			// --- e. Rilascia la struttura dati del risultato
			// n.d.
			// --- f. Rilascia la struttura dati dello statement
			prep_stmt.close();
		}
		// --- 5. Gestione di eventuali eccezioni ---
		catch (Exception e) {
			logger.warning("delete(): failed to delete entry with id = " + id+": "+e.getMessage());
			e.printStackTrace();
		}
		// --- 6. Rilascio, SEMPRE E COMUNQUE, la connessione prima di restituire il controllo al chiamante
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		// --- 7. Restituzione del risultato (eventualmente di fallimento)
		return result;
	}

	// -------------------------------------------------------------------------------------

	/**
	 * Find by name
	 */
	public List<StudentDTO> findStudentByLastName(String lastName) {
		// --- 1. Dichiarazione della variabile per il risultato ---
		List<StudentDTO> result = new ArrayList<StudentDTO>();
		// --- 2. Controlli preliminari sui dati in ingresso ---
		if ( lastName == null || lastName.equals("") ){
			logger.warning("findStudentByLastName(): lastName is invalid");
			return result;
		}
		// --- 3. Apertura della connessione ---
		Connection conn = Db2DAOFactory.createConnection();
		// --- 4. Tentativo di accesso al db e impostazione del risultato ---
		try {
			// --- a. Crea (se senza parametri) o prepara (se con parametri) lo statement
			PreparedStatement prep_stmt = conn.prepareStatement(Db2StudentDAO.find_student_by_lastname);
			// --- b. Pulisci e imposta i parametri (se ve ne sono)
			prep_stmt.clearParameters();
			prep_stmt.setString(1, lastName);
			// --- c. Esegui l'azione sul database ed estrai il risultato (se atteso)
			ResultSet rs = prep_stmt.executeQuery();
			// --- d. Cicla sul risultato (se presente) pe accedere ai valori di ogni sua tupla
			while ( rs.next() ) {
				StudentDTO entry = new StudentDTO();
				entry.setId(rs.getInt(ID));
				entry.setFirstName(rs.getString(FIRSTNAME));
				entry.setLastName(rs.getString(LASTNAME));
				long secs = rs.getDate(BIRTHDATE).getTime();
				entry.setBirthDate(new java.util.Date(secs));
				result.add( entry );
			}
			// --- e. Rilascia la struttura dati del risultato
			rs.close();
			// --- f. Rilascia la struttura dati dello statement
			prep_stmt.close();
		}
		// --- 5. Gestione di eventuali eccezioni ---
		catch (Exception e) {
			logger.warning("find(): failed to retrieve entry with lastName = " + lastName + ": "+e.getMessage());
			e.printStackTrace();
		}
		// --- 6. Rilascio, SEMPRE E COMUNQUE, la connessione prima di restituire il controllo al chiamante
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		// --- 7. Restituzione del risultato (eventualmente di fallimento)
		return result;
	}

	// -------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------

	/**
	 * Creazione della table
	 */
	public boolean createTable() {
		// --- 1. Dichiarazione della variabile per il risultato ---
		boolean result = false;
		// --- 2. Controlli preliminari sui dati in ingresso ---
		// n.d.
		// --- 3. Apertura della connessione ---
		Connection conn = Db2DAOFactory.createConnection();
		// --- 4. Tentativo di accesso al db e impostazione del risultato ---
		try {
			// --- a. Crea (se senza parametri) o prepara (se con parametri) lo statement
			Statement stmt = conn.createStatement();
			// --- b. Pulisci e imposta i parametri (se ve ne sono)
			// n.d.
			// --- c. Esegui l'azione sul database ed estrai il risultato (se atteso)
			stmt.execute(create);
			// --- d. Cicla sul risultato (se presente) pe accedere ai valori di ogni sua tupla
			// n.d. Qui devo solo dire al chiamante che Ã¨ andato tutto liscio
			result = true;
			// --- e. Rilascia la struttura dati del risultato
			// n.d.
			// --- f. Rilascia la struttura dati dello statement
			stmt.close();
		}
		// --- 5. Gestione di eventuali eccezioni ---
		catch (Exception e) {
			logger.warning("createTable(): failed to create table '"+TABLE+"': "+e.getMessage());
		}
		// --- 6. Rilascio, SEMPRE E COMUNQUE, la connessione prima di restituire il controllo al chiamante
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		// --- 7. Restituzione del risultato (eventualmente di fallimento)
		return result;
	}

	// -------------------------------------------------------------------------------------

	/**
	 * Rimozione della table
	 */
	public boolean dropTable() {
		// --- 1. Dichiarazione della variabile per il risultato ---
		boolean result = false;
		// --- 2. Controlli preliminari sui dati in ingresso ---
		// n.d.
		// --- 3. Apertura della connessione ---
		Connection conn = Db2DAOFactory.createConnection();
		// --- 4. Tentativo di accesso al db e impostazione del risultato ---
		try {
			// --- a. Crea (se senza parametri) o prepara (se con parametri) lo statement
			Statement stmt = conn.createStatement();
			// --- b. Pulisci e imposta i parametri (se ve ne sono)
			// n.d.
			// --- c. Esegui l'azione sul database ed estrai il risultato (se atteso)
			stmt.execute(drop);
			// --- d. Cicla sul risultato (se presente) pe accedere ai valori di ogni sua tupla
			// n.d. Qui devo solo dire al chiamante che Ã¨ andato tutto a posto.
			result = true;
			// --- e. Rilascia la struttura dati del risultato
			// n.d.
			// --- f. Rilascia la struttura dati dello statement
			stmt.close();
		}
		// --- 5. Gestione di eventuali eccezioni ---
		catch (Exception e) {
			logger.warning("dropTable(): failed to drop table '"+TABLE+"': "+e.getMessage());
		}
		// --- 6. Rilascio, SEMPRE E COMUNQUE, la connessione prima di restituire il controllo al chiamante
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		// --- 7. Restituzione del risultato (eventualmente di fallimento)
		return result;
	}

}