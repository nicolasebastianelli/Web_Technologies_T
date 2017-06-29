package beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Righe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<String> righe;

	public Set<String> getRighe() {
		return righe;
	}

	public synchronized void  addRiga(Set<String> righe) {
		this.righe = righe;
	}

	public Righe() {
		righe= new HashSet<String>();
	}
	
	

}
