package it.unibo.tw.dao;

public class SCMappingDTO {
	private int idStudent;
	private int idCourse;
	
	public SCMappingDTO() {
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
		SCMappingDTO other = (SCMappingDTO) obj;
		if (this.idStudent != other.idStudent ||this.idCourse != other.idCourse)
			return false;
		return true;
	}
	
}
