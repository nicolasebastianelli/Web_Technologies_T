package servlets;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Pokemons;

public class Filter extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson;
	private Set<Pokemons> pokemons;
	public void init() throws ServletException {
		gson = new Gson();
		 pokemons = new HashSet<Pokemons>();
		 pokemons.add(new Pokemons("erba", "pk1", 10, 30));
		 pokemons.add(new Pokemons("fuoco", "pk2", 20, 35));
		 pokemons.add(new Pokemons("acqua", "pk3", 30, 25));
		 pokemons.add(new Pokemons("elettro", "pk4", 60, 30));
		 pokemons.add(new Pokemons("buio", "pk5", 70, 30));
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<Pokemons> pokemonV = new HashSet<Pokemons>();
		Set<Pokemons> pokemonF = new HashSet<Pokemons>();
		double x ;
		double y ;
		Set<Set<Pokemons>> output = new HashSet<Set<Pokemons>>();;
		if(request.getParameter("x")!=null&&request.getParameter("y")!=null){
			x= Double.parseDouble(request.getParameter("x"));
			y= Double.parseDouble(request.getParameter("y"));
			for (Pokemons pk : pokemons) {
				if(Math.sqrt(Math.pow(x-pk.getX(), 2)+Math.pow(y-pk.getY(), 2))<=10)
					pokemonV.add(pk);
				else if(Math.sqrt(Math.pow(x+50-pk.getX(), 2)+Math.pow(y-pk.getY(), 2))<=10)
					pokemonF.add(pk);
			}
		}
		output.add(pokemonV);
		output.add(pokemonF);
		response.getWriter().println(gson.toJson(output));
		gson.toJson(output);
	}
}
