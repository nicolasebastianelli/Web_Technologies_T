package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scommesse implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Scommessa> scommesse = new ArrayList<Scommessa>();
	private Map<String,Double> partite = new HashMap<String, Double>() ;
	public List<Scommessa> getScommesse() {
		return scommesse;
	}
	public void setScommesse(List<Scommessa> scommesse) {
		this.scommesse = scommesse;
	}
	public Map<String, Double> getPartite() {
		return partite;
	}
	public void setPartite(Map<String, Double> partite) {
		this.partite = partite;
	}
}
