package it.unibo.tw.dao.db2;

import java.util.List;

import it.unibo.tw.dao.FooDTO;
import it.unibo.tw.dao.FooBarMappingDAO;
import it.unibo.tw.dao.BarDTO;

public class Db2FooProxyDTO extends FooDTO{

	private static final long serialVersionUID = 1L;

	public Db2FooProxyDTO() {
		super();
	}
	
	@Override
	public List<BarDTO> getBars(){
		if(isAlreadyLoaded())
			return super.getBars();
		else{
			FooBarMappingDAO fooBarMappingDAO = new Db2FooBarMappingDAO();
			isAlreadyLoaded(true);
			return fooBarMappingDAO.getBarByFoo(super.getId());
		}
	}

}
