package it.unibo.tw.beans;

import java.io.Serializable;
import java.util.Set;

public class Partecipante implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String nickname;
	private String nome;
	private String cognome;
	private int eta;
	private Set<Party> party;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) {
		this.eta = eta;
	}
	public Partecipante(long id, String nickname, String nome, String cognome, int eta) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
	}
	public Partecipante() {
		
	}
	public Set<Party> getParty() {
		return party;
	}
	public void setParty(Set<Party> party) {
		this.party = party;
	}
	
}
