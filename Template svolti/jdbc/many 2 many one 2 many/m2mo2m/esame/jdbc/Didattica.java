package m2mo2m.esame.jdbc;

import java.io.Serializable;

public class Didattica implements Serializable{
	private static final long serialVersionUID = 1L;

	private long Docente;
	private long Corso;
	public long getDocente() {
		return Docente;
	}
	public void setDocente(long docente) {
		Docente = docente;
	}
	public long getCorso() {
		return Corso;
	}
	public void setCorso(long corso) {
		Corso = corso;
	}
	@Override
	public String toString() {
		return "Didattica [Docente=" + Docente + ", Corso=" + Corso + "]";
	}
	public Didattica(long docente, long corso) {
		super();
		Docente = docente;
		Corso = corso;
	}
	public Didattica() {
		// TODO Auto-generated constructor stub
	}
	
}