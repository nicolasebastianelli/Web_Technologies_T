package beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Documenti implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<Documento> documenti ;

	public Documenti() {
		documenti = new HashSet<Documento>();
	}

	public Set<Documento> getDocumenti() {
		return documenti;
	}

	public void addDocumento(Documento documento) {
		this.documenti.add(documento);
	}
	
}
