package it.unibo.tw.dao;

import java.io.Serializable;

public class ChefDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	// ---------------------------
	
	private String nomeChef;
	private int numeroStelle;
	private String nomeRistorante;
	
	
	// --- constructor ----------
	
	public ChefDTO() {
	}
	
	
	// --- getters and setters --------------
	public String getNomeChef() {
		return nomeChef;
	}


	public void setNomeChef(String nomeChef) {
		this.nomeChef = nomeChef;
	}


	public int getNumeroStelle() {
		return numeroStelle;
	}


	public void setNumeroStelle(int numeroStelle) {
		this.numeroStelle = numeroStelle;
	}


	public String getNomeRistorante() {
		return nomeRistorante;
	}


	public void setNomeRistorante(String nomeRistorante) {
		this.nomeRistorante = nomeRistorante;
	}



	// --- utilities ----------------------------


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChefDTO other = (ChefDTO) obj;
		if (nomeChef != other.nomeChef)
			return false;
		return true;
	}
	

}
