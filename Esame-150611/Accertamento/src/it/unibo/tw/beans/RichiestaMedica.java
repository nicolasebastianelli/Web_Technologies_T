package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class RichiestaMedica implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String codicepaziente;
	private Date data;	
	private String nomemedico;
	private Set<Accertamento> accertamenti;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdpaziente() {
		return codicepaziente;
	}
	public void setIdpaziente(String codicepaziente) {
		this.codicepaziente = codicepaziente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getNomemedico() {
		return nomemedico;
	}
	public void setNomemedico(String nomemedico) {
		this.nomemedico = nomemedico;
	}
	public Set<Accertamento> getAccertamenti() {
		return accertamenti;
	}
	public void setAccertamenti(Set<Accertamento> accertamenti) {
		this.accertamenti = accertamenti;
	}
	public RichiestaMedica() {
		super();
	}
}
