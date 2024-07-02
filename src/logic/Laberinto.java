package logic;
import gui.Principal;
import gui.Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Laberinto {
	private int filas;
	private int columnas;
	private Celda[][] celdas;
	private Principal parent;
	private final int ARRIBA=1;
	private final int ABAJO=2;
	private final int IZQUIERDA=3;
	private final int DERECHA=4;
	public Laberinto(Principal parent, int filas, int columnas){
		this.parent=parent;
		this.filas=filas;
		this.columnas=columnas;
		init();
	}
	public final void init(){
		this.celdas =new Celda[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				celdas[i][j] = new Celda(i * columnas + j);
			}
		}
	}
	public void generar(){
		TreeSet<Celda> visitados=new TreeSet<Celda>();
		List<Celda> porVisitar=new ArrayList<Celda>();
		porVisitar.add(0, celdas[0][0]);
		int filaActual=0,columnaActual=0;
		Celda vecino=null;

		List<Integer> direcciones[]=new ArrayList[filas*columnas];
		for(int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
				int numero=i*columnas+j;
				direcciones[numero]=new ArrayList<Integer>();
				
				if(i!=0){
					direcciones[numero].add(ARRIBA);
				}
				if(i!=filas-1){ //
					direcciones[numero].add(ABAJO);
				}
				if(j!=0){
					direcciones[numero].add(IZQUIERDA);
				}
				if(j!=columnas-1){
					direcciones[numero].add(DERECHA);
				}
				Collections.shuffle(direcciones[numero]);
			}
		}

		while(porVisitar.size()!=0){
			Celda actual=porVisitar.get(0);
			visitados.add(actual);
			int numero=actual.getNumero();
			filaActual=numero/columnas;
			columnaActual=numero%columnas;
			vecino=actual;

			while(visitados.contains(vecino) && direcciones[numero].size()!=0){
				int direccionElegida=direcciones[numero].get(0);
				direcciones[numero].remove(0);
				switch(direccionElegida){
					case ARRIBA:
						vecino= celdas[filaActual-1][columnaActual];
						if(!visitados.contains(vecino)){
							actual.setVecinoSup(true);
							vecino.setVecinoInf(true);
							porVisitar.add(0,vecino);
						}
						break;
					case ABAJO:
						vecino= celdas[filaActual+1][columnaActual];
						if(!visitados.contains(vecino)){
							actual.setVecinoInf(true);
							vecino.setVecinoSup(true);
							porVisitar.add(0,vecino);
						}
						break;
					case IZQUIERDA:
						vecino= celdas[filaActual][columnaActual-1];
						if(!visitados.contains(vecino)){
							actual.setVecinoIzq(true);
							vecino.setVecinoDer(true);
							porVisitar.add(0,vecino);
						}
						break;
					case DERECHA:
						vecino= celdas[filaActual][columnaActual+1];
						if(!visitados.contains(vecino)){
							actual.setVecinoDer(true);
							vecino.setVecinoIzq(true);
							porVisitar.add(0,vecino);
						}
						break;
				}
			}
			if(direcciones[actual.getNumero()].size()==0){ 
				porVisitar.remove(actual);
			}

		}
		//guardamos el laberinto generado en un archivo temporal y lo cargamos en la pantalla principal
		File archivo=new File("Laberintos");
		Util.guardar(archivo, celdas, 0, filas*columnas-1);
		parent.leerArchivo(archivo,false);
		guardarLaberintoEnXML("Laberintos");
	}
	public void setFilas(int filas) {
		this.filas = filas;
	}
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	public void guardarLaberintoEnXML(String nombreArchivo) {
		try {
			FileWriter fileWriter = new FileWriter(nombreArchivo);
			fileWriter.write(generarXML());
			fileWriter.close();
			System.out.println("Laberinto guardado en " + nombreArchivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String generarXML() {
		StringBuilder xmlBuilder = new StringBuilder();
		xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xmlBuilder.append("<laberinto>\n");
		xmlBuilder.append("<filas>").append(filas).append("</filas>\n");
		xmlBuilder.append("<columnas>").append(columnas).append("</columnas>\n");

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				Celda celda = celdas[i][j];
				xmlBuilder.append("<celda>\n");
				xmlBuilder.append("<fila>").append(i).append("</fila>\n");
				xmlBuilder.append("<columna>").append(j).append("</columna>\n");
				xmlBuilder.append("<vecinoSup>").append(celda.tieneVecinoSup()).append("</vecinoSup>\n");
				xmlBuilder.append("<vecinoInf>").append(celda.tieneVecinoInf()).append("</vecinoInf>\n");
				xmlBuilder.append("<vecinoIzq>").append(celda.tieneVecinoIzq()).append("</vecinoIzq>\n");
				xmlBuilder.append("<vecinoDer>").append(celda.tieneVecinoDer()).append("</vecinoDer>\n");
				xmlBuilder.append("</celda>\n");
			}
		}
		xmlBuilder.append("</laberinto>");
		return xmlBuilder.toString();
	}
}
