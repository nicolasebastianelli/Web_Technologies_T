package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TipoAccertamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long id;
	private Integer codice;
	private String descrizione;
	private Set<Accertamento> accertamentiDiQuestoTipo;
	
	public TipoAccertamento() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipoAccertamento(Integer codice, String descrizione) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.accertamentiDiQuestoTipo = new HashSet<>();
		this.struttureCheLoOffrono = new HashSet<>();
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Set<Accertamento> getAccertamentiDiQuestoTipo() {
		return accertamentiDiQuestoTipo;
	}
	public void setAccertamentiDiQuestoTipo(Set<Accertamento> accertamentiDiQuestoTipo) {
		this.accertamentiDiQuestoTipo = accertamentiDiQuestoTipo;
	}
	public Set<Struttura> getStruttureCheLoOffrono() {
		return struttureCheLoOffrono;
	}
	public void setStruttureCheLoOffrono(Set<Struttura> struttureCheLoOffrono) {
		this.struttureCheLoOffrono = struttureCheLoOffrono;
	}
	private Set<Struttura> struttureCheLoOffrono;
}