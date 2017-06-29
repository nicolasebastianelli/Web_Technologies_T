package m2mo2m.esame.jdbc;

import java.io.Serializable;

public class Corso implements Serializable{
	private static final long serialVersionUID = 1L;

	private long CodiceCorso;
	private String nome;
	public Corso() {
		// TODO Auto-generated constructor stub
	}
	public Corso(long codiceCorso, String nome, int creditiFormativi) {
		super();
		CodiceCorso = codiceCorso;
		this.nome = nome;
		this.creditiFormativi = creditiFormativi;
	}
	@Override
	public String toString() {
		return "Corso [CodiceCorso=" + CodiceCorso + ", nome=" + nome + ", creditiFormativi=" + creditiFormativi + "]";
	}
	private int creditiFormativi;
	public long getCodiceCorso() {
		return CodiceCorso;
	}
	public void setCodiceCorso(long codiceCorso) {
		CodiceCorso = codiceCorso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCreditiFormativi() {
		return creditiFormativi;
	}
	public void setCreditiFormativi(int creditiFormativi) {
		this.creditiFormativi = creditiFormativi;
	}
}