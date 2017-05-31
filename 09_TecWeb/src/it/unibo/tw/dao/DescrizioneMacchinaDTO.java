package it.unibo.tw.dao;

import java.io.Serializable;

public class DescrizioneMacchinaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	// ---------------------------
	private String targa;
	private String modello;
	private String colore;
	
	
	

	// --- utilities ----------------------------
	
	public DescrizioneMacchinaDTO() {
	
	}


	public String getTarga() {
		return targa;
	}


	public void setTarga(String targa) {
		this.targa = targa;
	}


	public String getModello() {
		return modello;
	}


	public void setModello(String modello) {
		this.modello = modello;
	}


	public String getColore() {
		return colore;
	}


	public void setColore(String colore) {
		this.colore = colore;
	}


	public DescrizioneMacchinaDTO(String targa, String modello, String colore) {
		super();
		this.targa = targa;
		this.modello = modello;
		this.colore = colore;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DescrizioneMacchinaDTO other = (DescrizioneMacchinaDTO) obj;
		if (targa != other.targa)
			return false;
		return true;
	}
	
	

}
