package beans;

import java.io.Serializable;

public class Libro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String autore;
	private String titolo;
	private String isbn;
	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Libro(String autore, String titolo, String isbn) {
		super();
		this.autore = autore;
		this.titolo = titolo;
		this.isbn = isbn;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}
