package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Ospedale implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long id;
	private Integer codice;
	private String nome;
	private String citta;
	private String indirizzo;
	private Set<TipoAccertamento> tipiaccertamento;
	
	public Ospedale() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ospedale(Integer codice, String nome,String citta, String indirizzo) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.setTipiaccertamento(new HashSet<>());
	}
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public Integer getCodice() {
		return codice;
	}
	public void setCodice(Integer codice) {
		this.codice = codice;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public Set<TipoAccertamento> getTipiaccertamento() {
		return tipiaccertamento;
	}
	public void setTipiaccertamento(Set<TipoAccertamento> tipiaccertamento) {
		this.tipiaccertamento = tipiaccertamento;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
}