package it.unibo.tw.dao;

import java.io.Serializable;
import java.util.*;

public class BarDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// ---------------------------
	
	private int id;
	private String value;
	//  Add attributes here
	
	private List<FooDTO> foos;
	private boolean alreadyLoaded;
	
	
	// --- constructor ----------
	
	public BarDTO() {
		foos = new ArrayList<FooDTO>();
		alreadyLoaded = false;
	}
	
	
	// --- getters and setters --------------
	
	public int getId() {
		return id;
	}

	public boolean isAlreadyLoaded(){
		return this.alreadyLoaded;
	}
	
	public void isAlreadyLoaded(boolean loaded){
		this.alreadyLoaded=loaded;
	}

	public List<FooDTO> getFoos() {
		return foos;
	}

	public void setFoos(List<FooDTO> foos) {
		this.foos = foos;
	}

	public void setId(int id) {
		this.id = id;
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
		BarDTO other = (BarDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
