package m2m.esame.jdbc;

import java.io.Serializable;
import java.sql.Date;

public class Partita implements Serializable{
	private static final long serialVersionUID = 1L;

	private long squadraCasa;
	
	public Partita() {
		// TODO Auto-generated constructor stub
	}
	public Partita(long squadraCasa, long squadraOspite, long stadio, Date data) {
		super();
		this.squadraCasa = squadraCasa;
		this.squadraOspite = squadraOspite;
		this.stadio = stadio;
		this.data = data;
	}
	public long getSquadraCasa() {
		return squadraCasa;
	}
	public void setSquadraCasa(long id) {
		this.squadraCasa = id;
	}
	public long getSquadraOspite() {
		return squadraOspite;
	}
	public void setSquadraOspite(long squadraOspite) {
		this.squadraOspite = squadraOspite;
	}
	public long getStadio() {
		return stadio;
	}
	public void setStadio(long stadio) {
		this.stadio = stadio;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	private long squadraOspite;
	private long stadio;
	private Date data;
}