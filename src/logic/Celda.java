package logic;
public class Celda implements Comparable<Celda>{
	private int valor;
	private boolean vecinoArriba,vecinoAbajo,vecinoIzquirda,vecinoDerecha;
	
	public Celda(int n){
		this.valor=n;
		this.vecinoArriba=false;
		this.vecinoAbajo=false;
		this.vecinoIzquirda=false;
		this.vecinoDerecha=false;
	}

	public int getNumero() {
		return valor;
	}

	public boolean tieneVecinoSup() {
		return vecinoArriba;
	}

	public void setVecinoSup(boolean vecinoArribaa) {
		this.vecinoArriba = vecinoArribaa;
	}

	public boolean tieneVecinoInf() {
		return vecinoAbajo;
	}

	public void setVecinoInf(boolean vecinoAbajoo) {
		this.vecinoAbajo = vecinoAbajoo;
	}

	public boolean tieneVecinoIzq() {
		return vecinoIzquirda;
	}

	public void setVecinoIzq(boolean vecinoIzquirdaa) {
		this.vecinoIzquirda = vecinoIzquirdaa;
	}

	public boolean tieneVecinoDer() {
		return vecinoDerecha;
	}

	public void setVecinoDer(boolean vecinoDerechaa) {
		this.vecinoDerecha = vecinoDerechaa;
	}

	public int compareTo(Celda c) {
		return this.valor-c.getNumero();
	}
	
	public String toString(){
		return this.valor+"";
	}
	
}
