package beans;

import java.util.HashSet;
import java.util.Set;

public class Libri {
	private Set<Libro> libri ;

	public Libri() {
		libri = new HashSet<Libro>();
	}

	public Set<Libro> getLibri() {
		return libri;
	}

	public void setLibri(Set<Libro> libri) {
		this.libri = libri;
	}
	
	
}
