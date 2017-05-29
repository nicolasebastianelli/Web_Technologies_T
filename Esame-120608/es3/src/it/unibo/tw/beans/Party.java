package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Party implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String titolo;
	private String luogo;
	private Date data;
	private Set<Partecipante> partecipanti;
	public Party(long id, String titolo, String luogo, Date data, Set<Partecipante> partecipanti) {
		super();
		this.id = id;
		this.titolo = titolo;
		this.luogo = luogo;
		this.data = data;
		this.partecipanti = partecipanti;
	}
	public Party() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Set<Partecipante> getPartecipanti() {
		return partecipanti;
	}
	public void setPartecipanti(Set<Partecipante> partecipanti) {
		this.partecipanti = partecipanti;
	}
	
}
