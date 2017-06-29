package beans;

import java.io.BufferedReader;

public class FileAccessor extends Thread{
	private Conteggio c;
	private BufferedReader br;

	public FileAccessor(Conteggio c, BufferedReader br) {
		this.c = c;
		this.br = br;
	}

	@Override
	public void run() {
		int count = 0;
		try {
			int read = -1;
			long bytesToRead = 1024;
			while (bytesToRead > 0 && (read = br.read()) >=0) {
				bytesToRead--;
				if (read == c.getCarattere())
					count++;
			}
			br.close();
			
			// se ci sono errori non aggiungo al risultato parziale 
			c.addNumeroOccorrenze(count);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
}
