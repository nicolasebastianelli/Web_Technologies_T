package beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListaOp {
 private Map<Integer,Set<Operazione>> listaop ;

public ListaOp() {
	super();
	this.listaop = new HashMap<Integer,Set<Operazione>>();
}

public Map<Integer, Set<Operazione>> getListaop() {
	return listaop;
}

public void setListaop(Map<Integer, Set<Operazione>> listaop) {
	this.listaop = listaop;
}
 
 
}
