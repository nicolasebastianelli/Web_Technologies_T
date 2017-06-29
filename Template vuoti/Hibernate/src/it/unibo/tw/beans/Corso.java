package it.unibo.tw.es3.beans;

import java.io.Serializable;
import java.util.Set;

public class Corso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// ---------------------------

		private long id;
		private int codice;
		private String titolo;
		private int crediti_formativi;
		private String nome_docente;
		private Set<Studente> studenti;


		// --- constructor ----------

		public Corso() {
		}


		// --- getters and setters --------------

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		public void setCodice(int codice) {
			this.codice = codice;
		}


		public int getCodice() {
			return codice;
		}


		public void setTitolo(String titolo) {
			this.titolo = titolo;
		}


		public String getTitolo() {
			return titolo;
		}


		public void setCrediti_formativi(int crediti_formativi) {
			this.crediti_formativi = crediti_formativi;
		}


		public int getCrediti_formativi() {
			return crediti_formativi;
		}


		public void setNome_docente(String nome_docente) {
			this.nome_docente = nome_docente;
		}


		public String getNome_docente() {
			return nome_docente;
		}


		// --- utilities ----------------------------

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * this.codice;
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
			Corso other = (Corso) obj;
			if (id != other.id)
				return false;
			return true;
		}


		public void setStudenti(Set<Studente> studenti) {
			this.studenti = studenti;
		}


		public Set<Studente> getStudenti() {
			return studenti;
		}
}
