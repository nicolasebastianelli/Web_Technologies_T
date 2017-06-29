package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conteggi implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Conteggio> conteggi = new ArrayList<Conteggio>();

	public List<Conteggio> getConteggi() {
		return conteggi;
	}
	public void setConteggi(List<Conteggio> conteggi) {
		this.conteggi = conteggi;
	}
}
