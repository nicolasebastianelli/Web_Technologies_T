package it.unibo.tw.web.beans;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	String description;
	double price;
	int quantity;
	
	// --- constructor ----------
	
	public Item() {
	}

	// --- getters and setters --------------
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	// --- utilities ----------------------------
	
	@Override
	public boolean equals(Object o) {
		if ( ! ( o instanceof Item ) ) return false;
		Item i = (Item)o;
		return i.getDescription().equals(this.description);
	}
	
	@Override
	public int hashCode() {
		return this.description.hashCode();
	}

}
