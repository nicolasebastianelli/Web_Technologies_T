package it.unibo.tw.dao;

import java.io.Serializable;
import java.util.Date;

public class PartitaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	// ---------------------------
	
	private int codicep;
	private String categoria;
	private String girone;
	private String nomecasa;
	private String nomeospite;
	private int codicestadio;
	private Date data;
	
	
	// --- constructor ----------
	
	public PartitaDTO() {
	}
	
	
	public PartitaDTO(int codice, String categoria, String girone, String nomecasa, String nomeospite, Date data) {
		super();
		this.codicep = codice;
		this.categoria = categoria;
		this.girone = girone;
		this.nomecasa = nomecasa;
		this.nomeospite = nomeospite;
		this.data = data;
	}


	// --- getters and setters --------------
	
	public int getCodice() {
		return codicep;
	}

	public void setCodice(int codice) {
		this.codicep = codice;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getGirone() {
		return girone;
	}

	public void setGirone(String girone) {
		this.girone = girone;
	}

	public String getNomecasa() {
		return nomecasa;
	}

	public void setNomecasa(String nomecasa) {
		this.nomecasa = nomecasa;
	}

	public String getNomeospite() {
		return nomeospite;
	}

	public void setNomeospite(String nomeospite) {
		this.nomeospite = nomeospite;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date date) {
		this.data = date;
	}


	public int getCodicestadio() {
		return codicestadio;
	}


	public void setCodicestadio(int codicestadio) {
		this.codicestadio = codicestadio;
	}


}
