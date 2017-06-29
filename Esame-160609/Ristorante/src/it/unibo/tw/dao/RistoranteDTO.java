package it.unibo.tw.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RistoranteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	// ---------------------------
	
	private long id;
	private String nomeRistorante;
	private String indirizzo;
	private int rating;
	private Set<PiattoDTO> piatti;
	
	
	// --- constructor ----------
	
	public RistoranteDTO() {
		this.piatti = new HashSet<PiattoDTO>();
	}


	public RistoranteDTO(long id, String nomeRistorante, String indirizzo, int rating) {
		super();
		this.id = id;
		this.nomeRistorante = nomeRistorante;
		this.indirizzo = indirizzo;
		this.rating = rating;
		this.piatti = new HashSet<PiattoDTO>();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNomeRistorante() {
		return nomeRistorante;
	}


	public void setNomeRistorante(String nomeRistorante) {
		this.nomeRistorante = nomeRistorante;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public Set<PiattoDTO> getPiatti() {
		return piatti;
	}


	public void setPiatti(Set<PiattoDTO> piatti) {
		this.piatti = piatti;
	}


	@Override
	public String toString() {
		return "RistoranteDTO [id=" + id + ", nomeRistorante=" + nomeRistorante + ", indirizzo=" + indirizzo
				+ ", rating=" + rating + ", piatti=" + piatti + "]";
	}
	
	
	// --- getters and setters --------------
	

}
