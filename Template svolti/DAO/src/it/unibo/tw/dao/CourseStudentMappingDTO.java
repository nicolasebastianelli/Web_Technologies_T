package it.unibo.tw.dao;

import java.io.Serializable;

public class CourseStudentMappingDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	// ---------------------------
	
	private int idCourse;
	private int idStudent;
	
	
	// --- constructor ----------
	
	public CourseStudentMappingDTO() {
	}
	
	
	// --- getters and setters --------------
	
	public int getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}
	

	public int getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}


	// --- utilities ----------------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCourse;
		result = prime * result + idStudent;
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
		CourseStudentMappingDTO other = (CourseStudentMappingDTO) obj;
		if (idCourse != other.idCourse)
			return false;
		if (idStudent != other.idStudent)
			return false;
		return true;
	}
	
	

	
}
