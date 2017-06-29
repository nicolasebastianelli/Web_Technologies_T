package beans;

import java.io.Serializable;

public class Documento implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nome;
	String file;
	public Documento(String nome, String file) {
		super();
		this.nome = nome;
		this.file = file;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	
	
}
