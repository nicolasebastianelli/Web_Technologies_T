package it.unibo.tw.beans;

import java.io.Serializable;

public class Accertamento implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private Integer codice;
	private String esito;
	private TipoAccertamento tipoAccertamento;
	private RichiestaMedica richiestaMedica;

	public Accertamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Accertamento(Integer codice, String esito) {
		super();
		this.codice = codice;
		this.esito = esito;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public TipoAccertamento getTipoAccertamento() {
		return tipoAccertamento;
	}

	public void setTipoAccertamento(TipoAccertamento tipoAccertamento) {
		this.tipoAccertamento = tipoAccertamento;
	}

	public RichiestaMedica getRichiestaMedica() {
		return richiestaMedica;
	}

	public void setRichiestaMedica(RichiestaMedica richiestaMedica) {
		this.richiestaMedica = richiestaMedica;
	}
}