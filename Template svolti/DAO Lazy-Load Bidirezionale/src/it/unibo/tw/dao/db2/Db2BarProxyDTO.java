package it.unibo.tw.dao.db2;

import java.util.List;

import it.unibo.tw.dao.FooDTO;
import it.unibo.tw.dao.FooBarMappingDAO;
import it.unibo.tw.dao.BarDTO;

public class Db2BarProxyDTO extends BarDTO{
	
	private static final long serialVersionUID = 1L;

	public Db2BarProxyDTO(){
		super();
	}
	
	@Override
	public List<FooDTO> getFoos(){
		if(isAlreadyLoaded()){
			return super.getFoos();
		}
		else{
			FooBarMappingDAO fooBarMappingDAO = new Db2FooBarMappingDAO();
			isAlreadyLoaded(true);
			return fooBarMappingDAO.getFooByBar(super.getId());
		}
	}

}
