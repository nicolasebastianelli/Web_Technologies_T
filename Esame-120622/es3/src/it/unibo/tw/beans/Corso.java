package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.Set;

public class Corso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private int codice;
	private String titolo;
	private int crediti_formativi;
	private String nome_docente;
	private Set<Studente> studenti;
	public Corso() {

	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public int getCrediti_formativi() {
		return crediti_formativi;
	}
	public void setCrediti_formativi(int crediti_formativi) {
		this.crediti_formativi = crediti_formativi;
	}
	public String getNome_docente() {
		return nome_docente;
	}
	public void setNome_docente(String nome_docente) {
		this.nome_docente = nome_docente;
	}
	public Set<Studente> getStudenti() {
		return studenti;
	}
	public void setStudenti(Set<Studente> studenti) {
		this.studenti = studenti;
	}
	
	
}
