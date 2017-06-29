package it.unibo.tw.dao;

import java.io.Serializable;

public class StadioDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
private int codices;
private String nome;
private String citta;
	// ---------------------------
	
	
	
	// --- constructor ----------
	
	public StadioDTO() {
	}



	public StadioDTO(int codice, String nome, String citta) {
		super();
		this.codices = codice;
		this.nome = nome;
		this.citta = citta;
	}



	public int getCodice() {
		return codices;
	}



	public void setCodice(int codice) {
		this.codices = codice;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCitta() {
		return citta;
	}



	public void setCitta(String citta) {
		this.citta = citta;
	}


	

	
}
