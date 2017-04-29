package it.unibo.tw.es1.beans;

import java.util.Vector;

public class InsiemeDiArticoli {
	
	private Vector<Articolo> merce;
	
	public InsiemeDiArticoli(){
		merce = new Vector<Articolo>();
	}
	
	public Vector<Articolo> getMerce() {
		return merce;
	}
	public void setMerce(Vector<Articolo> merce) {
		this.merce = merce;
	}
	
}
