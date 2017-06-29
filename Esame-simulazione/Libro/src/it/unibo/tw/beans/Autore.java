package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Autore implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private Set<Libro> listalibri ;
	public Autore() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Autore(String codiceFiscale, String nome, String cognome) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		listalibri = new HashSet<Libro>();
	}
	
	public Set<Libro> getListalibri() {
		return listalibri;
	}
	public void setListalibri(Set<Libro> libri) {
		this.listalibri = libri;
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
	
}