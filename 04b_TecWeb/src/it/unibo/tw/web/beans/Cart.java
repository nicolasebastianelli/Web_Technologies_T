package it.unibo.tw.web.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Item,Integer> items = new HashMap<Item,Integer>();

	public Set<Item> getItems() {
		return items.keySet();
	}

	public int getOrder(Item item) {
		return items.get(item);
	}

	public void put(Item item, int order) {
		items.put(item, order);
	}
	
	public void empty() {
		this.items = new HashMap<Item,Integer>();
	}

}
