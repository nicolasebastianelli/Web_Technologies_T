package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.Set;

public class TipoAccertamento implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String codice;
	private String descrizione;	
	private Set<Accertamento> accertamenti;
	private Set<Struttura> strutture;
	public TipoAccertamento() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Set<Accertamento> getAccertamenti() {
		return accertamenti;
	}
	public void setAccertamenti(Set<Accertamento> accertamenti) {
		this.accertamenti = accertamenti;
	}
	public Set<Struttura> getStrutture() {
		return strutture;
	}
	public void setStrutture(Set<Struttura> strutture) {
		this.strutture = strutture;
	}
}
