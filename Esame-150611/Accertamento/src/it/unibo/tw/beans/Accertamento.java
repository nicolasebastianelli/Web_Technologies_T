package it.unibo.tw.beans;

import java.io.Serializable;

public class Accertamento implements Serializable{
	private static final long serialVersionUID = 1L;
	private long idaccertamento;
	private String codice;
	private String esito;
	public Accertamento() {
		super();
	}
	public long getIdaccertamento() {
		return idaccertamento;
	}
	public void setIdaccertamento(long idaccertamento) {
		this.idaccertamento = idaccertamento;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	
}
