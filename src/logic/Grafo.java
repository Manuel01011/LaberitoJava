package logic;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
public class Grafo {
	private Vector<Nodo> nodos;
	public Grafo(){
		this.nodos=new Vector<Nodo>(0);
	}
	public void add(Nodo n){
		this.nodos.add(n);
	}
	public Nodo obtenerNodo(int nodoNumero){
		for(Nodo nodo:nodos){
			if(nodo.getNumero() == nodoNumero){
				return nodo;
			}
		}
		return null;
	}
	public Nodo procesarAgregar(int nodoNumero){
		if(existe(nodoNumero)){
			return obtenerNodo(nodoNumero);
		}else{
			Nodo nuevo=new Nodo(nodoNumero);
			add(nuevo);
			return nuevo;
		}
	}
	public boolean existe(int numeroNodo){
		return obtenerNodo(numeroNodo)!=null;
	}
	public Vector<Nodo> DFS(int inicio,int fin){
		TreeSet<Nodo> visitados = new TreeSet<Nodo>();
		Stack<Nodo> NoVisitados = new Stack<Nodo>();
		Vector<Nodo> resultado = null;
		Nodo nodoInicial = obtenerNodo(inicio);
		NoVisitados.push(nodoInicial);
		
		boolean listo=false;
		Nodo nodofin=null;
		while(NoVisitados.size() != 0 && !listo){
			Nodo actual = NoVisitados.pop();
			visitados.add(actual);
			TreeSet<Nodo> vecinos=actual.getVecinos();
			
			for(Nodo n:vecinos){
				if(!visitados.contains(n)){
					n.setPadre(actual);
					if(n.getNumero()==fin){
						listo=true;
						nodofin=n;
						break;
					}
					NoVisitados.add(0,n);
				}
			}
		}
		
		if(listo){
			Nodo nodoActual=nodofin;
			resultado=new Vector<Nodo>();
			while(nodoActual != null){
				resultado.add(nodoActual);
				nodoActual = nodoActual.getPadre();
			}
		}
		return resultado;
	}
}
