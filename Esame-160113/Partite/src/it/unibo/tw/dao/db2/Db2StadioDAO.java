package it.unibo.tw.dao.db2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.unibo.tw.dao.StadioDAO;
import it.unibo.tw.dao.StadioDTO;
import it.unibo.tw.dao.PartitaDTO;

public class Db2StadioDAO implements StadioDAO {

	// === Costanti letterali per non sbagliarsi a scrivere !!! ============================

	static final String TABLE = "stadio";

	// -------------------------------------------------------------------------------------

	static final String CODICE = "codices";
	static final String NOME = "nome";
	static final String CITTA = "citta";

	// == STATEMENT SQL ====================================================================

	// INSERT INTO table ( idCourse, idStudent ) VALUES ( ?,? );
	static final String insert = 
			"INSERT " +
					"INTO " + TABLE + " ( " + 
					CODICE +", "+NOME + ","+CITTA+" " +
					") " +
					"VALUES (?,?,?) "
					;

	// SELECT * FROM table WHERE idcolumns = ?;
	static String read = 
			"SELECT * " +
					"FROM " + TABLE + " " +
					"WHERE " + CODICE + " = ? "
					;

	// SELECT * FROM table WHERE idcolumns = ?;
	static String read_by_id_stadio = 
			"SELECT * " +
					"FROM partite INNER JOIN stadio on partite.codicestadio = stadio."+CODICE+" "+
					"WHERE " + CODICE + " = ? "
					;

	// SELECT * FROM table WHERE stringcolumn = ?;
	static String read_all = 
			"SELECT * " +
					"FROM " + TABLE + " ";

	// DELETE FROM table WHERE idcolumn = ?;
	static String delete = 
			"DELETE " +
					"FROM " + TABLE + " " +
					"WHERE " + CODICE + " = ? "
					;

	// UPDATE table SET xxxcolumn = ?, ... WHERE idcolumn = ?;
	/*static String update = 
		"UPDATE " + TABLE + " " +
			"SET " + 
				NAME + " = ?, " +
			"WHERE " + ID + " = ? "
		;*/

	// SELECT * FROM table;
	static String query = 
			"SELECT * " +
					"FROM " + TABLE + " "
					;
	
	static String update = 
			"UPDATE " + TABLE + " " +
				"SET " + 
				NOME + " = ?, " +
				CITTA + " = ? " +
				"WHERE " + CODICE + " = ? "
			;
	// -------------------------------------------------------------------------------------

	// CREATE entrytable ( code INT NOT NULL PRIMARY KEY, ... );
	static String create = 
			"CREATE " +
					"TABLE " + TABLE +" ( " +
					CODICE + " INT NOT NULL PRIMARY KEY, " +
					NOME + " VARCHAR(50), " +
					CITTA + " VARCHAR(50) " +
					") "
					;
	static String drop = 
			"DROP " +
					"TABLE " + TABLE + " "
					;


	// === METODI DAO =========================================================================

	@Override
	public void create(StadioDTO s) {
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(insert);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, s.getCodice());
			prep_stmt.setString(2, s.getNome());
			prep_stmt.setString(3, s.getCitta());
			prep_stmt.executeUpdate();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("create(): failed to insert entry: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public StadioDTO read(int codice) {
		StadioDTO result = null;
		if ( codice < 0 )  {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, codice);
			ResultSet rs = prep_stmt.executeQuery();
			if ( rs.next() ) {
				StadioDTO entry = new StadioDTO();
				entry.setCodice(rs.getInt(CODICE));
				entry.setNome(rs.getString(NOME));
				entry.setCitta(rs.getString(CITTA));
				result = entry;
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to retrieve entry with codice = " + codice+": "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	public List<StadioDTO> readAll() {
		List<StadioDTO> result = new ArrayList<StadioDTO>();
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(read_all);
			prep_stmt.clearParameters();
			ResultSet rs = prep_stmt.executeQuery();
			while ( rs.next() ) {
				StadioDTO entry = new StadioDTO();
				entry.setCodice(rs.getInt(CODICE));
				entry.setNome(rs.getString(NOME));
				entry.setCitta(rs.getString(CITTA));
				result.add(entry);
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to retrieve entry : "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}
	@Override
	public boolean update(StadioDTO stadio) {
		boolean result = false;
		if ( stadio == null)  {
			System.out.println( "update(): failed to update a null entry");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(Db2StadioDAO.update);
			prep_stmt.clearParameters();
			prep_stmt.setString(1, stadio.getNome());
			prep_stmt.setString(2, stadio.getCitta());
			prep_stmt.setInt(3, stadio.getCodice());
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("insert(): failed to update entry: "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean delete(int codice) {
		boolean result = false;
		if ( codice < 0)  {
			System.out.println("delete(): cannot delete an entry with an invalid id ");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			PreparedStatement prep_stmt = conn.prepareStatement(delete);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, codice);
			prep_stmt.executeUpdate();
			result = true;
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("delete(): failed to delete entry with codice = " + codice +" : "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean createTable() {
		boolean result = false;
		Connection conn = Db2DAOFactory.createConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(create);
			result = true;
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("createTable(): failed to create table '"+TABLE+"': "+e.getMessage());
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public boolean dropTable() {
		boolean result = false;
		Connection conn = Db2DAOFactory.createConnection();
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(drop);
			result = true;
			stmt.close();
		}
		catch (Exception e) {
			System.out.println("dropTable(): failed to drop table '"+TABLE+"': "+e.getMessage());
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

	@Override
	public List<PartitaDTO> findPartiteByStadioId(int codice) {
		List<PartitaDTO> result = null;
		if ( codice < 0 )  {
			System.out.println("read(): cannot read an entry with a negative id");
			return result;
		}
		Connection conn = Db2DAOFactory.createConnection();
		try {
			result = new ArrayList<PartitaDTO>();
			PreparedStatement prep_stmt = conn.prepareStatement(read_by_id_stadio);
			prep_stmt.clearParameters();
			prep_stmt.setInt(1, codice);
			ResultSet rs = prep_stmt.executeQuery();
			while ( rs.next() ) {
				PartitaDTO entry = new PartitaDTO();
				entry.setCodice(rs.getInt("codicep"));
				entry.setCategoria(rs.getString("categoria"));
				entry.setGirone(rs.getString("girone"));
				entry.setNomecasa(rs.getString("nomecasa"));
				entry.setNomeospite(rs.getString("nomeospite"));
				entry.setCodicestadio(rs.getInt("codicestadio"));
				entry.setData(rs.getDate("data"));
				result.add(entry);
			}
			rs.close();
			prep_stmt.close();
		}
		catch (Exception e) {
			System.out.println("read(): failed to retrieve entry with codicestadio = " + codice+": "+e.getMessage());
			e.printStackTrace();
		}
		finally {
			Db2DAOFactory.closeConnection(conn);
		}
		return result;
	}

}
