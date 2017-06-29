package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Struttura implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long id;
	private Integer codice;
	private String nome;
	
	public Struttura() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Struttura(Integer codice, String nome, String indirizzo) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.tipiDiAccertamentoOfferti = new HashSet<>();
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public Set<TipoAccertamento> getTipiDiAccertamentoOfferti() {
		return tipiDiAccertamentoOfferti;
	}
	public void setTipiDiAccertamentoOfferti(Set<TipoAccertamento> tipiDiAccertamentoOfferti) {
		this.tipiDiAccertamentoOfferti = tipiDiAccertamentoOfferti;
	}
	private String indirizzo;
	private Set<TipoAccertamento> tipiDiAccertamentoOfferti;
}