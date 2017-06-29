package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Paziente implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String codiceFiscale;
	
	public Paziente() {
	}
	public Paziente(String codiceFiscale, String nome, String cognome, Character sesso) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.richiesteMediche = new HashSet<>();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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
	public Character getSesso() {
		return sesso;
	}
	public void setSesso(Character sesso) {
		this.sesso = sesso;
	}
	public Set<RichiestaMedica> getRichiesteMediche() {
		return richiesteMediche;
	}
	public void setRichiesteMediche(Set<RichiestaMedica> richiesteMediche) {
		this.richiesteMediche = richiesteMediche;
	}
	private String nome;
	private String cognome;
	private Character sesso;
	private Set<RichiestaMedica> richiesteMediche;
}