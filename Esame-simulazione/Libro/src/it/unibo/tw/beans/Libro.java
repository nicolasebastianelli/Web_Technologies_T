package it.unibo.tw.beans;

import java.io.Serializable;

public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String isbn;
	private String titolo;
	private Autore idautore;

	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public Libro(String isbn, String titolo,Autore autore) {
		super();
		this.isbn = isbn;
		this.titolo = titolo;
		this.idautore=autore;
	}

	public Autore getIdautore() {
		return idautore;
	}

	public void setIdautore(Autore idautore) {
		this.idautore = idautore;
	}

	
}