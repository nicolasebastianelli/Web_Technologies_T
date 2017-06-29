package m2m.esame.jdbc;

import java.io.Serializable;

public class Squadra implements Serializable{
	private static final long serialVersionUID = 1L;

	private long codSquadra;
	public long getCodSquadra() {
		return codSquadra;
	}
	
	public Squadra() {
		// TODO Auto-generated constructor stub
	}
	public Squadra(long codSquadra, String nome, String categoria, String girone) {
		super();
		this.codSquadra = codSquadra;
		this.nome = nome;
		this.categoria = categoria;
		this.girone = girone;
	}
	public void setId(long id) {
		this.codSquadra = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getGirone() {
		return girone;
	}
	public void setGirone(String girone) {
		this.girone = girone;
	}
	private String nome;
	private String categoria;
	private String girone;
}