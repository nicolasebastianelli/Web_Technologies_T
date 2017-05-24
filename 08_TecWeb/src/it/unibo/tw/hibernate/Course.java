package it.unibo.tw.hibernate;

import java.io.Serializable;

public class Course  implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
