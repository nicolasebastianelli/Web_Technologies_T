package it.unibo.tw.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PiattoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String nomePiatto;
	private String tipo;
	private Set<RistoranteDTO> ristoranti;
	public PiattoDTO() {
		this.ristoranti= new HashSet<RistoranteDTO>();
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNomePiatto() {
		return nomePiatto;
	}


	public void setNomePiatto(String nomePiatto) {
		this.nomePiatto = nomePiatto;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Set<RistoranteDTO> getRistoranti() {
		return ristoranti;
	}


	public void setRistoranti(Set<RistoranteDTO> ristoranti) {
		this.ristoranti = ristoranti;
	}


	public PiattoDTO(long id, String nomePiatto, String tipo) {
		super();
		this.id = id;
		this.nomePiatto = nomePiatto;
		this.tipo = tipo;
		this.ristoranti= new HashSet<RistoranteDTO>();
	}
	
	
}
