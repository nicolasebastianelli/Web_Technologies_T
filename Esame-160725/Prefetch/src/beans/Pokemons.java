package beans;

import java.io.Serializable;

public class Pokemons implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String description;
	private String name;
	private double x;
	private double y;
	public Pokemons() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pokemons(String description, String name, double x, double y) {
		super();
		this.description = description;
		this.name = name;
		this.x = x;
		this.y = y;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	

}
