package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.Set;

public class Paziente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String cf;
	private String nome;
	private String cognome;
	private char sesso;
	private Set<RichiestaMedica> richiestemediche;
	public Paziente() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public char getSesso() {
		return sesso;
	}
	public void setSesso(char sesso) {
		this.sesso = sesso;
	}
	public Set<RichiestaMedica> getRichiesteMediche() {
		return richiestemediche;
	}
	public void setRichiesteMediche(Set<RichiestaMedica> richiesteMediche) {
		this.richiestemediche = richiesteMediche;
	}
	
	
}
