package it.unibo.tw.beans;

import java.io.Serializable;

public class Accertamento implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private Integer codice;
	private String nome;
	private String descrizione;
	private TipoAccertamento tipoAccertamento;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public TipoAccertamento getTipoAccertamento() {
		return tipoAccertamento;
	}
	public void setTipoAccertamento(TipoAccertamento tipoAccertamento) {
		this.tipoAccertamento = tipoAccertamento;
	}
	public Accertamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Accertamento(Integer codice, String nome, String descrizione, TipoAccertamento tipoAccertamento) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipoAccertamento = tipoAccertamento;
	}
	

}