package it.unibo.tw.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DAOTest {
	
	public static final int DAO = DAOFactory.DB2;
	
	public static void main(String[] args) {
		
		// --- Factory ---
		
		DAOFactory daoFactoryInstance = DAOFactory.getDAOFactory(DAO);
		
		// --- DAOs ---
		
		BarDAO barDAO = daoFactoryInstance.getBarDAO();
		barDAO.dropTable();
		barDAO.createTable();
		
		FooDAO fooDAO = DAOFactory.getDAOFactory(DAO).getFooDAO();
		fooDAO.dropTable();
		fooDAO.createTable();
		
		FooBarMappingDAO mappingDAO = DAOFactory.getDAOFactory(DAO).getFooBarMappingDAO();
		mappingDAO.dropTable();
		mappingDAO.createTable();
		
		// --- DTOs ---
		
		BarDTO bar = new BarDTO();
		bar.setId(1);
		bar.setValue("barValue1");
		barDAO.create(bar);

		bar = new BarDTO();
		bar.setId(2);
		bar.setValue("barValue2");
		barDAO.create(bar);
		
		FooDTO foo = new FooDTO();
		foo.setId(1);
		foo.setValue("fooValue1");
		fooDAO.create(foo);

		foo = new FooDTO();
		foo.setId(2);
		foo.setValue("fooValue2");
		fooDAO.create(foo);
		
		mappingDAO.create(1, 1);
		
		mappingDAO.create(2, 1);
		
		mappingDAO.create(2, 2);
		
		//TODO
		
		bar = barDAO.read(1);
		System.out.println("Bar: "+bar.getId()+" is associated with the following foos: ");
		List<FooDTO> foosOfBar = bar.getFoos();
		for(FooDTO fooOfBar : foosOfBar)
			System.out.println(""+fooOfBar.getId()+" "+fooOfBar.getValue());
		System.out.println();
		
		foo = fooDAO.read(1);
		System.out.println("Foo: "+foo.getId()+" is associated with the following bars: ");
		List<BarDTO> barsOfFoo = foo.getBars();
		for(BarDTO barOfFoo : barsOfFoo)
			System.out.println(""+barOfFoo.getId()+" "+barOfFoo.getValue());
		System.out.println();
        
		File f = new File("Result.txt");
        try {
        if(!f.exists())
        {
				f.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(f);
        
        PrintWriter out = new PrintWriter(fos);
        out.println();      
        out.close();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
