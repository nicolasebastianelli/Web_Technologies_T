package it.unibo.tw.hibernate;

import java.io.Serializable;

public class SCMapping implements Serializable{
	private static final long serialVersionUID = 1L;
	private int idStudent;
	private int idCourse;
	
	public SCMapping() {
		// TODO Auto-generated constructor stub
	}
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public int getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SCMapping other = (SCMapping) obj;
		if (this.idStudent != other.idStudent ||this.idCourse != other.idCourse)
			return false;
		return true;
	}
	
}
