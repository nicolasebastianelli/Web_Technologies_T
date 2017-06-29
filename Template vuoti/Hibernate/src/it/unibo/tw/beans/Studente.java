package it.unibo.tw.es3.beans;

import java.io.Serializable;
import java.util.Date;

public class Studente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// ---------------------------

		private long id;
		private String matricola;
		private String nome;
		private String cognome;
		private String sesso;
		private Date data_nascita;

		// --- constructor ----------

		public Studente() {
		}


		// --- getters and setters --------------

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		public void setMatricola(String matricola) {
			this.matricola = matricola;
		}


		public String getMatricola() {
			return matricola;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public String getNome() {
			return nome;
		}


		public void setSesso(String sesso) {
			this.sesso = sesso;
		}


		public String getSesso() {
			return sesso;
		}


		public void setCognome(String cognome) {
			this.cognome = cognome;
		}


		public String getCognome() {
			return cognome;
		}
		

		public void setData_nascita(Date data_nascita) {
			this.data_nascita = data_nascita;
		}


		public Date getData_nascita() {
			return data_nascita;
		}


		// --- utilities ----------------------------

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * this.matricola.hashCode();
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Studente other = (Studente) obj;
			if (id != other.id)
				return false;
			return true;
		}

}
