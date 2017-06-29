package it.unibo.tw.dao;

import java.io.Serializable;
import java.util.*;

public class FooDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// ---------------------------
	
	private int id;
	private String value;
	//  Add attributes here 
	
	private List<BarDTO> bars;
	private boolean alreadyLoaded;
	
	
	// --- constructor ----------
	
	public FooDTO() {
		this.bars = new ArrayList<BarDTO>();
		this.alreadyLoaded = false;
	}
	
	// --- getters and setters --------------
	
	public boolean isAlreadyLoaded(){
		return this.alreadyLoaded;
	}
	
	public void isAlreadyLoaded(boolean loaded){
		this.alreadyLoaded=loaded;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<BarDTO> getBars() {
		return bars;
	}

	public void setBars(List<BarDTO> bars) {
		this.bars = bars;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void setValue(String value){
		this.value = value;
	}

	// --- utilities ----------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FooDTO other = (FooDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
