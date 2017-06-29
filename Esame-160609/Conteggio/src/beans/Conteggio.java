package beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Conteggio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomeFile;
	private int numeroOccorrenze=0;
	private char carattere;
	private LocalDateTime oraRicerca ;
	
	public Conteggio() {
		
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public int getNumeroOccorrenze() {
		return numeroOccorrenze;
	}

	public synchronized void addNumeroOccorrenze(int numeroOccorrenze) {
		this.numeroOccorrenze += numeroOccorrenze;
	}

	public char getCarattere() {
		return carattere;
	}

	public void setCarattere(char carattere) {
		this.carattere = carattere;
	}

	public LocalDateTime getOraRicerca() {
		return oraRicerca;
	}

	public void setOraRicerca(LocalDateTime oraRicerca) {
		this.oraRicerca = oraRicerca;
	}
	
	

}
