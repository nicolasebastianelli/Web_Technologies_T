package m2mo2m.esame.jdbc;

import java.io.Serializable;

public class Docente implements Serializable{
	private static final long serialVersionUID = 1L;

	private long MatricolaDocente;
	private String nome;
	private String Cognome;
	public long getMatricolaDocente() {
		return MatricolaDocente;
	}
	public void setMatricolaDocente(long matricolaDocente) {
		MatricolaDocente = matricolaDocente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	@Override
	public String toString() {
		return "Docente [MatricolaDocente=" + MatricolaDocente + ", nome=" + nome + ", Cognome=" + Cognome + "]";
	}
	public Docente(long matricolaDocente, String nome, String cognome) {
		super();
		MatricolaDocente = matricolaDocente;
		this.nome = nome;
		Cognome = cognome;
	}
	public Docente() {
		// TODO Auto-generated constructor stub
	}
	
}