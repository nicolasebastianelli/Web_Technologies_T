package it.unibo.tw.dao;

import java.io.Serializable;

public class PiattoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	// ---------------------------
	
	private String nomePiatto;
	private int tempoPreparazione;
	private double calorie;
	private String nomeChef;
	
	// --- constructor ----------
	
	public PiattoDTO() {
	}
	
	
	// --- getters and setters --------------
	public String getNomePiatto() {
		return nomePiatto;
	}


	public void setNomePiatto(String nomePiatto) {
		this.nomePiatto = nomePiatto;
	}


	public int getTempoPreparazione() {
		return tempoPreparazione;
	}


	public void setTempoPreparazione(int tempoPreparazione) {
		this.tempoPreparazione = tempoPreparazione;
	}


	public double getCalorie() {
		return calorie;
	}


	public void setCalorie(double calorie) {
		this.calorie = calorie;
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
		PiattoDTO other = (PiattoDTO) obj;
		if (nomePiatto != other.nomePiatto)
			return false;
		return true;
	}


	public String getNomeChef() {
		return nomeChef;
	}


	public void setNomeChef(String nomeChef) {
		this.nomeChef = nomeChef;
	}


}
