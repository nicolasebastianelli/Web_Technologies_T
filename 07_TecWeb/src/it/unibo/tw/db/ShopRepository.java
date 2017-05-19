package it.unibo.tw.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


import it.unibo.tw.web.beans.Cart;
import it.unibo.tw.web.beans.Item;

public class ShopRepository {
	private DataSource dataSource;

	public ShopRepository(int databaseType) {
		dataSource = new DataSource(databaseType);
	}

	public void dropAndCreateTable() throws PersistenceException{
		Connection connection = this.dataSource.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement ();
			try{
				statement.executeUpdate ("DROP TABLE cart");
				System.out.println("eliminata tabella cart");
				statement.executeUpdate ("DROP TABLE item");
				System.out.println("eliminata tabella item");
			}
			catch (SQLException e) {
				// the table does not exist
			}
			statement.executeUpdate (
					"CREATE TABLE cart ("
							+ "idc VARCHAR(30) NOT NULL, "
							+ "iditem INT NOT NULL , " 
							+ "qtyorder INT NOT NULL DEFAULT 0, " 
							+"primary key (idc,iditem)"
							+ ") "
					);
			System.out.println("creata tabella cart");
			statement.executeUpdate (
					"CREATE TABLE item ("
							+ "idi INT NOT NULL PRIMARY KEY,"
							+ "descriz VARCHAR(100), " 
							+ "prezzo FLOAT NOT NULL," 
							+ "qta INT NOT NULL DEFAULT 0" 
							+ ") "
					);
			System.out.println("creata tabella item");
			statement.close ();
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			}
			catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}


	public void persistCart(Cart carrello) throws PersistenceException{
		Connection connection = this.dataSource.getConnection();
		int status;
		PreparedStatement statement = null; 
		String insert = "insert into cart(idc, iditem, qtyorder) values (?,?,?)";
		try {
			for (Item item : carrello.getItems()) {
				status =findCartByPrimaryKey(carrello.getMail(),item.getId(),carrello.getOrder(item));
				if (status==0){
					if(itemExist(item.getId())==0)
						persistItem(item);
					statement = connection.prepareStatement(insert);
					statement.setString(1, carrello.getMail());
					statement.setInt(2, item.getId());
					statement.setInt(3, carrello.getOrder(item));
					statement.executeUpdate();					
					System.out.println("Inserito oggetto "+item.getId()+" con mail "+carrello.getMail()+" in CART");
				}
				else if(status==1){
					UpdateCartElem(carrello.getMail(),item.getId(),carrello.getOrder(item));
					System.out.println("Aggiornato oggetto "+item.getId()+" per mail "+carrello.getMail()+" in CART");
				}
				else{
					System.out.println("Oggetto "+item.getId()+" per mail "+carrello.getMail()+" gia esistente in CART");
				}

			}            
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			}
			catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	private void UpdateCartElem(String mail, int idi, int order) throws PersistenceException{
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		String update ="UPDATE cart set qtyorder = ? where idc =? and iditem =?";
		try {
			statement = connection.prepareStatement(update);
			statement.setInt(1,order);
			statement.setString(2, mail);
			statement.setInt(3,idi);
			try{
				statement.executeUpdate();
			}
			catch (SQLException e) {
				// the table does not exist
			}
			statement.close ();
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			}
			catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

	}

	public void persistItem(Item oggetto) throws PersistenceException{
		Connection connection = this.dataSource.getConnection();

		PreparedStatement statement = null; 
		String insert = "insert into item(idi, descriz, prezzo, qta) values (?,?,?,?)";
		try {
			statement = connection.prepareStatement(insert);
			statement.setInt(1, oggetto.getId());
			statement.setString(2, oggetto.getDescription());
			statement.setDouble(3, oggetto.getPrice());
			statement.setInt(4, oggetto.getQuantity());
			statement.executeUpdate();       
			System.out.println("inserito elemento "+oggetto.getId()+" in ITEM");
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			}
			catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void deleteCart(String email) throws PersistenceException{
		Connection connection = this.dataSource.getConnection();

		PreparedStatement statement = null;
		String insert = "delete from cart where idc = ?";
		try {
			statement = connection.prepareStatement(insert);
			statement.setString(1, email);
			statement.executeUpdate();
			System.out.println("Rimosso carrello della mail "+email);
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			}
			catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}   

	public Cart findCartByEmail(String email) throws PersistenceException {
		Cart carrello = new Cart() ;
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		String query = "select * from cart where idc =?";
		int iditem;
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				iditem=result.getInt("iditem");
				carrello.put(findItemById(iditem),result.getInt("qtyorder"));
			}
			carrello.setMail(email);
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			}
			catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return carrello;
	}

	private Item findItemById(int idi) throws PersistenceException{
		Item oggetto = new Item();
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		String query = "select * from item where idi =?";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, idi);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				oggetto.setId(result.getInt("idi"));
				oggetto.setDescription(result.getString("descriz"));
				oggetto.setPrice(result.getInt("prezzo"));
				oggetto.setQuantity(result.getInt("qta"));
			}
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			}
			catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return oggetto;
	}

	public List<Cart> findAll() throws PersistenceException {
		List<Cart> carrelli = null;
		Cart carrello = new Cart();
		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		String query = "select distinct idc from cart";
		try {
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				carrelli = new LinkedList<Cart>();
				System.out.println("Cerco carrello per mail "+result.getString("idc"));
				carrello = findCartByEmail(result.getString("idc"));
				carrelli.add(carrello);
			}
			while(result.next()) {
				System.out.println("Cerco carrello per mail "+result.getString("idc"));
				carrello = findCartByEmail(result.getString("idc"));;
				carrelli.add(carrello);
			}
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return carrelli;
	}  

	public int findCartByPrimaryKey(String idc, int idi , int qty) throws PersistenceException {
		int result = 0;

		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		String query = "select * from cart where idc=? and iditem =?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, idc);
			statement.setInt(2, idi);
			ResultSet resultq = statement.executeQuery();
			if (resultq.next()) {
				result =1;
				if(resultq.getInt("qtyorder")==qty){
					result=2;
				}
			}
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return result;
	}   

	public int itemExist(int idi) throws PersistenceException {
		int result = 0;

		Connection connection = this.dataSource.getConnection();
		PreparedStatement statement = null;
		String query = "select * from item where idi=?";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, idi);
			ResultSet resultq = statement.executeQuery();
			if (resultq.next()) {
				result =1;
			}
		}
		catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return result;
	}   

}
