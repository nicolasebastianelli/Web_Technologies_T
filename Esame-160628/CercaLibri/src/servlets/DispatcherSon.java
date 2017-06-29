package servlets;
import java.util.Set;

import beans.Libro;

public class DispatcherSon extends Thread{
	
	private Set<Libro> libri;
	private Set<Libro> libriTrovati;
	private int n;
	private Set<String> autori;
	public DispatcherSon(Set<Libro> libri, int n,Set<Libro> libriTrovati,Set<String> autori) {
		this.libri=libri;
		this.n =n;
		this.autori=autori;
		this.libriTrovati=libriTrovati;
	}

	@Override
	public void run() {
        try {
        	Libro[] libriArray = libri.toArray(new Libro[0]);
			for(int i = n*10;i<(n*10)+10;i++)
				for (String autore : autori) {
					if (libriArray[i].getAutore().equals(autore))
							libriTrovati.add(libriArray[i]); 
				}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
}
