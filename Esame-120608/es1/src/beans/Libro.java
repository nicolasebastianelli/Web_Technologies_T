package beans;

import java.io.Serializable;

public class Libro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titolo;
	private String autore;
	private int giornoPrelievo;
	private int giornoConsegna;
	private int mesePrelievo;
	private int meseConsegna;
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public int getGiornoPrelievo() {
		return giornoPrelievo;
	}
	public void setGiornoPrelievo(int giornoPrelievo) {
		this.giornoPrelievo = giornoPrelievo;
	}
	public int getGiornoConsegna() {
		return giornoConsegna;
	}
	public void setGiornoConsegna(int giornoConsegna) {
		this.giornoConsegna = giornoConsegna;
	}
	public int getMesePrelievo() {
		return mesePrelievo;
	}
	public void setMesePrelievo(int mesePrelievo) {
		this.mesePrelievo = mesePrelievo;
	}
	public int getMeseConsegna() {
		return meseConsegna;
	}
	public void setMeseConsegna(int meseConsegna) {
		this.meseConsegna = meseConsegna;
	}
	public Libro(String titolo, String autore, int giornoPrelievo, int giornoConsegna, int mesePrelievo,
			int meseConsegna) {
		super();
		this.titolo = titolo;
		this.autore = autore;
		this.giornoPrelievo = giornoPrelievo;
		this.giornoConsegna = giornoConsegna;
		this.mesePrelievo = mesePrelievo;
		this.meseConsegna = meseConsegna;
	}
	
	
	
	
}
