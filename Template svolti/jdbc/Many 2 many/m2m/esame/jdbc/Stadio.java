package m2m.esame.jdbc;

import java.io.Serializable;

public class Stadio implements Serializable{
	private static final long serialVersionUID = 1L;

	public Stadio() {
		// TODO Auto-generated constructor stub
	}
	private long codStadio;
	public long getCodStadio() {
		return codStadio;
	}
	public void setCodStadio(long id) {
		this.codStadio = id;
	}
	public Stadio(long codStadio, String nome, String citta) {
		super();
		this.codStadio = codStadio;
		this.nome = nome;
		this.citta = citta;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	private String nome;
	private String citta;
}