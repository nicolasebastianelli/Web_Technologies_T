package it.unibo.tw.db;


import it.unibo.tw.web.beans.Cart;
import it.unibo.tw.web.beans.Item;

public class TestShopRepository {
	 public static void main(String[] args) throws PersistenceException {
	        
	        ShopRepository sr = new ShopRepository(DataSource.DB2);
	    	sr.dropAndCreateTable();
	        
	    	Cart carrello = new Cart("prova1@prova1.it");
	    	Cart result=null;
	        Item s = new Item();
	        s.setId(1);;
	        s.setDescription("macbook");;
	        s.setPrice(1000);;
	        s.setQuantity(10);
	        sr.persistItem(s);

	        Item s2 = new Item();
	        s2.setId(2);;
	        s2.setDescription("pera");;
	        s2.setPrice(2.1);;
	        s2.setQuantity(11);
	        sr.persistItem(s2);
	        
	        carrello.put(s2, 2);
	        carrello.put(s, 5);
	        sr.persistCart(carrello);
	        
	        carrello = new Cart("prova2@prova2.it");
	        Item s3 = new Item();
	        s3.setId(3);;
	        s3.setDescription("banana");;
	        s3.setPrice(1.3);;
	        s3.setQuantity(100);
	        sr.persistItem(s3);

	        Item s4 = new Item();
	        s4.setId(4);;
	        s4.setDescription("lavacose");;
	        s4.setPrice(300);;
	        s4.setQuantity(3);
	        sr.persistItem(s4);
	        
	        carrello.put(s3, 50);
	        carrello.put(s4, 1);
	        sr.persistCart(carrello);
	        
	        carrello = new Cart("prova3@prova3.it");
	        Item s5 = new Item();
	        s5.setId(5);;
	        s5.setDescription("Armadio");;
	        s5.setPrice(200);;
	        s5.setQuantity(7);
	        sr.persistItem(s5);
	        
	        carrello.put(s5, 7);
	        sr.persistCart(carrello);
	        carrello.empty();
	        carrello.put(s5, 8);
	        sr.persistCart(carrello);
	        sr.persistCart(carrello);
	        
	        System.out.println("Carico carrelli:");
	        result=sr.findCartByEmail("prova1@prova1.it");
	        System.out.println("----------------------------------");
	        System.out.println("Carrello di prova1@prova1.it:");
	        System.out.println("");
	        System.out.println("ID\t\tDescr\t\tPrezzo\t\tGiace.\t\tQT");	        
	        for (Item sc : result.getItems()) {
	            System.out.println(sc.getId()+"\t\t"+sc.getDescription()+"\t\t"+ sc.getPrice()+"\t\t"+sc.getQuantity()+"\t\t"+result.getOrder(sc));
	        } 
	        System.out.println("----------------------------------");
	        
	        result=sr.findCartByEmail("prova2@prova2.it");
	        System.out.println("----------------------------------");
	        System.out.println("Carrello di prova2@prova2.it:");
	        System.out.println("");
	        System.out.println("ID\t\tDescr\t\tPrezzo\t\tGiace.\t\tQT");	        
	        for (Item sc : result.getItems()) {
	            System.out.println(sc.getId()+"\t\t"+sc.getDescription()+"\t\t"+ sc.getPrice()+"\t\t"+sc.getQuantity()+"\t\t"+result.getOrder(sc));
	        } 
	        System.out.println("----------------------------------");
	        System.out.println("Cerco tutti carrelli associatia delle mail:");
	        for (Cart cart : sr.findAll()) {
	        	System.out.println("----------------------------------");
		        System.out.println("Carrello di "+cart.getMail()+":");
		        System.out.println("");
		        System.out.println("ID\t\tDescr\t\tPrezzo\t\tGiace.\t\tQT");	        
		        for (Item sc : cart.getItems()) {
		            System.out.println(sc.getId()+"\t\t"+sc.getDescription()+"\t\t"+ sc.getPrice()+"\t\t"+sc.getQuantity()+"\t\t"+cart.getOrder(sc));
		        } 
		        System.out.println("----------------------------------");
			}
	        
	    }
}
