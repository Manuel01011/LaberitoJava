package logic;
import java.util.TreeSet;
public class Nodo implements Comparable<Nodo>{
	private Nodo padre;
	private int numero;
	private TreeSet<Nodo> vecinos;
	public Nodo(int n){
		this.numero=n;
		this.vecinos=new TreeSet<Nodo>();
	}
	public Nodo getPadre() {
		return padre;
	}
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}
	public int getNumero() {
		return numero;
	}
	public String toString(){
		return numero + "";
	}
	public int compareTo(Nodo otroNodo) {
		return numero - otroNodo.getNumero();
	}
	public TreeSet<Nodo> getVecinos() {
		return vecinos;
	}
}
