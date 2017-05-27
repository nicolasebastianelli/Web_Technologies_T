package beans;

import java.io.Serializable;

public class Scommessa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String vincitore;
	private String perdente;
	private Double importo;
	private Double vincita;
	
	public Scommessa() {
		super();
	}
	public String getVincitore() {
		return vincitore;
	}
	public void setVincitore(String vincitore) {
		this.vincitore = vincitore;
	}
	public String getPerdente() {
		return perdente;
	}
	
	public void setPerdente(String perdente) {
		this.perdente = perdente;
	}
	public Double getImporto() {
		return importo;
	}
	public void setImporto(Double importo) {
		this.importo = importo;
	}
	public Double getVincita() {
		return vincita;
	}
	public void setVincita(Double vincita) {
		this.vincita = vincita;
	}

}
