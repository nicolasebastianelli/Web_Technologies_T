package it.unibo.tw.dao;

import java.util.HashSet;
import java.util.Set;

import it.unibo.tw.dao.db2.Db2LinkDAO;
import it.unibo.tw.dao.db2.Db2PiattoDAO;

public class Db2RistoranteProxy extends RistoranteDTO{
	private static final long serialVersionUID = 5224938053385896589L;
	private boolean caricato = false;

	public Db2RistoranteProxy() {
		super();
	}



	public Db2RistoranteProxy(long id, String nomeRistorante, String indirizzo,
			int rating) {
		super(id, nomeRistorante, indirizzo, rating);
	}



	@Override
	public Set<PiattoDTO> getPiatti() {
		if (!caricato) {
			Set<PiattoDTO> passeggeri = new HashSet<>();
			Db2PiattoDAO db2PiattoDAO = new Db2PiattoDAO();
			for (long id_Piatto : new Db2LinkDAO().read_by_id_Ristorante(getId())) {
				passeggeri.add(db2PiattoDAO.read(id_Piatto));
			}
			this.setPiatti(passeggeri);
		}
		return super.getPiatti();
	}

	@Override
	public void setPiatti(Set<PiattoDTO> piatti) {
		caricato = true;
		super.setPiatti(piatti);
	}
}
