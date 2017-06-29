package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RichiestaMedica implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String codicePaziente;
	private String nomeMedico;
	private Date data;
	
	public RichiestaMedica() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RichiestaMedica(String codicePaziente, String nomeMedico, Date data) {
		super();
		this.codicePaziente = codicePaziente;
		this.nomeMedico = nomeMedico;
		this.data = data;
		this.accertamenti = new HashSet<>();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCodicePaziente() {
		return codicePaziente;
	}
	public void setCodicePaziente(String codicePaziente) {
		this.codicePaziente = codicePaziente;
	}
	public String getNomeMedico() {
		return nomeMedico;
	}
	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Paziente getPaziente() {
		return paziente;
	}
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	public Set<Accertamento> getAccertamenti() {
		return accertamenti;
	}
	public void setAccertamenti(Set<Accertamento> accertamenti) {
		this.accertamenti = accertamenti;
	}
	private Paziente paziente;
	private Set<Accertamento> accertamenti;
}